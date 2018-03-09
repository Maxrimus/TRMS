package com.Servlets;

import com.Beans.Employee;
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

class LoginReturn{
    boolean verified;
    String firstName;
    int id;
    String title;

    public LoginReturn(boolean verified, String firstName, int id, String title){
        this.verified = verified;
        this.firstName = firstName;
        this.id = id;
        this.title = title;
    }
}

public class LoginProcessor extends HttpServlet {

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

        String username = "";
        String password = "";
        String check = "";

        while ((check = body.readLine()) != null)
        {
            JsonObject json = new Gson().fromJson(check, JsonObject.class);
            password = json.get("password").getAsString();
            username = json.get("username").getAsString();
        }

        LoginReturn toReturn = new LoginReturn(false,"", -1,"");

        toReturn.verified = employeeService.verifyLogin(username,password);
        Employee loggedIn = employeeService.getByUsername(username);
        toReturn.firstName = loggedIn.getFirstName();
        toReturn.id = loggedIn.getId();
        toReturn.title = loggedIn.getTitle();

        PrintWriter out = resp.getWriter();
        String jsonToReturn = new Gson().toJson(toReturn);
        out.print(jsonToReturn);
        out.flush();
        out.close();
    }
}
