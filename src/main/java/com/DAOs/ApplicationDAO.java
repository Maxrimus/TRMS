package com.DAOs;

import com.Beans.Application;
import com.TRMS.ConnectionUtil;
import com.sun.javaws.security.AppPolicy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ApplicationDAO {

    ConnectionUtil connectionUtil;

    public ApplicationDAO(ConnectionUtil connectionUtil){ this.connectionUtil = connectionUtil; }

    public boolean create(Application application) throws SQLException {
        if(application.isInDatabase()){
            int rows = update(application);
            if(rows == 0) return false;
            else return true;
        }
        else {
            try(Connection connection = connectionUtil.getConnection()){
                PreparedStatement statement = connection.prepareStatement("INSERT INTO reimbursementapplications (justification, gradeformat, employeeid, eventid,status) VALUES (?,?,?,?,?) RETURNING id");
                statement.setString(1,application.getJustification());
                statement.setInt(2,application.getGradingFormat());
                statement.setInt(3,application.getEmployeeId());
                statement.setInt(4,application.getEventId());
                statement.setString(5,application.getStatus());
                ResultSet resultSet = statement.executeQuery();
                if(resultSet.next()){
                    application.setId(resultSet.getInt("id"));
                    application.setInDatabase(true);
                    return true;
                } else return false;

            }
        }
    }

    public Application readById(int id) throws SQLException {
        Application application = new Application();
        try(Connection connection = connectionUtil.getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM reimbursementapplications WHERE id = ?");
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                application = new Application();
                application.setId(id);
                application.setEmployeeId(resultSet.getInt("employeeid"));
                application.setEventId(resultSet.getInt("eventid"));
                application.setGradingFormat(resultSet.getInt("gradeformat"));
                application.setJustification(resultSet.getString("justification"));
                application.setStatus(resultSet.getString("status"));
                application.setInDatabase(true);
            }
            return application;

        }
    }

    public List<Application> readByEmployeeId(int employeeId) throws SQLException {
        List<Application> applications = new ArrayList<>();
        Application application = new Application();
        try(Connection connection = connectionUtil.getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM reimbursementapplications WHERE employeeId = ?");
            statement.setInt(1,employeeId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                application = new Application();
                application.setId(resultSet.getInt("id"));
                application.setEmployeeId(employeeId);
                application.setEventId(resultSet.getInt("eventid"));
                application.setGradingFormat(resultSet.getInt("gradeformat"));
                application.setJustification(resultSet.getString("justification"));
                application.setStatus(resultSet.getString("status"));
                application.setInDatabase(true);
                applications.add(application);
            }
            return applications;

        }
    }

    public List<Application> readByStatus(String status) throws SQLException {
        List<Application> applications = new ArrayList<>();
        Application application = new Application();
        try(Connection connection = connectionUtil.getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM reimbursementapplications WHERE status = ?");
            statement.setString(1,status);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                application = new Application();
                application.setId(resultSet.getInt("id"));
                application.setEmployeeId(resultSet.getInt("employeeid"));
                application.setEventId(resultSet.getInt("eventid"));
                application.setGradingFormat(resultSet.getInt("gradeformat"));
                application.setJustification(resultSet.getString("justification"));
                application.setStatus(status);
                application.setInDatabase(true);
                applications.add(application);
            }
            return applications;

        }
    }

    public List<Application> readAll() throws SQLException {
        List<Application> applications = new ArrayList<>();
        Application application = new Application();
        try(Connection connection = connectionUtil.getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM reimbursementapplications");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                application = new Application();
                application.setId(resultSet.getInt("id"));
                application.setEmployeeId(resultSet.getInt("employeeid"));
                application.setEventId(resultSet.getInt("eventid"));
                application.setGradingFormat(resultSet.getInt("gradeformat"));
                application.setJustification(resultSet.getString("justification"));
                application.setStatus(resultSet.getString("status"));
                applications.add(application);
            }
            return applications;

        }
    }

    public int update(Application application) throws SQLException {
        int rows = 0;
        if(!application.isInDatabase()){
            boolean created = create(application);
            if(created) rows = 1;
        }
        else{
            try(Connection connection = connectionUtil.getConnection()){
                PreparedStatement statement = connection.prepareStatement("UPDATE reimbursementapplications " +
                        "SET employeeid = ?, eventid = ?, gradeformat = ?, justification = ?, status = ? WHERE id = ?");
                statement.setInt(1,application.getEmployeeId());
                statement.setInt(2,application.getEventId());
                statement.setInt(3,application.getGradingFormat());
                statement.setString(4,application.getJustification());
                statement.setString(5,application.getStatus());
                statement.setInt(6,application.getId());
                rows = statement.executeUpdate();

            }
        }
        return rows;
    }

    public int delete(Application application) throws SQLException {
        int rows = 0;
        if(application.isInDatabase()){
            try(Connection connection = connectionUtil.getConnection()){
                PreparedStatement statement = connection.prepareStatement("DELETE FROM reimbursementapplications WHERE id = ?");
                statement.setInt(1,application.getId());
                rows = statement.executeUpdate();


            }
        }
        return rows;
    }
}
