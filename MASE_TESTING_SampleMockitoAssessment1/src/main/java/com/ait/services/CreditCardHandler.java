package com.ait.services;



public interface CreditCardHandler extends PaymentHandler{
	void pay(long prescriptionId,String cardNumber, String cardName, String ccv,
			String expiryDate, double amount);
}
