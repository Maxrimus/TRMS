package com.Services;

import com.Beans.Application;
import com.Beans.Employee;
import com.Beans.LearningEvent;
import com.DAOs.ApplicationDAO;
import com.DAOs.EmployeeDAO;
import com.TRMS.ConnectionUtil;
import com.TRMS.Main;

import java.sql.SQLException;
import java.util.*;

public class ApplicationService {

    ApplicationDAO applicationDAO;
    LearningEventService learningEventService;
    EmployeeDAO employeeDAO = new EmployeeDAO(ConnectionUtil.getConnectionUtil());

    enum Statuses{
        APPROVED("approved"),
        DENIED("denied"),
        INPROGRESS("inprogress");

        String identifier;

        Statuses(String identifier){
            this.identifier = identifier;
        }
    }

    public ApplicationService(){

        applicationDAO = new ApplicationDAO(ConnectionUtil.getConnectionUtil());
        learningEventService = new LearningEventService();
    }

    public int createApplication(int employeeId, java.sql.Date date, java.sql.Time time, String address, String description, int gradingFormat, String eventType, String justification, String[] fileNames, double eventCost){
        LearningEvent learningEvent = learningEventService.createLearningEvent(eventType,time,address,description,date,eventCost);
        if(learningEvent.isInDatabase()){
            Application application = new Application();
            application.setInDatabase(false);
            application.setStatus(Statuses.INPROGRESS.identifier);
            application.setId(-1);
            application.setGradingFormat(gradingFormat);
            application.setEventId(learningEvent.getId());
            application.setEmployeeId(employeeId);
            application.setJustification(justification);
            try {
                boolean created = applicationDAO.create(application);
                if(created) return application.getId();
                else return -1;
            } catch (SQLException e) {
                e.printStackTrace();
                return -1;
            }
        } else return -1;
    }

    public List<Application> viewPending(int employeeid){
        List<Application> apps = new ArrayList<>();
        List<Application> toReturn = new ArrayList<>();
        try {
             apps = applicationDAO.readByEmployeeId(employeeid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for(Application application:apps){
            if(application.getStatus().equals(Statuses.INPROGRESS.identifier)){
                toReturn.add(application);
            }
        }
        return toReturn;
    }

    public List<Application> viewCompleted(int employeeid){
        List<Application> apps = new ArrayList<>();
        List<Application> toReturn = new ArrayList<>();
        try {
            apps = applicationDAO.readByEmployeeId(employeeid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for(Application application:apps){
            if(!application.getStatus().equals(Statuses.INPROGRESS.identifier)) toReturn.add(application);
        }
        return toReturn;
    }

    public Application readById(int applicationId){
        Application application = new Application();
        try {
            application = applicationDAO.readById(applicationId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return application;
    }

    public List<Application> readByDepartment(int department){
        List<Application> applications = new ArrayList<>();
        try {
            List<Employee> employees = employeeDAO.readByDepartment(department);
            for(int i = 0; i < employees.size(); i++){
                List<Application> applications1 = new ArrayList<>();
                applications1 = applicationDAO.readByEmployeeId(employees.get(i).getId());
                for(int j = 0; j < applications1.size(); j++){
                    applications.add(applications1.get(j));
                }
            }
            return applications;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Application> readByStatus(String status){
        List<Application> application = new ArrayList<>();
        try {
            application = applicationDAO.readByStatus(status);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return application;
    }
}
