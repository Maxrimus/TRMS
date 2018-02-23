package com.Beans;

public class Application {
    private int id;
    private int employeeId;
    private int eventId;
    private int gradingFormat;
    private String justification;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    public int getGradingFormat() {
        return gradingFormat;
    }

    public void setGradingFormat(int gradingFormat) {
        this.gradingFormat = gradingFormat;
    }

    public Application(){
    }
}
