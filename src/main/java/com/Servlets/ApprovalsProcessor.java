package com.Servlets;

import com.Beans.ApplicationApproval;
import com.Beans.Employee;
import com.Services.ApplicationApprovalsService;
import com.Services.EmployeeService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ApprovalsProcessor extends HttpServlet{

    ApplicationApprovalsService applicationApprovalsService = new ApplicationApprovalsService();
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
        int appId = -1;
        String type = "";
        String check = "";


        while ((check = body.readLine()) != null)
        {
            JsonObject json = new Gson().fromJson(check, JsonObject.class);
            id = json.get("id").getAsInt();
            appId = json.get("appId").getAsInt();
            type = json.get("type").getAsString();
        }

        Employee employee = new Employee();
        employee = employeeService.getById(id);

        if(type.equals("accept")){
            applicationApprovalsService.approve(appId,employee.getTitle(),id);
        }
        else{
            applicationApprovalsService.disapprove(appId);
        }

        PrintWriter out = resp.getWriter();
        String jsonToReturn = new Gson().toJson(true);
        out.print(jsonToReturn);
        out.flush();
        out.close();
    }
}
