package com.nguyenvanai.app.test.managers;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.nguyenvanai.app.managers.StudentManager;
import com.nguyenvanai.app.models.Student;

public class StudentManagerTest {
	StudentManager manager = StudentManager.getInstance();



	// set up first
	@Before
	public void setup() {
		manager.clear();
		manager.add(new Student("GC00704", "terry", "terry@gmail.com", "0123456789", "B01", "C01"));
		manager.add(new Student("GC00423", "kaka", "kaka@gmail.com", "0123456789", "B02", "C02"));
		manager.add(new Student("GC00502", "tom", "tom@gmail.com", "0123456789", "B03", "C03"));
	}

	// test add a student
	@Test
	public void testAddStudent() {

		boolean expectedResult = true;
		boolean result = manager.add(new Student("GC00102", "messi", "messi@gmail.com", "0123456789", "B03", "C03"));
		assertEquals(expectedResult, result);

		expectedResult = false;
		result =manager.add(new Student("GC00502", "tom", "tom@gmail.com", "0123456789", "B03", "C03"));
		assertEquals(expectedResult, result);

	}

	@Test
	// test clear all students
	public void testClear() {

		boolean expectedResult = true;
		boolean result = manager.clear();
		assertEquals(expectedResult, result);
	}

	// test delete a student
	@Test
	public void testDelete() {

		boolean expectedResult = true;
		boolean result = manager.delete("GC00704");
		assertEquals(expectedResult, result);

		expectedResult = false;
		result = manager.delete("GC00705");
		assertEquals(expectedResult, result);
	}

	// test update a student
	@Test
	public void testUpdate() {
		boolean expectedResult = true;
		boolean result = manager.update(new Student("GC00502", "tony", "tony@gmail.com", "0123456789", "B03", "C03"));
		assertEquals(expectedResult, result);

		expectedResult = false;
		result =  manager.update(new Student("GC00509", "tony", "tony@gmail.com", "0123456789", "B03", "C03"));
		assertEquals(expectedResult, result);
	}

	// validate student's email
	@Test
	public void testValidateEmail() {
		boolean expectedResult = true;
		boolean result = manager.validateEmail("kaka@gmail.com");
		assertEquals(expectedResult, result);

		expectedResult = false;
		result = manager.validateEmail("asasa@sas");
		assertEquals(expectedResult, result);

		result = manager.validateEmail("@sas.");
		assertEquals(expectedResult, result);
	}

	// validate student's phone
	@Test
	public void testValidatePhone() {
		boolean expectedResult = true;
		boolean result = manager.validatePhone("0123456789");
		assertEquals(expectedResult, result);

		expectedResult = false;
		result = manager.validatePhone("1254-213");
		assertEquals(expectedResult, result);

		result = manager.validatePhone("1254");
		assertEquals(expectedResult, result);
	}

	// validate get json file name
	@Test
	public void testGetFileName() {
		String expectedResult = "students.json";
		String result = manager.getFileName();
		assertEquals(expectedResult, result);
	}


	// test load data
	@Test
	public void testLoadData() throws Exception {
		boolean expectedResult = true;
		boolean result = manager.loadData();
		assertEquals(expectedResult, result);
	}

	// test sort by ID
	@Test
	public void testSortByID() {
		String expectedResult = "GC00423";
		String result = manager.sortByID().get(0).getId();
		assertEquals(expectedResult, result);
	}

	// test sort by Name
	@Test
	public void testSortByName() {
		String expectedResult = "kaka";
		String result = manager.sortByName().get(0).getName();
		assertEquals(expectedResult, result);
	}
	
	
}
