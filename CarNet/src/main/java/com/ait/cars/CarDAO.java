package com.ait.cars;

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
	
	public List<Car> getAllCars() {
		Query query = em.createQuery("SELECT c FROM Car c");
		return query.getResultList();
	}

	public Car getCar(int id) {
		return em.find(Car.class, id);
	}

	public List<Car> getCarByYear(String startDate, String endDate) {
		Query query = em.createQuery("SELECT c FROM Car c WHERE c.year BETWEEN :sDate AND :eDate");
		query.setParameter("sDate", Integer.parseInt(startDate));
		query.setParameter("eDate", Integer.parseInt(endDate));
		return query.getResultList();
	}
}
