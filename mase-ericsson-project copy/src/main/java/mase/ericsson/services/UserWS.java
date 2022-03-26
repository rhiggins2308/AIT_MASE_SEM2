package mase.ericsson.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import mase.ericsson.dao.UserDao;
import mase.ericsson.entities.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/users")
//@Stateless
//@LocalBean
public class UserWS {
	
	
	@Inject
	private UserDao userDao;

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/register")
	public Response saveUser(User user) {
		userDao.registerUser(user);
		return Response.status(201).entity(user).build();
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/allUsers")
	public Response findAllUser() {
		List<User> users = userDao.getAllUsers();
		return Response.status(200).entity(users).build();
	}

	@Path("/login/{userId}/{password}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response loginUser(@PathParam("userId") int userId, @PathParam("password") String password)
			throws JsonProcessingException {
		User user = userDao.findbyuserid(userId);
		if (user != null && user.getPassword().equals(password)) {
			return Response.status(201).entity(user).build();
		} else {
			return Response.status(400).entity(user).build();
		}
		
	}
}
