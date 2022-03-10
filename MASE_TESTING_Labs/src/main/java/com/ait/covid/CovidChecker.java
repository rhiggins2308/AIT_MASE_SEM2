package com.ait.covid;

public class CovidChecker {

	private static final String ILLEGAL_WARN = "Illegal reading recorded : [";
	private static final String TEST = "TEST FOR COVID";
	private static final String OK = "OK";
	private static final String READ_WARN = "Incorrect no. of readings taken : [";
	private static final String READ_WARN_2 = "] ... must be 5";
	private static final int MIN_TEMP = 30;
	private static final int MAX_TEMP = 50;
	private static final int NUM_BP_READINGS = 5;
	private static final int MIN_BP = 60;
	private static final int MAX_BP = 90;
	private static final int MAX_AVG_BP = 74;
	private static final double  MAX_HEALTHY_TEMP = 37.5d;
	
	public String checkTempForCovid(double temperature) {
		
		if (temperature < MIN_TEMP || temperature > MAX_TEMP) {
			
			throw new IllegalArgumentException(ILLEGAL_WARN + temperature + "]");
		}
		
		if (temperature <= MAX_HEALTHY_TEMP) {
			
			return OK;
		} else {

			return TEST;
		}		
	}
	
	public String checkBPForCovid(int[] bloodPressure) {
		
		if (bloodPressure.length != NUM_BP_READINGS) {
			
			throw new IllegalArgumentException(READ_WARN + bloodPressure.length + READ_WARN_2);
		}
		
		for (int bp : bloodPressure) {
			if (bp < MIN_BP || bp > MAX_BP) {
				throw new IllegalArgumentException(ILLEGAL_WARN + bp + "]");
			}
		}
		
		int sum = 0;
		for (int bp : bloodPressure) {
			sum += bp;
		}
		if (Math.round(sum/NUM_BP_READINGS) > MAX_AVG_BP) {
			
			return TEST;
			
		} else {
			
			return OK;
		}
	}
}