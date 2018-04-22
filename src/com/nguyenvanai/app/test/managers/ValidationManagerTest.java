package com.nguyenvanai.app.test.managers;

import static org.junit.Assert.*;

import org.junit.Test;

import com.nguyenvanai.app.managers.ValidationManager;

public class ValidationManagerTest {
	
	@Test
	public void testValidateEmail() {
		boolean expectedResult = true;
		boolean result = ValidationManager.validateEmail("kaka@gmail.com");
		assertEquals(expectedResult,result);
		
		result = ValidationManager.validateEmail("kaka@yahoo.com");
		assertEquals(expectedResult,result);
		
		expectedResult = false;
		result = ValidationManager.validateEmail("@gmail.com");
		assertEquals(expectedResult,result);
		
		result = ValidationManager.validateEmail("asss.com");
		assertEquals(expectedResult,result);
	}
	
	
	@Test
	public void testValidatePhone() {
		boolean expectedResult = true;
		boolean result = ValidationManager.validatePhone("0123456789");
		assertEquals(expectedResult,result);
		
		result = ValidationManager.validatePhone("0962134562");
		assertEquals(expectedResult,result);
		
		expectedResult = false;
		result = ValidationManager.validatePhone("1213");
		assertEquals(expectedResult,result);
		
		result = ValidationManager.validatePhone("123abc");
		assertEquals(expectedResult,result);
	}
	
}
