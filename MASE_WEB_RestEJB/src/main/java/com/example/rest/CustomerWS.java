package com.example.rest;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/customers")
@Stateless
@LocalBean
public class CustomerWS {

	@EJB
	private CustomerDAO customersDao;
	
	@GET
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Customer getCustomer(@PathParam("id") int id) {
		return customersDao.getCustomer(id);
	}
}