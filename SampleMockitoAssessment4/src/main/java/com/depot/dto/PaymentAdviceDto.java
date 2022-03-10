package com.depot.dto;

public class PaymentAdviceDto {
	private final double amount;
	private final String clientPayPalId;
	private final String desc;

	public PaymentAdviceDto(double amount, String clientPayPalId, String desc) {
		super();
		this.amount = amount;
		this.clientPayPalId = clientPayPalId;
		this.desc = desc;
	}

	public double getAmount() {
		return amount;
	}

	public String getTargetPayPalId() {
		return clientPayPalId;
	}

	public String getDesc() {
		return desc;
	}

}
