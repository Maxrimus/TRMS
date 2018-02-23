package com.DAOs;

import com.Beans.LearningEvent;
import com.TRMS.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LearningEventDAO {

    public LearningEventDAO(){}

    public void create(LearningEvent event) throws SQLException {
        if(event.getId() == -1){
            PreparedStatement statement = Main.conn.prepareStatement("INSERT INTO events (eventdate,eventtime,eventdescription,eventcost,eventlocation,eventtype) " +
                    "VALUES (?,?,?,?,?,?) RETURNING id");
            statement.setDate(1,event.getEventDate());
            statement.setTime(2,event.getEventTime());
            statement.setString(3,event.getEventDescription());
            statement.setDouble(4,event.getEventCost());
            statement.setString(5,event.getEventLocation());
            statement.setString(6,event.getEventType());
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) event.setId(resultSet.getInt("id"));
        }
        else update(event);
    }

    public LearningEvent read(int id) throws SQLException {
        LearningEvent event = new LearningEvent();
        PreparedStatement statement = Main.conn.prepareStatement("SELECT * FROM events WHERE id = ?");
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            event.setId(id);
            event.setEventCost(resultSet.getDouble("eventcost"));
            event.setEventDate(resultSet.getDate("eventdate"));
            event.setEventDescription(resultSet.getString("eventdescription"));
            event.setEventLocation(resultSet.getString("eventlocation"));
            event.setEventTime(resultSet.getTime("eventtime"));
            event.setEventType(resultSet.getString("eventtype"));
        }
        return event;
    }

    public void update(LearningEvent event) throws SQLException {
        if(event.getId() == -1) create(event);
        else{
            PreparedStatement statement = Main.conn.prepareStatement("UPDATE events " +
                    "SET eventdate = ?, eventtime = ?, eventdescription = ?, eventcost = ?, eventlocation = ?, eventtype = ? WHERE id = ?");
            statement.setDate(1,event.getEventDate());
            statement.setTime(2,event.getEventTime());
            statement.setString(3,event.getEventDescription());
            statement.setDouble(4,event.getEventCost());
            statement.setString(5,event.getEventLocation());
            statement.setString(6,event.getEventType());
            statement.setInt(3,event.getId());
            statement.executeUpdate();
        }
    }

    public void delete(LearningEvent event) throws SQLException {
        if(event.getId() != -1){
            PreparedStatement statement = Main.conn.prepareStatement("DELETE FROM events WHERE id = ?");
            statement.setInt(1,event.getId());
            statement.executeUpdate();
        }
    }
}
