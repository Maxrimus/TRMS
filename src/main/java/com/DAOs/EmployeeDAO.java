package com.DAOs;

import com.Beans.Employee;
import com.TRMS.ConnectionUtil;
import com.TRMS.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    ConnectionUtil connectionUtil;
    
    public EmployeeDAO(ConnectionUtil connectionUtil){
        this.connectionUtil = connectionUtil;
    }

    public boolean create(Employee employee) throws SQLException {
        if(employee.isInDatabase()){
            int rows = update(employee);
            if(rows == 0) return false;
            else return true;
        }
        else{
            try(Connection connection = connectionUtil.getConnection()){
                PreparedStatement statement = connection.prepareStatement("INSERT INTO employees (first_name,last_name,title,departmentid, username, password, email) VALUES (?,?,?,?,?,?,?) RETURNING id");
                statement.setString(1,employee.getFirstName());
                statement.setString(2,employee.getLastName());
                statement.setString(3,employee.getTitle());
                statement.setInt(4,employee.getDepartmentId());
                statement.setString(5,employee.getUsername());
                statement.setString(6,employee.getPassword());
                statement.setString(7,employee.getEmaill());
                ResultSet resultSet = statement.executeQuery();
                if(resultSet.next()){
                    employee.setId(resultSet.getInt("id"));
                    employee.setInDatabase(true);
                    return true;
                }
                else return true;

            }
        }
    }

    public Employee readById(int id) throws SQLException {
        Employee employee = new Employee();
        try(Connection connection = connectionUtil.getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM employees WHERE id = ?");
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                employee.setId(id);
                employee.setDepartmentId(resultSet.getInt("departmentid"));
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setTitle(resultSet.getString("title"));
                employee.setUsername(resultSet.getString("username"));
                employee.setPassword(resultSet.getString("password"));
                employee.setEmaill(resultSet.getString("email"));
            }
            return employee;

        }
    }

    public Employee readByUsername(String username) throws SQLException {
        Employee employee = new Employee();
        try(Connection connection = connectionUtil.getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM employees WHERE username = ?");
            statement.setString(1,username);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                employee.setId(resultSet.getInt("id"));
                employee.setDepartmentId(resultSet.getInt("departmentid"));
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setTitle(resultSet.getString("title"));
                employee.setUsername(username);
                employee.setPassword(resultSet.getString("password"));
                employee.setEmaill(resultSet.getString("email"));
            }
            return employee;

        }
    }

    public List<Employee> readByDepartment(int department) throws SQLException {
        Employee employee = new Employee();
        List<Employee> employees = new ArrayList<>();
        try(Connection connection = connectionUtil.getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM employees WHERE departmentid = ?");
            statement.setInt(1,department);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                employee = new Employee();
                employee.setId(resultSet.getInt("id"));
                employee.setDepartmentId(department);
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setTitle(resultSet.getString("title"));
                employee.setUsername(resultSet.getString("username"));
                employee.setPassword(resultSet.getString("password"));
                employee.setEmaill(resultSet.getString("email"));
                employees.add(employee);
            }
            return employees;

        }
    }

    public Employee readByTitle(String title) throws SQLException {
        Employee employee = new Employee();
        try(Connection connection = connectionUtil.getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM employees WHERE title = ?");
            statement.setString(1,title);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                employee.setId(resultSet.getInt("id"));
                employee.setDepartmentId(resultSet.getInt("departmentid"));
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setTitle(title);
                employee.setUsername(resultSet.getString("username"));
                employee.setPassword(resultSet.getString("password"));
                employee.setEmaill(resultSet.getString("email"));
            }
            return employee;

        }
    }

    public List<Employee> readAll() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        Employee employee = new Employee();
        try(Connection connection = connectionUtil.getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM employees");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                employee.setId(resultSet.getInt("id"));
                employee.setDepartmentId(resultSet.getInt("departmentid"));
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setTitle(resultSet.getString("title"));
                employee.setUsername(resultSet.getString("username"));
                employee.setPassword(resultSet.getString("password"));
                employee.setEmaill(resultSet.getString("email"));
                employees.add(employee);
            }
            return employees;

        }
    }

    public int update(Employee employee) throws SQLException {
        int rows = 0;
        if(!employee.isInDatabase()){
            boolean created = create(employee);
            if(created) rows = 1;
        }
        else{
            try(Connection connection = connectionUtil.getConnection()){
                PreparedStatement statement = connection.prepareStatement("UPDATE employees " +
                        "SET departmentid = ?, first_name = ?, last_name = ?, title = ?, username = ?, password = ?, email = ? WHERE id = ?");
                statement.setInt(1,employee.getDepartmentId());
                statement.setString(2,employee.getFirstName());
                statement.setString(3,employee.getLastName());
                statement.setString(4,employee.getTitle());
                statement.setString(5,employee.getUsername());
                statement.setString(6,employee.getPassword());
                statement.setString(7,employee.getEmaill());
                statement.setInt(8,employee.getId());
                rows = statement.executeUpdate();

            }
        }
        return rows;
    }

    public int delete(Employee employee) throws SQLException {
        int rows = 0;
        if(employee.isInDatabase()){
            try(Connection connection = connectionUtil.getConnection()){
                PreparedStatement statement = connection.prepareStatement("DELETE FROM employees WHERE id = ?");
                statement.setInt(1,employee.getId());
                rows = statement.executeUpdate();

            }
        }
        return rows;
    }
}
