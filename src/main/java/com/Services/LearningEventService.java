package com.Services;

import com.Beans.LearningEvent;
import com.DAOs.LearningEventDAO;
import com.TRMS.ConnectionUtil;
import com.TRMS.Main;

import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class LearningEventService {

    Map<String,Double> percentages;

    LearningEventDAO learningEventDAO;
    ReimbursementService reimbursementService;

    public LearningEventService(){
        percentages = new HashMap<>();
        percentages.put("University Course",0.8);
        percentages.put("Seminar", 0.6);
        percentages.put("Certification Preparation Class", 0.75);
        percentages.put("Certification", 1.0);
        percentages.put("Technical Training",0.9);
        percentages.put("Other",0.3);

        learningEventDAO = new LearningEventDAO(ConnectionUtil.getConnectionUtil());
        reimbursementService = new ReimbursementService();
    }

    public LearningEvent createLearningEvent(String type, Time time, String location, String description, java.sql.Date date, double cost){
        LearningEvent learningEvent = new LearningEvent();
        learningEvent.setEventType(type);
        learningEvent.setEventTime(time);
        learningEvent.setEventLocation(location);
        learningEvent.setEventDescription(description);
        learningEvent.setEventDate(date);
        learningEvent.setEventCost(cost);
        learningEvent.setId(-1);
        learningEvent.setInDatabase(false);
        try {
            learningEventDAO.create(learningEvent);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return learningEvent;
    }

    public LearningEvent readById(int id){
        try {
            return learningEventDAO.readById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public double determineAmount(double cost, String eventType,int employeeId){
        double adjustedCost = cost*percentages.get(eventType);
        double awarded = reimbursementService.calculateAwarded(employeeId);
        if(adjustedCost > 1000.0) adjustedCost = 1000.0;
        double toAward = adjustedCost - awarded;
        if(toAward < 0.0) toAward = 0.0;
        return toAward;
    }
}
