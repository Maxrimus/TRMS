package com.TRMS.Beans;

import com.Beans.Attachment;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* Attachment Tester. 
* 
* @author <Authors name> 
* @since <pre>Feb 26, 2018</pre> 
* @version 1.0 
*/ 
public class AttachmentTest { 

    Attachment attachment;

@Before
public void before() throws Exception {
    attachment = new Attachment();
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
    attachment.setId(expected);
    int actual = attachment.getId();
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
    attachment.setId(expected);
    int actual = attachment.getId();
    Assert.assertEquals(expected,actual);
} 

/** 
* 
* Method: getApplicationId() 
* 
*/ 
@Test
public void testGetApplicationId() throws Exception {
    int expected = 1;
    attachment.setApplicationId(expected);
    int actual = attachment.getApplicationId();
    Assert.assertEquals(expected,actual);
} 

/** 
* 
* Method: setApplicationId(int applicationId) 
* 
*/ 
@Test
public void testSetApplicationId() throws Exception {
    int expected = 1;
    attachment.setApplicationId(expected);
    int actual = attachment.getApplicationId();
    Assert.assertEquals(expected,actual);
} 

/** 
* 
* Method: getFilename() 
* 
*/ 
@Test
public void testGetFilename() throws Exception {
    String expected = "test";
    attachment.setFilename(expected);
    String actual = attachment.getFilename();
    Assert.assertEquals(expected,actual);
} 

/** 
* 
* Method: setFilename(String filename) 
* 
*/ 
@Test
public void testSetFilename() throws Exception {
    String expected = "test";
    attachment.setFilename(expected);
    String actual = attachment.getFilename();
    Assert.assertEquals(expected,actual);
} 


} 
