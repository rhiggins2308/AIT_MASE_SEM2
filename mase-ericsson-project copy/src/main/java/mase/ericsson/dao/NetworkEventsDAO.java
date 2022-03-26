package mase.ericsson.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.RollbackException;
import javax.validation.ConstraintViolationException;

import mase.ericsson.entities.BaseData;
import mase.ericsson.entities.EventCause;
import mase.ericsson.entities.EventCauseID;
import mase.ericsson.entities.FailureClass;
import mase.ericsson.entities.MccMnc;
import mase.ericsson.entities.MccMncID;
import mase.ericsson.entities.UserEquipment;

import java.sql.SQLIntegrityConstraintViolationException;

import java.util.*;

@Stateless
@Local
public class NetworkEventsDAO {

    private static final String DB_INSTANCE = "networkEventsDB";

    @PersistenceContext
    EntityManagerFactory emf;
    EntityManager em;

    public void initEntityManager() {
        emf = Persistence.createEntityManagerFactory(DB_INSTANCE);
        em = emf.createEntityManager();
    }

    /**
     * Only to be able to mock the {@code entityManager}
     */
    public void setEntityManager(EntityManager entityManager) {
        this.em = entityManager;
    }

    public boolean addData(List<Object> persistAll) {
        boolean insertSuccess = false;
        // try {
        initEntityManager();

        em.getTransaction().begin();

        for (Object dataRecord : persistAll) {
            if (dataRecord instanceof BaseData) {
                em.persist((BaseData) dataRecord);
            } else if (dataRecord instanceof EventCause) {
                em.persist((EventCause) dataRecord);
            } else if (dataRecord instanceof MccMnc) {
                em.persist((MccMnc) dataRecord);
            } else if (dataRecord instanceof FailureClass) {
                em.persist((FailureClass) dataRecord);
            } else if (dataRecord instanceof UserEquipment) {
                em.persist((UserEquipment) dataRecord);
            }
        }

        em.getTransaction().commit();
        em.close();
        emf.close();
        insertSuccess = true;
        return insertSuccess;
        /*
         * } catch (IllegalStateException ise) { ise.printStackTrace(); } catch
         * (IllegalArgumentException iae) { iae.printStackTrace(); }
         */

        // return insertSuccess;
    }

    public List<Integer> getValidFailureClassList() {
        initEntityManager();

        List<Integer> validFailureClassList = new ArrayList<>();
        validFailureClassList = em.createQuery("SELECT failureClass FROM FailureClass f").getResultList();

        return validFailureClassList;
    }

    public List<EventCauseID> getValidEventCauseId() {
        initEntityManager();

        List<EventCause> eventCauseList = em.createQuery("SELECT e from EventCause e").getResultList();
        List<EventCauseID> validEventCauseIdList = new ArrayList<>();

        for (EventCause eventCause : eventCauseList) {
            EventCauseID ecid = new EventCauseID(eventCause.getCauseCode(), eventCause.getEventId());
            validEventCauseIdList.add(ecid);
        }

        return validEventCauseIdList;
    }

    public List<MccMncID> getValidMccMncId() {
        initEntityManager();

        List<MccMnc> mccMncList = em.createQuery("SELECT m from MccMnc m").getResultList();
        List<MccMncID> validMccMncIdList = new ArrayList<>();

        for (MccMnc mccMnc : mccMncList) {
            MccMncID mmid = new MccMncID(mccMnc.getMcc(), mccMnc.getMnc());
            validMccMncIdList.add(mmid);
        }

        return validMccMncIdList;
    }

    public List<Integer> getValidTacList() {
        initEntityManager();

        List<Integer> validTacList = new ArrayList<>();
        validTacList = em.createQuery("SELECT e.tac FROM UserEquipment e").getResultList();

        return validTacList;
    }

    public EventCause getData(EventCauseID ecid) throws IllegalStateException, IllegalArgumentException {
        EventCause ec = null;
        initEntityManager();
        ec = em.find(EventCause.class, ecid);
        /*
         * try { emf = Persistence.createEntityManagerFactory(DB_INSTANCE); em =
         * emf.createEntityManager(); ec = em.find(EventCause.class, ecid); return ec; }
         * catch (IllegalStateException ise) { ise.printStackTrace(); } catch
         * (IllegalArgumentException iae) { iae.printStackTrace(); }
         */
        return ec;
    }

    public FailureClass getData(int fcid) throws IllegalStateException, IllegalArgumentException {
        FailureClass fc = null;
        initEntityManager();
        fc = em.find(FailureClass.class, fcid);
        /*
         * try { emf = Persistence.createEntityManagerFactory(DB_INSTANCE); em =
         * emf.createEntityManager(); fc = em.find(FailureClass.class, fcid); return fc;
         * } catch (IllegalStateException ise) { ise.printStackTrace(); } catch
         * (IllegalArgumentException iae) { iae.printStackTrace(); }
         */
        return fc;
    }

    public MccMnc getData(MccMncID mccMncId) {
        MccMnc mccMnc = null;
        initEntityManager();
        // em.getTransaction().begin();
        mccMnc = em.find(MccMnc.class, mccMncId);
        return mccMnc;
    }

    public UserEquipment getUeData(int tac) {
        UserEquipment ue = null;
        initEntityManager();
        em.getTransaction().begin();
        ue = em.find(UserEquipment.class, tac);
        /*
         * try { emf = Persistence.createEntityManagerFactory(DB_INSTANCE); em =
         * emf.createEntityManager(); em.getTransaction().begin(); ue =
         * em.find(UserEquipment.class, tac); return ue; } catch (IllegalStateException
         * ise) { ise.printStackTrace(); } catch (IllegalArgumentException iae) {
         * iae.printStackTrace(); }
         */
        return ue;
    }

    // SELECT DISTINCT e.eventId, e.causeCode, e.description FROM CallFailure c,
    // EventCause e where c.causeCode = e.causeCode And c.eventId = e.eventId AND
    // c.imsi =:imsi

    public List<Object[]> getNetworkEventsByImsi(long imsi) {
        initEntityManager();
        Query query = em.createQuery(
                "SELECT DISTINCT e.eventId, e.causeCode, e.description FROM BaseData c, EventCause e where c.causeCode = e.causeCode And c.eventId = e.eventId AND c.imsi = ?1");
        query.setParameter(1, imsi);
        return query.getResultList();
    }

    // only for the dropdown
    @SuppressWarnings("unchecked")
    public List<Object[]> getAllImsiForNetwork() {
        initEntityManager();
        Query query = em.createQuery("SELECT DISTINCT c.imsi FROM BaseData c");
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Object[]> imsiReport(long imsi, Date fromDate, Date toDate) {
        // try {
        initEntityManager();

        Query selectQuery = em.createQuery("SELECT  imsi ,COUNT(imsi) as total, sum(duration) as sum from BaseData d "
                + "WHERE d.imsi = ?1 and  d.eventDate between ?2  and ?3 " + "Group by imsi");
        selectQuery.setParameter(1, imsi);
        selectQuery.setParameter(2, fromDate);
        selectQuery.setParameter(3, toDate);

        return selectQuery.getResultList();

        /*
         * } catch (IllegalStateException | IllegalArgumentException exception) {
         * exception.printStackTrace(); }
         * 
         * return new ArrayList<>();
         */
    }

    public List<Object[]> getAllUserEquipment() {
        initEntityManager();
        Query query = em.createQuery("SELECT distinct u.model FROM UserEquipment u ORDER BY model");
        return query.getResultList();
    }

    public List<Object[]> getFailureDataByModel(String model) {
        initEntityManager();
        System.out.println(model);
        Query query = em.createQuery(
                "SELECT b.eventId , b.causeCode, count(b.id) as numOccurences from BaseData b, UserEquipment u " +
                // Query query = em.createQuery("SELECT b from BaseData b, UserEquipment u " +
                        "where u.tac = b.ueType and u.model = ?1  group by b.eventId , b.causeCode ");
        query.setParameter(1, model);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Object[]> getListOfIMSIForDateRange(Date fromdate, Date todate) {
        initEntityManager();

        Query query = em.createQuery("select DISTINCT d.imsi from BaseData d where d.eventDate between ?1 and ?2");

        query.setParameter(1, fromdate);
        query.setParameter(2, todate);
        return query.getResultList();

    }

    public List<Integer> getUniqueMccMnc() {

        List<Integer> uniqueMccMnc = new ArrayList<>();
        uniqueMccMnc = em.createQuery("SELECT mcc, mnc FROM MccMnc m").getResultList();
        return uniqueMccMnc;
    }

    public List<BaseData> getFailureDataByModelAndTimePeriod(long ueType, Date fromdate, Date todate) {

        try {
            System.out.println("$$$$$$$$$$$$$$DATE VALUES TESTIN%%%%%%%%%%%%%");
            System.out.println(todate);
            System.out.println(fromdate);
            System.out.println(todate);
            emf = Persistence.createEntityManagerFactory(DB_INSTANCE);
            em = emf.createEntityManager();

            Query query1 = em.createQuery(
                    "SELECT  ueType ,COUNT(ueType) as total, sum(duration) as sum from BaseData d where d.ueType = ?1 and  d.eventDate between ?2  and ?3  Group by ueType");
            // Query query1 = em.createQuery("SELECT ueType ,COUNT(ueType) as total,
            // sum(duration) as sum from BaseData d where d.ueType = 33000153 and
            // d.eventDate between '2020-01-11 17:15:00' and '2020-01-11 17:16:00' Group by
            // ueType");
            query1.setParameter(1, ueType);

            query1.setParameter(2, fromdate);
            query1.setParameter(3, todate);

            System.out.println("result====" + em.toString());
            return (query1.getResultList());

        } catch (IllegalStateException ise) {
            ise.printStackTrace();
        } catch (IllegalArgumentException iae) {
            iae.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<BaseData> ueReport(int ue, Date fromDate, Date toDate) {
        // try {
        initEntityManager();

        Query selectQuery = em
                .createQuery("SELECT  ueType ,COUNT(ueType) as total, sum(duration) as sum from BaseData d "
                        + "WHERE d.ueType = ?1 and  d.eventDate between ?2  and ?3 " + "Group by ueType");
        selectQuery.setParameter(1, ue);
        selectQuery.setParameter(2, fromDate);
        selectQuery.setParameter(3, toDate);

        return selectQuery.getResultList();
    }
    
    @SuppressWarnings("unchecked")
   			public List<Object[]> getAllImsi() {
   		        initEntityManager();
   		        Query query = em.createQuery("SELECT DISTINCT c.imsi FROM BaseData c ");
   		         return query.getResultList();
   		    }
   			
   			@SuppressWarnings("unchecked")
   			public List<Object[]> getAllFailureClass() {
   		        initEntityManager();
   		        Query query = em.createQuery("SELECT DISTINCT c.failureClass FROM BaseData c ");
   		         return query.getResultList();
   		    }
    @SuppressWarnings("unchecked")
   		public List<Object[]> getImsiByFailureClass(int failureClass) {
   	        initEntityManager();
   	        Query query = em.createQuery(
   	                "SELECT DISTINCT c.imsi FROM BaseData c where c.failureClass = ?1");
   	        query.setParameter(1, failureClass);
   	        return query.getResultList();
   	    }
    @SuppressWarnings("unchecked")
   		public List<Object[]> getAllByImsi() {
   	        initEntityManager();
   	        Query query = em.createQuery("SELECT imsi,count(id) as numOfFailures, sum(duration)as totalDuration FROM BaseData c GROUP BY c.imsi order by count(id) DESC").setMaxResults(10);

   	        return query.getResultList();
   	    }
    @SuppressWarnings("unchecked")
   	public List<Object[]> getCauseCodeByImsi(long imsi) {
           initEntityManager();
           Query query = em.createQuery(
                   "SELECT DISTINCT e.causeCode, e.description FROM BaseData c, EventCause e where c.causeCode = e.causeCode And c.eventId = e.eventId AND c.imsi = ?1");
           query.setParameter(1, imsi);
           return query.getResultList();
       }

	

}