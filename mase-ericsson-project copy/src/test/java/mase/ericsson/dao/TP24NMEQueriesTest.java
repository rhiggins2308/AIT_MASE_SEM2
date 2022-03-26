package mase.ericsson.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TP24NMEQueriesTest {

	private EntityManagerFactory emf;
	private EntityManager em;
	private TP24_NME_DateRange_Top10MarketOperatorCellId_DAO dao;
	private List<Object[]> top10Results;

	private SimpleDateFormat formatter;
	@BeforeEach
	public void initTestCase() {
		emf = Persistence.createEntityManagerFactory("networkEventsDB");
		em = emf.createEntityManager();

		dao = new TP24_NME_DateRange_Top10MarketOperatorCellId_DAO();
		dao.em = em;
		formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
	}

	@AfterEach
	public void closeEntityManager() {
		em.close();
		emf.close();
	}
	
	@Test
	public void testRetrieveTop10() throws ParseException {
		top10Results = dao.getTop10MarketOperatorCellId(formatter.parse("2020-02-21T09:00"), formatter.parse("2020-05-30T09:00"));		
		assertEquals(10, top10Results.size());
	}
}
