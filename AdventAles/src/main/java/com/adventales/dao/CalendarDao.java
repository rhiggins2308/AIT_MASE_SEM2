package com.adventales.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.adventales.entities.Calendar;

@Stateless
@LocalBean
public class CalendarDao {

	@PersistenceContext
	private EntityManager em;
	
	public List<Calendar> getAllCalendars() {
		Query query = em.createQuery("SELECT c FROM Calendar c");
		return query.getResultList();
	}

	public List<Calendar> getCalendarsByType(String calType) {
		Query query = em.createQuery("SELECT c FROM Calendar c WHERE c.calType = ?1");
		query.setParameter(1, calType);
		return query.getResultList();
	}

	public List<Calendar> getCalendarsByCost(double cost) {
		Query query = em.createQuery("SELECT c FROM Calendar c WHERE c.cost <= ?1");
		query.setParameter(1, cost);
		return query.getResultList();
	}
}
