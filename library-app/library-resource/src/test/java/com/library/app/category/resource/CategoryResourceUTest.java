package com.library.app.category.resource;
import static com.library.app.commontests.category.CategoryForTestsRepository.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import javax.ws.rs.core.Response;

import com.library.app.category.exception.CategoryExistentException;
import com.library.app.category.exception.CategoryNotFoundException;
import com.library.app.category.model.Category;
import com.library.app.category.resource.error.ErrorMessage;
import com.library.app.category.services.CategoryServices;
import com.library.app.common.exception.FieldNotValidException;

public class CategoryResourceUTest {
	
	private CategoryResource categoryResource;
	
	@Mock
	private CategoryServices categoryServices;
	
	@BeforeEach
	public void initTestCase() {
		MockitoAnnotations.initMocks(this); 
		categoryResource = new CategoryResource();
		categoryResource.categoryServices = categoryServices;
	}
	
	@Test
	public void addValidCategory() {
		when(categoryServices.add(java())).thenReturn(categoryWithId(java(), 1L));
		final Response response = categoryResource.add(java());
		assertEquals(HttpStatus.SC_OK, response.getStatus());
		Category categoryAdded = (Category) response.getEntity();
		assertThat(categoryAdded.getId(), is(equalTo(1L)));
		assertThat(categoryAdded.getName(), is(equalTo(java().getName())));
	}
	
	@Test
	public void addExistentCategory() {
		when(categoryServices.add(java())).thenThrow(new CategoryExistentException());
		final Response response = categoryResource.add(java());
		assertEquals(HttpStatus.SC_FORBIDDEN, response.getStatus());
		ErrorMessage message = (ErrorMessage) response.getEntity();
		assertEquals(message.getErrorMessage(), "Category already exists");
	}
	
	@Test
	public void addCategoryWithNullName() {
		Category category=new Category();
		category.setName(null);
		when(categoryServices.add(category)).thenThrow(new FieldNotValidException("name", "may not be null"));
		final Response response = categoryResource.add(category);
		assertEquals(HttpStatus.SC_FORBIDDEN, response.getStatus());
		ErrorMessage message = (ErrorMessage) response.getEntity();
		assertEquals(message.getErrorMessage(), "Invalid field");
	}
	
	@Test
	public void updateValidCategory() {
		final Response response = categoryResource.update(categoryWithId(java(),1L));
		assertThat(response.getStatus(), is(equalTo(HttpStatus.SC_OK)));
		assertThat(response.getEntity().toString(), is(equalTo("Category [id=1, name=Java]")));
		verify(categoryServices).update(categoryWithId(java(), 1L));
	}

	@Test
	public void updateCategoryWithNameBelongingToOtherCategory() {
		doThrow(new CategoryExistentException()).when(categoryServices).update(categoryWithId(java(), 1L));
		final Response response = categoryResource.update(categoryWithId(java(),1L));
		assertEquals(HttpStatus.SC_FORBIDDEN, response.getStatus());
		ErrorMessage message = (ErrorMessage) response.getEntity();
		assertEquals(message.getErrorMessage(), "Category already exists");
	}

	@Test
	public void updateCategoryWithNullName() {
		Category category=new Category();
		doThrow(new FieldNotValidException("name", "may not be null")).when(categoryServices).update(
				categoryWithId(category, 1L));
		final Response response = categoryResource.update(category);
		assertEquals(HttpStatus.SC_FORBIDDEN, response.getStatus());
		ErrorMessage message = (ErrorMessage) response.getEntity();
		assertEquals(message.getErrorMessage(), "Invalid field");
	}

	@Test
	public void updateCategoryNotFound() {
		doThrow(new CategoryNotFoundException()).when(categoryServices).update(categoryWithId(java(), 2L));
		final Response response = categoryResource.update(categoryWithId(java(),2L));
		assertEquals(HttpStatus.SC_FORBIDDEN, response.getStatus());
		ErrorMessage message = (ErrorMessage) response.getEntity();
		assertEquals(message.getErrorMessage(), "Category does not exist");
	}
	
	@Test
	public void findCategory() {
		when(categoryServices.findById(1L)).thenReturn(categoryWithId(java(), 1L));
		final Response response = categoryResource.findById(1L);
		assertEquals(HttpStatus.SC_OK, response.getStatus());
		Category category = (Category) response.getEntity();
		assertThat(category.getId(), is(equalTo(1L)));
		assertThat(category.getName(), is(equalTo(java().getName())));
		
	}

	
	@Test
	public void findCategoryNotFound() {
		when(categoryServices.findById(1L)).thenThrow(new CategoryNotFoundException());
		final Response response = categoryResource.findById(1L);
		assertThat(response.getStatus(), is(equalTo(HttpStatus.SC_FORBIDDEN)));
		ErrorMessage message = (ErrorMessage) response.getEntity();
		assertEquals(message.getErrorMessage(), "Category does not exist");
	}

	@Test
	public void findAllNoCategory() {
		when(categoryServices.findAll()).thenReturn(new ArrayList<>());

		final Response response = categoryResource.findAll();
		assertThat(response.getStatus(), is(equalTo(HttpStatus.SC_OK)));
		List<Category> categoryList = (List<Category>) response.getEntity();
		assertEquals(categoryList.size(), 0);	
	}

	@Test
	public void findAllTwoCategories() {
		when(categoryServices.findAll()).thenReturn(
				Arrays.asList(categoryWithId(java(), 1L), categoryWithId(networks(), 2L)));

		final Response response = categoryResource.findAll();
		assertThat(response.getStatus(), is(equalTo(HttpStatus.SC_OK)));
		List<Category> categoryList = (List<Category>) response.getEntity();
		assertEquals(categoryList.size(), 2);	
	}

	

}





