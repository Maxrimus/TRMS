package com.Services;

import com.Beans.Application;
import com.Beans.LearningEvent;

import java.sql.Time;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ApplicationService {

    private enum dateErrors{
        AFTER,
        ONEWEEK,
        TWOWEEKS,
        GOOD
    }

    private Map<String, Double> percents;

    public ApplicationService(){
        percents = new HashMap<>();
        percents.put("University Course",.8);
        percents.put("Seminars",.6);
        percents.put("Certification Preperation Class",.75);
        percents.put("Certification",1.0);
        percents.put("Technical Training",.9);
        percents.put("Other",.3);
    }

    public Application createApplication(int employeeId, String justification, int gradingFormat, int eventId){
        Application application = new Application();
        application.setJustification(justification);
        application.setGradingFormat(gradingFormat);
        application.setEventId(eventId);
        application.setEmployeeId(employeeId);
        application.setId(-1);
        return application;
    }

    public LearningEvent createLearningEvent(String type, Time time, String location, String description, Date date, double cost){
        LearningEvent learningEvent = new LearningEvent();
        learningEvent.setEventType(type);
        learningEvent.setEventTime(time);
        learningEvent.setEventLocation(location);
        learningEvent.setEventDescription(description);
        dateErrors error = checkDate(date);
        if(error == dateErrors.GOOD) learningEvent.setEventDate(new java.sql.Date(date.getTime()));
        else learningEvent.setEventDate(new java.sql.Date(new Date().getTime()));
        learningEvent.setEventCost(cost);
        learningEvent.setId(-1);
        return learningEvent;
    }

    private dateErrors checkDate(Date date){
        Date today = new Date();
        if(date.after(today)) return dateErrors.AFTER;
        else return dateErrors.GOOD;
    }

    public double returnPercent(String type){
        return percents.get(type);
    }

    private double determineAmount(double cost, double percent){
        double adjustedCost = cost*percent;
        if(adjustedCost > 1000) return 1000.0;
        else return adjustedCost;
    }
}
