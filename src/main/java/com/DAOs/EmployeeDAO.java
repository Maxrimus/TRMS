package com.DAOs;

import com.Beans.Employee;
import com.TRMS.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDAO {

    public EmployeeDAO(){}

    public void create(Employee employee) throws SQLException {
        if(employee.getId() == -1){
            PreparedStatement statement = Main.conn.prepareStatement("INSERT INTO employees (first_name,last_name,title,departmentid) VALUES (?,?,?,?) RETURNING id");
            statement.setString(1,employee.getFirstName());
            statement.setString(2,employee.getLastName());
            statement.setString(3,employee.getTitle());
            statement.setInt(4,employee.getDepartmentId());
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) employee.setId(resultSet.getInt("id"));
        }
        else update(employee);
    }

    public Employee read(int id) throws SQLException {
        Employee employee = new Employee();
        PreparedStatement statement = Main.conn.prepareStatement("SELECT * FROM employees WHERE id = ?");
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            employee.setId(id);
            employee.setDepartmentId(resultSet.getInt("departmentid"));
            employee.setFirstName(resultSet.getString("first_name"));
            employee.setLastName(resultSet.getString("last_name"));
            employee.setTitle(resultSet.getString("title"));
        }
        return employee;
    }

    public void update(Employee employee) throws SQLException {
        if(employee.getId() == -1) create(employee);
        else{
            PreparedStatement statement = Main.conn.prepareStatement("UPDATE employees " +
                    "SET departmentid = ?, first_name = ?, last_name = ?, title = ? WHERE id = ?");
            statement.setInt(1,employee.getDepartmentId());
            statement.setString(2,employee.getFirstName());
            statement.setString(3,employee.getLastName());
            statement.setString(4,employee.getTitle());
            statement.setInt(5,employee.getId());
            statement.executeUpdate();
        }
    }

    public void delete(Employee employee) throws SQLException {
        if(employee.getId() != -1){
            PreparedStatement statement = Main.conn.prepareStatement("DELETE FROM employees WHERE id = ?");
            statement.setInt(1,employee.getId());
            statement.executeUpdate();
        }
    }
}
