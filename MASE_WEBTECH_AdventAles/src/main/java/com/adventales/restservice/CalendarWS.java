package com.adventales.restservice;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.adventales.dao.CalendarDao;
import com.adventales.entities.Calendar;

@Path("/calendars")
@Stateless
@LocalBean
public class CalendarWS {

	@EJB
	private CalendarDao calendarDao;
	
	@GET
	@Produces ({MediaType.APPLICATION_JSON})
	public Response findAll() {
		List<Calendar> calendars = calendarDao.getAllCalendars();
		return Response.status(200).entity(calendars).build();
	}
	
	@GET
	@Produces ({MediaType.APPLICATION_JSON})
	@Path("/{id}")
	public Response findCalendarById(@PathParam("id") int calId) {
		Calendar calendar = calendarDao.getCalendar(calId);
		return Response.status(200).entity(calendar).build();
	}
}