package com.adventales.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.adventales.entities.Order;

@Stateless
@LocalBean
public class OrderDao {

	@PersistenceContext
	private EntityManager em;

	public Order makeOrder(Order order) {
		em.persist(order);
		return order;
	}

	public List<Order> getAllOrders() {
		Query query = em.createQuery("SELECT o FROM Order o WHERE o.orderShipped = 0");
		return query.getResultList();
	}

	public void shipOrder(int orderId) {
		Query query = em.createNativeQuery("UPDATE orders SET orderShipped = 1 WHERE orderId = ?1");
		query.setParameter(1, orderId);
		query.executeUpdate();
	}
}