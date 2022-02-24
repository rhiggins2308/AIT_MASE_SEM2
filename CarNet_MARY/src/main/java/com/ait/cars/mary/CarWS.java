package com.ait.cars.mary;

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

@Path("/cars")
@Stateless
@LocalBean
public class CarWS {
	
	@EJB
	CarDAO carDao = new CarDAO();
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public Response findAll(){
		List<Car> cars = carDao.getAll();
		return Response.status(200).entity(cars).build();
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/{id}")
	public Response findCarById(@PathParam("id") int id) {
		Car car = carDao.getCarById(id);
		return Response.status(200).entity(car).build();
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/search/{startDate}/{endDate}")
	public Response getCarByYear(@PathParam("startDate") int startDate, @PathParam("endDate") int endDate) {
		List<Car> cars = carDao.getCarByYear(startDate, endDate);
		return Response.status(200).entity(cars).build();
	}

}
