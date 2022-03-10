package com.ait.insurance;

public class HealthInsurance {
	
	public double calculatePremium(double bmi, int bloodPressure) {
		
		final double BMI_MIN = 10.0, BMI_MAX = 30.0, BMI_CAT_1 = 19.0, BMI_CAT_2 = 25.0;
		final int BP_MIN = 80, BP_MAX = 160, BP_CAT_2 = 130;
		
		if (bmi < BMI_MIN || bmi > BMI_MAX) {
			throw new IllegalArgumentException("Invalid BMI value: [" + bmi + "]");
		}
		
		if (bloodPressure < BP_MIN || bloodPressure > BP_MAX) {
			throw new IllegalArgumentException("Invalid Blood Pressure value: [" + bloodPressure + "]");
		}
		
		if (bmi < BMI_CAT_1) {
			if (bloodPressure <= BP_CAT_2) {
				return 2_500.0;
			} else {
				return 4_500.0;
			}
		} else if (bmi < BMI_CAT_2) {
			if (bloodPressure <= BP_CAT_2) {
				return 1_500.0;
			} else {
				return 2_500.0;
			}
		} else {
			if (bloodPressure <= BP_CAT_2) {
				return 2_500.0;
			} else {
				return 4_500.0;
			}	
		}
	}

}
