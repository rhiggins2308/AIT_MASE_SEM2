package com.ait.exception;

public class ATMAccountException extends ATMException {

	private static final long serialVersionUID = 334051992916748022L;

	public ATMAccountException(final String AccountNum) {
		super("Unrecognized: "+ AccountNum);
	}

}
