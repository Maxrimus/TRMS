package com.Servlets;

import com.Beans.Application;
import com.Beans.Employee;
import com.Beans.LearningEvent;
import com.Services.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

class SupervisorForm{
    int applicationId;
    int employeeId;
    String location;
    String description;
    String format;
    String type;
    String justification;
    String dateTime;
    double cost;
    double compensation;
    String supApproval;
    String headApproval;
    String benCoApproval;
    String status;

    public SupervisorForm(int applicationId, int employeeId, String location, String description, String format, String type, String justification,
                String dateTime, double cost, double compensation, String supApproval, String headApproval, String benCoApproval, String status){
        this.applicationId = applicationId;
        this.employeeId = employeeId;
        this.location = location;
        this.description = description;
        this.format = format;
        this.type = type;
        this.justification = justification;
        this.dateTime = dateTime;
        this.cost = cost;
        this.compensation = compensation;
        this.supApproval = supApproval;
        this.headApproval = headApproval;
        this.benCoApproval = benCoApproval;
        this.status = status;
    }
}

public class SupervisorRequestProcessor extends HttpServlet {

    ApplicationService applicationService = new ApplicationService();
    LearningEventService learningEventService = new LearningEventService();
    GradingFormatService gradingFormatService = new GradingFormatService();
    ApplicationApprovalsService applicationApprovalsService = new ApplicationApprovalsService();
    SupervisorService supervisorService = new SupervisorService();
    EmployeeService employeeService = new EmployeeService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader body = req.getReader();
        resp.setHeader("Content-Type", "text/plain");
        resp.setStatus(200);
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        resp.setHeader("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setContentType("text/html;charset=UTF-8");

        int id = -1;
        String type="";
        String title = "";
        String check = "";

        while ((check = body.readLine()) != null)
        {
            JsonObject json = new Gson().fromJson(check, JsonObject.class);
            id = json.get("id").getAsInt();
            type=json.get("type").getAsString();
        }

        title = employeeService.getById(id).getTitle();

        List<Integer> employees = supervisorService.getEmployees(id);

        List<Application> applications = new ArrayList<>();
        List<Application> tempApps = new ArrayList<>();
        String[] pieces = title.split(" ");

        if(title.equals("BenCo")){
            if(type.equals("pending"))applications = applicationService.readByStatus("inprogress");
        }
        else if(pieces[pieces.length-1].equals("Head")){
            applications = applicationService.readByDepartment(employeeService.getById(id).getDepartmentId());
        }
        else{
            if(type.equals("pending")) {
                for(int i = 0; i < employees.size(); i++){
                    tempApps = applicationService.viewPending(employees.get(i));
                    for(Application application:tempApps){
                        applications.add(application);
                    }
                }
            }
            else{
                for(int i = 0; i < employees.size(); i++){
                    tempApps = applicationService.viewCompleted(employees.get(i));
                    for(Application application:tempApps){
                        applications.add(application);
                    }
                }
            }
        }

        SupervisorForm[] toReturn = new SupervisorForm[applications.size()];

        for(int i = 0; i < toReturn.length; i++){
            Application app = applications.get(i);
            LearningEvent learningEvent = new LearningEvent();
            learningEvent = learningEventService.readById(app.getEventId());

            String date = learningEvent.getEventDate().toString();
            String time = learningEvent.getEventTime().toString();
            double compensation = learningEventService.determineAmount(learningEvent.getEventCost(),learningEvent.getEventType(),id);

            List<Boolean> statuses = applicationApprovalsService.getStatuses(app.getId());
            List<String> statusesS = new ArrayList<>();
            for(int j = 0; j < statuses.size(); j++){
                boolean status = statuses.get(j);
                if(status) statusesS.add("Yes");
                else statusesS.add("No");
            }

            SupervisorForm form = new SupervisorForm(app.getId(),app.getEmployeeId(),learningEvent.getEventLocation(),learningEvent.getEventDescription(),gradingFormatService.getNameById(app.getGradingFormat()),learningEvent.getEventType(),
                    app.getJustification(),date + " " + time,learningEvent.getEventCost(),compensation,statusesS.get(0),statusesS.get(1),statusesS.get(2),app.getStatus());

            toReturn[i] = form;
        }

        PrintWriter out = resp.getWriter();
        String jsonToReturn = new Gson().toJson(toReturn);
        out.print(jsonToReturn);
        out.flush();
        out.close();
    }
}
