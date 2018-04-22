package com.nguyenvanai.app.test.managers;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.nguyenvanai.app.managers.CourseManager;
import com.nguyenvanai.app.models.Course;

public class CourseManagerTest {

	CourseManager manager = CourseManager.getInstance();

	String cId = "C01";
	String cName = "Java Course";
	String lecturerId = "L01";

	// setup data
	@Before
	public void setup() {
		String id = cId;
		manager.add(new Course(id, cName,lecturerId));
		id = "C02";
		manager.add(new Course(id, cName,lecturerId));
		id = "C03";
		manager.add(new Course(id, cName,lecturerId));
	}

	// test add a course
	@Test
	public void testAddCourse() {
		String id = cId;
		boolean expectedResult = false;
		boolean result = manager.add(new Course(id, cName,lecturerId));
		assertEquals(expectedResult, result);

		id = "C0001";
		expectedResult = false;
		result = manager.add(new Course(id, cName,lecturerId));
		assertEquals(expectedResult, result);
	}

	// test get json file Name
	@Test
	public void testGetFileName() {
		String expectedResult = "courses.json";
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
