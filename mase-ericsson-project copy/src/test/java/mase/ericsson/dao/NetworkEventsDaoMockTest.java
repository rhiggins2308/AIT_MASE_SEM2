package mase.ericsson.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import mase.ericsson.entities.BaseData;
import mase.ericsson.test.setup.BaseDataFactory;


class NetworkEventsDaoMockTest {

  private static final String TEST_DB_INSTANCE = "test-db";

  @Mock
  private EntityManager mockedEntityManager;
  @Mock
  private Query mockedQuery;

  @InjectMocks 
  private NetworkEventsDAO networkEventsDao;

  @BeforeEach
  public void initMocks() {
      MockitoAnnotations.initMocks(this);
      networkEventsDao.setEntityManager(mockedEntityManager);
//      
      // Old init
      networkEventsDao = new NetworkEventsDAO();
      networkEventsDao.em = mockedEntityManager;
  }
//  
  @AfterEach
  public void closeEntityManager() {
      mockedEntityManager.close();
      mockedEntityManager.close();
  }
//
  @Test
  public void testFailureByModel() {

	  List<Object[]> expectedResult= new ArrayList<>();
	  
	  Mockito.when(mockedEntityManager.createQuery(Matchers.anyString())).thenReturn(mockedQuery);
      Mockito.when(mockedQuery.getResultList()).thenReturn(expectedResult );

      List<Object[]> actualResult = new ArrayList<>();
	 	actualResult = networkEventsDao.getFailureDataByModel("Gobi3000"); 
	 	//assertEquals(actualResult, expectedResult ); 	
        assertThat(actualResult, is(expectedResult ));

    }
  
	@Test
	public void testUniqueModel() {

		List<Object[]> ueList = new ArrayList<>();
		
		List<Object[]> actualResult= new ArrayList<>();

		Mockito.when(mockedEntityManager.createQuery(Matchers.anyString())).thenReturn(mockedQuery);
	    Mockito.when(mockedQuery.getResultList()).thenReturn(ueList );
	
		networkEventsDao.getAllUserEquipment();		
		
		assertEquals(ueList.size(),0);

	}
	
	
	@Test
	public void testGetUniqueMccMnc() {
		List<Integer> fcList = new ArrayList<>();

		Mockito.when(mockedEntityManager.createQuery(Matchers.anyString())).thenReturn(mockedQuery);
	    Mockito.when(mockedQuery.getResultList()).thenReturn(fcList);
		
	    fcList = networkEventsDao.getUniqueMccMnc();
		assertEquals(0, fcList.size());
	}	
	
	/*    
    @Test
    public void testGetFailureDataByModelAndTimePeriod_Positive() {
        // given
        long ueType = 77777L;
        Date fromDate = new Date(2020, 2, 17);
        Date toDate = new Date(2021, 2, 17);
        // Expected result
        List<BaseData> expectedBaseData = new ArrayList<>();
        expectedBaseData.add(BaseDataFactory.createSampleBaseData());
        // Mock the return value
        Mockito.when(mockedEntityManager.createQuery(Matchers.anyString())).thenReturn(mockedQuery);
        Mockito.when(mockedQuery.getResultList()).thenReturn(expectedBaseData);

        // when
        List<BaseData> resultBaseData = networkEventsDao.getFailureDataByModelAndTimePeriod(ueType, fromDate, toDate);

        // then
        assertThat(resultBaseData, is(expectedBaseData));
    }
*/
    @Test
    public void testGetFailureDataByModelAndTimePeriod() {
        // given
        long ueType = 7777777;
        Date fromDate = new Date(2020, 2, 17);
        Date toDate = new Date(2021, 2, 17);
        // Mock the return value
        Mockito.when(mockedEntityManager.createQuery(Matchers.anyString())).thenThrow(IllegalStateException.class);

        // when
        List<BaseData> resultBaseData = networkEventsDao.getFailureDataByModelAndTimePeriod(ueType, fromDate, toDate);

        // then
        assertThat(resultBaseData.size(), is(0));
    }

    @Test
    public void testgetNetworkEventsByImsi() {
        // given
        long imsi = 123L;
        // Mock the return value

        // when
        List<Object[]> resultBaseData = new ArrayList<>();
        Mockito.when(mockedEntityManager.createQuery(Matchers.anyString())).thenReturn(mockedQuery);
        Mockito.when(mockedQuery.getResultList()).thenReturn(resultBaseData );

        List<Object[]> actualResult = networkEventsDao.getNetworkEventsByImsi(imsi);

        // then
        assertThat(actualResult.size(), is(0));
    }

    @Test
    public void testgetListOfIMSIForDateRange() {
        // given
        Date fromDate = new Date(2020, 2, 17);
        Date toDate = new Date(2021, 2, 17);
        
        // when
        List<Object[]> resultData = new ArrayList<>();
        // Mock the return value
        Mockito.when(mockedEntityManager.createQuery(Matchers.anyString())).thenReturn(mockedQuery);
        Mockito.when(mockedQuery.getResultList()).thenReturn(resultData );
        List<Object[]> actualResult = networkEventsDao.getListOfIMSIForDateRange(fromDate, toDate);

        // then
        assertThat(actualResult.size(), is(0));
    }

	  
}
