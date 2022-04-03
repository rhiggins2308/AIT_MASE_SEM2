package com.adventales.restservice;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.adventales.dao.CalendarDao;
import com.adventales.dao.CartDao;
import com.adventales.entities.Calendar;
import com.adventales.entities.Cart;

@Path("/cart")
@Stateless
@LocalBean
public class CartWs {

	@EJB
	private CartDao cartDao;
	@EJB
	private CalendarDao calendarDao;
	
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/add")
	public Response addToCart(Cart cartItem) {
		cartDao.addCartItem(cartItem);
		return Response.status(201).entity(cartItem).build();
	}
	
	@GET
	@Produces ({MediaType.APPLICATION_JSON})
	@Path("/getall")
	public Response getAllCartItems() {
		List<Cart> cartItems = cartDao.getAllCartItems();
		return Response.status(200).entity(cartItems).build();
	}
}