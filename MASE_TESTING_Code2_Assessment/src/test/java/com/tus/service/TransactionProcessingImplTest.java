package com.tus.service;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anyObject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ait.security.AuthenticationFilter;
import com.tus.dao.ArtGalleryDAO;
import com.tus.dispatch.Dispatcher;
import com.tus.entity.Artist;
import com.tus.entity.Painting;
import com.tus.entity.PaymentDetails;
import com.tus.entity.TransactionDto;
import com.tus.exception.ArtGalleryDAOException;
import com.tus.exception.ArtGalleryException;
import com.tus.exception.ArtGallerySecurityException;
import com.tus.exception.ArtistNotFoundException;
import com.tus.payment.PaymentFacade;

class TransactionProcessingImplTest {
	
	// mock objects
	private final ArtGalleryDAO artGalleryDAO = mock(ArtGalleryDAO.class);
	private final PaymentFacade paymentFacade = mock(PaymentFacade.class);
	private final AuthenticationFilter authenticationFilter = mock(AuthenticationFilter.class);
	private final Dispatcher dispatcher = mock(Dispatcher.class);
	
	private TransactionProcessingImpl transactionProcessingImpl;
	private Artist artist;
	private PaymentDetails paymentDetails;
	private TransactionDto transactionDto_1, transactionDto_2;
	private ArrayList<TransactionDto> transactions;
	private Painting painting_1, painting_2;
	
	private static final String ARTIST_ID = "1", SECURITY_TOKEN = "pass";
	private static final String IBAN = "AIB123", BANK_ADDR = "BANK ADDRESS", ACCOUNT_NAME = "Robbie Gots Money";
	private static final String PAINTING_ID_1 = "paint_1", PAINTING_ID_2 = "2";
	private static final double PAINT_COST_1 = 2_000.00, PAINT_COST_2 = 20_000.00, FEE = 0.1;
		
	@BeforeEach
	public void setUp() {
		transactionProcessingImpl = new TransactionProcessingImpl(artGalleryDAO, paymentFacade, authenticationFilter, dispatcher);
		paymentDetails = new PaymentDetails(IBAN, BANK_ADDR, ACCOUNT_NAME);
		artist = new Artist(ARTIST_ID, paymentDetails);
		painting_1 = new Painting(PAINTING_ID_1, "Painting Title 1", PAINT_COST_1);
		painting_2 = new Painting(PAINTING_ID_2, "Painting Title 2", PAINT_COST_2);
		
		transactions = new ArrayList<>();
		transactionDto_1 = new TransactionDto();
		transactionDto_2 = new TransactionDto();
	}

	
	// Test 1
	@SuppressWarnings("deprecation")
	@Test
	void testArtGallerySecurityException() throws ArtGalleryException, SQLException {
		
		Throwable exception = assertThrows(ArtGallerySecurityException.class, () -> {
			transactionProcessingImpl.processTransactions(ARTIST_ID, SECURITY_TOKEN);
		});
		
		assertEquals("Authentication Failed", exception.getMessage());
		verify(paymentFacade, times(0)).pay(anyString(), anyDouble(), anyObject());
	}
	
	
	// Test 2
	@SuppressWarnings("deprecation")
	@Test
	void testArtGallerySQLException() throws SQLException, ArtGalleryException {
	
		when(authenticationFilter.isTokenValid(SECURITY_TOKEN)).thenReturn(true);
		when(artGalleryDAO.getArtistForId(ARTIST_ID)).thenReturn(artist);
		
		when(transactionProcessingImpl.processTransactions(ARTIST_ID, SECURITY_TOKEN)).thenThrow(SQLException.class);
		
		Throwable exception = assertThrows(ArtGalleryDAOException.class, () -> {
			transactionProcessingImpl.processTransactions(ARTIST_ID, SECURITY_TOKEN);
		});
		
		assertEquals("Error connecting to database", exception.getMessage());	
		verify(dispatcher, times(0)).dispatch(anyString(), anyString());
		verify(paymentFacade, times(0)).pay(anyString(), anyDouble(), anyObject());
	}
	
	
	// Test 3
	@SuppressWarnings("deprecation")
	@Test
	void testArtGalleryArtistNotFound() throws ArtGalleryException, SQLException {
		
		when(authenticationFilter.isTokenValid(SECURITY_TOKEN)).thenReturn(true);
		when(artGalleryDAO.getArtistForId(ARTIST_ID)).thenReturn(null);
		
		Throwable exception = assertThrows(ArtistNotFoundException.class, () -> {
			transactionProcessingImpl.processTransactions(ARTIST_ID, SECURITY_TOKEN);
		});
		
		assertEquals("Artist with Id "+ ARTIST_ID +" not found", exception.getMessage());
		verify(dispatcher, times(0)).dispatch(anyString(), anyString());
		verify(paymentFacade, times(0)).pay(anyString(), anyDouble(), anyObject());		
	}
	
	
	// Test 4
	@SuppressWarnings("deprecation")
	@Test
	void testNoTransactionToProcess() throws Exception {
	
		when(authenticationFilter.isTokenValid(SECURITY_TOKEN)).thenReturn(true);
		when(artGalleryDAO.getArtistForId(ARTIST_ID)).thenReturn(artist);
		
		assertEquals(0, transactionProcessingImpl.processTransactions(ARTIST_ID, SECURITY_TOKEN));
		verify(dispatcher, times(0)).dispatch(anyString(), anyString());
		verify(paymentFacade, times(0)).pay(anyString(), anyDouble(), anyObject());
	}
	
	
	// Test 5
	@Test
	void testOneTransactionToProcess() throws Exception {
		
		transactionDto_1.setPurchaserId("1");
		transactionDto_1.setPaintingId(PAINTING_ID_1);
		transactions.add(transactionDto_1);
		
		when(authenticationFilter.isTokenValid(SECURITY_TOKEN)).thenReturn(true);
		when(artGalleryDAO.getArtistForId(ARTIST_ID)).thenReturn(artist);
		when(artGalleryDAO.getPaintingForId(PAINTING_ID_1)).thenReturn(painting_1);
		when(artGalleryDAO.retrieveUnSettledTransactions(ARTIST_ID)).thenReturn(transactions);
		
		assertEquals(1, transactionProcessingImpl.processTransactions(ARTIST_ID, SECURITY_TOKEN));
		verify(dispatcher, times(1)).dispatch("Painting Title 1", "1");
		verify(paymentFacade, times(1)).pay(ARTIST_ID, PAINT_COST_1 - (PAINT_COST_1 * FEE), paymentDetails);
	}
	
	
	// Test 6
	@Test
	void testTwoTransactionsToProcess() throws Exception {
		transactionDto_1.setPurchaserId("1");
		transactionDto_1.setPaintingId(PAINTING_ID_1);
		transactions.add(transactionDto_1);
		
		transactionDto_2.setPurchaserId("2");
		transactionDto_2.setPaintingId(PAINTING_ID_2);
		transactions.add(transactionDto_2);
		
		when(authenticationFilter.isTokenValid(SECURITY_TOKEN)).thenReturn(true);
		when(artGalleryDAO.getArtistForId(ARTIST_ID)).thenReturn(artist);
		when(artGalleryDAO.getPaintingForId(PAINTING_ID_1)).thenReturn(painting_1);
		when(artGalleryDAO.getPaintingForId(PAINTING_ID_2)).thenReturn(painting_2);
		
		when(artGalleryDAO.retrieveUnSettledTransactions(ARTIST_ID)).thenReturn(transactions);
		assertEquals(2, transactionProcessingImpl.processTransactions(ARTIST_ID, SECURITY_TOKEN));
		verify(dispatcher, times(1)).dispatch("Painting Title 1", "1");
		verify(dispatcher, times(1)).dispatch("Painting Title 2", "2");
		verify(paymentFacade, times(1)).pay(ARTIST_ID, (PAINT_COST_1 + PAINT_COST_2) - ((PAINT_COST_1 + PAINT_COST_2) * FEE), paymentDetails);	
	}		
}
