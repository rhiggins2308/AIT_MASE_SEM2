package mase.ericsson.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;



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
public class TP_29_graphI_MSI_ReportDAO {

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
    }


    @SuppressWarnings("unchecked")
    public List<Object[]> GraphimsiReport(long imsi, Date fromDate, Date toDate) {
        // try {
        initEntityManager();
        System.out.println("&&&&&&&&&&in dao&&&&&&&&&&&&");

        Query selectQuery = em.createQuery("SELECT  Distinct d.failureClass,f.description,sum(d.duration) as sum from BaseData d ,FailureClass f "
                + " WHERE f.failureClass=d.failureClass and d.imsi = ?1 and  d.eventDate between ?2  and ?3 group by f.failureClass");
       
        System.out.println("$$$$$$$$$$$$$$$$$$$");
        selectQuery.setParameter(1, imsi);
        selectQuery.setParameter(2, fromDate);
        selectQuery.setParameter(3, toDate);

        return selectQuery.getResultList();


}
    
    @SuppressWarnings("unchecked")
    public List<Object[]> getAllImsiForNetwork() {
        initEntityManager();
        Query query = em.createQuery("SELECT DISTINCT c.imsi FROM BaseData c");
        return query.getResultList();
    };
	    
}
