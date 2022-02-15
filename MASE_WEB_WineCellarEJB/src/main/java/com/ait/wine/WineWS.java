package com.ait.wine;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/wines")
@Stateless
@LocalBean
public class WineWS {

	@EJB
	private WineDAO wineDao;
	
	@GET
	@Produces ({ MediaType.APPLICATION_JSON })
	public Response findAll() {
		List<Wine> wines = wineDao.getAllWines();
		return Response.status(200).entity(wines).build();
	}
	
	@GET
	@Produces ({ MediaType.APPLICATION_JSON })
	@Path("/{id}")
	public Response findWineById(@PathParam("id") int id) {
		Wine wine = wineDao.getWine(id);
		return Response.status(200).entity(wine).build();
	}
	
	@GET @Path("search/{query}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Wine> findByName(@PathParam("query") String query) {
		System.out.println("findByCountry: " + query);
		return wineDao.getWinesByName(query);
	}
	
	@GET
	@Path("searchCountry/{query}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Wine> findByCountry(@PathParam("query") String query) {
		System.out.println("Find by country: " + query);
		return wineDao.getWinesByCountry(query);
	}
	
	@POST
	@Produces({ MediaType.APPLICATION_JSON})
	public Response saveWine(Wine wine) {
		wineDao.save(wine);
		return Response.status(201).entity(wine).build();
	}
	
	@PUT
	@Path("/{id}")
	@Consumes("application/json")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response updateWine(Wine wine) {
		wineDao.update(wine);
		return Response.status(200).entity(wine).build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response deleteWine(@PathParam("id") int id) {
		wineDao.delete(id);
		return Response.status(204).build();
	}
}
