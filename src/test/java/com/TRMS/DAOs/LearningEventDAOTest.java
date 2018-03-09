package com.TRMS.DAOs;

import com.Beans.LearningEvent;
import com.DAOs.LearningEventDAO;
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
* LearningEventDAO Tester. 
* 
* @author <Authors name> 
* @since <pre>Feb 27, 2018</pre> 
* @version 1.0 
*/
@RunWith(MockitoJUnitRunner.class)
public class LearningEventDAOTest {

    @Mock
    ConnectionUtil connectionUtil;

    @Mock
    Connection connection;

    @Mock
    PreparedStatement ps;

    @Mock
    ResultSet resultSet;

    LearningEventDAO learningEventDAO;

    java.sql.Date curDate;
    java.sql.Time curTime;

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
    when(resultSet.getDouble(anyString())).thenReturn(1.0);
    curDate = new java.sql.Date(new java.util.Date().getTime());
    when(resultSet.getDate(anyString())).thenReturn(curDate);
    curTime = new java.sql.Time(1000);
    when(resultSet.getTime(anyString())).thenReturn(curTime);
    learningEventDAO = new LearningEventDAO(connectionUtil);
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: create(LearningEvent event) 
* 
*/ 
@Test
public void testCreate() throws Exception {
    LearningEvent learningEvent = new LearningEvent();
    learningEvent.setEventType("test");
    learningEvent.setEventLocation("test");
    learningEvent.setEventCost(1.0);
    learningEvent.setEventDescription("test");
    learningEvent.setEventDate(curDate);
    learningEvent.setEventTime(curTime);
    learningEvent.setId(-1);
    learningEventDAO.create(learningEvent);
    assertTrue(1==learningEvent.getId());
} 

/** 
* 
* Method: read(int id) 
* 
*/ 
//@Test
//public void testRead() throws Exception {
//    LearningEvent learningEvent = learningEventDAO.read(1);
//    assertTrue("test" == learningEvent.getEventType());
//    assertTrue("test" == learningEvent.getEventLocation());
//    assertTrue(1.0 == learningEvent.getEventCost());
//    assertTrue("test" == learningEvent.getEventDescription());
//    assertTrue(curDate.equals(learningEvent.getEventDate()));
//    assertTrue(curTime.equals(learningEvent.getEventTime()));
//    assertTrue(1==learningEvent.getId());
//}

/** 
* 
* Method: update(LearningEvent event) 
* 
*/ 
@Test
public void testUpdate() throws Exception {
    LearningEvent learningEvent = new LearningEvent();
    learningEvent.setEventType("test");
    learningEvent.setEventLocation("test");
    learningEvent.setEventCost(1.0);
    learningEvent.setEventDescription("test");
    learningEvent.setEventDate(curDate);
    learningEvent.setEventTime(curTime);
    learningEvent.setId(1);
    assertTrue(1==learningEventDAO.update(learningEvent));
} 

/** 
* 
* Method: delete(LearningEvent event) 
* 
*/ 
@Test
public void testDelete() throws Exception {
    LearningEvent learningEvent = new LearningEvent();
    learningEvent.setEventType("test");
    learningEvent.setEventLocation("test");
    learningEvent.setEventCost(1.0);
    learningEvent.setEventDescription("test");
    learningEvent.setEventDate(curDate);
    learningEvent.setEventTime(curTime);
    learningEvent.setId(1);
    learningEvent.setInDatabase(true);
    assertTrue(1==learningEventDAO.delete(learningEvent));
} 


} 
