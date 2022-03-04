package com.depot.transactions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyString;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ait.paypal.PayPalFacade;
import com.depot.dao.FinancialTransactionDAO;
import com.depot.dao.MembershipDAO;
import com.depot.dto.MembershipStatusDto;
import com.depot.dto.PaymentAdviceDto;
import com.depot.dto.TransactionDto;
import com.depot.exceptions.MusicDepotDAOException;
import com.depot.exceptions.MusicDepotException;
import com.depot.exceptions.MusicDepotPayPalException;

public class ReconciliationJobTest {

	// mock objects
	private final FinancialTransactionDAO ftdao = mock(FinancialTransactionDAO.class);
	private final MembershipDAO mdao = mock(MembershipDAO.class);
	private final PayPalFacade ppFace = mock(PayPalFacade.class);
	
	private ReconciliationJob recon;
	private PaymentAdviceDto padto;
	private ArrayList<TransactionDto> alTranDto;
	private TransactionDto txdto;
	private TransactionDto txdto_2;
	private MembershipStatusDto msdto;
	
	private final static String CLIENT_ID = "12345";
	private final static String CLIENT_PAYPAL_ID = "PP12345";
	private final static double TX_AMT_1 = 200.00;
	private final static double TX_AMT_2 = 400.00;
	private final static double fee = 0.1;
	
	@BeforeEach
	public void setUp() {
		recon = new ReconciliationJob(ftdao, mdao, ppFace);
		alTranDto = new ArrayList<>();
		msdto = new MembershipStatusDto(CLIENT_PAYPAL_ID, fee);
		txdto = new TransactionDto(CLIENT_ID, TX_AMT_1);
		txdto_2 = new TransactionDto(CLIENT_ID, TX_AMT_2);
	}
	
	// Test 1
	@Test
	void whenNoTransactionToProcessReturnsZero() throws MusicDepotException, SQLException {
		assertEquals(recon.reconcile(CLIENT_ID), 0);
		
		verify(ftdao, times(1)).retrieveUnSettledTransactions(CLIENT_ID);
		verify(mdao, times(0)).setPaymentAdvice(padto);
		verify(ppFace, times(0)).sendAdvice(anyString(), anyString());
	}
	
	// Test 2
	@Test
	void testMusicDepotDAOException() throws MusicDepotException, SQLException {
		
		when(ftdao.retrieveUnSettledTransactions(CLIENT_ID)).thenThrow(SQLException.class);
		
		Throwable exception = assertThrows(MusicDepotDAOException.class, () -> {
			recon.reconcile(CLIENT_ID);
		});
		
		assertEquals("Error in connection to MusicDepot database", exception.getMessage());
		verify(ftdao, times(1)).retrieveUnSettledTransactions(CLIENT_ID);
		verify(mdao, times(0)).setPaymentAdvice(padto);
		verify(ppFace, times(0)).sendAdvice(anyString(), anyString());
	}
	
	// Test 3
	@Test
	void whenOneTransactionToProcessReturnsOne() throws SQLException, MusicDepotException {
		
		when(mdao.getStatusFor(CLIENT_ID)).thenReturn(msdto);
		alTranDto.add(txdto);
		when(ftdao.retrieveUnSettledTransactions(CLIENT_ID)).thenReturn(alTranDto);
		
		mdao.setPaymentAdvice(padto);
				
		DecimalFormat f= new DecimalFormat("##.00");
		String formatPayableAmount=f.format(200.00 - (200 * 0.1));
		
		assertEquals(recon.reconcile(CLIENT_ID), 1);
		
		verify(ftdao, times(1)).retrieveUnSettledTransactions(CLIENT_ID);
		verify(ppFace, times(1)).sendAdvice(CLIENT_PAYPAL_ID, "Sent payment for " + CLIENT_ID + " of " + formatPayableAmount + " euro");
		verify(mdao, times(1)).setPaymentAdvice(padto);
		
	}
	
	// Test 4
	@Test
	void whenTwoTransactionsToProcessReturnsTwo() throws SQLException, MusicDepotException {
		when(mdao.getStatusFor(CLIENT_ID)).thenReturn(msdto);
		alTranDto.add(txdto);
		alTranDto.add(txdto_2);
		
		when(ftdao.retrieveUnSettledTransactions(CLIENT_ID)).thenReturn(alTranDto);
		
		mdao.setPaymentAdvice(padto);
				
		DecimalFormat f= new DecimalFormat("##.00");
		String formatPayableAmount=f.format(600.00 - (600 * 0.1));
		
		assertEquals(recon.reconcile(CLIENT_ID), 2);
		
		verify(ftdao, times(1)).retrieveUnSettledTransactions(CLIENT_ID);
		verify(ppFace, times(1)).sendAdvice(CLIENT_PAYPAL_ID, "Sent payment for " + CLIENT_ID + " of " + formatPayableAmount + " euro");
		verify(mdao, times(1)).setPaymentAdvice(padto);
		
	}
	
	// Test 5
	@Test
	void testMusicDepotPaypalException() throws MusicDepotException, SQLException {
		
		when(mdao.getStatusFor(CLIENT_ID)).thenReturn(msdto);
		alTranDto.add(txdto);
		
		when(ftdao.retrieveUnSettledTransactions(CLIENT_ID)).thenReturn(alTranDto);
		
		DecimalFormat f= new DecimalFormat("##.00");
		String formatPayableAmount=f.format(600.00 - (600 * 0.1));
		
		when(ppFace.sendAdvice(anyString(), anyString())).thenThrow(MusicDepotPayPalException.class);
		
		Throwable exception = assertThrows(MusicDepotPayPalException.class, () -> {
			recon.reconcile(CLIENT_ID);
		});
		assertEquals("PayPal Error", exception.getMessage());
	
		//verify(ftdao, times(1)).retrieveUnSettledTransactions(CLIENT_ID);
		verify(mdao, times(0)).setPaymentAdvice(padto);
		//verify(ppFace, times(0)).sendAdvice(anyString(), anyString());
	
	}
}
