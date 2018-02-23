package com.DAOs;

import com.Beans.Reimbursement;
import com.TRMS.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReimbursementDAO {

    public ReimbursementDAO(){}

    public void create(Reimbursement reimbursement) throws SQLException {
        if(reimbursement.getId() == -1){
            PreparedStatement statement = Main.conn.prepareStatement("INSERT INTO reimbursements (amountawarded, amountpending) VALUES (?,?) RETURNING id");
            statement.setDouble(1,reimbursement.getAmountAwarded());
            statement.setDouble(2,reimbursement.getAmountPending());
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) reimbursement.setId(resultSet.getInt("id"));
        }
        else update(reimbursement);
    }

    public Reimbursement read(int id) throws SQLException {
        Reimbursement reimbursement = new Reimbursement();
        PreparedStatement statement = Main.conn.prepareStatement("SELECT * FROM reimbursements WHERE id = ?");
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            reimbursement.setId(id);
            reimbursement.setAmountAwarded(resultSet.getDouble("amountawarded"));
            reimbursement.setAmountPending(resultSet.getDouble("amountpending"));
        }
        return reimbursement;
    }

    public void update(Reimbursement reimbursement) throws SQLException {
        if(reimbursement.getId() == -1) create(reimbursement);
        else{
            PreparedStatement statement = Main.conn.prepareStatement("UPDATE reimbursements " +
                    "SET amountawarded = ?, amountpending = ? WHERE id = ?");
            statement.setDouble(1,reimbursement.getAmountAwarded());
            statement.setDouble(2,reimbursement.getAmountPending());
            statement.setInt(3,reimbursement.getId());
            statement.executeUpdate();
        }
    }

    public void delete(Reimbursement reimbursement) throws SQLException {
        if(reimbursement.getId() != -1){
            PreparedStatement statement = Main.conn.prepareStatement("DELETE FROM reimbursements WHERE id = ?");
            statement.setInt(1,reimbursement.getId());
            statement.executeUpdate();
        }
    }
}
