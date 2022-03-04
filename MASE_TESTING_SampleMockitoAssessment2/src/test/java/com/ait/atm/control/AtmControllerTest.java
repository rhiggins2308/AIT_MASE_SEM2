package com.ait.atm.control;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.anyDouble;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.verification.Times;
import com.ait.atm.control.Account;
import com.ait.atm.control.AtmController;
import com.ait.atm.io.AtmInterface;
import com.ait.boundaries.AccountDAO;
import com.ait.boundaries.NotificationService;
import com.ait.exception.ATMAccountException;
import com.ait.exception.ATMPINLimitException;
import com.ait.exception.ATMInsufficientFundsException;


public class AtmControllerTest {

	private final AccountDAO accountDAO=mock(AccountDAO.class);
	private final AtmInterface atmInterface=mock(AtmInterface.class);
	private final NotificationService notificationService=mock(NotificationService.class);
	private AtmController atmController=null;
	private Account account=null;
	static final String ACCOUNT_NAME="John";
	static final String ACCOUNT_NUMBER="123456";
	static final String VALID_PIN="4444";
	static final String INVALID_PIN="4443";
	static final double ACCOUNT_BALANCE=500;
	
	@BeforeEach
	public void setUp() {
		atmController=new AtmController(atmInterface, accountDAO,notificationService);
		account = new Account(ACCOUNT_NAME,ACCOUNT_NUMBER,VALID_PIN,ACCOUNT_BALANCE);
	}
	
	@Test
	public void testWithDrawMoneySuccess() throws Exception {
		when(accountDAO.findAccount(ACCOUNT_NUMBER)).thenReturn(account);
		atmController.cardInserted(ACCOUNT_NUMBER);
		atmController.checkPINCode(VALID_PIN);
		atmController.withDrawAmount(100);
		assertEquals(400,account.getBalance(),0.001);
		verify(atmInterface, new Times(1)).printMsg("Please Enter Amount");
		verify(atmInterface, new Times(1)).printMsg("Please Enter PIN");
		verify(atmInterface, new Times(1)).dispenseMoney(100);
		verify(atmInterface, new Times(1)).printMsg("Please take your money");
		verify(atmInterface, new Times(1)).printReceipt("Receipt for 100.0 euro");
		
	}
	
	@Test
	public void testWithDrawMoneyAccountNotFound() throws Exception {
		when(accountDAO.findAccount(ACCOUNT_NUMBER)).thenReturn(null);
		Throwable exception=assertThrows(ATMAccountException.class, ()->{
			atmController.cardInserted(ACCOUNT_NUMBER);
		});
		assertEquals("Unrecognized: "+ ACCOUNT_NUMBER, exception.getMessage());	
		verify(atmInterface, new Times(0)).dispenseMoney(anyDouble());
	}
	
	@Test
	public void testWithDrawMoneyIncorrectPINOnce() throws Exception {
		when(accountDAO.findAccount(ACCOUNT_NUMBER)).thenReturn(account);
		atmController.cardInserted(ACCOUNT_NUMBER);
		verify(atmInterface, new Times(1)).printMsg("Please Enter PIN");
		atmController.checkPINCode(INVALID_PIN);
		verify(atmInterface, new Times(1)).printMsg("Invalid PIN- Please Re-Enter");
		atmController.checkPINCode(VALID_PIN);
		verify(atmInterface, new Times(1)).printMsg("Please Enter Amount");
		verify(notificationService, new Times(1)).securityAlert(ACCOUNT_NUMBER);
		verify(atmInterface, new Times(0)).dispenseMoney(anyDouble());
	}
	
	@Test
	public void testWithDrawMoneyIncorrectPINTwice() throws Exception {
		when(accountDAO.findAccount(ACCOUNT_NUMBER)).thenReturn(account);
		atmController.cardInserted(ACCOUNT_NUMBER);
		verify(atmInterface, new Times(1)).printMsg("Please Enter PIN");
		atmController.checkPINCode(INVALID_PIN);
		verify(atmInterface, new Times(1)).printMsg("Invalid PIN- Please Re-Enter");
		atmController.checkPINCode(INVALID_PIN);
		verify(atmInterface, new Times(1)).printMsg("Please Enter PIN");
		atmController.checkPINCode(VALID_PIN);
		verify(atmInterface, new Times(1)).printMsg("Please Enter Amount");
		verify(notificationService, new Times(2)).securityAlert(ACCOUNT_NUMBER);
		verify(atmInterface, new Times(0)).dispenseMoney(anyDouble());
		
	}
	
	@Test
	public void testWithDrawMoneyIncorrectPINThreeTimes() throws Exception {
		when(accountDAO.findAccount(ACCOUNT_NUMBER)).thenReturn(account);
		atmController.cardInserted(ACCOUNT_NUMBER);
		verify(atmInterface, new Times(1)).printMsg("Please Enter PIN");
		atmController.checkPINCode(INVALID_PIN);
		verify(atmInterface, new Times(1)).printMsg("Invalid PIN- Please Re-Enter");
		atmController.checkPINCode(INVALID_PIN);
		verify(atmInterface, new Times(2)).printMsg("Invalid PIN- Please Re-Enter");
		verify(notificationService, new Times(2)).securityAlert(ACCOUNT_NUMBER);
		Throwable exception=assertThrows(ATMPINLimitException.class, ()->{
			atmController.checkPINCode(INVALID_PIN);
		});
		assertEquals("Maximum incorrect PIN limit", exception.getMessage());
		verify(atmInterface, new Times(0)).dispenseMoney(anyDouble());
		
	}
	
	@Test
	public void testWithDrawMoneyInsufficientFunds() throws Exception {
		when(accountDAO.findAccount(ACCOUNT_NUMBER)).thenReturn(account);
			atmController.cardInserted(ACCOUNT_NUMBER);
		verify(atmInterface, new Times(1)).printMsg("Please Enter PIN");
		atmController.checkPINCode(VALID_PIN);
		verify(atmInterface, new Times(1)).printMsg("Please Enter Amount");
		Throwable exception=assertThrows(ATMInsufficientFundsException.class, ()->{
			atmController.withDrawAmount(501);
		});
		assertEquals("Insufficient Funds: 501.0",exception.getMessage());
		verify(atmInterface, new Times(0)).dispenseMoney(anyDouble());
	}

}
