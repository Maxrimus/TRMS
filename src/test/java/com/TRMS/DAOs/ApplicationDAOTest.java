package com.TRMS.DAOs;

import com.Beans.Application;
import com.DAOs.ApplicationDAO;
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
* ApplicationDAO Tester. 
* 
* @author <Authors name> 
* @since <pre>Feb 27, 2018</pre> 
* @version 1.0 
*/
@RunWith(MockitoJUnitRunner.class)
public class ApplicationDAOTest {

    @Mock
    ConnectionUtil connectionUtil;

    @Mock
    Connection connection;

    @Mock
    PreparedStatement ps;

    @Mock
    ResultSet resultSet;

    ApplicationDAO applicationDAO;

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
    applicationDAO = new ApplicationDAO(connectionUtil);
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: create(Application application) 
* 
*/ 
@Test
public void testCreate() throws Exception {
    Application application = new Application();
    application.setJustification("test");
    application.setEmployeeId(1);
    application.setEventId(1);
    application.setGradingFormat(1);
    application.setId(-1);
    applicationDAO.create(application);
    assertTrue(1==application.getId());
} 

/** 
* 
* Method: read(int id) 
* 
*/ 
//@Test
//public void testRead() throws Exception {
//    Application application = applicationDAO.read(1);
//    assertTrue("test".equals(application.getJustification()));
//    assertTrue(1 == application.getEmployeeId());
//    assertTrue(1 == application.getId());
//    assertTrue(1 == application.getEventId());
//    assertTrue(1==application.getGradingFormat());
//}

/** 
* 
* Method: update(Application application) 
* 
*/ 
@Test
public void testUpdate() throws Exception {
    Application application = new Application();
    application.setJustification("test");
    application.setEmployeeId(1);
    application.setEventId(1);
    application.setGradingFormat(2);
    application.setId(1);
    assertTrue(1==applicationDAO.update(application));
} 

/** 
* 
* Method: delete(Application application) 
* 
*/ 
@Test
public void testDelete() throws Exception {
    Application application = new Application();
    application.setJustification("test");
    application.setEmployeeId(1);
    application.setEventId(1);
    application.setGradingFormat(2);
    application.setId(1);
    application.setInDatabase(true);
    assertTrue(1==applicationDAO.delete(application));
} 


} 
