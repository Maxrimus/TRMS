package com.DAOs;

import com.Beans.Department;
import com.TRMS.ConnectionUtil;
import com.TRMS.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO {

    ConnectionUtil connectionUtil;

    public DepartmentDAO(ConnectionUtil connectionUtil){
        this.connectionUtil = connectionUtil;
    }

    public boolean create(Department department) throws SQLException {
        if(department.isInDatabase()){
            int rows = update(department);
            if(rows == 0) return false;
            else return true;
        }
        else{
            try(Connection connection = connectionUtil.getConnection()){
                PreparedStatement statement = connection.prepareStatement("INSERT INTO departments (name, headid) VALUES (?,?) RETURNING id");
                statement.setString(1,department.getName());
                statement.setInt(2,department.getHeadId());
                ResultSet resultSet = statement.executeQuery();
                if(resultSet.next()){
                    department.setId(resultSet.getInt("id"));
                    department.setInDatabase(true);
                    return true;
                }else return false;

            }
        }
    }

    public Department readById(int id) throws SQLException {
        Department department = new Department();
        try(Connection connection = connectionUtil.getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM departments WHERE id = ?");
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                department.setId(id);
                department.setHeadId(resultSet.getInt("headid"));
                department.setName(resultSet.getString("name"));
            }
            return department;

        }
    }

    public List<Department> readAll() throws SQLException {
        List<Department> departments = new ArrayList<>();
        Department department = new Department();
        try(Connection connection = connectionUtil.getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM departments");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                department.setId(resultSet.getInt("id"));
                department.setHeadId(resultSet.getInt("headid"));
                department.setName(resultSet.getString("name"));
            }
            return departments;

        }
    }

    public int update(Department department) throws SQLException {
        int rows = 0;
        if(department.isInDatabase()){
            boolean created = create(department);
            if(created) rows = 1;
        }
        else{
            try(Connection connection = connectionUtil.getConnection()){
                PreparedStatement statement = connection.prepareStatement("UPDATE departments " +
                        "SET name = ?, headid = ? WHERE id = ?");
                statement.setString(1,department.getName());
                statement.setInt(2,department.getHeadId());
                statement.setInt(3,department.getId());
                rows = statement.executeUpdate();

            }
        }
        return rows;
    }

    public int delete(Department department) throws SQLException {
        int rows = 0;
        if(department.isInDatabase()){
            try(Connection connection = connectionUtil.getConnection()){
                PreparedStatement statement = connection.prepareStatement("DELETE FROM departments WHERE id = ?");
                statement.setInt(1,department.getId());
                rows = statement.executeUpdate();

            }
        }
        return rows;
    }
}
