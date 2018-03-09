package com.DAOs;

import com.Beans.ApplicationApproval;
import com.TRMS.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ApplicationApprovalsDAO {

    ConnectionUtil connectionUtil;

    public ApplicationApprovalsDAO(ConnectionUtil connectionUtil){
        this.connectionUtil = connectionUtil;
    }

    public boolean create(ApplicationApproval applicationApproval) throws SQLException {
        if(applicationApproval.isInDatabase()){
            int rows = update(applicationApproval);
            if(rows == 0) return false;
            else return true;
        }
        else {
            try(Connection connection = connectionUtil.getConnection()) {
                PreparedStatement statement = connection.prepareStatement("INSERT INTO applicationapprovals (applicationid,approverid,approvertype,approvaldate,approved) VALUES (?,?,?,?,?) RETURNING id");
                statement.setInt(1, applicationApproval.getApplicationId());
                statement.setInt(2, applicationApproval.getApproverId());
                statement.setString(3, applicationApproval.getApproverType());
                statement.setDate(4, applicationApproval.getApprovalDate());
                statement.setBoolean(5, applicationApproval.isApproved());
                ResultSet resultSet = statement.executeQuery();
                if(resultSet.next()){
                    applicationApproval.setId(resultSet.getInt("id"));
                    applicationApproval.setInDatabase(true);
                    return true;
                } else return false;
            }
        }
    }

    public ApplicationApproval readById(int id) throws SQLException {
        ApplicationApproval applicationApproval = new ApplicationApproval();
        try(Connection connection = connectionUtil.getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM applicationapprovals WHERE id = ?");
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                applicationApproval.setId(id);
                applicationApproval.setApplicationId(resultSet.getInt("applicationid"));
                applicationApproval.setApprovalDate(resultSet.getDate("approvaldate"));
                applicationApproval.setApproved(resultSet.getBoolean("approved"));
                applicationApproval.setApproverId(resultSet.getInt("approverid"));
                applicationApproval.setApproverType(resultSet.getString("approvertype"));
                applicationApproval.setInDatabase(true);
            }
            return applicationApproval;
        }
    }

    public List<ApplicationApproval> readByApplicationId(int applicationId) throws SQLException {
        List<ApplicationApproval> applicationApprovals = new ArrayList<>();
        ApplicationApproval applicationApproval = new ApplicationApproval();
        try(Connection connection = connectionUtil.getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM applicationapprovals WHERE applicationid = ?");
            statement.setInt(1,applicationId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                applicationApproval = new ApplicationApproval();
                applicationApproval.setId(resultSet.getInt("id"));
                applicationApproval.setApplicationId(applicationId);
                applicationApproval.setApprovalDate(resultSet.getDate("approvaldate"));
                applicationApproval.setApproved(resultSet.getBoolean("approved"));
                applicationApproval.setApproverId(resultSet.getInt("approverid"));
                applicationApproval.setApproverType(resultSet.getString("approvertype"));
                applicationApproval.setInDatabase(true);
                applicationApprovals.add(applicationApproval);
            }
            return applicationApprovals;
        }
    }

    public List<ApplicationApproval> readAll() throws SQLException {
        List<ApplicationApproval> applicationApprovals = new ArrayList<>();
        ApplicationApproval applicationApproval = new ApplicationApproval();
        try(Connection connection = connectionUtil.getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM applicationapprovals");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                applicationApproval.setId(resultSet.getInt("id"));
                applicationApproval.setApplicationId(resultSet.getInt("applicationid"));
                applicationApproval.setApprovalDate(resultSet.getDate("approvaldate"));
                applicationApproval.setApproved(resultSet.getBoolean("approved"));
                applicationApproval.setApproverId(resultSet.getInt("approverid"));
                applicationApproval.setApproverType(resultSet.getString("approvertype"));
                applicationApprovals.add(applicationApproval);
            }
            return applicationApprovals;
        }
    }

    public int update(ApplicationApproval applicationApproval) throws SQLException {
        int rows = 0;
        if(!applicationApproval.isInDatabase()) {
            boolean created = create(applicationApproval);
            if(created) rows = 1;
        }
        else{
            try(Connection connection = connectionUtil.getConnection()){
                PreparedStatement statement = connection.prepareStatement("UPDATE applicationapprovals " +
                        "SET applicationid = ?, approverid = ?, approvertype = ?, approvaldate = ?, approved = ? WHERE id = ?");
                statement.setInt(1, applicationApproval.getApplicationId());
                statement.setInt(2, applicationApproval.getApproverId());
                statement.setString(3, applicationApproval.getApproverType());
                statement.setDate(4, applicationApproval.getApprovalDate());
                statement.setBoolean(5, applicationApproval.isApproved());
                statement.setInt(6, applicationApproval.getId());
                rows = statement.executeUpdate();
            }
        }
        return rows;
    }

    public int delete(ApplicationApproval applicationApproval) throws SQLException {
        int rows = 0;
        if(applicationApproval.isInDatabase()){
            try(Connection connection = connectionUtil.getConnection()){
                PreparedStatement statement = connection.prepareStatement("DELETE FROM applicationapprovals WHERE id = ?");
                statement.setInt(1, applicationApproval.getId());
                rows = statement.executeUpdate();
            }
        }
        return rows;
    }
}
