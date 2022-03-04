package com.ait.paypal;

import com.depot.exceptions.MusicDepotPayPalException;

public interface PayPalFacade {

	int sendAdvice(String clientId, String paymentMessage) throws MusicDepotPayPalException;

}
