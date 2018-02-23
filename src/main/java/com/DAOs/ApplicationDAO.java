package com.DAOs;

import com.Beans.Application;
import com.TRMS.Main;

import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ApplicationDAO {

    public ApplicationDAO(){}

    public void create(Application application) throws SQLException {
        if(application.getId() == -1){
            PreparedStatement statement = Main.conn.prepareStatement("INSERT INTO reimbursementapplications (justification, gradeformat, employeeid, eventid) VALUES (?,?,?,?) RETURNING id");
            statement.setString(1,application.getJustification());
            statement.setInt(2,application.getGradingFormat());
            statement.setInt(3,application.getEmployeeId());
            statement.setInt(4,application.getEventId());
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) application.setId(resultSet.getInt("id"));
        }
        else update(application);
    }

    public Application read(int id) throws SQLException {
        Application application = new Application();
        PreparedStatement statement = Main.conn.prepareStatement("SELECT * FROM reimbursementapplications WHERE id = ?");
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            application.setId(id);
            application.setEmployeeId(resultSet.getInt("employeeid"));
            application.setEventId(resultSet.getInt("eventid"));
            application.setGradingFormat(resultSet.getInt("gradingformat"));
            application.setJustification(resultSet.getString("justification"));
        }
        return application;
    }

    public void update(Application application) throws SQLException {
        if(application.getId() == -1) create(application);
        else{
            PreparedStatement statement = Main.conn.prepareStatement("UPDATE reimbursementapplications " +
                    "SET employeeid = ?, eventid = ?, gradingformat = ?, justification = ? WHERE id = ?");
            statement.setInt(1,application.getEmployeeId());
            statement.setInt(2,application.getEventId());
            statement.setInt(3,application.getGradingFormat());
            statement.setString(4,application.getJustification());
            statement.setInt(5,application.getId());
            statement.executeUpdate();
        }
    }

    public void delete(Application application) throws SQLException {
        if(application.getId() != -1){
            PreparedStatement statement = Main.conn.prepareStatement("DELETE FROM reimbursementapplications WHERE id = ?");
            statement.setInt(1,application.getId());
            statement.executeUpdate();
        }
    }
}
