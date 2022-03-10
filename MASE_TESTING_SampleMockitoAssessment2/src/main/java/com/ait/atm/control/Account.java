package com.ait.atm.control;

public class Account {
	private String name;
	private String accountNum;
	private String PIN;
	private double balance;
	
	public Account(String name, String accountNum, String PIN, double balance) {
		this.name=name;
		this.accountNum=accountNum;
		this.PIN=PIN;
		this.balance=balance;
	}

	public String getPIN() {
		return PIN;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	

}
