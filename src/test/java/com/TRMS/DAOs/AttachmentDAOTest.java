package com.TRMS.DAOs;

import com.Beans.Attachment;
import com.DAOs.ApplicationDAO;
import com.DAOs.AttachmentDAO;
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
* AttachmentDAO Tester. 
* 
* @author <Authors name> 
* @since <pre>Feb 27, 2018</pre> 
* @version 1.0 
*/
@RunWith(MockitoJUnitRunner.class)
public class AttachmentDAOTest {

    @Mock
    ConnectionUtil connectionUtil;

    @Mock
    Connection connection;

    @Mock
    PreparedStatement ps;

    @Mock
    ResultSet resultSet;

    AttachmentDAO attachmentDAO;

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
    attachmentDAO = new AttachmentDAO(connectionUtil);
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: create(Attachment attachment) 
* 
*/ 
@Test
public void testCreate() throws Exception {
    Attachment attachment = new Attachment();
    attachment.setFilename("test");
    attachment.setApplicationId(1);
    attachment.setId(-1);
    attachmentDAO.create(attachment);
    assertTrue(1==attachment.getId());
} 

/** 
* 
* Method: read(int id) 
* 
*/ 
//@Test
//public void testRead() throws Exception {
//    Attachment attachment = attachmentDAO.read(1);
//    assertTrue("test".equals(attachment.getFilename()));
//    assertTrue(1 == attachment.getApplicationId());
//    assertTrue(1 == attachment.getId());
//}

/** 
* 
* Method: update(Attachment attachment) 
* 
*/ 
@Test
public void testUpdate() throws Exception {
    Attachment attachment = new Attachment();
    attachment.setFilename("test");
    attachment.setApplicationId(1);
    attachment.setId(1);
    assertTrue(1==attachmentDAO.update(attachment));
} 

/** 
* 
* Method: delete(Attachment attachment) 
* 
*/ 
@Test
public void testDelete() throws Exception {
    Attachment attachment = new Attachment();
    attachment.setFilename("test");
    attachment.setApplicationId(1);
    attachment.setId(1);
    attachment.setInDatabase(true);
    assertTrue(1==attachmentDAO.delete(attachment));
} 


} 
