package com.adventales.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.adventales.entities.User;

@Stateless
@LocalBean
public class UserDao {

	@PersistenceContext
	private EntityManager em;
	
	public List<User> getAllUsers() {
		Query query = em.createQuery("SELECT u FROM User u");
		return query.getResultList();
	}

	public User getUserByEmail(String email) {
		Query query = em.createQuery("SELECT u FROM User u WHERE u.userEmail = ?1");
		query.setParameter(1, email);
		if (query.getResultList().size() == 1) {
			return (User) query.getResultList().get(0);
		}
		
		return null;
	}
}