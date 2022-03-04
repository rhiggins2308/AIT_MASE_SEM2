package com.ait.atm.control;

import com.ait.atm.io.AtmInterface;
import com.ait.boundaries.AccountDAO;
import com.ait.boundaries.NotificationService;
import com.ait.exception.ATMAccountException;
import com.ait.exception.ATMException;
import com.ait.exception.ATMInsufficientFundsException;
import com.ait.exception.ATMPINLimitException;

public class AtmController {
	private AtmInterface atmInterface;
	private AccountDAO accountDAO;
	private Account account;
	private NotificationService notificationService;
	private int numPINAttempts;
	private String accountNum;
	
	
	public AtmController(AtmInterface atmInterface,AccountDAO accountDAO, NotificationService notificationService) {
		this.atmInterface=atmInterface;
		this.accountDAO=accountDAO;
		this.notificationService=notificationService;
	}
	
	public void cardInserted(String accountNum) throws ATMException {
		numPINAttempts=0;	
		account=accountDAO.findAccount(accountNum);
		if(account!=null) {
			this.accountNum=accountNum;
			atmInterface.printMsg("Please Enter PIN");
		}
		else {
			throw new ATMAccountException(accountNum);
		}
		
	}
	
	public void checkPINCode(String pinCode) throws ATMException {
		numPINAttempts++;
		if (pinCode==account.getPIN()) {
			atmInterface.printMsg("Please Enter Amount");
		}
		else {
			if (numPINAttempts<3) {
				notificationService.securityAlert(accountNum);
				atmInterface.printMsg("Invalid PIN- Please Re-Enter");
			}
			else {
				throw new ATMPINLimitException();
			}
		}
	}
	
	public void withDrawAmount(double amount) throws ATMException {
		double balance=account.getBalance();
		if (amount<=balance) {
			atmInterface.dispenseMoney(amount);
			account.setBalance(balance-amount);
			atmInterface.printMsg("Please take your money");
			atmInterface.printReceipt("Receipt for "+amount+" euro");
		}
		else {
				throw new ATMInsufficientFundsException(amount);
			}
		}
	

}
