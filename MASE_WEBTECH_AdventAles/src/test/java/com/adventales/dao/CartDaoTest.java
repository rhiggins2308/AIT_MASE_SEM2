package com.adventales.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.adventales.entities.Calendar;
import com.adventales.entities.Cart;

public class CartDaoTest {

	private Calendar testCalendar1, testCalendar2;
	private Cart testCartItem1, testCartItem2;
	private List<Cart> expectedResult;
	
	@Mock
	private EntityManager em;
	
	@Mock
	private Query query;
	
	@InjectMocks
	private CartDao cartDao;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		testCalendar1 = new Calendar();
		testCalendar1.setCalSize(12);
		testCalendar1.setCalType("stout");
		testCalendar1.setCost(29.99);
		testCalendar1.setImage("anyimage.jpg");
				
		testCartItem1 = new Cart();
		testCartItem1.setCalId(testCalendar1.getCalId());
		testCartItem1.setCalSize(testCalendar1.getCalSize());
		testCartItem1.setCalType(testCalendar1.getCalType());
		testCartItem1.setCost(testCalendar1.getCost());
		testCartItem1.setImage(testCalendar1.getImage());
		
		expectedResult = new ArrayList<>();
	}

	@After
	public void tearDown() throws Exception {
		em.close();
	}

	@Test
	public void testAddCartItem() {

		Mockito.doNothing().when(em).persist(testCartItem1);
		Cart actualResult = cartDao.addCartItem(testCartItem1);
		
		assertThat(actualResult, is(testCartItem1));
	}
	
	@Test
	public void testGetAllCartItems() {
		
		testCalendar2 = new Calendar();
		testCalendar2.setCalSize(24);
		testCalendar2.setCalType("ale");
		testCalendar2.setCost(49.99);
		testCalendar2.setImage("anyotherimage.jpg");
		
		testCartItem2 = new Cart();
		testCartItem2.setCalId(testCalendar2.getCalId());
		testCartItem2.setCalSize(testCalendar2.getCalSize());
		testCartItem2.setCalType(testCalendar2.getCalType());
		testCartItem2.setCost(testCalendar2.getCost());
		testCartItem2.setImage(testCalendar2.getImage());
				
		expectedResult.add(testCartItem1);
		expectedResult.add(testCartItem2);
		
		Mockito.when(em.createQuery("SELECT c FROM Cart c")).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(expectedResult);
		
		List<Cart> actualResult = cartDao.getAllCartItems();
		assertThat(actualResult, is(expectedResult));
		assertEquals(2, expectedResult.size());		
	}
	
	@Test
	public void testEmptyCart() {
		Mockito.when(em.createNativeQuery("DELETE FROM cart")).thenReturn(query);
		Mockito.when(query.executeUpdate()).thenReturn(1);
		
		int recordsDeleted = cartDao.emptyCart();
		assertEquals(1, recordsDeleted);		
	}
}