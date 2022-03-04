package com.depot.dto;

public class TransactionDto {

	private String clientId;
	private double amount;
	
	public TransactionDto(String clientId, double amount) {
		this.clientId=clientId;
		this.amount=amount;
	}
	public double getAmount() {
		return amount;
	}


	public String getClientId() {
		return clientId;
	}

}
