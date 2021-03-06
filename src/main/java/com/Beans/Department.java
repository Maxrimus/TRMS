package com.Beans;

public class Department {
    private int id;
    private int headId;
    private String name;
    private boolean inDatabase;

    public boolean isInDatabase() { return inDatabase; }

    public void setInDatabase(boolean inDatabase) { this.inDatabase = inDatabase; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHeadId() {
        return headId;
    }

    public void setHeadId(int headId) {
        this.headId = headId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department(){}
}
