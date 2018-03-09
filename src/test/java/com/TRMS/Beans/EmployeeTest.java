package com.TRMS.Beans;

import com.Beans.Employee;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* Employee Tester. 
* 
* @author <Authors name> 
* @since <pre>Feb 26, 2018</pre> 
* @version 1.0 
*/ 
public class EmployeeTest { 

    Employee employee;

@Before
public void before() throws Exception {
    employee = new Employee();
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
    employee.setId(expected);
    int actual = employee.getId();
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
    employee.setId(expected);
    int actual = employee.getId();
    Assert.assertEquals(expected,actual);
} 

/** 
* 
* Method: getDepartmentId() 
* 
*/ 
@Test
public void testGetDepartmentId() throws Exception {
    int expected = 1;
    employee.setDepartmentId(expected);
    int actual = employee.getDepartmentId();
    Assert.assertEquals(expected,actual);
} 

/** 
* 
* Method: setDepartmentId(int departmentId) 
* 
*/ 
@Test
public void testSetDepartmentId() throws Exception {
    int expected = 1;
    employee.setDepartmentId(expected);
    int actual = employee.getDepartmentId();
    Assert.assertEquals(expected,actual);
} 

/** 
* 
* Method: getFirstName() 
* 
*/ 
@Test
public void testGetFirstName() throws Exception {
    String expected = "test";
    employee.setFirstName(expected);
    String actual = employee.getFirstName();
    Assert.assertEquals(expected,actual);
} 

/** 
* 
* Method: setFirstName(String firstName) 
* 
*/ 
@Test
public void testSetFirstName() throws Exception {
    String expected = "test";
    employee.setFirstName(expected);
    String actual = employee.getFirstName();
    Assert.assertEquals(expected,actual);
} 

/** 
* 
* Method: getLastName() 
* 
*/ 
@Test
public void testGetLastName() throws Exception {
    String expected = "test";
    employee.setLastName(expected);
    String actual = employee.getLastName();
    Assert.assertEquals(expected,actual);
} 

/** 
* 
* Method: setLastName(String lastName) 
* 
*/ 
@Test
public void testSetLastName() throws Exception {
    String expected = "test";
    employee.setLastName(expected);
    String actual = employee.getLastName();
    Assert.assertEquals(expected,actual);
} 

/** 
* 
* Method: getTitle() 
* 
*/ 
@Test
public void testGetTitle() throws Exception {
    String expected = "test";
    employee.setTitle(expected);
    String actual = employee.getTitle();
    Assert.assertEquals(expected,actual);
} 

/** 
* 
* Method: setTitle(String title) 
* 
*/ 
@Test
public void testSetTitle() throws Exception {
    String expected = "test";
    employee.setTitle(expected);
    String actual = employee.getTitle();
    Assert.assertEquals(expected,actual);
} 


} 
