package com.Services;

import com.Beans.Application;
import com.Beans.ApplicationApproval;
import com.Beans.Employee;
import com.DAOs.ApplicationApprovalsDAO;
import com.DAOs.EmployeeDAO;
import com.TRMS.ConnectionUtil;
import com.TRMS.Main;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ApplicationApprovalsService {

    private ApplicationApprovalsDAO applicationApprovalsDAO;
    private ApplicationService applicationService;
    private EmployeeDAO employeeDAO;

    public ApplicationApprovalsService(){
        applicationService = new ApplicationService();
        applicationApprovalsDAO = new ApplicationApprovalsDAO(ConnectionUtil.getConnectionUtil());
        employeeDAO = new EmployeeDAO(ConnectionUtil.getConnectionUtil());
    }

    public boolean approve(int appId,String approverType, int approverId){
        Application application = new Application();;
        List<ApplicationApproval> applicationApprovals = new ArrayList<>();
        boolean approved = true;
        try {
            System.out.println(appId);
            applicationApprovals = applicationApprovalsDAO.readByApplicationId(appId);
            System.out.println(applicationApprovals.size());
            application = applicationService.readById(appId);
            for(ApplicationApproval app:applicationApprovals){
                System.out.println(app.getId());
                if(app.getApproverId()==approverId) app.setApproved(true);
                System.out.println(app.isApproved());
                applicationApprovalsDAO.update(app);
                if(!app.isApproved()) approved = false;
            }
            if(approved) application.setStatus(ApplicationService.Statuses.APPROVED.identifier);
            applicationService.applicationDAO.update(application);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean disapprove(int appId){
        Application application = applicationService.readById(appId);
        System.out.println(application.getStatus());
        application.setStatus(ApplicationService.Statuses.DENIED.identifier);
        System.out.println(application.getStatus());
        try {
            applicationService.applicationDAO.update(application);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public void addApprovers(Employee[] approvers, int appId){
        ApplicationApproval applicationApproval = new ApplicationApproval();
        for(int i = 0; i < approvers.length; i++){
            Employee approver = approvers[i];
            applicationApproval.setApproverId(approver.getId());
            applicationApproval.setApplicationId(appId);
            applicationApproval.setApprovalDate(new Date(new java.util.Date().getTime()));
            applicationApproval.setApproved(false);
            applicationApproval.setApproverType(approver.getTitle());
            applicationApproval.setInDatabase(false);
            try {
                applicationApprovalsDAO.create(applicationApproval);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Employee> getApprovers(int appId){
        List<ApplicationApproval> applicationApprovals = new ArrayList<>();
        try {
            applicationApprovals = applicationApprovalsDAO.readByApplicationId(appId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<Employee> approvers = new ArrayList<>();
        for(int i = 0; i < applicationApprovals.size(); i++){
            try {
                approvers.add(employeeDAO.readById(applicationApprovals.get(i).getId()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return approvers;
    }

    public List<Boolean> getStatuses(int appId){
        List<ApplicationApproval> applicationApprovals = new ArrayList<>();
        try {
            applicationApprovals = applicationApprovalsDAO.readByApplicationId(appId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<Boolean> statuses = new ArrayList<>();
        for(int i = 0; i < applicationApprovals.size(); i++){
            statuses.add(applicationApprovals.get(i).isApproved());
        }
        return statuses;
    }
}
