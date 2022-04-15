package com.adventales.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.adventales.entities.Order;

public class OrderDaoTest {

	private Order testOrder1, testOrder2;
	private List<Order> expectedResult;
	
	@Mock
	private EntityManager em;
	
	@Mock
	private Query query;
	
	@InjectMocks
	private OrderDao orderDao;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		testOrder1 = new Order();
		testOrder1.setOrderShipped(0);
		testOrder1.setUserEmail("testCustomer@email.com");
		testOrder1.setOrderTotal(100.00);
		testOrder1.setAddress1("test address 1");
		testOrder1.setAddress2("test address 2");
		testOrder1.setTown("test town");
		testOrder1.setCounty("test county");
		
		expectedResult = new ArrayList<>();
	}

	@After
	public void tearDown() throws Exception {
		em.close();
	}

	@Test
	public void testMakeOrder() {
		Mockito.doNothing().when(em).persist(testOrder1);
		Order actualResult = orderDao.makeOrder(testOrder1);
		
		assertThat(actualResult, is(testOrder1));
	}
	
	@Test
	public void testGetAllOrders() {
		testOrder2 = new Order();
		testOrder2.setOrderShipped(1);
		testOrder2.setUserEmail("testCustomer2@email.com");
		testOrder2.setOrderTotal(200.00);
		testOrder2.setAddress1("test address 3");
		testOrder2.setAddress2("test address 4");
		testOrder2.setTown("test town2");
		testOrder2.setCounty("test county2");
		
		List<Order> orders = new ArrayList<>();
		
		orders.add(testOrder1);
		orders.add(testOrder2);
		
		expectedResult = orders.stream()
					.filter(o -> o.getOrderShipped() == 0)
					.collect(Collectors.toList());
		
		Mockito.when(em.createQuery("SELECT o FROM Order o WHERE o.orderShipped = 0")).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(expectedResult);
	
		List<Order> actualResult = orderDao.getAllOrders();
		assertThat(actualResult, is(expectedResult));
		assertEquals(1, actualResult.size());		
	}
	
	@Test
	public void testShipOrder() {
	
		Mockito.when(em.createNativeQuery("UPDATE orders SET orderShipped = 1 WHERE orderId = " + Matchers.anyInt())).thenReturn(query);
		Mockito.when(query.executeUpdate()).thenReturn(1);
		
		int ordersShipped = orderDao.shipOrder(Matchers.anyInt());
		
		assertEquals(1, ordersShipped);	
	}
}