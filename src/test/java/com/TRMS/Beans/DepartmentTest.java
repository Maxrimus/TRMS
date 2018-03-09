package com.TRMS.Beans;

import com.Beans.Department;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* Department Tester. 
* 
* @author <Authors name> 
* @since <pre>Feb 26, 2018</pre> 
* @version 1.0 
*/ 
public class DepartmentTest { 

    Department department;

@Before
public void before() throws Exception {
    department = new Department();
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
    department.setId(expected);
    int actual = department.getId();
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
    department.setId(expected);
    int actual = department.getId();
    Assert.assertEquals(expected,actual);
} 

/** 
* 
* Method: getHeadId() 
* 
*/ 
@Test
public void testGetHeadId() throws Exception {
    int expected = 1;
    department.setHeadId(expected);
    int actual = department.getHeadId();
    Assert.assertEquals(expected,actual);
} 

/** 
* 
* Method: setHeadId(int headId) 
* 
*/ 
@Test
public void testSetHeadId() throws Exception {
    int expected = 1;
    department.setHeadId(expected);
    int actual = department.getHeadId();
    Assert.assertEquals(expected,actual);
} 

/** 
* 
* Method: getName() 
* 
*/ 
@Test
public void testGetName() throws Exception {
    String expected = "test";
    department.setName(expected);
    String actual = department.getName();
    Assert.assertEquals(expected,actual);
}

/** 
* 
* Method: setName(String name) 
* 
*/ 
@Test
public void testSetName() throws Exception {
    String expected = "test";
    department.setName(expected);
    String actual = department.getName();
    Assert.assertEquals(expected,actual);
} 


} 
