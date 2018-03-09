package com.TRMS.DAOs;

import com.Beans.Department;
import com.DAOs.DepartmentDAO;
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
* DepartmentDAO Tester. 
* 
* @author <Authors name> 
* @since <pre>Feb 27, 2018</pre> 
* @version 1.0 
*/
@RunWith(MockitoJUnitRunner.class)
public class DepartmentDAOTest {

    @Mock
    ConnectionUtil connectionUtil;

    @Mock
    Connection connection;

    @Mock
    PreparedStatement ps;

    @Mock
    ResultSet resultSet;

    DepartmentDAO departmentDAO;

@Before
public void before() throws Exception {
    when(ConnectionUtil.getConnectionUtil()).thenReturn(connectionUtil);
    when(ConnectionUtil.getConnectionUtil().getConnection()).thenReturn(connection);
    when(connection.prepareStatement(anyString())).thenReturn(ps);
    when(ps.executeQuery()).thenReturn(resultSet);
    when(ps.executeUpdate()).thenReturn(1);
    when(resultSet.next()).thenReturn(true).thenReturn(false);
    when(resultSet.getInt(anyString())).thenReturn(1);
    when(resultSet.getString(anyString())).thenReturn("test");
    departmentDAO = new DepartmentDAO(connectionUtil);
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: create(Department department) 
* 
*/ 
@Test
public void testCreate() throws Exception {
    Department department = new Department();
    department.setName("test");
    department.setHeadId(1);
    department.setId(-1);
    departmentDAO.create(department);
    assertTrue(1==department.getId());
} 

/** 
* 
* Method: read(int id) 
* 
*/ 
//@Test
//public void testRead() throws Exception {
//    Department department = departmentDAO.read(1);
//    assertTrue("test".equals(department.getName()));
//    assertTrue(1 == department.getHeadId());
//    assertTrue(1 == department.getId());
//}

/** 
* 
* Method: update(Department department) 
* 
*/ 
@Test
public void testUpdate() throws Exception {
    Department department = new Department();
    department.setName("test");
    department.setHeadId(1);
    department.setId(1);
    assertTrue(1==departmentDAO.update(department));
} 

/** 
* 
* Method: delete(Department department) 
* 
*/ 
@Test
public void testDelete() throws Exception {
    Department department = new Department();
    department.setName("test");
    department.setHeadId(1);
    department.setId(1);
    department.setInDatabase(true);
    assertTrue(1==departmentDAO.delete(department));
} 


} 
