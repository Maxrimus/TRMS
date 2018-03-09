package com.DAOs;

import com.Beans.GradingFormat;
import com.TRMS.ConnectionUtil;
import com.TRMS.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GradingFormatDAO {

    ConnectionUtil connectionUtil;

    public GradingFormatDAO(ConnectionUtil connectionUtil){
        this.connectionUtil = connectionUtil;
    }

    public boolean create(GradingFormat gradingFormat) throws SQLException {
        if(gradingFormat.isInDatabase()){
            int rows = update(gradingFormat);
            if(rows == 0) return false;
            else return true;
        }
        else{
            try(Connection connection = connectionUtil.getConnection()){
                PreparedStatement statement = connection.prepareStatement("INSERT INTO gradingformat (format) VALUES (?) RETURNING id");
                statement.setString(1,gradingFormat.getGradingFormat());
                ResultSet resultSet = statement.executeQuery();
                if(resultSet.next()) {
                    gradingFormat.setId(resultSet.getInt("id"));
                    gradingFormat.setInDatabase(true);
                    return true;
                }else return false;

            }
        }
    }

    public GradingFormat readById(int id) throws SQLException {
        GradingFormat gradingFormat = new GradingFormat();
        try(Connection connection = connectionUtil.getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM gradingformat WHERE id = ?");
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                gradingFormat.setId(id);
                gradingFormat.setGradingFormat(resultSet.getString("format"));
            }
            return gradingFormat;

        }
    }

    public GradingFormat readByName(String name) throws SQLException {
        GradingFormat gradingFormat = new GradingFormat();
        try(Connection connection = connectionUtil.getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM gradingformat WHERE format = ?");
            statement.setString(1,name);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                gradingFormat.setId(resultSet.getInt("id"));
                gradingFormat.setGradingFormat(name);
            }
            return gradingFormat;

        }
    }

    public List<GradingFormat> readAll() throws SQLException {
        List<GradingFormat> gradingFormats = new ArrayList<>();
        GradingFormat gradingFormat = new GradingFormat();
        try(Connection connection = connectionUtil.getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM gradingformat");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                gradingFormat.setId(resultSet.getInt("id"));
                gradingFormat.setGradingFormat(resultSet.getString("format"));
                gradingFormats.add(gradingFormat);
            }
            return gradingFormats;

        }
    }

    public int update(GradingFormat gradingFormat) throws SQLException {
        int rows = 0;
        if(!gradingFormat.isInDatabase()){
            boolean created = create(gradingFormat);
            if(created) rows = 1;
        }
        else{
            try(Connection connection = connectionUtil.getConnection()){
                PreparedStatement statement = connection.prepareStatement("UPDATE gradingformat " +
                        "SET format = ? WHERE id = ?");
                statement.setString(1,gradingFormat.getGradingFormat());
                statement.setInt(2,gradingFormat.getId());
                rows = statement.executeUpdate();

            }
        }
        return rows;
    }

    public int delete(GradingFormat gradingFormat) throws SQLException {
        int rows = 0;
        if(gradingFormat.isInDatabase()){
            try(Connection connection = connectionUtil.getConnection()){
                PreparedStatement statement = connection.prepareStatement("DELETE FROM gradingformat WHERE id = ?");
                statement.setInt(1,gradingFormat.getId());
                rows = statement.executeUpdate();

            }
        }
        return rows;
    }
}
