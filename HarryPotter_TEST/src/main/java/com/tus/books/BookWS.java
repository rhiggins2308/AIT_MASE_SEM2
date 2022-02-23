package com.tus.books;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
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

@Path("/books")
@Stateless
@LocalBean
public class BookWS {

	@EJB
	private BookDAO bookDao;
	
	@GET
	@Produces ({MediaType.APPLICATION_JSON})
	public Response findAll() {
		List<Book> books = bookDao.getAllBooks();
		return Response.status(200).entity(books).build();
	}

	@GET
	@Produces ({MediaType.APPLICATION_JSON})
	@Path("/{id}")
	public Response findBookById(@PathParam("id") int id) {
		Book book = bookDao.getBook(id);
		return Response.status(200).entity(book).build();
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("title/{query}")
	public Response findBookByTitle(@PathParam("query") String query) {
		List<Book> books = bookDao.getBookByTitle(query);
		return Response.status(200).entity(books).build();
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("illustrator/{query}")
	public Response findBookByIllustrator(@PathParam("query") String query) {
		List<Book> books = bookDao.getBookByIllustrator(query);
		return Response.status(200).entity(books).build();
	}
/*	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("series/{query}")
	public Response findBookBySeries(@PathParam("query") String query) {
		List<Book> books = bookDao.getBookBySeries(query);
		return Response.status(200).entity(books).build();
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("rrp/{rrp}")
	public Response findBookByRrp(@PathParam("rrp") Double rrp) {
		List<Book> books = bookDao.getBookByRrp(rrp);
		return Response.status(200).entity(books).build();
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("online/{online}")
	public Response findBookByOnlinePrice(@PathParam("online") Double onlinePrice) {
		List<Book> books = bookDao.getBookByOnlinePrice(onlinePrice);
		return Response.status(200).entity(books).build();
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("priceDiff/{difference}")
	public Response findBookByPriceDifference(@PathParam("difference") Double difference) {
		List<Book> books = bookDao.getBookByPriceDifference(difference);
		return Response.status(200).entity(books).build();
	}
	
	@POST
	@Produces({ MediaType.APPLICATION_JSON})
	public Response saveBook(Book book) {
		bookDao.save(book);
		return Response.status(201).entity(book).build();
	}
	
	@PUT
	@Path("/{id}")
	@Consumes("application/json")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response updateBook(Book book) {
		bookDao.update(book);
		return Response.status(200).entity(book).build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response deleteBook(@PathParam("id") int id) {
		bookDao.delete(id);
		return Response.status(204).build();
	}
*/
}