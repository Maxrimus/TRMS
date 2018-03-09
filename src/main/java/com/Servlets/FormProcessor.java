package com.Servlets;

import com.Beans.Application;
import com.Beans.Employee;
import com.Beans.LearningEvent;
import com.Beans.Reimbursement;
import com.Services.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sun.corba.se.spi.ior.IORTemplateList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

class FormReturn{
    boolean verified;
    double cost;

    public FormReturn(boolean verified, double cost){
        this.verified = verified;
        this.cost = cost;
    }
}

public class FormProcessor extends HttpServlet {

    LearningEventService learningEventService = new LearningEventService();
    ApplicationService applicationService = new ApplicationService();
    GradingFormatService gradingFormatService = new GradingFormatService();
    EmployeeService employeeService = new EmployeeService();
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

        int employeeId = -1;
        String address1 = "";
        String address2 = "";
        String city = "";
        String state = "";
        String zip = "";
        String country = "";
        String description = "";
        String gradingFormat = "";
        String eventType = "";
        String justification = "";
        String dateTime = "";
        int cost = -1;
        String check = "";

        while ((check = body.readLine()) != null)
        {
            JsonObject json = new Gson().fromJson(check, JsonObject.class);
            employeeId = json.get("employeeId").getAsInt();
            address1 = json.get("address1").getAsString();
            address2 = json.get("address2").getAsString();
            city = json.get("city").getAsString();
            state = json.get("state").getAsString();
            zip = json.get("zip").getAsString();
            country = json.get("country").getAsString();
            description = json.get("description").getAsString();
            gradingFormat = json.get("gradingFormat").getAsString();
            eventType = json.get("eventType").getAsString();
            justification = json.get("justification").getAsString();
            dateTime = json.get("dateTime").getAsString();
            cost = json.get("cost").getAsInt();
        }

        String[] dateTimeSep = dateTime.split(" ");
        String date = dateTimeSep[0];
        String time = dateTimeSep[1];
        String[] dateSep = date.split("/");
        String[] timeSep = time.split(":");
        int day = Integer.parseInt(dateSep[1]);
        int month = Integer.parseInt(dateSep[0]);
        int year = Integer.parseInt(dateSep[2]);
        int hour = Integer.parseInt(timeSep[0]);
        int minute = Integer.parseInt(timeSep[1]);
        String dateAdj = year + "-" + month + "-" + day;
        Date dateD = java.sql.Date.valueOf(dateAdj);
//        System.out.println(dateD.toString());
//        long timeL = ((hour*3600)+(minute*60))*1000;
//        System.out.println(timeL);
        SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy hh:mm");
        long ms = 0;
        try {
            ms = sdf.parse(dateTime).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Time timeT = new Time(ms);
        String location = address1 + " " + address2 + " " + city + " " + state + " " + zip + " " + country;
        int formatId = gradingFormatService.getIdByName(gradingFormat);

        String[] emptyArray = new String[0];
        int id = applicationService.createApplication(employeeId,dateD,timeT,location,description,formatId,eventType,justification,emptyArray,cost);

        Employee supervisor = employeeService.getSupervisor(employeeId);
        Employee departmentHead = employeeService.getHead(employeeId);
        Employee benco = employeeService.getBenco();

        Employee[] approvers = new Employee[3];
        approvers[0] = supervisor;
        approvers[1] = departmentHead;
        approvers[2] = benco;

        applicationApprovalsService.addApprovers(approvers,id);

        double compensation = learningEventService.determineAmount(cost,eventType,employeeId);

        FormReturn formReturn;
        if(compensation == -1.0){
            formReturn = new FormReturn(false,compensation);
        }
        else formReturn = new FormReturn(true,compensation);

        PrintWriter out = resp.getWriter();
        String jsonToReturn = new Gson().toJson(formReturn);
        out.print(jsonToReturn);
        out.flush();
        out.close();
    }
}
