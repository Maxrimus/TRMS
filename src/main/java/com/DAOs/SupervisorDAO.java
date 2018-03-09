package com.DAOs;

import com.Beans.Supervisor;
import com.TRMS.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupervisorDAO {

    ConnectionUtil connectionUtil;

    public SupervisorDAO(ConnectionUtil connectionUtil){
        this.connectionUtil = connectionUtil;
    }

    public void create(Supervisor supervisor) throws SQLException {
        if(!supervisor.isInDatabase()){
            try(Connection connection = connectionUtil.getConnection()){
                PreparedStatement statement = connection.prepareStatement("SELECT EXISTS WHERE supervisorid = ? AND employeeid = ?");
                statement.setInt(1,supervisor.getSupervisorId());
                statement.setInt(2,supervisor.getEmployeeId());
                ResultSet resultSet = statement.executeQuery();
                if(!resultSet.next()){
                    statement = connectionUtil.getConnection().prepareStatement("INSERT INTO supervisors (supervisorid,employeeid) VALUES (?,?) RETURNING id");
                    statement.setInt(1,supervisor.getSupervisorId());
                    statement.setInt(2,supervisor.getEmployeeId());
                    resultSet = statement.executeQuery();
                }

            }
        }
    }

    public List<Integer> readEmployees(int id) throws SQLException {
        List<Integer> toReturn = new ArrayList<>();
        try(Connection connection = connectionUtil.getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT employeeid FROM supervisors WHERE supervisorid = ?");
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) toReturn.add(resultSet.getInt("employeeid"));
            return toReturn;

        }
    }

    public int readSupervisors(int id) throws SQLException {
        int toReturn = -1;
        try(Connection connection = connectionUtil.getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT supervisorid FROM supervisors WHERE employeeid = ?");
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) toReturn = resultSet.getInt("supervisorid");
            return toReturn;

        }
    }

    public int deleteEmployee(Supervisor supervisor) throws SQLException {
        int rows = 0;
        if(supervisor.isInDatabase()){
            try(Connection connection = connectionUtil.getConnection()){
                PreparedStatement statement = connection.prepareStatement("DELETE FROM supervisors WHERE employeeid = ?");
                statement.setInt(1,supervisor.getEmployeeId());
                rows = statement.executeUpdate();

            }
        }
        return rows;
    }

    public int deleteSupervisor(Supervisor supervisor) throws SQLException {
        int rows = 0;
        if(supervisor.isInDatabase()){
            try(Connection connection = connectionUtil.getConnection()){
                PreparedStatement statement = connection.prepareStatement("DELETE FROM supervisors WHERE supervisorid = ?");
                statement.setInt(1,supervisor.getSupervisorId());
                rows = statement.executeUpdate();

            }
        }
        return rows;
    }
}
