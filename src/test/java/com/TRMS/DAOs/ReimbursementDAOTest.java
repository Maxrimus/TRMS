package com.TRMS.DAOs;

import com.Beans.Reimbursement;
import com.DAOs.ReimbursementDAO;
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
* ReimbursementDAO Tester. 
* 
* @author <Authors name> 
* @since <pre>Feb 27, 2018</pre> 
* @version 1.0 
*/
@RunWith(MockitoJUnitRunner.class)
public class ReimbursementDAOTest {

    @Mock
    ConnectionUtil connectionUtil;

    @Mock
    Connection connection;

    @Mock
    PreparedStatement ps;

    @Mock
    ResultSet resultSet;

    ReimbursementDAO reimbursementDAO;

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
    reimbursementDAO = new ReimbursementDAO(connectionUtil);
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: create(Reimbursement reimbursement) 
* 
*/ 
@Test
public void testCreate() throws Exception {
    Reimbursement reimbursement = new Reimbursement();
    reimbursement.setAmountPending(1.0);
    reimbursement.setAmountAwarded(1.0);
    reimbursement.setId(-1);
    reimbursementDAO.create(reimbursement);
    assertTrue(1==reimbursement.getId());
} 

/** 
* 
* Method: read(int id) 
* 
*/ 
//@Test
//public void testRead() throws Exception {
//    Reimbursement reimbursement = reimbursementDAO.read(1);
//    reimbursement.setAmountAwarded(1.0);
//    reimbursement.setAmountPending(1.0);
//    assertTrue(1==reimbursement.getId());
//}

/** 
* 
* Method: update(Reimbursement reimbursement) 
* 
*/ 
@Test
public void testUpdate() throws Exception {
    Reimbursement reimbursement = new Reimbursement();
    reimbursement.setAmountPending(1.0);
    reimbursement.setAmountAwarded(1.0);
    reimbursement.setId(-1);
    reimbursement.setId(1);
    assertTrue(1==reimbursementDAO.update(reimbursement));
} 

/** 
* 
* Method: delete(Reimbursement reimbursement) 
* 
*/ 
@Test
public void testDelete() throws Exception {
    Reimbursement reimbursement = new Reimbursement();
    reimbursement.setAmountPending(1.0);
    reimbursement.setAmountAwarded(1.0);
    reimbursement.setId(-1);
    reimbursement.setId(1);
    reimbursement.setInDatabase(true);
    assertTrue(1==reimbursementDAO.delete(reimbursement));
} 


} 
