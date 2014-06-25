package com.MyOVS.listener;

import org.testng.Assert;
import org.testng.annotations.Test;

public class VerifyTest extends ErrorUtil {

	@Test
	public void test1() {
		//verifyTrue(false);
		//verifyEquals("pass", "fail");
		//verifyFalse(true);
		try{
			Assert.assertEquals(true, false);
		}catch(Throwable e){
			ErrorUtil.addVerificationFailure(e);
		}
		
	}
	
	@Test
	public void test2() {
		verifyTrue(false);
		assertEquals("pass", "pass");
		verifyFalse(true);
		System.out.println("test 2 code");
	}
	
	@Test
	public void test3() {
		verifyTrue(true);
		verifyTrue(false);
		verifyTrue(true);
	}
	
	@Test
	public void test4() {
		assertTrue(true);
		assertTrue(false);
		assertTrue(true);
	}
	
}
