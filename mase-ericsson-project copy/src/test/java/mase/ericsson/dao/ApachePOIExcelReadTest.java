package mase.ericsson.dao;

import static org.junit.jupiter.api.Assertions.*;
//import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.CoreMatchers.notNullValue;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class ApachePOIExcelReadTest {

	private EntityManagerFactory emf;
	private EntityManager em;
	private ApachePOIExcelRead apachePoiExRead;	

	@BeforeEach
	public void initTestCase() {
		emf = Persistence.createEntityManagerFactory("networkEventsDB");
		em = emf.createEntityManager();

		apachePoiExRead = new ApachePOIExcelRead ();
	}
	
	@AfterEach
	public void closeEntityManager() {
		em.close();
		emf.close();
	}
	
	// file location needs correcting for location independence
	@Test
	public void testFileNameNotFound() {
		boolean result = false;
		result = apachePoiExRead.readExcel("C:/AITSample.xls");
		assertFalse(result);
	}
	
	@Test
	public void testFileNotAnExcel() {
		boolean result = false;
		result = apachePoiExRead.readExcel("C:/Failure.csv");
		assertFalse(result);
	}
	
	@Test
	public void testReadExcelSuccessfully() {
		boolean result = false;
		result = apachePoiExRead.readExcel("uploadData/AIT Group Project - Sample Dataset.xls");
		assertTrue(result);
	}
}
