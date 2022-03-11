package com.adventales.restservice;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.adventales.dao.UserDao;
import com.adventales.entities.User;

@Path("/users")
@Stateless
@LocalBean
public class UserWS {

	@EJB
	private UserDao userDao;
	
	@GET
	@Produces ({MediaType.APPLICATION_JSON})
	public Response findAll() {
		List<User> users = userDao.getAllUsers();
		return Response.status(200).entity(users).build();
	}
	
	@GET
	@Produces ({MediaType.APPLICATION_JSON})
	@Path("email/{email}")
	public Response findUserByEmail(@PathParam("email") String email) {
		User user = userDao.getUserByEmail(email);
		return Response.status(200).entity(user).build();
	}
	
	@POST
	@Produces({ MediaType.APPLICATION_JSON }) 
	@Path("/register")
	public Response saveUser(User user){
		userDao.registerUser(user); 
		return Response.status(201).entity(user).build(); 
	}
}
