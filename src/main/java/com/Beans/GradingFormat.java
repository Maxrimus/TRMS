package com.Beans;

public class GradingFormat {
    private int id;
    private String gradingFormat;
    private boolean inDatabase;

    public boolean isInDatabase() { return inDatabase; }

    public void setInDatabase(boolean inDatabase) { this.inDatabase = inDatabase; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGradingFormat() {
        return gradingFormat;
    }

    public void setGradingFormat(String gradingFormat) {
        this.gradingFormat = gradingFormat;
    }

    public GradingFormat(){}
}
