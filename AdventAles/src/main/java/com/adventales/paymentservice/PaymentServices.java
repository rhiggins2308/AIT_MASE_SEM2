package com.adventales.paymentservice;
/*
 * Paypal/Gmail account details
 * email: adventalesmase2022@gmail.com
 * passw: @?P455sw0rd?@
 */


import com.paypal.api.payments.*;
public class PaymentServices {

	private static final String CLIENT_ID = "AY_4KJfv4pw8GtaCX9fWFp-djzAZCVcOt1z7GlI-yApwYn9QN7s4DyBPJ6tAmVl5m_y078NrG5ewMUp5";
	private static final String CLIENT_SECRET = "ECxba1DYWgScMwS_XjW1TYm4UeCwGdoU7UCCtaJpj0L7CTZo71HK3V6wijPzvT1e_mWsb6_zXfpmJ2Qg";
	private static final String MODE = "sandbox";
	
	PayerInfo payerInfo = new PayerInfo();
	payerInfo.setFirstName("Robbie");
	payerInfo.setLastName("Higgins");
	payerInfo.setEmail("sb-jbkrl14629323@personal.example.com");
	
	Payer payer = new Payer();
	payer.setPayerInfo(payerInfo);
}
