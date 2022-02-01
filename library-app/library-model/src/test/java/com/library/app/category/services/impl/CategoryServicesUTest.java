package com.library.app.category.services.impl;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.*;

import javax.validation.Validation;
import javax.validation.Validator;

import static org.hamcrest.CoreMatchers.*;
import static com.library.app.commontests.category.CategoryForTestsRepository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.library.app.category.exception.CategoryExistentException;
import com.library.app.category.exception.CategoryNotFoundException;

//import org.junit.Before;
//import org.junit.Test;

import com.library.app.category.model.Category;
import com.library.app.category.repository.CategoryRepository;
import com.library.app.category.services.CategoryServices;
import com.library.app.common.exception.FieldNotValidException;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CategoryServicesUTest {
	
	private CategoryServicesImpl categoryServices;
	private Validator validator;
	private CategoryRepository categoryRepository;

	
	@BeforeEach
	public void initTestCase() {
		validator = Validation.buildDefaultValidatorFactory().getValidator();
		categoryRepository = mock(CategoryRepository.class);
		categoryServices = new CategoryServicesImpl();
		((CategoryServicesImpl) categoryServices).validator = validator;
		((CategoryServicesImpl) categoryServices).categoryRepository = categoryRepository;

	}

	@Test
	public void addCategoryWithNullName() {
		Throwable exception=assertThrows(FieldNotValidException.class, () -> {
			categoryServices.add(new Category());
		});
		assertThat(((FieldNotValidException) exception).getFieldName(),is(equalTo("name")));
	}
	
	@Test
	public void addCategoryWithShortName() {
		Throwable exception=assertThrows(FieldNotValidException.class, () -> {
			categoryServices.add(new Category("A"));
		});
		assertThat(((FieldNotValidException) exception).getFieldName(),is(equalTo("name")));
	}
	
	@Test
	public void addCategoryWithLongName() {
		Throwable exception=assertThrows(FieldNotValidException.class, () -> {
			categoryServices.add(new Category("This is a long name with more than 25 characters"));
		});
		assertThat(((FieldNotValidException) exception).getFieldName(),is(equalTo("name")));
	}
	
	@Test
	public void addCategoryWithExistentName() {
		when(categoryRepository.alreadyExists(java())).thenReturn(true);
		Throwable exception=assertThrows(CategoryExistentException.class, () -> {
			categoryServices.add(java());
		});
	}
	
	@Test
	public void addValidCategory() {
		when(categoryRepository.alreadyExists(java())).thenReturn(false);
		when(categoryRepository.add(java())).thenReturn(categoryWithId(java(),1L));
		final Category categoryAdded = categoryServices.add(java());
		assertThat(categoryAdded.getId(), is(equalTo(1L)));	
	}
	//update tests
	@Test
	public void updateCategoryWithNullName() {
		Throwable exception=assertThrows(FieldNotValidException.class, () -> {
			categoryServices.update(new Category());
		});
		assertThat(((FieldNotValidException) exception).getFieldName(),is(equalTo("name")));
	}
	
	public void updateCategoryWithShortName() {
		Throwable exception=assertThrows(FieldNotValidException.class, () -> {
			categoryServices.update(new Category("A"));
		});
		assertThat(((FieldNotValidException) exception).getFieldName(),is(equalTo("name")));
	}
	
	@Test
	public void updateCategoryWithLongName() {
		Throwable exception=assertThrows(FieldNotValidException.class, () -> {
			categoryServices.update(new Category("This is a long name with more than 25 characters"));
		});
		assertThat(((FieldNotValidException) exception).getFieldName(),is(equalTo("name")));
	}
	
	@Test
	public void updateCategoryWithExistentName() {
		when(categoryRepository.alreadyExists(categoryWithId(java(), 1L))).thenReturn(true);
		Throwable exception=assertThrows(CategoryExistentException.class, () -> {
			categoryServices.update(categoryWithId(java(),1L));
		});
	}
	
	@Test
	public void updateCategoryNotFound() {
		when(categoryRepository.alreadyExists(categoryWithId(java(), 1L))).thenReturn(false);
		when(categoryRepository.existsById(1L)).thenReturn(false);
		Throwable exception=assertThrows(CategoryNotFoundException.class, () -> {
			categoryServices.update(categoryWithId(java(),1L));
		});
	}
	
	@Test
	public void updateValidCategory() {
		when(categoryRepository.alreadyExists(categoryWithId(java(), 1L))).thenReturn(false);
		when(categoryRepository.existsById(1L)).thenReturn(true);
		categoryServices.update(categoryWithId(java(), 1L));
		verify(categoryRepository).update(categoryWithId(java(), 1L));
	}
	//findById tests
	@Test
	public void findCategoryById() {
		when(categoryRepository.findById(1L)).thenReturn(categoryWithId(java(), 1L));
		final Category category = categoryServices.findById(1L);
		assertThat(category, is(notNullValue()));
		assertThat(category.getId(), is(equalTo(1L)));
		assertThat(category.getName(), is(equalTo(java().getName())));
	}

	@Test
	public void findCategoryByIdNotFound() {
		when(categoryRepository.findById(1L)).thenReturn(null);
		Throwable exception=assertThrows(CategoryNotFoundException.class, () -> {
			categoryServices.findById(1L);
		});
		
	}
	
	@Test
	public void findAllNoCategories() {
		when(categoryRepository.findAll("name")).thenReturn(new ArrayList<>());
		final List<Category> categories = categoryServices.findAll();
		assertThat(categories.isEmpty(), is(equalTo(true)));
	}
//findAll
	@Test
	public void findAllCategories() {
		when(categoryRepository.findAll("name")).thenReturn(
				Arrays.asList(categoryWithId(java(), 1L), categoryWithId(networks(), 2L)));
		final List<Category> categories = categoryServices.findAll();
		assertThat(categories.size(), is(equalTo(2)));
		assertThat(categories.get(0).getName(), is(equalTo(java().getName())));
		assertThat(categories.get(1).getName(), is(equalTo(networks().getName())));
	}





}
