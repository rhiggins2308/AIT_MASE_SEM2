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

class TP_27_Top_10_IMSI_DAOMockTest {
	
	 private static final String TEST_DB_INSTANCE = "test-db";

	  @Mock
	  private EntityManager mockedEntityManager;
	  @Mock
	  private Query mockedQuery;

	  @InjectMocks 
	  private  TP_27_Top_10_IMSI_DAO top10IMSIDao;

	  @BeforeEach
	  public void initMocks() {
	      MockitoAnnotations.initMocks(this);
	      top10IMSIDao.setEntityManager(mockedEntityManager);
//	      
	      // Old init
	      top10IMSIDao = new TP_27_Top_10_IMSI_DAO();
	      top10IMSIDao.em = mockedEntityManager;
	  }
	//  
	  @AfterEach
	  public void closeEntityManager() {
	      mockedEntityManager.close();
	      mockedEntityManager.close();
	  }
	  
	  @Test
	    public void testTP_27_getTop10ListOfIMSIForDateRange() {
	        // given
	        Date fromDate = new Date(2020, 2, 17);
	        Date toDate = new Date(2021, 2, 17);
	        
	        // when
	        List<Object[]> resultData = new ArrayList<>();
	        // Mock the return value
	        Mockito.when(mockedEntityManager.createQuery(Matchers.anyString())).thenReturn(mockedQuery);
	        Mockito.when(mockedQuery.getResultList()).thenReturn(resultData );
	        List<Object[]> actualResult = top10IMSIDao.TP_27_Top_10_imsiCountforNtwEngineer(fromDate, toDate);

	        // then
	        assertThat(actualResult.size(), is(0));

	

}
}
