package mase.ericsson.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import mase.ericsson.entities.User;
import java.util.List;

public class UserDao {
	EntityManagerFactory emf;
	EntityManager em;

	static final String DB_INSTANCE = "networkEventsDB";

	public User registerUser(User user) {
		System.out.println("Registering User: " + user.toString());
		emf = Persistence.createEntityManagerFactory(DB_INSTANCE);
		em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		em.close();
		emf.close();
		return user;

	}

	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {

		emf = Persistence.createEntityManagerFactory(DB_INSTANCE);
		em = emf.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("SELECT u FROM User u");
		return query.getResultList();
	}

	public User findbyuserid(int userId) {
		emf = Persistence.createEntityManagerFactory(DB_INSTANCE);
		em = emf.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("SELECT r FROM User r WHERE r.userId = ?1");
		query.setParameter(1, userId);
		return (User) query.getSingleResult();
	}
}
