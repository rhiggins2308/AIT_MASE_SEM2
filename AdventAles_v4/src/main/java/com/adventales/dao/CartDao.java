package com.adventales.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.adventales.entities.Cart;

@Stateless
@LocalBean
public class CartDao {

	@PersistenceContext
	private EntityManager em;
	
	public void addCartItem(Cart cartItem) {
		em.persist(cartItem);
	}
	
	public List<Cart> getAllCartItems() {
		Query query = em.createQuery("SELECT c FROM Cart c");
		return query.getResultList();
	}
	
	public void emptyCart() {
		Query query = em.createNativeQuery("DELETE FROM cart");
		query.executeUpdate();
	}
}