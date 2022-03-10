package com.ait.control;

import java.sql.SQLException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.internal.verification.Times;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ait.dao.*;
import com.ait.entities.*;
import com.ait.payment.CreditCardStrategy;
import com.ait.payment.PaypalStrategy;
import com.ait.services.CreditCardHandler;
import com.ait.services.Dispenser;
import com.ait.services.PayPalHandler;
import com.ait.services.PaymentHandlerFactory;
import com.ait.exception.MedAvailException;
import com.ait.exception.MedAvailOutOfStockException;
import com.ait.exception.MedAvailCustomerException;
import com.ait.exception.MedAvailDAOException;
import com.ait.exception.MedAvailPrescriptionException;

public class MedAvailControllerImplTest {

	// mocked objects as instructed
	private final Dispenser dispenser = mock(Dispenser.class);
	private final MedAvailDAO dao = mock(MedAvailDAO.class);
	private final PaymentHandlerFactory paymentHandlerFactory = mock(PaymentHandlerFactory.class);
	private final PayPalHandler payPalHandler = mock(PayPalHandler.class);
	private final CreditCardHandler creditCardHandler = mock(CreditCardHandler.class);
	
	// declare other object references, as required
	private MedAvailControllerImpl medAvailControllerImpl;
	private Customer customer;
	private Prescription prescription;
	private Product productOne, productTwo;
	private PaypalStrategy paypalStrategy;
	private CreditCardStrategy creditCardStrategy;
	
	// static variables required for instantiation, as required
	final static String USER_EMAIL = "robbie@email.com";
	final static String USER_PWD = "password";
	final static String CARD_NAME = "Robert Higgins";
	final static String CARD_NUM = "1234 1234 1234 1234";
	final static String EXPIRY = "12/23";
	final static String CVV = "123";
	
	final static long CUST_ACC_ID = 12345678L;
	final static String CUST_NAME = "Robbie Higgins";
	final static String CUST_ADDRESS = "Galway";
	final static String CUST_EMAIL = "robbie@email.com";
	
	final static long PRODUCT_ONE_CODE = 11111L;
	final static long PRODUCT_TWO_CODE = 22222L;
	final static double PRODUCT_ONE_COST = 13.95; 
	final static double PRODUCT_TWO_COST = 11.15;
	
	final static long PRESCRIPTION_ID = 23456789L;
	
	// instantiate objects required for individual tests
	@BeforeEach
	void setUp() {
		
		paypalStrategy = new PaypalStrategy(USER_EMAIL, USER_PWD);
		creditCardStrategy = new CreditCardStrategy(CARD_NAME, CARD_NUM, CVV, EXPIRY);
		
		customer = new Customer(CUST_ACC_ID, CUST_NAME, CUST_ADDRESS, CUST_EMAIL, "CreditCard", creditCardStrategy);
		
		productOne = new Product(PRODUCT_ONE_CODE, PRODUCT_ONE_COST);
		productTwo = new Product(PRODUCT_TWO_CODE, PRODUCT_TWO_COST);
		
		prescription = new Prescription();
		prescription.addPrescriptionItem(productOne);
		
		medAvailControllerImpl = new MedAvailControllerImpl(dao, dispenser, paymentHandlerFactory);
	}
	
	
	// Test 1
	@Test
	void testCustomerNotFoundException() throws MedAvailException, SQLException {
		
		// setup required objects to progress to point where any Exception will be thrown
		// in this case ... no setup required
		
		// Assert that exception will be thrown when CUST_ACC_ID is passed to the executing method
		// i.e. this customer ID does not exist
		Throwable exception = assertThrows(MedAvailCustomerException.class, () -> {
			// getCustomerForId() is called from within processPrescription() in medAvailControllerImpl.
			// make this call via the instantiated implementation object with all required parameters to allow a successful call
			medAvailControllerImpl.processPrescription(CUST_ACC_ID, PRESCRIPTION_ID);
		});
		
		// Test that the exception message matches what is expected by the thrown exception
		assertEquals("Unknown Customer: "+ CUST_ACC_ID, exception.getMessage());
		
		// verify relevant method calls using relevant declared constant attributes (not getters)
		verify(dao, new Times(1)).getCustomerForId(CUST_ACC_ID);
		verify(dao, new Times(1)).getPrescriptionForId(PRESCRIPTION_ID);
		
		// when verifying zero runs use the any() matchers
		verify(creditCardHandler, new Times(0)).pay(anyLong(), anyString(), anyString(), anyString(), anyString(), anyDouble());
		verify(dispenser, new Times(0)).dispense(anyLong());
	}
	
	
	// Test 2
	@Test
	void testPrescriptionNotFoundException() throws MedAvailException, SQLException {
		
		// setup required objects to progress to point where any Exception will be thrown
		when(dao.getCustomerForId(CUST_ACC_ID)).thenReturn(customer);
		
		// Assert that relevant exception will be thrown
		Throwable exception = assertThrows(MedAvailPrescriptionException.class, () -> {
			
			// getPrescriptionId() is called from within processPrescription() in medAvailControllerImpl.
			// make this call via the instantiated implementation object with all required parameters to allow a successful call
			medAvailControllerImpl.processPrescription(CUST_ACC_ID, PRESCRIPTION_ID);
		});
		
		// Test that the exception message matches what is expected by the thrown exception
		assertEquals("Prescription not found: "+ PRESCRIPTION_ID, exception.getMessage());
		
		// verify relevant method calls using relevant declared constant attributes (not getters)
		verify(dao, new Times(1)).getCustomerForId(CUST_ACC_ID);
		verify(dao, new Times(1)).getPrescriptionForId(PRESCRIPTION_ID);
		
		// when verifying zero runs use the any() matchers
		verify(creditCardHandler, new Times(0)).pay(anyLong(), anyString(), anyString(), anyString(), anyString(), anyDouble());
		verify(dispenser, new Times(0)).dispense(anyLong());
	}
	
	
	// Test 3
	@Test
	void testOneItemOnPrescriptionSuccess() throws MedAvailException, SQLException {
		
		// setup required objects to progress to point where any Exception will be thrown
		// in this case, no exception. Successful run through, so all objects should be returned
		when(dao.getCustomerForId(CUST_ACC_ID)).thenReturn(customer);
		when(dao.getPrescriptionForId(PRESCRIPTION_ID)).thenReturn(prescription);
		when(dao.checkProductInStock(PRODUCT_ONE_CODE)).thenReturn(true);
		when(paymentHandlerFactory.getPaymentHandler("CreditCard")).thenReturn(creditCardHandler);
		
		// call processPrescription() method
		medAvailControllerImpl.processPrescription(CUST_ACC_ID, PRESCRIPTION_ID);
		
		// verify that other subsequent method calls have been made (or not) as required, with relevant parameters
		verify(dao, new Times(1)).getCustomerForId(CUST_ACC_ID);
		verify(dao, new Times(1)).getPrescriptionForId(PRESCRIPTION_ID);
		verify(dao, new Times(1)).checkProductInStock(PRODUCT_ONE_CODE);
		
		// when verifying zero runs use the any() matchers
		verify(creditCardHandler, new Times(1)).pay(PRESCRIPTION_ID, CARD_NUM, CARD_NAME, CVV, EXPIRY, PRODUCT_ONE_COST);
		verify(dispenser, new Times(1)).dispense(PRODUCT_ONE_CODE);
	}
	
	
	// Test 4
	@Test
	void testMedAvailDAOExceptionCustomer() throws MedAvailException, SQLException {
		
		// setup required objects to progress to point where any Exception will be thrown
		// in this case, from assessment instructions, showing how to mock DAO into throwing SQL exception when specified method is called
		when(dao.getCustomerForId(CUST_ACC_ID)).thenThrow(SQLException.class);

		// Assert that relevant exception will be thrown when processPrescription() method is called
		Throwable exception = assertThrows(MedAvailDAOException.class, () -> {
			
			medAvailControllerImpl.processPrescription(CUST_ACC_ID, PRESCRIPTION_ID);
		});
		
		// Test that the exception message matches what is expected by the thrown exception
		assertEquals("Error in connection to MedAvail database", exception.getMessage());
		
		// verify that other subsequent method calls have been made (or not) as required, with relevant parameters
		verify(dao, new Times(1)).getCustomerForId(CUST_ACC_ID);
		
		// when verifying zero runs use the any() matchers
		verify(dao, new Times(0)).getPrescriptionForId(anyLong());
		verify(dispenser, new Times(0)).dispense(anyLong());	
	}
	
	
	// Test 5
	@Test
	void testMedAvailDAOExceptionPrescription() throws MedAvailException, SQLException {
		
		// setup required objects to progress to point where SQL Exception will be thrown
		when(dao.getCustomerForId(CUST_ACC_ID)).thenReturn(customer);
		
		// mock relevant method call to return Exception type
		when(dao.getPrescriptionForId(PRESCRIPTION_ID)).thenThrow(SQLException.class);
		
		// Assert that relevant exception will be thrown when processPrescription() method is called
		Throwable exception = assertThrows(MedAvailDAOException.class, () -> {
			
			medAvailControllerImpl.processPrescription(CUST_ACC_ID, PRESCRIPTION_ID);
		});
		
		// Test that the exception message matches what is expected by the thrown exception
		assertEquals("Error in connection to MedAvail database", exception.getMessage());
		
		// verify that other subsequent method calls have been made (or not) as required, with relevant parameters
		verify(dao, new Times(1)).getCustomerForId(CUST_ACC_ID);
		verify(dao, new Times(1)).getPrescriptionForId(PRESCRIPTION_ID);
				
		// when verifying zero runs use the any() matchers
		verify(dispenser, new Times(0)).dispense(anyLong());	
	}
	
	// Test 6	
	@Test
	void testOneItemOnPrescriptionCheckStockException() throws MedAvailException, SQLException {
		
		
		// setup required objects to progress to point where SQL Exception will be thrown
		when(dao.getCustomerForId(CUST_ACC_ID)).thenReturn(customer);
		when(dao.getPrescriptionForId(PRESCRIPTION_ID)).thenReturn(prescription);
		
		// specify the method call that will throw the SQL Exception
		when(dao.checkProductInStock(PRODUCT_ONE_CODE)).thenThrow(SQLException.class);

		// test that the exception thrown is the MedAvailDAOException
		Throwable exception = assertThrows(MedAvailDAOException.class, () -> {
			
			medAvailControllerImpl.processPrescription(CUST_ACC_ID, PRESCRIPTION_ID);
		});
		
		// test that the exception message matches that for the expected thrown exception
		assertEquals("Error in connection to MedAvail database", exception.getMessage());
		
		// verify relevant method calls
		verify(dao, new Times(1)).getCustomerForId(CUST_ACC_ID);
		verify(dao, new Times(1)).getPrescriptionForId(PRESCRIPTION_ID);
		verify(dao, new Times(1)).checkProductInStock(PRODUCT_ONE_CODE);
				
		// when verifying zero runs use the any() matchers
		verify(creditCardHandler, new Times(0)).pay(anyLong(), anyString(), anyString(), anyString(), anyString(), anyDouble());
		verify(dispenser, new Times(0)).dispense(anyLong());
	}
	
	// Test 7
	@Test
	void testOutOfStockDAOException() throws MedAvailException, SQLException {
		
		// setup required objects to progress to point where SQL Exception will be thrown
		when(dao.getCustomerForId(CUST_ACC_ID)).thenReturn(customer);
		when(dao.getPrescriptionForId(PRESCRIPTION_ID)).thenReturn(prescription);
		when(dao.checkProductInStock(PRODUCT_ONE_CODE)).thenReturn(false);
	
		// test that the exception thrown is the MedAvailDAOException
		Throwable exception = assertThrows(MedAvailOutOfStockException.class, () -> {
			
			medAvailControllerImpl.processPrescription(CUST_ACC_ID, PRESCRIPTION_ID);
		});
				
		// test that the exception message matches that for the expected thrown exception
		assertEquals("Product Out of Stock "+ PRODUCT_ONE_CODE, exception.getMessage());
		
		// verify relevant method calls
		verify(dao, new Times(1)).getCustomerForId(CUST_ACC_ID);
		verify(dao, new Times(1)).getPrescriptionForId(PRESCRIPTION_ID);
		verify(dao, new Times(1)).checkProductInStock(PRODUCT_ONE_CODE);
		
		// when verifying zero runs use the any() matchers
		verify(creditCardHandler, new Times(0)).pay(anyLong(), anyString(), anyString(), anyString(), anyString(), anyDouble());
		verify(dispenser, new Times(0)).dispense(anyLong());
	}
	
	
	// Test 8
	@Test
	void testTwoItemsOnPrescriptionSuccess() throws MedAvailException, SQLException {
		// setup required objects to progress to point where any Exception will be thrown
		// in this case, no exception. Successful run through, so all objects should be returned
		when(dao.getCustomerForId(CUST_ACC_ID)).thenReturn(customer);
		when(dao.getPrescriptionForId(PRESCRIPTION_ID)).thenReturn(prescription);
		when(dao.checkProductInStock(PRODUCT_ONE_CODE)).thenReturn(true);
		when(dao.checkProductInStock(PRODUCT_TWO_CODE)).thenReturn(true);
		prescription.addPrescriptionItem(productTwo);
		
		when(paymentHandlerFactory.getPaymentHandler("CreditCard")).thenReturn(creditCardHandler);
		
		// call processPrescription() method
		medAvailControllerImpl.processPrescription(CUST_ACC_ID, PRESCRIPTION_ID);
		
		// verify that other subsequent method calls have been made (or not) as required, with relevant parameters
		verify(dao, new Times(1)).getCustomerForId(CUST_ACC_ID);
		verify(dao, new Times(1)).getPrescriptionForId(PRESCRIPTION_ID);
		verify(dao, new Times(1)).checkProductInStock(PRODUCT_ONE_CODE);
		verify(dao, new Times(1)).checkProductInStock(PRODUCT_TWO_CODE);
		
		// when verifying zero runs use the any() matchers
		verify(creditCardHandler, new Times(1)).pay(PRESCRIPTION_ID, CARD_NUM, CARD_NAME, CVV, EXPIRY, PRODUCT_ONE_COST + PRODUCT_TWO_COST);
		verify(dispenser, new Times(1)).dispense(PRODUCT_ONE_CODE);
		verify(dispenser, new Times(1)).dispense(PRODUCT_TWO_CODE);
	}
	
	
	// Test 9
	@Test
	void testOneItemOnPrescriptionPayPal() throws MedAvailException, SQLException {
		// setup required objects to progress to point where any Exception will be thrown
		// in this case, no exception. Successful run through, so all objects should be returned
		customer.setPaymentType("PayPal");
		customer.setPaymentStrategy(paypalStrategy);
		
		when(dao.getCustomerForId(CUST_ACC_ID)).thenReturn(customer);
		when(dao.getPrescriptionForId(PRESCRIPTION_ID)).thenReturn(prescription);
		when(dao.checkProductInStock(PRODUCT_ONE_CODE)).thenReturn(true);
		
		when(paymentHandlerFactory.getPaymentHandler("PayPal")).thenReturn(payPalHandler);
		
		// call processPrescription() method
		medAvailControllerImpl.processPrescription(CUST_ACC_ID, PRESCRIPTION_ID);
		
		// verify that other subsequent method calls have been made (or not) as required, with relevant parameters
		verify(dao, new Times(1)).getCustomerForId(CUST_ACC_ID);
		verify(dao, new Times(1)).getPrescriptionForId(PRESCRIPTION_ID);
		verify(dao, new Times(1)).checkProductInStock(PRODUCT_ONE_CODE);
		
		// when verifying zero runs use the any() matchers
		verify(payPalHandler, new Times(1)).pay(PRESCRIPTION_ID, USER_EMAIL, USER_PWD, PRODUCT_ONE_COST);
		verify(dispenser, new Times(1)).dispense(PRODUCT_ONE_CODE);
	}	
}
