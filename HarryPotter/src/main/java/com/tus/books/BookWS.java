package com.tus.books;

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
	public Response fineCarById(@PathParam("id") int id) {
		Book book = bookDao.getBook(id);
		return Response.status(200).entity(book).build();
	}
}