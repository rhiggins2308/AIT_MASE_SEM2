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
public class TP21_IMSI_Failures_DAO {

    private static final String DB_INSTANCE = "networkEventsDB";

    @PersistenceContext
    EntityManagerFactory emf;
    public EntityManager em;

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


//only for the dropdown
    @SuppressWarnings("unchecked")
    public List<Object[]> getAllImsi() {
        initEntityManager();
        Query query = em.createQuery("SELECT DISTINCT c.imsi FROM BaseData c");
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Object[]> imsiCountForServiceRep(long imsi, Date fromDate, Date toDate) {
        // try {
        initEntityManager();

        Query selectQuery = em.createQuery(
                "SELECT imsi ,COUNT(imsi) as count from BaseData d " +
                        "WHERE d.imsi = ?1 and  d.eventDate between ?2  and ?3 " +
                        "Group by imsi");
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

}
