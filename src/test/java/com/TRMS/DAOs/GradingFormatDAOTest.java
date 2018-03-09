package com.TRMS.DAOs;

import com.Beans.GradingFormat;
import com.DAOs.GradingFormatDAO;
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
* GradingFormatDAO Tester. 
* 
* @author <Authors name> 
* @since <pre>Feb 27, 2018</pre> 
* @version 1.0 
*/
@RunWith(MockitoJUnitRunner.class)
public class GradingFormatDAOTest {

    @Mock
    ConnectionUtil connectionUtil;

    @Mock
    Connection connection;

    @Mock
    PreparedStatement ps;

    @Mock
    ResultSet resultSet;

    GradingFormatDAO gradingFormatDAO;

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
    gradingFormatDAO = new GradingFormatDAO(connectionUtil);
}

    @After
public void after() throws Exception { 
} 

/** 
* 
* Method: create(GradingFormat gradingFormat) 
* 
*/ 
@Test
public void testCreate() throws Exception {
    GradingFormat gradingFormat = new GradingFormat();
    gradingFormat.setGradingFormat("test");
    gradingFormat.setId(-1);
    gradingFormatDAO.create(gradingFormat);
    assertTrue(1==gradingFormat.getId());
} 

/** 
* 
* Method: read(int id) 
* 
*/ 
//@Test
//public void testRead() throws Exception {
//    GradingFormat gradingFormat = gradingFormatDAO.read(1);
//    assertTrue("test".equals(gradingFormat.getGradingFormat()));
//    assertTrue(1 == gradingFormat.getId());
//}

/** 
* 
* Method: update(GradingFormat gradingFormat) 
* 
*/ 
@Test
public void testUpdate() throws Exception {
    GradingFormat gradingFormat = new GradingFormat();
    gradingFormat.setGradingFormat("test");
    gradingFormat.setId(1);
    assertTrue(1==gradingFormatDAO.update(gradingFormat));
} 

/** 
* 
* Method: delete(GradingFormat gradingFormat) 
* 
*/ 
@Test
public void testDelete() throws Exception {
    GradingFormat gradingFormat = new GradingFormat();
    gradingFormat.setGradingFormat("test");
    gradingFormat.setId(1);
    gradingFormat.setInDatabase(true);
    assertTrue(1==gradingFormatDAO.delete(gradingFormat));
} 


} 
