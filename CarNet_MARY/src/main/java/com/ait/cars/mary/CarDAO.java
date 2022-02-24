package com.ait.cars.mary;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
@LocalBean
public class CarDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Car> getAll(){
		Query query = em.createQuery("SELECT c FROM Car c");
		return query.getResultList();
	}
	
	public Car getCarById(int id) {
		return em.find(Car.class, id);
	}
	
	public List<Car> getCarByYear(int startDate, int endDate){
		Query query = em.createQuery("SELECT c FROM Car c WHERE c.year BETWEEN :sDate AND :eDate");
		query.setParameter("sDate", startDate);
		query.setParameter("eDate", endDate);
		return query.getResultList();
	}

}
