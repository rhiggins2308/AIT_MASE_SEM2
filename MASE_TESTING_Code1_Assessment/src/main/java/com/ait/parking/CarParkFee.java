package com.ait.parking;

public class CarParkFee {

	private static final int HOURLY_RATE = 2, WORKING_DAYS = 5, DISCOUNT_HRS_MIN = 6,
			FLAT_RATE_HRS = 10, WEEKLY_BAND_2 = 20, WEEKLY_BAND_3 = 50;
	private static final double DISCOUNT_RATE = 0.2, MIN_DISCOUNTED_FEE = 10.0, FLAT_RATE_FEE = 20.0,
			WEEKLY_FEE_1 = 10.0, WEEKLY_FEE_2 = 20.0, WEEKLY_FEE_3 = 30.0;
	private static final int MIN_DAILY_HOURS = 0, MAX_DAILY_HOURS = 24;
	
	private static final String INVALID_DAYS = "Invalid number of days entered [";
	private static final String INVALID_HOURS = "Invalid number of hours entered [";
	
	
	public double calculateDailyFee(int numHours) {
		double fee = numHours * HOURLY_RATE;
		
		if (numHours < MIN_DAILY_HOURS || numHours > MAX_DAILY_HOURS) {
			
			throw new IllegalArgumentException(INVALID_HOURS + numHours + "]");
		}
		
		if (numHours >= DISCOUNT_HRS_MIN && numHours <= FLAT_RATE_HRS) {
			fee = fee - (fee * DISCOUNT_RATE);
			if (fee < MIN_DISCOUNTED_FEE) {
				fee = MIN_DISCOUNTED_FEE;
			}
		}
		if (numHours > FLAT_RATE_HRS) {
			fee = FLAT_RATE_FEE;
		}
		
		return fee;
	}
	
	public double calculateWeeklyFee(int[] hoursPerDay) {
		
			if (hoursPerDay.length != WORKING_DAYS) {
				throw new IllegalArgumentException(INVALID_DAYS + hoursPerDay.length + "]");
			}
			
			int totalHours = 0;
			for (int day : hoursPerDay) {
				if (day < MIN_DAILY_HOURS || day > MAX_DAILY_HOURS) {
					throw new IllegalArgumentException(INVALID_HOURS + day + "]");
				}
				totalHours += day;
			}
			
			double fee;
			if (totalHours <= WEEKLY_BAND_2) {
				fee = WEEKLY_FEE_1;
			} else if (totalHours <= WEEKLY_BAND_3) {
				fee = WEEKLY_FEE_2;
			} else {
				fee = WEEKLY_FEE_3;
			}
			
			return fee;
	}
}
