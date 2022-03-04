package com.tus.entity;

public class PaymentDetails {
	
	String bankAccountIBAN;
	String bankBranchAddress;
	String bankAccountName;
	
	public PaymentDetails(String bankAccountIBAN, String bankBranchAddress, String bankAccountName) {
		super();
		this.bankAccountIBAN = bankAccountIBAN;
		this.bankBranchAddress = bankBranchAddress;
		this.bankAccountName = bankAccountName;
	}
}
