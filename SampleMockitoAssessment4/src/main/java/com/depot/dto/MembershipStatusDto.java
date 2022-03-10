package com.depot.dto;

public class MembershipStatusDto {

	private double deductable; //amount that should be deducted e.g 0.1 for 10%
	private String clientPayPalId;
	
	
	public MembershipStatusDto(String clientPayPalId, double deductable) {
		this.clientPayPalId=clientPayPalId;
		this.deductable=deductable;
	}
	public double getDeductable() {
		return deductable;
	}

	public String getClientPayPalId() {
		return clientPayPalId;
	}

}
