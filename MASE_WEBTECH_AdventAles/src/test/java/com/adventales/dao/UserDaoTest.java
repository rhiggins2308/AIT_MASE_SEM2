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
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.adventales.entities.User;

public class UserDaoTest {

	private User testUser1, testUser2;
	private List<User> expectedResult;
	
	@Mock
	private EntityManager em;
	
	@Mock
	private Query query;
	
	@InjectMocks
	private UserDao userDao;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		testUser1 = new User();
		testUser1.setUserEmail("test@testmail.com");
		testUser1.setFirstName("Tester");
		testUser1.setLastName("Testington");
		testUser1.setDob("01011970");
		testUser1.setPword("testPass");
		
		expectedResult= new ArrayList<>();
	}

	@After
	public void tearDown() throws Exception {
		em.close();
	}

	@Test
	public void testGetAllUsers() {
		
		testUser2 = new User();
		testUser2.setUserEmail("testertwo@testmail.com");
		testUser2.setFirstName("TesterTwo");
		testUser2.setLastName("Testington");
		testUser2.setDob("01011980");
		testUser2.setPword("testPassTwo");
		
		expectedResult.add(testUser1);
		expectedResult.add(testUser2);
		
		Mockito.when(em.createQuery("SELECT u FROM User u")).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(expectedResult);
		
		List<User> actualResult = userDao.getAllUsers();
		
		assertThat(actualResult, is(expectedResult));
		assertEquals(2, expectedResult.size());
	}
	
	@Test
	public void testGetUserByEmail() {
		
		expectedResult.add(testUser1);
		
		Mockito.when(em.createQuery("SELECT u FROM User u WHERE u.userEmail =" + Matchers.anyString())).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(expectedResult);
		
		User actualResult = userDao.getUserByEmail(testUser1.getUserEmail());
		
		assertThat(actualResult.getUserEmail(), is(testUser1.getUserEmail()));
		assertThat(actualResult.getPword(), is(testUser1.getPword()));
	}
	
	@Test(expected=NullPointerException.class)
	public void testGetUserByEmailNotFound() {
				
		Mockito.when(em.createQuery("SELECT u FROM User u WHERE u.userEmail = " + Matchers.anyString())).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(new ArrayList<User>());
		User actualResult = new User();
		String nonExistentEmail = "noexists@email.com";
		actualResult = userDao.getUserByEmail(nonExistentEmail);
	}
	
	@Test
	public void testRegisterUser() {
		
		Mockito.doNothing().when(em).persist(testUser1);
		User actualResult = userDao.registerUser(testUser1);
		
		assertThat(actualResult, is(testUser1));
	}

}