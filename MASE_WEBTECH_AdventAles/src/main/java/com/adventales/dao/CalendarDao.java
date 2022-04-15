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

	public Calendar getCalendar(int calId) {
		return em.find(Calendar.class, calId);
	}
}