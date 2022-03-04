package com.ait.insurance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

public class HealthInsuranceTest {

	private HealthInsurance ins;
	
	@BeforeEach
	void init() {
		ins = new HealthInsurance();
	}
	
	@ParameterizedTest(name = "Invalid Blood Pressure Reading : {0}")
	@ValueSource(ints = {78, 79, 161, 162})
	void testInvalidBpValues(int bp) {
		Throwable exception = assertThrows(IllegalArgumentException.class,() -> ins.calculatePremium(20.0, bp));
		assertEquals("Invalid Blood Pressure value: [" + bp + "]", exception.getMessage());
	}
	
	@ParameterizedTest(name = "Invalid BMI Reading : {0}")
	@ValueSource(doubles = {9.8, 9.9, 30.1, 30.2})
	void testInvalidBpValues(double bmi) {
		Throwable exception = assertThrows(IllegalArgumentException.class,() -> ins.calculatePremium(bmi, 90));
		assertEquals("Invalid BMI value: [" + bmi + "]", exception.getMessage());
	}
	
	@ParameterizedTest(name = "BMI: {0} and BP: {1} - Premium €{2}")
	@CsvFileSource(resources = "/health-insurance-data.csv")
	void testValidBpBmiValues(double bmi, int bp, double premium) {
		assertEquals(premium, ins.calculatePremium(bmi, bp));
	}
}
