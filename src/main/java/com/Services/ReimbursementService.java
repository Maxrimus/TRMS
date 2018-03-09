package com.Services;

import com.Beans.Reimbursement;
import com.DAOs.ReimbursementDAO;
import com.TRMS.ConnectionUtil;
import com.TRMS.Main;

import java.sql.SQLException;

public class ReimbursementService {
    ReimbursementDAO reimbursementDAO;

    public ReimbursementService(){
        reimbursementDAO = new ReimbursementDAO(ConnectionUtil.getConnectionUtil());
    }

    public double calculateAwarded(int employeeid){
        Reimbursement reimbursement = new Reimbursement();
        try {
            reimbursement = reimbursementDAO.readById(employeeid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reimbursement.getAmountAwarded() + reimbursement.getAmountPending();
    }
}
