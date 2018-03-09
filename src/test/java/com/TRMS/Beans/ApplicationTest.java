package com.TRMS.Beans;

import com.Beans.Application;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* Application Tester. 
* 
* @author <Authors name> 
* @since <pre>Feb 26, 2018</pre> 
* @version 1.0 
*/ 
public class ApplicationTest {

    Application application;

@Before
public void before() throws Exception {
    application = new Application();
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
    application.setId(expected);
    int actual = application.getId();
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
    application.setId(expected);
    int actual = application.getId();
    Assert.assertEquals(expected,actual);
} 

/** 
* 
* Method: getEmployeeId() 
* 
*/ 
@Test
public void testGetEmployeeId() throws Exception {
    int expected = 1;
    application.setEmployeeId(expected);
    int actual = application.getEmployeeId();
    Assert.assertEquals(expected,actual);
} 

/** 
* 
* Method: setEmployeeId(int employeeId) 
* 
*/ 
@Test
public void testSetEmployeeId() throws Exception {
    int expected = 1;
    application.setEmployeeId(expected);
    int actual = application.getEmployeeId();
    Assert.assertEquals(expected,actual);
} 

/** 
* 
* Method: getEventId() 
* 
*/ 
@Test
public void testGetEventId() throws Exception {
    int expected = 1;
    application.setEventId(expected);
    int actual = application.getEventId();
    Assert.assertEquals(expected,actual);
} 

/** 
* 
* Method: setEventId(int eventId) 
* 
*/ 
@Test
public void testSetEventId() throws Exception {
    int expected = 1;
    application.setEventId(expected);
    int actual = application.getEventId();
    Assert.assertEquals(expected,actual);
} 

/** 
* 
* Method: getJustification() 
* 
*/ 
@Test
public void testGetJustification() throws Exception {
    String expected = "test";
    application.setJustification(expected);
    String actual = application.getJustification();
    Assert.assertEquals(expected,actual);
} 

/** 
* 
* Method: setJustification(String justification) 
* 
*/ 
@Test
public void testSetJustification() throws Exception {
    String expected = "test";
    application.setJustification(expected);
    String actual = application.getJustification();
    Assert.assertEquals(expected,actual);
} 

/** 
* 
* Method: getGradingFormat() 
* 
*/ 
@Test
public void testGetGradingFormat() throws Exception {
    int expected = 1;
    application.setGradingFormat(expected);
    int actual = application.getGradingFormat();
    Assert.assertEquals(expected,actual);
} 

/** 
* 
* Method: setGradingFormat(int gradingFormat) 
* 
*/ 
@Test
public void testSetGradingFormat() throws Exception {
    int expected = 1;
    application.setGradingFormat(expected);
    int actual = application.getGradingFormat();
    Assert.assertEquals(expected,actual);
} 


} 
