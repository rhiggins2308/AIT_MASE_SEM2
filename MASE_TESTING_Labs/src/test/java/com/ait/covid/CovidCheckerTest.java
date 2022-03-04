package com.ait.covid;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

public class CovidCheckerTest {

	private static final String ILLEGAL_WARN = "Illegal reading recorded : [";
	private static final String READ_WARN = "Incorrect no. of readings taken : [";
	private static final String READ_WARN_2 = "] ... must be 5";
	
	private CovidChecker cc;
	
	@BeforeEach
	void setUp() {
		cc = new CovidChecker();
	}
	
	@ParameterizedTest(name = "Reading = {0} should be invalid")
	@ValueSource(doubles = {29.8, 29.9, 50.1, 50.2})
	void testInvalidTemperatureValues(double temp) {

		Throwable exception = assertThrows(IllegalArgumentException.class,() -> cc.checkTempForCovid(temp));
		assertEquals(ILLEGAL_WARN + temp + "]", exception.getMessage());
	}
	
	@ParameterizedTest(name = "Temperature Reading {0} should return {1}"
			+ "")
	@CsvFileSource(resources = "/covid-temp-data.csv")
	void testValidTemperatureValues(double temp, String result) {

		assertEquals(result, cc.checkTempForCovid(temp));
	}
	
	@ParameterizedTest(name = "Invalid Blood Pressure Readings : {0}")
	@MethodSource("generateInvalidBpValues")
	void testInvalidBpValues(int illegal, int[] bp) {
		Throwable exception = assertThrows(IllegalArgumentException.class,() -> cc.checkBPForCovid(bp));
		assertEquals(ILLEGAL_WARN + illegal + "]", exception.getMessage());
	}
	
	static Stream<Arguments> generateInvalidBpValues() {
		 return Stream.of(
				 Arguments.of(58, new int[] { 65, 70, 75, 80, 58}),
				 Arguments.of(59, new int[] { 65, 70, 75, 80, 59}),
				 Arguments.of(91, new int[] { 65, 70, 75, 80, 91}),
				 Arguments.of(92, new int[] { 65, 70, 75, 80, 92})
		  );
	}
	
	@ParameterizedTest(name = "Blood Pressure Result : {0} from readings {1}")
	@MethodSource("generateValidBpValues")
	void testAverageBp(String result, int[] bp) {
		assertEquals(result, cc.checkBPForCovid(bp));
	}
	
	static Stream<Arguments> generateValidBpValues() {
		
		final String OK = "OK";
		final String TEST = "TEST FOR COVID";
		
		return Stream.of(
				 Arguments.of(OK, new int[] { 60, 74, 74, 74, 74}),
				 Arguments.of(OK, new int[] { 61, 74, 74, 74, 74}),
				 Arguments.of(TEST, new int[] { 74, 74, 74, 74, 89}),
				 Arguments.of(TEST, new int[] { 74, 74, 74, 74, 90}),
				 Arguments.of(OK, new int[] { 74, 74, 74, 74, 74}), 
				 Arguments.of(OK, new int[] { 73, 73, 73, 73, 73}),
				 Arguments.of(TEST, new int[] { 75, 75, 75, 75, 75}), 
				 Arguments.of(TEST, new int[] { 76, 76, 76, 76, 76})
		  );
	}
	
	@ParameterizedTest(name = "Blood Pressure Readings : {0}")
	@MethodSource("generateInvalidNumberOfBpValues")
	void testNumberOfBpValues(int count, int[] bp) {
		Throwable exception = assertThrows(IllegalArgumentException.class,() -> cc.checkBPForCovid(bp));
		assertEquals(READ_WARN + count + READ_WARN_2, exception.getMessage());
	}
	
	static Stream<Arguments> generateInvalidNumberOfBpValues() {
		 return Stream.of(
				 Arguments.of(3, new int[] { 74, 74, 74}),
				 Arguments.of(4, new int[] { 74, 74, 74, 74}),
				 Arguments.of(6, new int[] { 74, 74, 74, 74, 74, 74}),
				 Arguments.of(7, new int[] { 74, 74, 74, 74, 74, 74, 74})
		  );
	}
}