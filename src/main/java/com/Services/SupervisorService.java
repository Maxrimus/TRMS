package com.Services;

import com.Beans.Supervisor;
import com.DAOs.SupervisorDAO;
import com.TRMS.ConnectionUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupervisorService {

    private SupervisorDAO supervisorDAO = new SupervisorDAO(ConnectionUtil.getConnectionUtil());

    public int getById(int id){
        try {
            return supervisorDAO.readSupervisors(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public List<Integer> getEmployees(int id){
        try {
            return supervisorDAO.readEmployees(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
