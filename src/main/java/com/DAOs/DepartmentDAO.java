package com.DAOs;

import com.Beans.Department;
import com.TRMS.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentDAO {

    public DepartmentDAO(){}

    public void create(Department department) throws SQLException {
        if(department.getId() == -1){
            PreparedStatement statement = Main.conn.prepareStatement("INSERT INTO departments (name, headid) VALUES (?,?) RETURNING id");
            statement.setString(1,department.getName());
            statement.setInt(2,department.getHeadId());
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) department.setId(resultSet.getInt("id"));
        }
        else update(department);
    }

    public Department read(int id) throws SQLException {
        Department department = new Department();
        PreparedStatement statement = Main.conn.prepareStatement("SELECT * FROM departments WHERE id = ?");
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            department.setId(id);
            department.setHeadId(resultSet.getInt("headid"));
            department.setName(resultSet.getString("name"));
        }
        return department;
    }

    public void update(Department department) throws SQLException {
        if(department.getId() == -1) create(department);
        else{
            PreparedStatement statement = Main.conn.prepareStatement("UPDATE departments " +
                    "SET name = ?, headid = ? WHERE id = ?");
            statement.setString(1,department.getName());
            statement.setInt(2,department.getHeadId());
            statement.setInt(3,department.getId());
            statement.executeUpdate();
        }
    }

    public void delete(Department department) throws SQLException {
        if(department.getId() != -1){
            PreparedStatement statement = Main.conn.prepareStatement("DELETE FROM departments WHERE id = ?");
            statement.setInt(1,department.getId());
            statement.executeUpdate();
        }
    }
}
