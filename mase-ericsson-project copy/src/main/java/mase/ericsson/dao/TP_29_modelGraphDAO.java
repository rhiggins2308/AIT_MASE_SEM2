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
public class TP_29_modelGraphDAO {

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

    
    
   
    public List<Object[]> getAllModel() {
        initEntityManager();
        Query query = em.createQuery("SELECT distinct u.model FROM UserEquipment u ORDER BY model");
        return query.getResultList();
    }

    public List<Object[]> getGraphFailureDataByModel(String model) {
        initEntityManager();
        System.out.println(model);
        Query query = em.createQuery(
                "SELECT b.eventId , b.causeCode, e.description,count(b.id) as numOccurences from BaseData b, UserEquipment u, EventCause e "  +
               
                        " where u.tac = b.ueType and e.causeCode = b.causeCode and e.eventId = b.eventId and u.model = ?1 group by b.eventId , b.causeCode");
        query.setParameter(1, model);
        System.out.println(query.getResultList());
        return query.getResultList();
     
    }

    }