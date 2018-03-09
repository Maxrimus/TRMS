package com.TRMS.DAOs;

import com.Beans.Employee;
import com.DAOs.EmployeeDAO;
import com.TRMS.ConnectionUtil;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/** 
* EmployeeDAO Tester. 
* 
* @author <Authors name> 
* @since <pre>Feb 27, 2018</pre> 
* @version 1.0 
*/
@RunWith(MockitoJUnitRunner.class)
public class EmployeeDAOTest {

    @Mock
    Connection connection;

    @Mock
    PreparedStatement ps;

    @Mock
    ResultSet resultSet;

    EmployeeDAO employeeDAO;

@Before
public void before() throws Exception {
    when(connection.prepareStatement(anyString())).thenReturn(ps);
    when(ps.executeQuery()).thenReturn(resultSet);
    when(ps.executeUpdate()).thenReturn(1);
    when(resultSet.next()).thenReturn(true).thenReturn(false);
    when(resultSet.getInt(anyString())).thenReturn(1);
    when(resultSet.getString(anyString())).thenReturn("test");
    employeeDAO = new EmployeeDAO(ConnectionUtil.getConnectionUtil());
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: create(Employee employee) 
* 
*/ 
@Test
public void testCreate() throws Exception {
    Employee employee = new Employee();
    employee.setTitle("test");
    employee.setFirstName("test");
    employee.setLastName("test");
    employee.setDepartmentId(1);
    employee.setId(-1);
    employeeDAO.create(employee);
    assertTrue(1==employee.getId());
} 

/** 
* 
* Method: read(int id) 
* 
*/ 
//@Test
//public void testRead() throws Exception {
//    Employee employee = employeeDAO.read(1);
//    assertTrue("test".equals(employee.getFirstName()));
//    assertTrue("test".equals(employee.getLastName()));
//    assertTrue("test".equals(employee.getTitle()));
//    assertTrue(1 == employee.getDepartmentId());
//    assertTrue(1 == employee.getId());
//}

/** 
* 
* Method: update(Employee employee) 
* 
*/ 
@Test
public void testUpdate() throws Exception {
    Employee employee = new Employee();
    employee.setTitle("test");
    employee.setFirstName("test");
    employee.setLastName("test");
    employee.setDepartmentId(1);
    employee.setId(1);
    assertTrue(1==employeeDAO.update(employee));
} 

/** 
* 
* Method: delete(Employee employee) 
* 
*/ 
@Test
public void testDelete() throws Exception {
    Employee employee = new Employee();
    employee.setTitle("test");
    employee.setFirstName("test");
    employee.setLastName("test");
    employee.setDepartmentId(1);
    employee.setId(1);
    employee.setInDatabase(true);
    assertTrue(1==employeeDAO.delete(employee));
} 


} 
