package com.ait.exception;

public class ATMInsufficientFundsException extends ATMException {

	private static final long serialVersionUID = 334051992916748022L;

	public ATMInsufficientFundsException(final double amount) {
		super("Insufficient Funds: "+ amount);
	}

}
