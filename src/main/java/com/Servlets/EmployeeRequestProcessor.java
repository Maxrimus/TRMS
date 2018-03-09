package com.Servlets;

import com.Beans.Application;
import com.Beans.Employee;
import com.Beans.LearningEvent;
import com.Services.ApplicationApprovalsService;
import com.Services.ApplicationService;
import com.Services.GradingFormatService;
import com.Services.LearningEventService;
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

class Form{
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

    public Form(int applicationId, int employeeId, String location, String description, String format, String type, String justification,
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

public class EmployeeRequestProcessor extends HttpServlet {

    ApplicationService applicationService = new ApplicationService();
    LearningEventService learningEventService = new LearningEventService();
    GradingFormatService gradingFormatService = new GradingFormatService();
    ApplicationApprovalsService applicationApprovalsService = new ApplicationApprovalsService();

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
        String check = "";

        while ((check = body.readLine()) != null)
        {
            JsonObject json = new Gson().fromJson(check, JsonObject.class);
            id = json.get("id").getAsInt();
            type=json.get("type").getAsString();
        }

        List<Application> applications = new ArrayList<>();
        if(type.equals("pending")) applications = applicationService.viewPending(id);
        else applications = applicationService.viewCompleted(id);

        Form[] toReturn = new Form[applications.size()];

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

            Form form = new Form(app.getId(),id,learningEvent.getEventLocation(),learningEvent.getEventDescription(),gradingFormatService.getNameById(app.getGradingFormat()),learningEvent.getEventType(),
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
