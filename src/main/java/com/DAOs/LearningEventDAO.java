package com.DAOs;

import com.Beans.LearningEvent;
import com.TRMS.ConnectionUtil;
import com.TRMS.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LearningEventDAO {

    ConnectionUtil connectionUtil;
    
    public LearningEventDAO(ConnectionUtil connectionUtil){
        this.connectionUtil = connectionUtil;
    }

    public boolean create(LearningEvent event) throws SQLException {
        if(event.isInDatabase()) {
            int rows = update(event);
            if(rows == 0) return false;
            else return true;
        }
        else{
            try(Connection connection = connectionUtil.getConnection()){
                PreparedStatement statement = connection.prepareStatement("INSERT INTO events (eventdate,eventtime,eventdescription,eventcost,eventlocation,eventtype) " +
                        "VALUES (?,?,?,?,?,?) RETURNING id");
                statement.setDate(1,event.getEventDate());
                statement.setTime(2,event.getEventTime());
                statement.setString(3,event.getEventDescription());
                statement.setDouble(4,event.getEventCost());
                statement.setString(5,event.getEventLocation());
                statement.setString(6,event.getEventType());
                ResultSet resultSet = statement.executeQuery();
                if(resultSet.next()){
                    event.setId(resultSet.getInt("id"));
                    event.setInDatabase(true);
                    return true;
                }else return false;

            }
        }
    }

    public LearningEvent readById(int id) throws SQLException {
        LearningEvent event = new LearningEvent();
        try(Connection connection = connectionUtil.getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM events WHERE id = ?");
            statement.setInt(1,id);
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
    }

    public List<LearningEvent> readAll() throws SQLException {
        List<LearningEvent> learningEvents = new ArrayList<>();
        LearningEvent event = new LearningEvent();
        try(Connection connection = connectionUtil.getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM events");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                event.setId(resultSet.getInt("id"));
                event.setEventCost(resultSet.getDouble("eventcost"));
                event.setEventDate(resultSet.getDate("eventdate"));
                event.setEventDescription(resultSet.getString("eventdescription"));
                event.setEventLocation(resultSet.getString("eventlocation"));
                event.setEventTime(resultSet.getTime("eventtime"));
                event.setEventType(resultSet.getString("eventtype"));
                learningEvents.add(event);
            }
            return learningEvents;

        }
    }

    public int update(LearningEvent event) throws SQLException {
        int rows = 0;
        if(!event.isInDatabase()){
            boolean created = create(event);
            if(created) rows = 1;
        }
        else{
            try(Connection connection = connectionUtil.getConnection()){
                PreparedStatement statement = connection.prepareStatement("UPDATE events " +
                        "SET eventdate = ?, eventtime = ?, eventdescription = ?, eventcost = ?, eventlocation = ?, eventtype = ? WHERE id = ?");
                statement.setDate(1,event.getEventDate());
                statement.setTime(2,event.getEventTime());
                statement.setString(3,event.getEventDescription());
                statement.setDouble(4,event.getEventCost());
                statement.setString(5,event.getEventLocation());
                statement.setString(6,event.getEventType());
                statement.setInt(3,event.getId());
                rows = statement.executeUpdate();

            }
        }
        return rows;
    }

    public int delete(LearningEvent event) throws SQLException {
        int rows = 0;
        if(event.isInDatabase()){
            try(Connection connection = connectionUtil.getConnection()){
                PreparedStatement statement = connection.prepareStatement("DELETE FROM events WHERE id = ?");
                statement.setInt(1,event.getId());
                rows = statement.executeUpdate();

            }
        }
        return rows;
    }
}
