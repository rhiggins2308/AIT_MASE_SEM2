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

import com.adventales.entities.User;

public class UserDaoTest {

	User testUser1, testUser2;
	
	@Mock
	private EntityManager em;
	
	@Mock
	private Query query;
	
	@InjectMocks
	private UserDao userDao;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void tearDown() throws Exception {
		em.close();
	}

	@Test
	public void testGetAllUsers() {
		testUser1 = new User();
		testUser1.setUserEmail("test@testmail.com");
		testUser1.setFirstName("Tester");
		testUser1.setLastName("Testington");
		testUser1.setDob("01011970");
		testUser1.setPword("testPass");
		
		testUser2 = new User();
		testUser2.setUserEmail("testertwo@testmail.com");
		testUser2.setFirstName("TesterTwo");
		testUser2.setLastName("Testington");
		testUser2.setDob("01011980");
		testUser2.setPword("testPassTwo");
		
		List<User> expectedResult= new ArrayList<>();
		
		expectedResult.add(testUser1);
		expectedResult.add(testUser2);
		
		Mockito.when(em.createNamedQuery("SELECT u FROM User u")).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(expectedResult);
		
		List<User> actualResult = new ArrayList<>();
		actualResult = userDao.getAllUsers();
		
		assertThat(actualResult, is(expectedResult ));
	}
	
	@Test
	public void testGetUserByEmail() {
		testUser1 = new User();
		testUser1.setUserEmail("test@testmail.com");
		testUser1.setFirstName("Tester");
		testUser1.setLastName("Testington");
		testUser1.setDob("01011970");
		testUser1.setPword("testPass");
		
		testUser2 = new User();
		testUser2.setUserEmail("testertwo@testmail.com");
		testUser2.setFirstName("TesterTwo");
		testUser2.setLastName("Testington");
		testUser2.setDob("01011980");
		testUser2.setPword("testPassTwo");
		
		List<User> expectedResult= new ArrayList<>();
		
		expectedResult.add(testUser1);
		expectedResult.add(testUser2);
		
		Mockito.when(em.createNamedQuery("SELECT u FROM User u")).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(expectedResult);
		
		List<User> actualResult = new ArrayList<>();
		actualResult = userDao.getAllUsers();
		
		assertThat(actualResult, is(expectedResult ));
	}
}
