package com.DAOs;

import com.Beans.Reimbursement;
import com.TRMS.ConnectionUtil;
import com.TRMS.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementDAO {

    ConnectionUtil connectionUtil;

    public ReimbursementDAO(ConnectionUtil connectionUtil){
        this.connectionUtil = connectionUtil;
    }

    public boolean create(Reimbursement reimbursement) throws SQLException {
        if(reimbursement.isInDatabase()){
            int rows = update(reimbursement);
            if(rows == 0) return false;
            else return true;
        }
        else{
            try(Connection connection = connectionUtil.getConnection()){
                PreparedStatement statement = connection.prepareStatement("INSERT INTO reimbursements (amountawarded, amountpending) VALUES (?,?) RETURNING employeeid");
                statement.setDouble(1,reimbursement.getAmountAwarded());
                statement.setDouble(2,reimbursement.getAmountPending());
                ResultSet resultSet = statement.executeQuery();
                if(resultSet.next()){
                    reimbursement.setId(resultSet.getInt("employeeid"));
                    reimbursement.setInDatabase(true);
                    return true;
                }else return false;

            }
        }
    }

    public Reimbursement readById(int id) throws SQLException {
        Reimbursement reimbursement = new Reimbursement();
        try(Connection connection = connectionUtil.getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM reimbursements WHERE employeeid = ?");
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                reimbursement.setId(id);
                reimbursement.setAmountAwarded(resultSet.getDouble("amountawarded"));
                reimbursement.setAmountPending(resultSet.getDouble("amountpending"));
            }
            return reimbursement;

        }
    }

    public List<Reimbursement> readAll() throws SQLException {
        List<Reimbursement> reimbursements = new ArrayList<>();
        Reimbursement reimbursement = new Reimbursement();
        try(Connection connection = connectionUtil.getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM reimbursements");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                reimbursement.setId(resultSet.getInt("employeeid"));
                reimbursement.setAmountAwarded(resultSet.getDouble("amountawarded"));
                reimbursement.setAmountPending(resultSet.getDouble("amountpending"));
                reimbursements.add(reimbursement);
            }
            return reimbursements;

        }
    }

    public int update(Reimbursement reimbursement) throws SQLException {
        int rows = 0;
        if(!reimbursement.isInDatabase()){
            boolean created = create(reimbursement);
            if(created) rows = 1;
        }
        else{
            try(Connection connection = connectionUtil.getConnection()){
                PreparedStatement statement = connection.prepareStatement("UPDATE reimbursements " +
                        "SET amountawarded = ?, amountpending = ? WHERE id = ?");
                statement.setDouble(1,reimbursement.getAmountAwarded());
                statement.setDouble(2,reimbursement.getAmountPending());
                statement.setInt(3,reimbursement.getId());
                rows = statement.executeUpdate();

            }
        }
        return rows;
    }

    public int delete(Reimbursement reimbursement) throws SQLException {
        int rows = 0;
        if(reimbursement.isInDatabase()){
            try(Connection connection = connectionUtil.getConnection()){
                PreparedStatement statement = connection.prepareStatement("DELETE FROM reimbursements WHERE id = ?");
                statement.setInt(1,reimbursement.getId());
                rows = statement.executeUpdate();

            }
        }
        return rows;
    }
}
