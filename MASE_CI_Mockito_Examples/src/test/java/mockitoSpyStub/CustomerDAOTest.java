package mockitoSpyStub;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import mockitoSpyStub.Customer;
import mockitoSpyStub.CustomerDAO;

@RunWith(MockitoJUnitRunner.class)
public class CustomerDAOTest {
	
	private CustomerDAO dao;
	
	private Customer homerSimpson, bruceWayne, tyrionLannister;
	private Answer<Customer> withCustomerById = new Answer<Customer>() {
		@Override
		public Customer answer(InvocationOnMock invocation) throws Throwable {
			Object[] args = invocation.getArguments();
			int id = ((Long)args[1]).intValue(); // Cast to int for switch.
			switch (id) {
			case 1 : return homerSimpson;
			case 2 : return bruceWayne;
			case 3 : return tyrionLannister;
			default : return null;
			}
		}
	};
	
	@Mock
	private EntityManager mockEntityManager;
	@Mock
	private TypedQuery<Customer> mockQuery;

	@Before
	public void setUp() throws Exception {
		dao = new CustomerDAO(mockEntityManager);
		setupCustomers();
	}

	private void setupCustomers() {
		homerSimpson = new Customer(1, "Homer Simpson", "Springfield");
		bruceWayne = new Customer(2, "Bruce Wayne", "Gotham City");
		tyrionLannister = new Customer(2, "Tyrion Lannister", "Kings Landing");		
	}

	@Test
	public void finding_existing_customer_should_return_customer() throws Exception {
		// Given
		long expectedId = 10L;
		String expectedName = "John Doe";
		String expectedAddress = "21 Main Street";
		Customer expectedCustomer = new Customer(expectedId, expectedName, expectedAddress);
		
		when(mockEntityManager.find(Customer.class, expectedId)).thenReturn(expectedCustomer);
		
		// When
		Optional<Customer> actualCustomer = dao.findById(expectedId);
		
		// Then
		assertTrue(actualCustomer.isPresent());
		assertEquals(expectedId, actualCustomer.get().getId());
		assertEquals(expectedName, actualCustomer.get().getName());
		assertEquals(expectedAddress, actualCustomer.get().getAddress());
	}
	
	@Test
	public void invoking_mock_with_unexpected_argument_returns_null() throws Exception {
		// Given
		long expectedId = 10L;
		long unexpectedId = 20L;
		String expectedName = "John Doe";
		String expectedAddress = "21 Main Street";
		Customer expectedCustomer = new Customer(expectedId, expectedName, expectedAddress);
		
		when(mockEntityManager.find(Customer.class, expectedId)).thenReturn(expectedCustomer);
		
		// When
		Optional<Customer> actualCustomer = dao.findById(unexpectedId);
		
		// Then
		assertFalse(actualCustomer.isPresent());
	}
	
	@Test
	public void invoking_mock_with_different_argument_returns_different_customers() throws Exception {
		// Given
		long expectedId1 = 10L;
		String expectedName1 = "John Doe";
		String expectedAddress1 = "21 Main Street";
		Customer expectedCustomer1 = new Customer(expectedId1, expectedName1, expectedAddress1);
		
		long expectedId2 = 20L;
		String expectedName2 = "Jane Deer";
		String expectedAddress2 = "46 High Street";
		Customer expectedCustomer2 = new Customer(expectedId2, expectedName2, expectedAddress2);
		
		when(mockEntityManager.find(Customer.class, expectedId1)).thenReturn(expectedCustomer1);
		when(mockEntityManager.find(Customer.class, expectedId2)).thenReturn(expectedCustomer2);
		
		// When
		Optional<Customer> actualCustomer1 = dao.findById(expectedId1);
		Optional<Customer> actualCustomer2 = dao.findById(expectedId2);
		
		// Then
		assertEquals(expectedName1, actualCustomer1.get().getName());
		assertEquals(expectedName2, actualCustomer2.get().getName());
	}
	
	@Test
	public void invoking_mock_with_chained_stubs_returns_different_customers() throws Exception {
		// Given
		long expectedId1 = 10L;
		String expectedName1 = "John Doe";
		String expectedAddress1 = "21 Main Street";
		Customer expectedCustomer1 = new Customer(expectedId1, expectedName1, expectedAddress1);
		
		long expectedId2 = 20L;
		String expectedName2 = "Jane Deer";
		String expectedAddress2 = "46 High Street";
		Customer expectedCustomer2 = new Customer(expectedId2, expectedName2, expectedAddress2);
		
		when(mockEntityManager.find(Customer.class, expectedId1))
			.thenReturn(expectedCustomer1).thenReturn(expectedCustomer2);
		
		// When
		Optional<Customer> actualCustomer1 = dao.findById(expectedId1);
		Optional<Customer> actualCustomer2 = dao.findById(expectedId1);
		
		// Then
		assertEquals(expectedName1, actualCustomer1.get().getName());
		assertEquals(expectedName2, actualCustomer2.get().getName());
	}
	
	@Test
	public void finding_missing_customer_should_return_null() throws Exception {
		// Given
		long expectedId = 10L;
		when(mockEntityManager.find(Customer.class, expectedId)).thenReturn(null);
		
		// When
		Optional<Customer> actualCustomer = dao.findById(expectedId);
		
		// Then
		assertFalse(actualCustomer.isPresent());
	}
	
	@Test
	public void finding_customer_should_respond_appropriately() throws Exception {
		// Given
		long expectedId = 10L;
		String expectedName = "John Doe";
		String expectedAddress = "21 Main Street";
		Customer expectedCustomer1 = new Customer(expectedId, expectedName, expectedAddress);
		Customer expectedCustomer2 = null;
		
		when(mockEntityManager.find(Customer.class, expectedId)).thenReturn(expectedCustomer1, expectedCustomer2);
		
		// When
		Optional<Customer> actualCustomer1 = dao.findById(expectedId);
		Optional<Customer> actualCustomer2 = dao.findById(expectedId);
		
		// Then
		assertTrue(actualCustomer1.isPresent());
		assertFalse(actualCustomer2.isPresent());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void finding_customer_should_throw_exception_up_the_stack() throws Exception {
		// Given
		long expectedId = 10L;
		
		when(mockEntityManager.find(Customer.class, expectedId)).thenThrow(new IllegalArgumentException());
		
		// When
		dao.findById(expectedId);
		
		// Then
		fail("Exception should be thrown.");
	}
	
	@Test
	public void finding_customer_by_id_returns_appropriate_customer() throws Exception {
		// Given
		long[] expectedId = {1, 2, 3};
		
		when(mockEntityManager.find(eq(Customer.class), anyLong())).thenAnswer(withCustomerById);
		
		// When
		Optional<Customer> actualCustomer0 = dao.findById(expectedId[0]);
		Optional<Customer> actualCustomer1 = dao.findById(expectedId[1]);
		Optional<Customer> actualCustomer2 = dao.findById(expectedId[2]);
		
		// Then
		assertEquals("Homer Simpson", actualCustomer0.get().getName());
		assertEquals("Bruce Wayne", actualCustomer1.get().getName());
		assertEquals("Tyrion Lannister", actualCustomer2.get().getName());	
	}
	
	@Test
	public void finding_existing_customer_should_return_customer_bdd() throws Exception {
		// Given
		long expectedId = 10L;
		String expectedName = "John Doe";
		String expectedAddress = "21 Main Street";
		Customer expectedCustomer = new Customer(expectedId, expectedName, expectedAddress);
		
		given(mockEntityManager.find(Customer.class, expectedId)).willReturn(expectedCustomer);
		
		// When
		Optional<Customer> actualCustomer = dao.findById(expectedId);
		
		// Then
		assertTrue(actualCustomer.isPresent());
		assertEquals(expectedId, actualCustomer.get().getId());
		assertEquals(expectedName, actualCustomer.get().getName());
		assertEquals(expectedAddress, actualCustomer.get().getAddress());
	}
	
	@Test
	public void finding_all_customers_should_return_all_customers() throws Exception {
		// Given
		given(mockQuery.getResultList()).willAnswer(i -> Arrays.asList(homerSimpson, bruceWayne, tyrionLannister));
		given(mockEntityManager.createQuery(anyString(), eq(Customer.class))).willReturn(mockQuery);
		
		// When
		List<Customer> actualCustomers = dao.findAll();
		
		// Then
		assertEquals(actualCustomers.size(), 3);		
	}
	
	@Test
	public void updating_customer_should_result_in_latest_version_from_db_being_returned() throws Exception {
		// Given
		Customer inputCustomer = new Customer (10L, "Input", "My Address");
		Customer outputCustomer = new Customer (10L, "Output", "My Address");
		given(mockEntityManager.merge(inputCustomer)).willReturn(outputCustomer);
		
		// When
		Customer actualCustomer = dao.update(inputCustomer);
		
		// Then
		assertEquals(actualCustomer.getName(), outputCustomer.getName());
	}
}
