package com.nguyenvanai.app.test.managers;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.nguyenvanai.app.managers.MarkManager;
import com.nguyenvanai.app.models.Mark;

public class MarkManagerTest {

	MarkManager manager = MarkManager.getInstance();

	

	// setup data
	@Before
	public void setup() {
		manager.clear();
		manager.add(new Mark("M01", "distinction", "E01", "GC00702"));
		manager.add(new Mark("M02", "merit", "E02", "GC00702"));
		manager.add(new Mark("M03", "distinction", "E03", "GC00702"));
	}

	// test add a mark
	@Test
	public void testAddMark() {
		boolean expectedResult = true;
		boolean result = manager.add(new Mark("M04", "distinction", "E01", "GC00702"));
		assertEquals(expectedResult, result);

	
		expectedResult = false;
		result = manager.add(new Mark("M04", "distinction", "E01", "GC00702"));
		assertEquals(expectedResult, result);
	}
	
	@Test
	public void testUpdateMark() {
		boolean expectedResult = true;
		boolean result = manager.update(new Mark("M01", "merit", "E01", "GC00702"));
		assertEquals(expectedResult, result);

	
		expectedResult = false;
		result = manager.update(new Mark("M05", "distinction", "E01", "GC00702"));
		assertEquals(expectedResult, result);
	}
	
	@Test
	public void testDeleteMark(){
		boolean expectedResult = true;
		boolean result = manager.delete("M02");
		assertEquals(expectedResult, result);

	
		expectedResult = false;
		result = manager.delete("M05");
		assertEquals(expectedResult, result);
	}
	
	@Test
	public void testSearchByName(){
		boolean expectedResult = true;
		boolean result = manager.searchByName("dis").size() == 2;
		assertEquals(expectedResult, result);

	
		expectedResult = false;
		result = manager.searchByName("high").size() > 0;
		assertEquals(expectedResult, result);
	}

	// test get json file Name
	@Test
	public void testGetFileName() {
		String expectedResult = "marks.json";
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
