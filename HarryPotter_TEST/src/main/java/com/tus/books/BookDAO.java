package com.tus.books;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
@LocalBean
public class BookDAO {

	@PersistenceContext
	private EntityManager em;
	
	public List<Book> getAllBooks() {
		Query query = em.createQuery("SELECT b FROM Book b");
		return query.getResultList();
		
	}

	public Book getBook(int id) {
		return em.find(Book.class, id);
	}

	public List<Book> getBookByTitle(String nameStr) {
		Query query = em.createQuery("SELECT b FROM Book b WHERE b.title LIKE ?1");
		query.setParameter(1, "%" + nameStr + "%");
		return query.getResultList();
	}

	public List<Book> getBookByIllustrator(String illustrator) {
		Query query = em.createQuery("SELECT b FROM Book b WHERE b.illustrated LIKE ?1");
		query.setParameter(1, "%" + illustrator + "%");
		return query.getResultList();
	}
	
	
}