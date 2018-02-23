package com.Beans;

import java.sql.Time;
import java.util.Date;

public class LearningEvent {
    private int id;
    private java.sql.Date eventDate;
    private Time eventTime;
    private String eventDescription;
    private double eventCost;
    private String eventLocation;
    private String eventType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public java.sql.Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(java.sql.Date eventDate) {
        this.eventDate = eventDate;
    }

    public Time getEventTime() {
        return eventTime;
    }

    public void setEventTime(Time eventTime) {
        this.eventTime = eventTime;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public double getEventCost() {
        return eventCost;
    }

    public void setEventCost(double eventCost) {
        this.eventCost = eventCost;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public LearningEvent(){}
}
