package mase.ericsson.dao;

import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

//import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import mase.ericsson.commontests.entity.EntitiesForTestsNetworkEventsDao;
import mase.ericsson.entities.BaseData;
import mase.ericsson.entities.EventCause;
import mase.ericsson.entities.EventCauseID;
import mase.ericsson.entities.FailureClass;
import mase.ericsson.entities.MccMnc;
import mase.ericsson.entities.MccMncID;
import mase.ericsson.entities.UserEquipment;

public class DataValidatorTest {

	private EntityManagerFactory emf;
	private EntityManager em;
	private NetworkEventsDAO neDao;
	
	private DataValidator dataVal;
	private List<Object> rawDataRecord;
	private List<Object> validatedDataRecord;
	
	private List<Integer> validFailureClassList; // = neDao.getValidFailureClassList();
	private List<Integer> validTacList; // = neDao.getValidTacList();
	private List<EventCauseID> validEventCauseIdList; // = neDao.getValidEventCauseId();
	private List<MccMncID> validMccMncIdList; // = neDao.getValidMccMncId();
	
	@Before
	public void initTestCase() {
		emf = Persistence.createEntityManagerFactory("networkEventsDB");
		em = emf.createEntityManager();
		neDao = new NetworkEventsDAO();
		neDao.em = em;
		dataVal = new DataValidator();
		rawDataRecord = new ArrayList<>();
		validFailureClassList = new ArrayList<>();
		validTacList = new ArrayList<>();
		validEventCauseIdList = new ArrayList<>();
		validMccMncIdList = new ArrayList<>();
	}	
	
	@Test
	public void testValidBaseDataRecord() {
		BaseData validBaseDataRecord = EntitiesForTestsNetworkEventsDao.baseData();
		rawDataRecord.add(validBaseDataRecord);
		assertEquals(1, rawDataRecord.size());
		validFailureClassList.add(EntitiesForTestsNetworkEventsDao.failureClass().getFailureClass());
		validTacList.add(EntitiesForTestsNetworkEventsDao.userEquipment().getTac());
		validEventCauseIdList.add(EntitiesForTestsNetworkEventsDao.eventCauseID());
		validMccMncIdList.add(EntitiesForTestsNetworkEventsDao.mccMncID());
		
		validatedDataRecord = dataVal.validateBaseData(rawDataRecord);
		assertEquals(1, validatedDataRecord.size());	
	}
	
	@Test
	public void testInvalidBaseDataRecordNullValues() {
		BaseData invalidBaseDataRecord = EntitiesForTestsNetworkEventsDao.invalidBaseData();
		rawDataRecord.add(invalidBaseDataRecord);
		assertEquals(1, rawDataRecord.size());
		validFailureClassList.add(EntitiesForTestsNetworkEventsDao.failureClass().getFailureClass());
		validTacList.add(EntitiesForTestsNetworkEventsDao.userEquipment().getTac());
		validEventCauseIdList.add(EntitiesForTestsNetworkEventsDao.eventCauseID());
		validMccMncIdList.add(EntitiesForTestsNetworkEventsDao.mccMncID());
		
		validatedDataRecord = dataVal.validateBaseData(rawDataRecord);
		assertTrue(validatedDataRecord.isEmpty());	
	}
	
	@Test
	public void testInvalidBaseDataRecordNoFailureClassMatch() {
		
	}
	
	@Test
	public void testInvalidBaseDataRecordNoUeTypeMatch() {
		
	}
	
	@Test
	public void testInvalidBaseDataRecordNoEventCauseIdMatch() {
		
	}
	
	@Test
	public void testInvalidBaseDataRecordNoMccMncIdMatch() {
		
	}
	
	@Test 
	public void testValidEventCauseRecord() {
		em.getTransaction().begin();
	    Query query = em.createQuery("DELETE FROM EventCause where causeCode=1 AND eventId=1");
	    int i = query.executeUpdate();
	    em.getTransaction().commit();
	    
		EventCause validEventCauseRecord = EntitiesForTestsNetworkEventsDao.eventCause();
		rawDataRecord.add(validEventCauseRecord);
		assertEquals(1, rawDataRecord.size());
		validFailureClassList.add(EntitiesForTestsNetworkEventsDao.failureClass().getFailureClass());
		validTacList.add(EntitiesForTestsNetworkEventsDao.userEquipment().getTac());
		validEventCauseIdList.add(EntitiesForTestsNetworkEventsDao.eventCauseID());
		validMccMncIdList.add(EntitiesForTestsNetworkEventsDao.mccMncID());
		
		validatedDataRecord = dataVal.validateReferenceData(rawDataRecord);
		assertEquals(1, validatedDataRecord.size());		
	}
	
	@Test
	public void testInvalidEventCauseRecordNullValues() {
		EventCause invalidEventCauseRecord = EntitiesForTestsNetworkEventsDao.invalidEventCause();
		rawDataRecord.add(invalidEventCauseRecord);
		assertEquals(1, rawDataRecord.size());
		validFailureClassList.add(EntitiesForTestsNetworkEventsDao.failureClass().getFailureClass());
		validTacList.add(EntitiesForTestsNetworkEventsDao.userEquipment().getTac());
		validEventCauseIdList.add(EntitiesForTestsNetworkEventsDao.eventCauseID());
		validMccMncIdList.add(EntitiesForTestsNetworkEventsDao.mccMncID());
		
		validatedDataRecord = dataVal.validateReferenceData(rawDataRecord);
		assertTrue(validatedDataRecord.isEmpty());	
	}
	
	@Test
	public void testInvalidEventCauseDuplicate() {
		
	}
	
	@Test 
	public void testValidFailureClassRecord() {
		em.getTransaction().begin();
	    Query query = em.createQuery("DELETE FROM FailureClass where failureClass=6");
	    int i = query.executeUpdate();
	    em.getTransaction().commit();
	    
		FailureClass validFailureClassRecord = EntitiesForTestsNetworkEventsDao.failureClass();
		rawDataRecord.add(validFailureClassRecord);
		assertEquals(1, rawDataRecord.size());
		validFailureClassList.add(EntitiesForTestsNetworkEventsDao.failureClass().getFailureClass());
		validTacList.add(EntitiesForTestsNetworkEventsDao.userEquipment().getTac());
		validEventCauseIdList.add(EntitiesForTestsNetworkEventsDao.eventCauseID());
		validMccMncIdList.add(EntitiesForTestsNetworkEventsDao.mccMncID());
		
		validatedDataRecord = dataVal.validateReferenceData(rawDataRecord);
		assertEquals(1, validatedDataRecord.size());
	}
	
	@Test
	public void testInvalidFailureClassRecordNullValues() {
		FailureClass invalidFailureClassRecord = EntitiesForTestsNetworkEventsDao.invalidFailureClass();
		rawDataRecord.add(invalidFailureClassRecord);
		assertEquals(1, rawDataRecord.size());
		validFailureClassList.add(EntitiesForTestsNetworkEventsDao.failureClass().getFailureClass());
		validTacList.add(EntitiesForTestsNetworkEventsDao.userEquipment().getTac());
		validEventCauseIdList.add(EntitiesForTestsNetworkEventsDao.eventCauseID());
		validMccMncIdList.add(EntitiesForTestsNetworkEventsDao.mccMncID());
		
		validatedDataRecord = dataVal.validateReferenceData(rawDataRecord);
		assertTrue(validatedDataRecord.isEmpty());	
	}
	
	@Test
	public void testInvalidFailureClassDuplicate() {
		
	}
	
	@Test public void testValidMccMncRecord() {
		em.getTransaction().begin();
	    Query query = em.createQuery("DELETE FROM MccMnc where mcc=1 AND mnc=1");
	    int i = query.executeUpdate();
	    em.getTransaction().commit();
	    
		MccMnc validMccMncRecord = EntitiesForTestsNetworkEventsDao.mccMnc();
		rawDataRecord.add(validMccMncRecord);
		assertEquals(1, rawDataRecord.size());
		validFailureClassList.add(EntitiesForTestsNetworkEventsDao.failureClass().getFailureClass());
		validTacList.add(EntitiesForTestsNetworkEventsDao.userEquipment().getTac());
		validEventCauseIdList.add(EntitiesForTestsNetworkEventsDao.eventCauseID());
		validMccMncIdList.add(EntitiesForTestsNetworkEventsDao.mccMncID());
		
		validatedDataRecord = dataVal.validateReferenceData(rawDataRecord);
		assertEquals(1, validatedDataRecord.size());
	}
	
	@Test
	public void testInvalidMccMncRecordNullValues() {
		MccMnc invalidMccMncRecord = EntitiesForTestsNetworkEventsDao.invalidMccMnc();
		rawDataRecord.add(invalidMccMncRecord);
		assertEquals(1, rawDataRecord.size());
		validFailureClassList.add(EntitiesForTestsNetworkEventsDao.failureClass().getFailureClass());
		validTacList.add(EntitiesForTestsNetworkEventsDao.userEquipment().getTac());
		validEventCauseIdList.add(EntitiesForTestsNetworkEventsDao.eventCauseID());
		validMccMncIdList.add(EntitiesForTestsNetworkEventsDao.mccMncID());
		
		validatedDataRecord = dataVal.validateReferenceData(rawDataRecord);
		assertTrue(validatedDataRecord.isEmpty());	
	}
	
	@Test
	public void testInvalidMccMncDuplicate() {
		
	}
	
	@Test public void testValidUserEquipmentRecord() {
		em.getTransaction().begin();
	    Query query = em.createQuery("DELETE FROM UserEquipment where tac=1");
	    int i = query.executeUpdate();
	    em.getTransaction().commit();
	    
		UserEquipment validUERecord = EntitiesForTestsNetworkEventsDao.userEquipment();
		rawDataRecord.add(validUERecord);
		assertEquals(1, rawDataRecord.size());
		validFailureClassList.add(EntitiesForTestsNetworkEventsDao.failureClass().getFailureClass());
		validTacList.add(EntitiesForTestsNetworkEventsDao.userEquipment().getTac());
		validEventCauseIdList.add(EntitiesForTestsNetworkEventsDao.eventCauseID());
		validMccMncIdList.add(EntitiesForTestsNetworkEventsDao.mccMncID());
		
		validatedDataRecord = dataVal.validateReferenceData(rawDataRecord);
		assertEquals(1, validatedDataRecord.size());
	}
	
	@Test
	public void testInvalidUserEquipmentNullValues() {
		UserEquipment invalidUserEquipmentRecord = EntitiesForTestsNetworkEventsDao.invalidUserEquipment();
		rawDataRecord.add(invalidUserEquipmentRecord);
		assertEquals(1, rawDataRecord.size());
		validFailureClassList.add(EntitiesForTestsNetworkEventsDao.failureClass().getFailureClass());
		validTacList.add(EntitiesForTestsNetworkEventsDao.userEquipment().getTac());
		validEventCauseIdList.add(EntitiesForTestsNetworkEventsDao.eventCauseID());
		validMccMncIdList.add(EntitiesForTestsNetworkEventsDao.mccMncID());
		
		validatedDataRecord = dataVal.validateReferenceData(rawDataRecord);
		assertTrue(validatedDataRecord.isEmpty());	
	}
		
	@Test
	public void testInvalidUserEquipmentDuplicate() {
		
	}
}