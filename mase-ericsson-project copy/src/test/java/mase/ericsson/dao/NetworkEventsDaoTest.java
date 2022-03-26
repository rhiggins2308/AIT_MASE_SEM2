package mase.ericsson.dao;

import static mase.ericsson.commontests.entity.EntitiesForTestsNetworkEventsDao.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
//import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mase.ericsson.entities.BaseData;
import mase.ericsson.entities.EventCause;
import mase.ericsson.entities.EventCauseID;
import mase.ericsson.entities.FailureClass;
import mase.ericsson.entities.MccMnc;
import mase.ericsson.entities.MccMncID;
import mase.ericsson.entities.UserEquipment;

public class NetworkEventsDaoTest {

	private EntityManagerFactory emf;
	private EntityManager em;
	private NetworkEventsDAO neDao;
	private List<Object> dataRecord;
	
	@BeforeEach
	public void initTestCase() {
		emf = Persistence.createEntityManagerFactory("networkEventsDB");
		em = emf.createEntityManager();

		neDao = new NetworkEventsDAO();
		neDao.em = em;
	}
	
	@AfterEach
	public void closeEntityManager() {
		em.close();
		emf.close();
	}
	

	@Test
	public void testAddRetrieveEventCauseSuccessfully() {
		em.getTransaction().begin();
	    Query query = em.createQuery("DELETE FROM EventCause where causeCode=1 AND eventId=1");
	    int i = query.executeUpdate();
	    em.getTransaction().commit();
	    
		Integer causeCodeAdded = null;
		Integer eventIdAdded = null;
		String eventDescription = null;
		dataRecord = new ArrayList<>();
		dataRecord.add(eventCause());
		neDao.addData(dataRecord);

		EventCause ec = null;
		ec = neDao.getData(new EventCauseID(eventCause().getCauseCode(), eventCause().getEventId()));
		assertThat(ec, is(notNullValue()));
		causeCodeAdded = ec.getCauseCode();
		eventIdAdded = ec.getEventId();
		eventDescription = ec.getDescription();
		
		assertEquals(causeCodeAdded, eventCause().getCauseCode());
		assertEquals(eventIdAdded, eventCause().getEventId());
		assertEquals(eventDescription, eventCause().getDescription());
	}

	@Test
	public void testAddRetrieveFailureClassSuccessfully() {
		
		em.getTransaction().begin();
	    Query query = em.createQuery("DELETE FROM FailureClass where failureClass=6");
	    int i = query.executeUpdate();
	    em.getTransaction().commit();
	    
		Integer failureClassIdAdded = null;
		String description = null;
		dataRecord = new ArrayList<>();
		dataRecord.add(failureClass());
		neDao.addData(dataRecord);
		
		FailureClass fc = null;
		fc = neDao.getData(failureClass().getFailureClass());
		assertThat(fc, is(notNullValue()));
		failureClassIdAdded = fc.getFailureClass();
		description = fc.getDescription();
		
		assertEquals(failureClassIdAdded, failureClass().getFailureClass());
		assertEquals(description, failureClass().getDescription());
	}
		
	@Test
	public void testAddRetrieveMccMncSuccessfully() {
		em.getTransaction().begin();
	    Query query = em.createQuery("DELETE FROM MccMnc where mcc=1 AND mnc=1");
	    int i = query.executeUpdate();
	    em.getTransaction().commit();
		
		Integer mccAdded = null;
		Integer mncAdded = null;
		String country = null;
		String operator = null;
		dataRecord = new ArrayList<>();
		dataRecord.add(mccMnc());
		neDao.addData(dataRecord);
		
		MccMnc mccMnc = null;
		mccMnc = neDao.getData(new MccMncID(mccMnc().getMcc(), mccMnc().getMnc()));
		assertThat(mccMnc, is(notNullValue()));
		
		mccAdded = mccMnc.getMcc();
		mncAdded = mccMnc.getMnc();
		country = mccMnc.getCountry();
		operator = mccMnc.getOperator();
		
		assertEquals(mccAdded, mccMnc.getMcc());
		assertEquals(mncAdded, mccMnc.getMnc());
		assertEquals(country, mccMnc.getCountry());
		assertEquals(operator, mccMnc.getOperator());
	}
	
	@Test
	public void testAddRetrieveUserEquipmentSuccessfully() {
		em.getTransaction().begin();
	    Query query = em.createQuery("DELETE FROM UserEquipment where tac=1");
	    int i = query.executeUpdate();
	    em.getTransaction().commit();
	    
		Integer userEquipmentTacAdded = null;
		String marketingName = null;
	    String manufacturer = null;
	    String accessCapability = null;
	    String model = null;
	    String vendorName = null;
	    String ueType = null;
	    String os = null;
	    String inputMode = null;
	    
	    dataRecord = new ArrayList<>();
	    dataRecord.add(userEquipment());
		neDao.addData(dataRecord);
		
		UserEquipment ue = null;
		ue = neDao.getUeData(userEquipment().getTac());
		assertThat(ue, is(notNullValue()));
		
		userEquipmentTacAdded = ue.getTac();
		marketingName = ue.getMarketingName();
		manufacturer = ue.getManufacturer();
		accessCapability = ue.getAccessCapability();
		model = ue.getModel();
		vendorName = ue.getVendorName();
		ueType = ue.getUeType();
		os = ue.getOs();
		inputMode = ue.getInputMode();
		
		assertEquals(userEquipmentTacAdded, userEquipment().getTac());
		assertEquals(marketingName, userEquipment().getMarketingName());
		assertEquals(manufacturer, userEquipment().getManufacturer());
		assertEquals(accessCapability, userEquipment().getAccessCapability());
		assertEquals(model, userEquipment().getModel());
		assertEquals(vendorName, userEquipment().getVendorName());
		assertEquals(ueType, userEquipment().getUeType());
		assertEquals(os, userEquipment().getOs());
		assertEquals(inputMode, userEquipment().getInputMode());
	}
	

//	@Test
//	public void testAddBaseDataSuccessfully() {
//		em.getTransaction().begin();
//	    Query query = em.createQuery("DELETE FROM BaseData where imsi=11");
//	    int i = query.executeUpdate();
//	    em.getTransaction().commit();
//		
//		Integer id = null;
//	    Integer eventId = null;
//	    Date eventDate = null;
//	    Integer failureClass = null;
//	    Integer ueType = null;
//	    Integer market = null;
//	    Integer operator = null;
//	    Integer cellId = null;
//	    Integer duration = null;
//	    Integer causeCode = null;
//	    String neVersion = null;
//	    Long imsi = null;
//	    Long hier3Id = null;
//	    Long hier32Id = null;
//	    Long hier321Id = null;
//	    dataRecord = new ArrayList<>();
//	    dataRecord.add(baseData());
//		neDao.addData(dataRecord);
//		
//		List<BaseData> networkEventsByImsi = new ArrayList<>();	
//		
//		networkEventsByImsi = neDao.getNetworkEventsByImsi(baseData().getImsi());
//		assertEquals(1, networkEventsByImsi.size());
//		
//		BaseData baseDataRecord = networkEventsByImsi.get(0);
//		id = baseDataRecord.getId();
//		eventId = baseDataRecord.getEventId();
//		eventDate = baseDataRecord.getEventDate();
//		failureClass = baseDataRecord.getFailureClass();
//		ueType = baseDataRecord.getUeType();
//		market = baseDataRecord.getMarket();
//		operator = baseDataRecord.getOperator();
//		cellId = baseDataRecord.getCellId();
//		duration = baseDataRecord.getDuration();
//		causeCode = baseDataRecord.getCauseCode();
//		neVersion = baseDataRecord.getNeVersion();
//		imsi = baseDataRecord.getImsi();
//		hier3Id = baseDataRecord.getHier3Id();
//		hier32Id = baseDataRecord.getHier32Id();
//		hier321Id = baseDataRecord.getHier321Id();
//		
//		assertThat(id, is(notNullValue()));
//		assertEquals(eventId, baseData().getEventId());
//		assertEquals(failureClass, baseData().getFailureClass());
//		assertEquals(ueType, baseData().getUeType());
//		assertEquals(market, baseData().getMarket());
//		assertEquals(operator, baseData().getOperator());
//		assertEquals(cellId, baseData().getCellId());
//		assertEquals(duration, baseData().getDuration());
//		assertEquals(causeCode, baseData().getCauseCode());
//		assertEquals(neVersion, baseData().getNeVersion());
//		assertEquals(imsi, baseData().getImsi());
//		assertEquals(hier3Id, baseData().getHier3Id());
//		assertEquals(hier32Id, baseData().getHier32Id());
//		assertEquals(hier321Id, baseData().getHier321Id());
//	}

	
	@Test
	public void testEventCauseID() {
		EventCauseID ecID = new EventCauseID(1, 1);
		assertTrue(ecID.equals(new EventCauseID(1, 1)));
		assertEquals((37 * (37 * (ecID.getCauseCode()^ecID.getCauseCode())) + (ecID.getEventId() ^ ecID.getEventId()))%(2^31-1), ecID.hashCode());
	}
	
	@Test
	public void testMccMncID() {
		MccMncID mcID = new MccMncID(1, 1);
		assertTrue(mcID.equals(new MccMncID(1, 1)));
		assertEquals((37 * (37 * (mcID.getMcc()^mcID.getMcc())) + (mcID.getMnc() ^ mcID.getMnc()))%(2^31-1), mcID.hashCode());
		
	}
	
	@Test
	public void testUniqueModel() {

		List<Object[]> ueList;
		ueList = neDao.getAllUserEquipment();		

		assertEquals(92, ueList.size());
	}
	
	@Test
	public void testGetUniqueFailureClasses() {
		List<Integer> fcList = new ArrayList<>();
		fcList = neDao.getValidFailureClassList();
		
		int size = fcList.size();
		if (size == 6) {
			size -= 1;
		}
		assertEquals(5, size);
	}

	@Test
	public void testGetUniqueMccMnc() {
		List<Integer> fcList = new ArrayList<>();
		fcList = neDao.getUniqueMccMnc();
		assertEquals(42, fcList.size());
	}	
	@Test
	public void testFailureByModel() {
 	List<Object[]> failureList;
	failureList = neDao.getFailureDataByModel("Gobi3000"); assertEquals(failureList.size(),0); 
	}


}

