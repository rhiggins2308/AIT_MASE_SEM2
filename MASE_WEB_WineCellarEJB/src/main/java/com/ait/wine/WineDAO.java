package com.ait.wine;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
@LocalBean
public class WineDAO {

	@PersistenceContext
	private EntityManager em;
	
	public List<Wine> getAllWines() {
		Query query = em.createQuery("SELECT w FROM Wine w");
		return query.getResultList();
	}

	public Wine getWine(int id) {
		return em.find(Wine.class, id);
	}

	public void save(Wine wine) {
		em.persist(wine);		
	}

	public void update(Wine wine) {
		em.merge(wine);
	}

	public void delete(int id) {
		em.remove(getWine(id));
	}

	public List<Wine> getWinesByCountry(String country) {
		Query query = em.createQuery("SELECT w FROM Wine w WHERE w.country LIKE ?1");
		query.setParameter(1, "%" + country.toUpperCase() + "%");
		return query.getResultList();
	}
	
	public List<Wine> getWinesByName(String name) {
		Query query = em.createQuery("SELECT w FROM Wine w WHERE NAME LIKE ?1");
		query.setParameter(1, "%" + name.toUpperCase() + "%");
		return query.getResultList();
	}
}
