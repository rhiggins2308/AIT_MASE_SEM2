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

import com.adventales.entities.Calendar;

public class CalendarDaoTest {

	private Calendar testCalendar1, testCalendar2;
	private List<Calendar> expectedResult;
	
	@Mock
	private EntityManager em;
	
	@Mock
	private Query query;
	
	@InjectMocks
	private CalendarDao calendarDao;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		testCalendar1 = new Calendar();
		testCalendar1.setCalType("stout");
		testCalendar1.setCalSize(12);
		testCalendar1.setCost(29.99);
		testCalendar1.setImage("anyfilename.jpg");
		
		expectedResult = new ArrayList<>();
	}

	@After
	public void tearDown() throws Exception {
		em.close();
	}

	@Test
	public void testGetAllCalendars() {
		
		testCalendar2 = new Calendar();
		testCalendar2.setCalType("ale");
		testCalendar2.setCalSize(24);
		testCalendar2.setCost(49.99);
		testCalendar2.setImage("anyotherfilename.jpg");
		
		expectedResult.add(testCalendar1);
		expectedResult.add(testCalendar2);
		
		Mockito.when(em.createQuery("SELECT c FROM Calendar c")).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(expectedResult);
		
		List<Calendar> actualResult = new ArrayList<>();
		actualResult = calendarDao.getAllCalendars();
		
		assertThat(actualResult, is(expectedResult));
		assertEquals(2, expectedResult.size());
	}
	
	@Test
	public void testGetCalendar() {
		
		expectedResult.add(testCalendar1);
		
		Mockito.when(em.find(Matchers.anyObject(), Matchers.anyInt())).thenReturn(testCalendar1);
		Calendar actualResult = calendarDao.getCalendar(testCalendar1.getCalId());
		
		assertThat(actualResult, is(testCalendar1));
	}
}