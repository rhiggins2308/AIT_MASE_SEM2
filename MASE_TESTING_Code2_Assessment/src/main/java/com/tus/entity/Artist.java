package com.tus.entity;

public class Artist {
	

	String artistId;
	PaymentDetails paymentDetails;
	
	public Artist(String artistId, PaymentDetails paymentDetails) {
		super();
		this.artistId = artistId;
		this.paymentDetails = paymentDetails;
	}
	public PaymentDetails getPaymentDetails() {
		return paymentDetails;
	}
	
}
