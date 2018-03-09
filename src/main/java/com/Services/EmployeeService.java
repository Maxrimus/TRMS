package com.Services;

import com.Beans.Employee;
import com.Beans.Supervisor;
import com.DAOs.EmployeeDAO;
import com.TRMS.ConnectionUtil;
import com.TRMS.Main;

import java.sql.SQLException;

public class EmployeeService {

    private EmployeeDAO employeeDAO;
    private SupervisorService supervisorService = new SupervisorService();
    private DepartmentService departmentService = new DepartmentService();

    public EmployeeService(){
        employeeDAO = new EmployeeDAO(ConnectionUtil.getConnectionUtil());
    }

    public boolean verifyLogin(String username, String password){
        Employee employee = new Employee();
        try {
            employee = employeeDAO.readByUsername(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(password.equals(employee.getPassword())) return true;
        else return false;
    }

    public Employee getByUsername(String username){
        try {
            return employeeDAO.readByUsername(username);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Employee getById(int id){
        try {
            return employeeDAO.readById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public Employee getSupervisor(int id){
        try {
            int supId = supervisorService.getById(id);
            Employee supervisor = employeeDAO.readById(supId);
            return supervisor;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Employee getHead(int id){
        try {
            Employee employee = employeeDAO.readById(id);
            int department = employee.getDepartmentId();
            int headId = departmentService.getHead(department);
            return employeeDAO.readById(headId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Employee getBenco(){
        try {
            return employeeDAO.readByTitle("BenCo");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
