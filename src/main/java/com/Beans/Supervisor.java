package com.Beans;

public class Supervisor {
    private int supervisorId;
    private int employeeId;
    private boolean inDatabase;

    public boolean isInDatabase() { return inDatabase; }

    public void setInDatabase(boolean inDatabase) { this.inDatabase = inDatabase; }

    public int getSupervisorId() { return supervisorId; }

    public void setSupervisorId(int supervisorId) { this.supervisorId = supervisorId; }

    public int getEmployeeId() { return employeeId; }

    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }

    public Supervisor(){}
}
