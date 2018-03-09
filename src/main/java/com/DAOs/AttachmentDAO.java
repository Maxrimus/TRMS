package com.DAOs;

import com.Beans.Attachment;
import com.TRMS.ConnectionUtil;
import com.TRMS.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AttachmentDAO {

    ConnectionUtil connectionUtil;

    public AttachmentDAO(ConnectionUtil connectionUtil){ this.connectionUtil = connectionUtil; }

    public boolean create(Attachment attachment) throws SQLException {
        if(attachment.isInDatabase()){
            int rows = update(attachment);
            if(rows == 0) return false;
            else return true;
        }
        else{
            try(Connection connection = connectionUtil.getConnection()){
                PreparedStatement statement = connection.prepareStatement("INSERT INTO attachments (filename, applicationid) VALUES (?,?) RETURNING id");
                statement.setString(1,attachment.getFilename());
                statement.setInt(2,attachment.getApplicationId());
                ResultSet resultSet = statement.executeQuery();
                if(resultSet.next()){
                    attachment.setId(resultSet.getInt("id"));
                    attachment.setInDatabase(true);
                    return true;
                }else return false;

            }
        }
    }

    public Attachment readById(int id) throws SQLException {
        Attachment attachment = new Attachment();
        try(Connection connection = connectionUtil.getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM attachments WHERE id = ?");
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                attachment.setId(id);
                attachment.setApplicationId(resultSet.getInt("applicationid"));
                attachment.setFilename(resultSet.getString("filename"));
            }
            return attachment;

        }
    }

    public Attachment readByApplicationId(int applicationId) throws SQLException {
        Attachment attachment = new Attachment();
        try(Connection connection = connectionUtil.getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM attachments WHERE applicationid = ?");
            statement.setInt(1,applicationId);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                attachment.setId(resultSet.getInt("id"));
                attachment.setApplicationId(applicationId);
                attachment.setFilename(resultSet.getString("filename"));
            }
            return attachment;

        }
    }

    public List<Attachment> readAll() throws SQLException {
        List<Attachment> attachments = new ArrayList<>();
        Attachment attachment = new Attachment();
        try(Connection connection = connectionUtil.getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM attachments");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                attachment.setId(resultSet.getInt("id"));
                attachment.setApplicationId(resultSet.getInt("applicationid"));
                attachment.setFilename(resultSet.getString("filename"));
                attachments.add(attachment);
            }
            return attachments;

        }
    }

    public int update(Attachment attachment) throws SQLException {
        int rows = 0;
        if(attachment.isInDatabase()){
            boolean created = create(attachment);
            if(created) rows = 1;
        }
        else{
            try(Connection connection = connectionUtil.getConnection()){
                PreparedStatement statement = connection.prepareStatement("UPDATE attachments " +
                        "SET filename = ?, applicationid = ? WHERE id = ?");
                statement.setString(1,attachment.getFilename());
                statement.setInt(2,attachment.getApplicationId());
                statement.setInt(3,attachment.getId());
                rows = statement.executeUpdate();

            }
        }
        return rows;
    }

    public int delete(Attachment attachment) throws SQLException {
        int rows = 0;
        if(attachment.isInDatabase()){
            try(Connection connection = connectionUtil.getConnection()){
                PreparedStatement statement = connection.prepareStatement("DELETE FROM attachments WHERE id = ?");
                statement.setInt(1,attachment.getId());
                rows = statement.executeUpdate();

            }
        }
        return rows;
    }
}