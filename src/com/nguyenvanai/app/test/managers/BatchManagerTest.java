package com.nguyenvanai.app.test.managers;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.nguyenvanai.app.managers.BatchManager;
import com.nguyenvanai.app.models.Batch;

public class BatchManagerTest {

	BatchManager manager = BatchManager.getInstance();

	// setup data
	@Before
	public void setup() {
		manager.clear();
		manager.add(new Batch("B01", "TH1032"));
		manager.add(new Batch("B02", "TH1052"));
		manager.add(new Batch("B03", "TH1055"));
	}

	// test add a batch
	@Test
	public void testAddBatch() {
		boolean expectedResult = true;
		boolean result = manager.add(new Batch("B04", "TH1034"));
		assertEquals(expectedResult, result);

		expectedResult = false;
		result = manager.add(new Batch("B01", "TH1032"));
		assertEquals(expectedResult, result);
	}

	// test get json file Name
	@Test
	public void testGetFileName() {
		String expectedResult = "batches.json";
		String result = manager.getFileName();
		assertEquals(expectedResult, result);
	}

	@Test
	public void testUpdateBatch() {
		boolean expectedResult = true;
		boolean result = manager.update(new Batch("B01", "TH1234"));
		assertEquals(expectedResult, result);

		expectedResult = false;
		result = manager.update(new Batch("B05", "TH1234"));
		assertEquals(expectedResult, result);
	}

	@Test
	public void testDeleteBatch() {
		boolean expectedResult = true;
		boolean result = manager.delete("B02");
		assertEquals(expectedResult, result);

		expectedResult = false;
		result = manager.delete("B05");
		assertEquals(expectedResult, result);
	}

	@Test
	public void testSearchByName() {
		boolean expectedResult = true;
		boolean result = manager.searchByName("TH").size() > 1;
		assertEquals(expectedResult, result);

		expectedResult = false;
		result = manager.searchByName("high").size() > 0;
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
