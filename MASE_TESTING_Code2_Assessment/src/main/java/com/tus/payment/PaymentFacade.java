package com.tus.payment;

import com.tus.entity.PaymentDetails;

public interface PaymentFacade {
	void pay(String artistId, double amount, PaymentDetails paymentDetails);
}
