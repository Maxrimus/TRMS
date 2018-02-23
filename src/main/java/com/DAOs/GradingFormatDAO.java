package com.DAOs;

import com.Beans.GradingFormat;
import com.TRMS.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GradingFormatDAO {

    public GradingFormatDAO(){}

    public void create(GradingFormat gradingFormat) throws SQLException {
        if(gradingFormat.getId() == -1){
            PreparedStatement statement = Main.conn.prepareStatement("INSERT INTO gradingformat (format) VALUES (?) RETURNING id");
            statement.setString(1,gradingFormat.getGradingFormat());
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) gradingFormat.setId(resultSet.getInt("id"));
        }
        else update(gradingFormat);
    }

    public GradingFormat read(int id) throws SQLException {
        GradingFormat gradingFormat = new GradingFormat();
        PreparedStatement statement = Main.conn.prepareStatement("SELECT * FROM gradingformat WHERE id = ?");
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            gradingFormat.setId(id);
            gradingFormat.setGradingFormat(resultSet.getString("format"));
        }
        return gradingFormat;
    }

    public void update(GradingFormat gradingFormat) throws SQLException {
        if(gradingFormat.getId() == -1) create(gradingFormat);
        else{
            PreparedStatement statement = Main.conn.prepareStatement("UPDATE gradingformat " +
                    "SET format = ? WHERE id = ?");
            statement.setString(1,gradingFormat.getGradingFormat());
            statement.setInt(2,gradingFormat.getId());
            statement.executeUpdate();
        }
    }

    public void delete(GradingFormat gradingFormat) throws SQLException {
        if(gradingFormat.getId() != -1){
            PreparedStatement statement = Main.conn.prepareStatement("DELETE FROM gradingformat WHERE id = ?");
            statement.setInt(1,gradingFormat.getId());
            statement.executeUpdate();
        }
    }
}
