package com.Services;

import com.Beans.Department;
import com.DAOs.DepartmentDAO;
import com.TRMS.ConnectionUtil;

import java.sql.SQLException;

public class DepartmentService {

    private DepartmentDAO departmentDAO = new DepartmentDAO(ConnectionUtil.getConnectionUtil());

    public int getHead(int departmentId){
        try {
            Department department = departmentDAO.readById(departmentId);
            return department.getHeadId();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
