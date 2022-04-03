package com.adventales.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.adventales.entities.Cart;

@Stateless
@LocalBean
public class CartDao {

	@PersistenceContext
	private EntityManager em;
	
	public void addCartItem(Cart cartItem) {
		em.persist(cartItem);	
	}
}