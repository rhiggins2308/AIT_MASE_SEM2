package com.ait.investment;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Investment {

	private static final double RATE1 = 0.02d, RATE2 = 0.05d, RATE3 = 0.07d;

	private static double round(double value, int places) {
	    
		if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = BigDecimal.valueOf(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}

	private static double getRate(int amount) {
		if (amount < 3_000) {
			return RATE1;				
		} else if (amount < 5_000) {
			return RATE2;
		} else {
			return RATE3;
		}
	}
	
	public static double calculateInvestmentValue (int term, int startAmount) throws IllegalArgumentException {
		
		if (startAmount < 1_000 || startAmount > 10_000) {
			throw new IllegalArgumentException("Illegal investment amount: [" + startAmount + "]");
		}
		if (term < 3 || term > 10) {
			throw new IllegalArgumentException("Illegal investment term: [" + startAmount + "]");
		}
		
		double rate = getRate(startAmount);
		

		return round(startAmount*Math.pow((1+rate), term), 2);
	}
}
