package mase.ericsson.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import mase.ericsson.entities.BaseData;

@Stateless
@Local
public class TP24_NME_DateRange_Top10MarketOperatorCellId_DAO {

	private static final String DB_INSTANCE = "networkEventsDB";

    @PersistenceContext
    EntityManagerFactory emf;
    EntityManager em;

    public void initEntityManager() {
        emf = Persistence.createEntityManagerFactory(DB_INSTANCE);
        em = emf.createEntityManager();
    }
    
    public List<Object[]> getTop10MarketOperatorCellId(Date fromDate, Date toDate) {
		initEntityManager();

		Query top10Query = em.createQuery("SELECT DISTINCT m.country, m.operator, b.cellId, COUNT(b.imsi) FROM MccMnc m, BaseData b "
				+ "WHERE b.market = m.mcc "
				+ "AND b.operator = m.mnc "
				+ "AND b.eventDate BETWEEN ?1 AND ?2 "
				+ "GROUP BY m.country, m.operator, b.cellId "
				+ "ORDER BY COUNT(b.imsi) DESC").setMaxResults(10);
		
		top10Query.setParameter(1, fromDate);
		top10Query.setParameter(2, toDate);	

		return top10Query.getResultList();
	}
}