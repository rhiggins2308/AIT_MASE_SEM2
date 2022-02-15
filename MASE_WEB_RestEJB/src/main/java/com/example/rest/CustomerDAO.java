package com.example.rest;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class CustomerDAO {

	@PersistenceContext
	private EntityManager em;
	
	public Customer getCustomer(int id) {
		return em.find(Customer.class, id);
	}
}
