package com.ait.shopping;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.ArgumentMatchers.anyInt;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.times;
//import org.mockito.internal.verification.Times;

import com.ait.cart.Cart;
import com.ait.cart.Customer;
import com.ait.cart.Product;
import com.ait.dao.CartDAO;
import com.ait.exception.CartDAOException;
import com.ait.exception.CartEmptyException;
import com.ait.exception.CartException;
import com.ait.exception.CartNotFoundException;
import com.ait.exception.CartUserException;
import com.ait.payment.CreditCardStrategy;
import com.ait.payment.PaypalStrategy;
import com.ait.services.*;

public class CheckoutImplTest {
	
	// mocked objects as instructed
	private final CreditCardHandler creditCardHandler = mock(CreditCardHandler.class);
	private final Invoicer invoicer = mock(Invoicer.class);
	private final OrderDispatcher orderDispatcher = mock(OrderDispatcher.class);
	private final PaymentHandler paymentHandler = mock(PaymentHandler.class);
	private final PaymentHandlerFactory paymentHandlerFactory = mock(PaymentHandlerFactory.class);
	private final PayPalHandler payPalHandler = mock(PayPalHandler.class);
	private final CartDAO cartDAO = mock(CartDAO.class);
	
	private CheckoutImpl checkoutImpl;
	private Customer customer;
	private Cart cart;
	private PaypalStrategy payPalStrategy;
	private CreditCardStrategy creditCardStrategy;
	private Product productOne, productTwo;
	
	final static long CUST_ACC_ID = 12345678L;
	final static String EMAIL = "robbie@email.com";
	final static String PWD = "password";
	final static String CUST_NAME = "Robbie";
	final static String CARD_NUM = "1234 1234 1234 1234";
	final static String CVV = "123";
	final static String EXP = "11/23";
	
	@BeforeEach
	void setUp() {
		checkoutImpl = new CheckoutImpl(orderDispatcher, invoicer, cartDAO, paymentHandlerFactory);
		customer = new Customer();
		customer.setAccountNumber(CUST_ACC_ID);
		customer.setAddress("Galway");
		customer.setEmail(EMAIL);
		customer.setName("Robbie");
		cart = new Cart();
		payPalStrategy = new PaypalStrategy(EMAIL, PWD);
		creditCardStrategy = new CreditCardStrategy(CUST_NAME, CARD_NUM, CVV, EXP);
		
		productOne = new Product("Galaxy S21", 1_000, "GS21");
		productTwo = new Product("iPhone 10", 900, "IP10");
		
	}
	
	// Test 1
	@Test
	void testCartUserException() throws CartException, SQLException {
		
		Throwable exception = assertThrows(CartUserException.class, () -> {
			checkoutImpl.checkOutCart(CUST_ACC_ID);
		});
		assertEquals("Unknown Customer: "+ CUST_ACC_ID, exception.getMessage());
		verify(invoicer, times(0)).invoiceCustomer(anyLong(), anyString(), anyFloat());
		verify(creditCardHandler, times(0)).pay(anyString(), anyString(), anyString(), anyString(), anyString(), anyInt());
		verify(payPalHandler, times(0)).pay(anyString(), anyString(), anyString(), anyInt());
		verify(orderDispatcher, times(0)).dispatchItem(anyString(), anyInt(), anyString(), anyString());
		verify(cartDAO, times(1)).findCustomerForId(CUST_ACC_ID);		
	}

	// Test 2
	@Test
	void testCartNotFoundException() throws CartException, SQLException {
		
		when(cartDAO.findCustomerForId(CUST_ACC_ID)).thenReturn(customer);
		
		Throwable exception = assertThrows(CartNotFoundException.class, () -> {
			checkoutImpl.checkOutCart(CUST_ACC_ID);
		});
		assertEquals("No shoppping cart found: " + CUST_ACC_ID, exception.getMessage());
		
		verify(invoicer, times(0)).invoiceCustomer(anyLong(), anyString(), anyFloat());
		verify(creditCardHandler, times(0)).pay(anyString(), anyString(), anyString(), anyString(), anyString(), anyInt());
		verify(payPalHandler, times(0)).pay(anyString(), anyString(), anyString(), anyInt());
		verify(orderDispatcher, times(0)).dispatchItem(anyString(), anyInt(), anyString(), anyString());	
	}
	
	// Test 3
	@Test
	void testCartEmptyException() throws CartException, SQLException {
		
		customer.setCart(cart);
		when(cartDAO.findCustomerForId(CUST_ACC_ID)).thenReturn(customer);
		
		Throwable exception = assertThrows(CartEmptyException.class, () -> {
			checkoutImpl.checkOutCart(CUST_ACC_ID);
		});
		
		assertEquals("Cart is Empty: " + CUST_ACC_ID, exception.getMessage());
		verify(invoicer, times(0)).invoiceCustomer(anyLong(), anyString(), anyFloat());
		verify(creditCardHandler, times(0)).pay(anyString(), anyString(), anyString(), anyString(), anyString(), anyInt());
		verify(payPalHandler, times(0)).pay(anyString(), anyString(), anyString(), anyInt());
		verify(orderDispatcher, times(0)).dispatchItem(anyString(), anyInt(), anyString(), anyString());		
	}
	
	// Test 4
	@Test
	void testCartDAOException() throws CartException, SQLException {
		
		customer.setCart(cart);
		when(cartDAO.findCustomerForId(CUST_ACC_ID)).thenThrow(SQLException.class);
		
		Throwable exception = assertThrows(CartDAOException.class, () -> {
			checkoutImpl.checkOutCart(CUST_ACC_ID);
		});
		
		assertEquals("Error connection to database: "+ CUST_ACC_ID, exception.getMessage());
		verify(invoicer, times(0)).invoiceCustomer(anyLong(), anyString(), anyFloat());
		verify(creditCardHandler, times(0)).pay(anyString(), anyString(), anyString(), anyString(), anyString(), anyInt());
		verify(payPalHandler, times(0)).pay(anyString(), anyString(), anyString(), anyInt());
		verify(orderDispatcher, times(0)).dispatchItem(anyString(), anyInt(), anyString(), anyString());		
	}
	
	// Test 5
	@Test
	void testAddOneItemToCartPaypalSuccess() throws CartException, SQLException {
		cart.addItem(productOne, 1);
		customer.setCart(cart);
		customer.setPaymentStrategy(payPalStrategy);
		customer.setPaymentType("PayPal");

		when(cartDAO.findCustomerForId(CUST_ACC_ID)).thenReturn(customer);
		when(paymentHandlerFactory.getPaymentHandler("PayPal")).thenReturn(payPalHandler);
		when(invoicer.invoiceCustomer(CUST_ACC_ID, EMAIL, productOne.getPrice())).thenReturn("INV123");
		
		checkoutImpl.checkOutCart(CUST_ACC_ID);
		
		verify(invoicer, times(1)).invoiceCustomer(CUST_ACC_ID, EMAIL, 1_000);
		verify(payPalHandler, times(1)).pay("INV123", EMAIL, PWD, 1_000);
		verify(orderDispatcher, times(1)).dispatchItem("GS21", 1, customer.getName(), customer.getAddress());		
		
	}
	
	// Test 6
	@Test
	void testAddOneItemToCartCreditCardSuccess() throws CartException, SQLException {
		cart.addItem(productOne, 1);
		customer.setCart(cart);
		customer.setPaymentStrategy(creditCardStrategy);
		customer.setPaymentType("CreditCard");

		when(cartDAO.findCustomerForId(CUST_ACC_ID)).thenReturn(customer);
		when(paymentHandlerFactory.getPaymentHandler("CreditCard")).thenReturn(creditCardHandler);
		when(invoicer.invoiceCustomer(CUST_ACC_ID, EMAIL, productOne.getPrice())).thenReturn("INV123");
		
		checkoutImpl.checkOutCart(CUST_ACC_ID);
		
		verify(invoicer, times(1)).invoiceCustomer(CUST_ACC_ID, EMAIL, productOne.getPrice());
		verify(payPalHandler, times(0)).pay("INV123", EMAIL, PWD, productOne.getPrice());
		verify(creditCardHandler, times(1)).pay("INV123", CARD_NUM, CUST_NAME, CVV, EXP, productOne.getPrice());
		verify(orderDispatcher, times(1)).dispatchItem("GS21", 1, customer.getName(), customer.getAddress());		
	}
	
	// Test 7
	@Test
	void testAddTwoItemsToCartPaypalSuccess() throws CartException, SQLException {
		cart.addItem(productOne, 1);
		cart.addItem(productTwo, 1);
		customer.setCart(cart);
		customer.setPaymentStrategy(payPalStrategy);
		customer.setPaymentType("PayPal");

		when(cartDAO.findCustomerForId(CUST_ACC_ID)).thenReturn(customer);
		when(paymentHandlerFactory.getPaymentHandler("PayPal")).thenReturn(payPalHandler);
		when(invoicer.invoiceCustomer(CUST_ACC_ID, EMAIL, productOne.getPrice() + productTwo.getPrice())).thenReturn("INV123");
		
		checkoutImpl.checkOutCart(CUST_ACC_ID);
		
		verify(invoicer, times(1)).invoiceCustomer(CUST_ACC_ID, EMAIL, productOne.getPrice() + productTwo.getPrice());
		verify(payPalHandler, times(1)).pay("INV123", EMAIL, PWD, productOne.getPrice() + productTwo.getPrice());
		verify(creditCardHandler, times(0)).pay(anyString(), anyString(), anyString(), anyString(), anyString(), anyInt());
		verify(orderDispatcher, times(1)).dispatchItem("GS21", 1, customer.getName(), customer.getAddress());
		verify(orderDispatcher, times(1)).dispatchItem("IP10", 1, customer.getName(), customer.getAddress());
	}
	
}
