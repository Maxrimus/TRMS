package com.DAOs;

import com.Beans.Attachment;
import com.TRMS.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AttachmentDAO {

    public AttachmentDAO(){}

    public void create(Attachment attachment) throws SQLException {
        if(attachment.getId() == -1){
            PreparedStatement statement = Main.conn.prepareStatement("INSERT INTO attachments (filename, applicationid) VALUES (?,?) RETURNING id");
            statement.setString(1,attachment.getFilename());
            statement.setInt(2,attachment.getApplicationId());
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) attachment.setId(resultSet.getInt("id"));
        }
        else update(attachment);
    }

    public Attachment read(int id) throws SQLException {
        Attachment attachment = new Attachment();
        PreparedStatement statement = Main.conn.prepareStatement("SELECT * FROM attachments WHERE id = ?");
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            attachment.setId(id);
            attachment.setApplicationId(resultSet.getInt("applicationid"));
            attachment.setFilename(resultSet.getString("filename"));
        }
        return attachment;
    }

    public void update(Attachment attachment) throws SQLException {
        if(attachment.getId() == -1) create(attachment);
        else{
            PreparedStatement statement = Main.conn.prepareStatement("UPDATE attachments " +
                    "SET filename = ?, applicationid = ? WHERE id = ?");
            statement.setString(1,attachment.getFilename());
            statement.setInt(2,attachment.getApplicationId());
            statement.setInt(3,attachment.getId());
            statement.executeUpdate();
        }
    }

    public void delete(Attachment attachment) throws SQLException {
        if(attachment.getId() != -1){
            PreparedStatement statement = Main.conn.prepareStatement("DELETE FROM attachments WHERE id = ?");
            statement.setInt(1,attachment.getId());
            statement.executeUpdate();
        }
    }
}
