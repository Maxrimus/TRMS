package com.TRMS.Beans;

import com.Beans.Reimbursement;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* Reimbursement Tester. 
* 
* @author <Authors name> 
* @since <pre>Feb 26, 2018</pre> 
* @version 1.0 
*/ 
public class ReimbursementTest { 

    Reimbursement reimbursement;

@Before
public void before() throws Exception {
    reimbursement = new Reimbursement();
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
    reimbursement.setId(expected);
    int actual = reimbursement.getId();
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
    reimbursement.setId(expected);
    int actual = reimbursement.getId();
    Assert.assertEquals(expected,actual);
} 

/** 
* 
* Method: getAmountAwarded() 
* 
*/ 
@Test
public void testGetAmountAwarded() throws Exception {
    double expected = 1.0;
    reimbursement.setAmountAwarded(expected);
    double actual = reimbursement.getAmountAwarded();
    Assert.assertEquals(expected,actual,0.0);
} 

/** 
* 
* Method: setAmountAwarded(double amountAwarded) 
* 
*/ 
@Test
public void testSetAmountAwarded() throws Exception {
    double expected = 1.0;
    reimbursement.setAmountAwarded(expected);
    double actual = reimbursement.getAmountAwarded();
    Assert.assertEquals(expected,actual,0.0);
} 

/** 
* 
* Method: getAmountPending() 
* 
*/ 
@Test
public void testGetAmountPending() throws Exception {
    double expected = 1.0;
    reimbursement.setAmountPending(expected);
    double actual = reimbursement.getAmountPending();
    Assert.assertEquals(expected,actual,0.0);
} 

/** 
* 
* Method: setAmountPending(double amountPending) 
* 
*/ 
@Test
public void testSetAmountPending() throws Exception {
    double expected = 1.0;
    reimbursement.setAmountPending(expected);
    double actual = reimbursement.getAmountPending();
    Assert.assertEquals(expected,actual,0.0);
} 


} 
