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
public class TP_27_Top_10_IMSI_DAO{

    private static final String DB_INSTANCE = "networkEventsDB";

    @PersistenceContext
    EntityManagerFactory emf;
    EntityManager em;

    public void initEntityManager() {
        emf = Persistence.createEntityManagerFactory(DB_INSTANCE);
        em = emf.createEntityManager();
    }

    public void setEntityManager(EntityManager entityManager) {
        this.em = entityManager;
    }

    public boolean addData(List<Object> persistAll) {
        boolean insertSuccess = false;
        //try {
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
    
    public List<Object[]>TP_27_Top_10_imsiCountforNtwEngineer(Date fromDate, Date toDate) {
    	       // try {
    	            initEntityManager();
    	            
    	            //"SELECT  imsi ,COUNT(imsi) as total, sum(duration) as sum from BaseData d " +
                   // "WHERE d.imsi = ?1 and  d.eventDate between ?2  and ?3 " +
                    //"Group by imsi");
    	            Query query = em.createQuery( "SELECT  imsi ,COUNT(id) from BaseData d " +
                    "WHERE  d.eventDate between ?1  and ?2 " +
                    "Group by imsi " +
                    "order by Count(id)DESC").setMaxResults(10);
    	
    	          /*  Query query = em.createQuery("select d.imsi ,Count(d.id) from BaseData d "  +
    	            		"where d.eventDate between ?1 and ?2 " +
    	            		"GROUP BY d.imsi  "  +
    	            		"order by Count(d.id) DESC").setMaxResults(10);*/
    	           // Query query = em.createQuery("select DISTINCT d.imsi from BaseData d where d.eventDate between ?1 and ?2").setMaxResults(10);
                    // Query query1=em.createQuery("select DISTINCT d.imsi from BaseData d where d.eventDate between '2020-02-19 19:35:00' and '2020-02-21 19:36:00' LIMIT 10");
    	            //Query q3=em..createQuery(SELECT distinct imsi , Count(id) from BaseData WHERE eventDate BETWEEN '2020-01-01 21:01:00' AND '2020-12-31 19:39:00'GROUP BY imsiorder by Count(imsi) DESCLIMIT 0, 10;
    	            
    	            
    	            
    	            
    	            query.setParameter(1, fromDate);
    	            query.setParameter(2, toDate);
    	            return query.getResultList();
    	
    
    
}
}

