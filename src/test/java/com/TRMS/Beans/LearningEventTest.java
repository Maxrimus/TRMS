package com.TRMS.Beans;

import com.Beans.LearningEvent;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.sql.Date;
import java.sql.Time;

/** 
* LearningEvent Tester. 
* 
* @author <Authors name> 
* @since <pre>Feb 26, 2018</pre> 
* @version 1.0 
*/ 
public class LearningEventTest { 

    LearningEvent learningEvent;

@Before
public void before() throws Exception {
    learningEvent = new LearningEvent();
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getId() 
* 
*/ 
@Test
public void testGetId() throws Exception {
    int expected = 1;
    learningEvent.setId(expected);
    int actual = learningEvent.getId();
    Assert.assertEquals(expected,actual);
} 

/** 
* 
* Method: setId(int id) 
* 
*/ 
@Test
public void testSetId() throws Exception {
    int expected = 1;
    learningEvent.setId(expected);
    int actual = learningEvent.getId();
    Assert.assertEquals(expected,actual);
} 

/** 
* 
* Method: getEventDate() 
* 
*/ 
@Test
public void testGetEventDate() throws Exception {
    Date expected = new Date(new java.util.Date().getTime());
    learningEvent.setEventDate(expected);
    Date actual = learningEvent.getEventDate();
    Assert.assertEquals(expected,actual);
} 

/** 
* 
* Method: setEventDate(java.sql.Date eventDate) 
* 
*/ 
@Test
public void testSetEventDate() throws Exception {
    Date expected = new Date(new java.util.Date().getTime());
    learningEvent.setEventDate(expected);
    Date actual = learningEvent.getEventDate();
    Assert.assertEquals(expected,actual);
} 

/** 
* 
* Method: getEventTime() 
* 
*/ 
@Test
public void testGetEventTime() throws Exception {
    Time expected = new Time(1000);
    learningEvent.setEventTime(expected);
    Time actual = learningEvent.getEventTime();
    Assert.assertEquals(expected,actual);
} 

/** 
* 
* Method: setEventTime(Time eventTime) 
* 
*/ 
@Test
public void testSetEventTime() throws Exception {
    Time expected = new Time(1000);
    learningEvent.setEventTime(expected);
    Time actual = learningEvent.getEventTime();
    Assert.assertEquals(expected,actual);
} 

/** 
* 
* Method: getEventDescription() 
* 
*/ 
@Test
public void testGetEventDescription() throws Exception {
    String expected = "test";
    learningEvent.setEventDescription(expected);
    String actual = learningEvent.getEventDescription();
    Assert.assertEquals(expected,actual);
} 

/** 
* 
* Method: setEventDescription(String eventDescription) 
* 
*/ 
@Test
public void testSetEventDescription() throws Exception {
    String expected = "test";
    learningEvent.setEventDescription(expected);
    String actual = learningEvent.getEventDescription();
    Assert.assertEquals(expected,actual);
} 

/** 
* 
* Method: getEventCost() 
* 
*/ 
@Test
public void testGetEventCost() throws Exception {
    double expected = 1.0;
    learningEvent.setEventCost(expected);
    double actual = learningEvent.getEventCost();
    Assert.assertEquals(expected,actual,0.0);
} 

/** 
* 
* Method: setEventCost(double eventCost) 
* 
*/ 
@Test
public void testSetEventCost() throws Exception {
    double expected = 1.0;
    learningEvent.setEventCost(expected);
    double actual = learningEvent.getEventCost();
    Assert.assertEquals(expected,actual,0.0);
} 

/** 
* 
* Method: getEventLocation() 
* 
*/ 
@Test
public void testGetEventLocation() throws Exception {
    String expected = "test";
    learningEvent.setEventLocation(expected);
    String actual = learningEvent.getEventLocation();
    Assert.assertEquals(expected,actual);
} 

/** 
* 
* Method: setEventLocation(String eventLocation) 
* 
*/ 
@Test
public void testSetEventLocation() throws Exception {
    String expected = "test";
    learningEvent.setEventLocation(expected);
    String actual = learningEvent.getEventLocation();
    Assert.assertEquals(expected,actual);
} 

/** 
* 
* Method: getEventType() 
* 
*/ 
@Test
public void testGetEventType() throws Exception {
    String expected = "test";
    learningEvent.setEventType(expected);
    String actual = learningEvent.getEventType();
    Assert.assertEquals(expected,actual);
} 

/** 
* 
* Method: setEventType(String eventType) 
* 
*/ 
@Test
public void testSetEventType() throws Exception {
    String expected = "test";
    learningEvent.setEventType(expected);
    String actual = learningEvent.getEventType();
    Assert.assertEquals(expected,actual);
} 


} 
