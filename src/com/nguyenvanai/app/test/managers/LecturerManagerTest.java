package com.nguyenvanai.app.test.managers;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.nguyenvanai.app.managers.LecturerManager;
import com.nguyenvanai.app.models.Lecturer;

public class LecturerManagerTest {

	LecturerManager manager = LecturerManager.getInstance();
	
	String lId = "C01";
	String lName = "Java Lecturer";
	String lEmail = "duong@fpt.edu.vn";
	String lPhone = "1234567890";

	// setup data
	@Before
	public void setup() {
		String id = lId;
		manager.add(new Lecturer(id, lName,lEmail,lPhone));
		id = "L02";
		manager.add(new Lecturer(id, lName,lEmail,lPhone));
		id = "L03";
		manager.add(new Lecturer(id, lName,lEmail,lPhone));
	}

	// test add a lecturer
	@Test
	public void testAddLecturer() {
		String id = lId;
		boolean expectedResult = false;
		boolean result = manager.add(new Lecturer(id, lName,lEmail,lPhone));
		assertEquals(expectedResult, result);

		id = "L0001";
		expectedResult = false;
		result = manager.add(new Lecturer(id, lName,lEmail,lPhone));
		assertEquals(expectedResult, result);
	}

	// test get json file Name
	@Test
	public void testGetFileName() {
		String expectedResult = "lecturers.json";
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
}
