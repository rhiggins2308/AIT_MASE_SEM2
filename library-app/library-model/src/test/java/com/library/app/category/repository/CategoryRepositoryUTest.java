package com.library.app.category.repository;

import static com.library.app.commontests.category.CategoryForTestsRepository.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;

import com.library.app.commontests.utils.DBCommandTransactionalExecutor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.library.app.category.model.Category;

public class CategoryRepositoryUTest {
	private EntityManagerFactory emf;
	private EntityManager em;
	private CategoryRepository categoryRepository;
	private DBCommandTransactionalExecutor dBCommandTransactionalExecutor;


	@Before
	public void initTestCase() {
		emf = Persistence.createEntityManagerFactory("libraryPU");
		em = emf.createEntityManager();
		categoryRepository = new CategoryRepository();
		categoryRepository.em = em;
		dBCommandTransactionalExecutor = new DBCommandTransactionalExecutor(em);
	}

	@After
	public void closeEntityManager() {
		em.close();
		emf.close();
	}

	@Test
	public void addCategoryAndFindIt() {
		final Long categoryAddedId = dBCommandTransactionalExecutor.executeCommand(() -> {
			return categoryRepository.add(java()).getId();
		});
		assertThat(categoryAddedId, is(notNullValue()));
		final Category category = categoryRepository.findById(categoryAddedId);
		assertThat(category, is(notNullValue()));
		assertThat(category.getName(), is(equalTo(java().getName())));
	}
	
	@Test
	public void findCategoryByIdNotFound() {
		final Category category = categoryRepository.findById(999L);
		assertThat(category, is(nullValue()));
	}
	
	@Test
	public void findCategoryByIdWithNullId() {
		final Category category = categoryRepository.findById(null);
		assertThat(category, is(nullValue()));
	}
	
	@Test
	public void updateCategory() {
		final Long categoryAddedId = dBCommandTransactionalExecutor.executeCommand(() -> {
			return categoryRepository.add(java()).getId();
		});
		Category categoryAfterAdd = categoryRepository.findById(categoryAddedId);
		assertThat(categoryAfterAdd.getName(), is(equalTo(java().getName())));
		categoryAfterAdd.setName(cleanCode().getName());
		dBCommandTransactionalExecutor.executeCommand(() -> {
			categoryRepository.update(categoryAfterAdd);
			return null;
		});
		Category categoryAfterUpdate = categoryRepository.findById(categoryAddedId);
		assertThat(categoryAfterUpdate.getName(),is(equalTo(cleanCode().getName())));
	}
	
	@Test
	public void findAllCategories() {
		dBCommandTransactionalExecutor.executeCommand(() -> {
			/*for (Category category :allCategories()) {
				categoryRepository.add(category);
			}*/
			allCategories().forEach(categoryRepository::add);
			return null;
		});
		final List<Category> categories =categoryRepository.findAll("name");
		assertThat(categories.size(), is(equalTo(4)));
		assertThat(categories.get(0).getName(), is(equalTo(architecture().getName())));
		assertThat(categories.get(1).getName(), is(equalTo(cleanCode().getName())));
		assertThat(categories.get(2).getName(), is(equalTo(java().getName())));
		assertThat(categories.get(3).getName(), is(equalTo(networks().getName())));
	}
	
	@Test
	public void alreadyExistsForAdd() {
		final Long categoryAddedId = dBCommandTransactionalExecutor.executeCommand(() -> {
			categoryRepository.add(java());
			return null;
		});
		assertThat(categoryRepository.alreadyExists(java()), is(equalTo(true)));
		assertThat(categoryRepository.alreadyExists(cleanCode()), is(equalTo(false)));	
	}
	
	@Test
	public void alreadyExistsCategoryWithId() {
		final Category java = dBCommandTransactionalExecutor.executeCommand(() -> {
			categoryRepository.add(cleanCode());
			return categoryRepository.add(java());
		});
 //If updating a category we should check if the category exists except for the 
//current category - only happens if update category with same name.
		assertThat(categoryRepository.alreadyExists(java), is(equalTo(false)));

		java.setName(cleanCode().getName());
		assertThat(categoryRepository.alreadyExists(java), is(equalTo(true)));

		java.setName(networks().getName());
		assertThat(categoryRepository.alreadyExists(java), is(equalTo(false)));
	}
	
	@Test
	public void existsById() {
		final Long categoryAddedId = dBCommandTransactionalExecutor.executeCommand(() -> {
			return categoryRepository.add(java()).getId();
		});
		assertThat(categoryRepository.existsById(categoryAddedId),is(equalTo(true)));
		assertThat(categoryRepository.existsById(999L),is(equalTo(false)));
	}
	

	
	
	

}