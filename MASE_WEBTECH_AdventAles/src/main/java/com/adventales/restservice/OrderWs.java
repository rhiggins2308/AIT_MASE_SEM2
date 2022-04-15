package com.adventales.restservice;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.adventales.dao.OrderDao;
import com.adventales.dao.CartDao;
import com.adventales.entities.Cart;
import com.adventales.entities.Order;

@Path("/order")
@Stateless
@LocalBean
public class OrderWs {

	@EJB
	private OrderDao orderDao;
	
	@EJB
	private CartDao cartDao;
	
	@GET
	@Produces ({MediaType.APPLICATION_JSON})
	public Response getPendingOrderItems() {
		List<Cart> cartItems = cartDao.getAllCartItems();
		return Response.status(200).entity(cartItems).build();
	}
	
	
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/makeorder")
	public Response createOrder(Order order) {
		order = orderDao.makeOrder(order);
		return Response.status(201).entity(order).build();
	}
	
	@GET
	@Produces ({MediaType.APPLICATION_JSON})
	@Path("/getopenorders")
	public Response getOpenOrders() {
		List<Order> openOrders = orderDao.getAllOrders();
		return Response.status(200).entity(openOrders).build();
	}
	
	@PUT
	@Path("/shiporder/{orderId}")
	@Consumes("application/json")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response shipOrder(@PathParam("orderId") int orderId) {
		orderDao.shipOrder(orderId);
		return Response.status(200).build();
	}
}