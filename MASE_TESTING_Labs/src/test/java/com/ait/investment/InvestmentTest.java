package com.ait.investment;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class InvestmentTest {

	private final int VALID_TERM = 4;
	private final int VALID_INVESTMENT = 2_000;
	
	
	/* -- All production methods are static, so no object instance required

	private Investment investment;
	
	@BeforeEach
	void setUp() {
		investment = new Investment();
	}

	*/
	
	// CSV Source example
	/*
	 
	@ParameterizedTest(name = "Initial amount {0} for {1} year(s) should result in {2}")
	@CsvSource({"1000, 3, 1061.21", "1001, 4, 1083.51"})
	void testGetInvestmentValue(int startAmount, int term, double finalAmount) {

		assertEquals(finalAmount, Investment.calculateInvestmentValue(term, startAmount));
	}
	
	*/
	
	@ParameterizedTest(name = "Initial amount {0} for {1} year(s) should result in {2}")
	@CsvFileSource(resources = "/dataInv.csv")
	void testGetInvestmentValueWithFile(int startAmount, int term, double finalAmount) {

		assertEquals(finalAmount, Investment.calculateInvestmentValue(term, startAmount));
	}
	
	@ParameterizedTest(name = "Amount = {0} should be invalid")
	@ValueSource(ints = {998, 999, 10_001, 10_002})
	void testInvalidAmount(int startingAmount) {
		Throwable exception = assertThrows(IllegalArgumentException.class,() -> {Investment.calculateInvestmentValue(VALID_TERM, startingAmount);});
		assertEquals("illegal investment amount: [" + startingAmount + "]", exception.getMessage());
	}
		
	@ParameterizedTest(name ="Term = {0} years should be invalid")
	@ValueSource(ints = {1, 2, 11, 12})
	void testInvalidTerm(int term) {
		Throwable exception = assertThrows(IllegalArgumentException.class, () -> {Investment.calculateInvestmentValue(term, VALID_INVESTMENT);});
		assertEquals("illegal investment term: [" + term + "]", exception.getMessage());
	}
}
