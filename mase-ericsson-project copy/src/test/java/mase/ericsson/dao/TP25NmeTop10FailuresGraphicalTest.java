package mase.ericsson.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TP25NmeTop10FailuresGraphicalTest {

	private EntityManagerFactory emf;
	private EntityManager em;
	private TP25_NME_Graphical_Top10QueryDAO dao;
	private List<Object[]> top10ResultsGraphical;

	@BeforeEach
	public void initTestCase() {
		emf = Persistence.createEntityManagerFactory("networkEventsDB");
		em = emf.createEntityManager();

		dao = new TP25_NME_Graphical_Top10QueryDAO ();
		dao.em = em;
	}

	@AfterEach
	public void closeEntityManager() {
		em.close();
		emf.close();
	}
	

//	@Test
//	public void testTop10FailuresGraphical () {
// 	List<Object[]> failureList;
//	failureList = dao.getTop10MarketOperatorCellId();
//	assertEquals(9, failureList.size()); 
//	}

}
