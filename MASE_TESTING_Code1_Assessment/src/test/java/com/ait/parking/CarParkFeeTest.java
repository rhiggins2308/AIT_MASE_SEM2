package com.ait.parking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

public class CarParkFeeTest {
	
	private static final String INVALID_DAYS = "Invalid number of days entered [";	
	private static final String INVALID_HOURS = "Invalid number of hours entered [";
	
	private CarParkFee carParkFee;
	
	@BeforeEach
	void init() {
		carParkFee = new CarParkFee();
	}
	
	@ParameterizedTest(name = "No. of hours = {0} should be invalid")
	@ValueSource(ints = {-2, -1, 25, 26})
	void invalidHoursDailyTest(int numHours) {

		Throwable exception = assertThrows(IllegalArgumentException.class,() -> carParkFee.calculateDailyFee(numHours));
		assertEquals(INVALID_HOURS + numHours + "]", exception.getMessage());
	}
	
	static Stream<Arguments> generateInvalidHoursPerWeek() {
		 return Stream.of(
				 Arguments.of(-2, new int[] { -2, 0, 0, 0, 0 }),
				 Arguments.of(-1, new int[] { 0, -1, 0, 0, 0 }),
				 Arguments.of(25, new int[] { 24, 24, 24, 24, 25 }),
				 Arguments.of(26, new int[] { 24, 24, 24, 26, 24 })
		  );
	}
	
	@ParameterizedTest(name = "No. of hours = {0} should be invalid")
	@MethodSource("generateInvalidHoursPerWeek")
	void invalidHoursWeeklyTest(int invalid, int[] hoursPerDay) {

		Throwable exception = assertThrows(IllegalArgumentException.class,() -> carParkFee.calculateWeeklyFee(hoursPerDay));
		assertEquals(INVALID_HOURS + invalid + "]", exception.getMessage());
	}
	
	static Stream<Arguments> generateInvalidNumberOfDays() {
		 return Stream.of(
				 Arguments.of(3, new int[] { 5, 5, 5 }),
				 Arguments.of(4, new int[] { 5, 5, 5, 5 }),
				 Arguments.of(6, new int[] { 5, 5, 5, 5, 5, 5 }),
				 Arguments.of(7, new int[] { 5, 5, 5, 5, 5, 5, 5 })
		  );
	}
	
	@ParameterizedTest(name = "No. of days = {0} should be invalid")
	@MethodSource("generateInvalidNumberOfDays")
	void invalidDaysPerWeekTest(int invalid, int[] hoursPerDay) {

		Throwable exception = assertThrows(IllegalArgumentException.class,() -> carParkFee.calculateWeeklyFee(hoursPerDay));
		assertEquals(INVALID_DAYS + invalid + "]", exception.getMessage());
	}

	@ParameterizedTest(name = "Number of hours [{0}] should return {1} fee")
	@CsvFileSource(resources = "/car-data.csv")
	void validDailyFeeTest(int numHours, double fee) {

		assertEquals(fee, carParkFee.calculateDailyFee(numHours));
	}
	
	static Stream<Arguments> generateValidWeeklyFee() {
		final double FEE_BAND_1 = 10.0, FEE_BAND_2 = 20.0, FEE_BAND_3 = 30.0;
		return Stream.of(
				 Arguments.of(FEE_BAND_1, new int[] { 0, 0, 0, 0, 0 }),
				 Arguments.of(FEE_BAND_1, new int[] { 1, 0, 0, 0, 0 }),
				 Arguments.of(FEE_BAND_1, new int[] { 3, 4, 4, 4, 4 }),
				 Arguments.of(FEE_BAND_1, new int[] { 4, 4, 4, 4, 4 }),
				 Arguments.of(FEE_BAND_2, new int[] { 4, 5, 4, 4, 4 }),
				 Arguments.of(FEE_BAND_2, new int[] { 4, 5, 5, 4, 4 }),
				 Arguments.of(FEE_BAND_2, new int[] { 9, 9, 10, 10, 10 }),
				 Arguments.of(FEE_BAND_2, new int[] { 9, 10, 10, 10, 10 }),
				 Arguments.of(FEE_BAND_2, new int[] { 10, 10, 10, 10, 10 }),
				 Arguments.of(FEE_BAND_3, new int[] { 10, 10, 11, 10, 10 }),
				 Arguments.of(FEE_BAND_3, new int[] { 23, 24, 24, 24, 24 }),
				 Arguments.of(FEE_BAND_3, new int[] { 24, 24, 24, 24, 24 })
		  );
	}
	
	@ParameterizedTest(name = "Weekly hours {1} should generate a {0} fee")
	@MethodSource("generateValidWeeklyFee")
	void validWeeklyFeeTest(double fee, int[] hoursPerDay) {

		assertEquals(fee, carParkFee.calculateWeeklyFee(hoursPerDay));
	}
}
