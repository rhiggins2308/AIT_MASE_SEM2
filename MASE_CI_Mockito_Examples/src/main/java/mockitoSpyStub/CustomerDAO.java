package mockitoSpyStub;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public class CustomerDAO {
	
	@PersistenceContext
	EntityManager em;
	
	public CustomerDAO(EntityManager em) {
		this.em = em;
	}
		
	public Optional<Customer> findById(long id) throws Exception {
		Customer customer = em.find(Customer.class, id);
		return Optional.ofNullable(customer);
	}
	
	public List<Customer> findAll() throws Exception {
		TypedQuery<Customer> query = em.createQuery("select * from CUSTOMER", Customer.class);
		return query.getResultList();	
	}

	public Customer update(Customer customer) throws Exception {
		return em.merge(customer);
	}
	
}
