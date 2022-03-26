package mase.ericsson.dao;

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

public class TP25_NME_Graphical_Top10QueryDAO {

	private static final String DB_INSTANCE = "networkEventsDB";

    @PersistenceContext
    EntityManagerFactory emf;
    EntityManager em;

    public void initEntityManager() {
        emf = Persistence.createEntityManagerFactory(DB_INSTANCE);
        em = emf.createEntityManager();
    }
    
    public List<Object[]> getTop10MarketOperatorCellId() {
		initEntityManager();

		Query top10Query = em.createQuery(
				"SELECT m.country, m.operator, b.cellId, count(b.id) AS failure" 
				+ " FROM BaseData b, MccMnc m"
				+ " WHERE b.market = m.mcc AND "
				+ " b.operator = m.mnc "
				+ "	GROUP BY b.market, b.operator, b.cellId "
				+ " ORDER BY failure DESC ").setMaxResults(10); 
	
		return top10Query.getResultList();
	}

    public List<Object[]> getTop10MarketOperatorCellIdDetails(String country, String operator, int cellId) {
		initEntityManager();

		Query top10Query = em.createQuery(
		"select ec.description as EventDetail, f.description as FailureDetail, count(b.id) as failureCount"
		+ " from BaseData b, FailureClass f, EventCause ec, MccMnc m"
		+ " where b.market = m.mcc "
		+ " and b.operator = m.mnc "
		+ " and m.country =  '" + country + "'"
		+ " and m.operator = '" + operator + "'" 
		+ " and cellID = " + cellId
		+ " and b.failureClass = f.failureClass "
		+ " and b.eventId = ec.eventId "
		+ " and b.causeCode = ec.causeCode "
		+ "group by ec.description, f.description") ;
	
		return top10Query.getResultList();
	}
    
    
}

