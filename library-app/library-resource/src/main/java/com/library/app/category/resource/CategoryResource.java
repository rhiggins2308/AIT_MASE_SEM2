package com.library.app.category.resource;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.library.app.category.exception.CategoryExistentException;
import com.library.app.category.exception.CategoryNotFoundException;
import com.library.app.category.model.Category;
import com.library.app.category.resource.error.ErrorMessage;
import com.library.app.category.resource.error.ErrorMessages;
import com.library.app.category.services.CategoryServices;
import com.library.app.common.exception.FieldNotValidException;

@Path("/categories")
public class CategoryResource {
	
	@Inject
	CategoryServices categoryServices;
	
	@POST 
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response add(Category category) {
		Response response;
		try {
			Category categoryAdded=categoryServices.add(category);
			response = Response.status(200).entity(categoryAdded).build();
		} catch (FieldNotValidException e) {
			ErrorMessage errorMessage = new ErrorMessage(ErrorMessages.INVALID_FIELD.getMsg());
			response = Response.status(403).entity(errorMessage).build();
		}
	    catch (CategoryExistentException e) {
		ErrorMessage errorMessage = new ErrorMessage(ErrorMessages.CATEGORY_EXISTS.getMsg());
		response = Response.status(403).entity(errorMessage).build();
	    }
		return response;
	}
	
	@PUT @Path("id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response update(Category category) {
		Response response;
		try {
			categoryServices.update(category);
			response = Response.status(200).entity(category).build();
		} catch (FieldNotValidException e) {
			ErrorMessage errorMessage = new ErrorMessage(ErrorMessages.INVALID_FIELD.getMsg());
			response = Response.status(403).entity(errorMessage).build();
		}
	    catch (CategoryExistentException e) {
		ErrorMessage errorMessage = new ErrorMessage(ErrorMessages.CATEGORY_EXISTS.getMsg());
		response = Response.status(403).entity(errorMessage).build();
	    }
		catch (final CategoryNotFoundException e) {
		ErrorMessage errorMessage = new ErrorMessage(ErrorMessages.CATEGORY_NOT_FOUND.getMsg());
		response = Response.status(403).entity(errorMessage).build();
	}
		return response;
	}
	
	@GET
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_JSON})
	public Response findById(@PathParam("id") final Long id) {
		Response response;
		try {
			final Category category = categoryServices.findById(id);
			response= Response.status(200).entity(category).build();
		} catch (final CategoryNotFoundException e) {
			ErrorMessage errorMessage = new ErrorMessage(ErrorMessages.CATEGORY_NOT_FOUND.getMsg());
			response = Response.status(403).entity(errorMessage).build();
		}
		return response;
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON})
	public Response findAll() {
		final List<Category> categories = categoryServices.findAll();
		return Response.status(200).entity(categories).build();
	}



}

