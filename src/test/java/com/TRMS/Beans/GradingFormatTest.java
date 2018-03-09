package com.TRMS.Beans;

import com.Beans.GradingFormat;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* GradingFormat Tester. 
* 
* @author <Authors name> 
* @since <pre>Feb 26, 2018</pre> 
* @version 1.0 
*/ 
public class GradingFormatTest { 

    GradingFormat gradingFormat;

@Before
public void before() throws Exception {
    gradingFormat = new GradingFormat();
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
    gradingFormat.setId(expected);
    int actual = gradingFormat.getId();
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
    gradingFormat.setId(expected);
    int actual = gradingFormat.getId();
    Assert.assertEquals(expected,actual);
} 

/** 
* 
* Method: getGradingFormat() 
* 
*/ 
@Test
public void testGetGradingFormat() throws Exception {
    String expected = "test";
    gradingFormat.setGradingFormat(expected);
    String actual = gradingFormat.getGradingFormat();
    Assert.assertEquals(expected,actual);
} 

/** 
* 
* Method: setGradingFormat(String gradingFormat) 
* 
*/ 
@Test
public void testSetGradingFormat() throws Exception {
    String expected = "test";
    gradingFormat.setGradingFormat(expected);
    String actual = gradingFormat.getGradingFormat();
    Assert.assertEquals(expected,actual);
} 


} 
