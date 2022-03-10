package com.ait.marks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class MarkCheckerTest {

	private MarkChecker markChecker;
	
	@BeforeEach
	public void init() throws Exception{
		markChecker = new MarkChecker();
	}
	
	// TC2-1
	@ParameterizedTest (name = "{0} or {1} should have an invalid number of marks")
	@MethodSource("generateInvalidNumberOfMarks")
	public void testInvalidNumberOfMarks(int[] markerOne, int[] markerTwo) {
		Throwable exception=assertThrows(IllegalArgumentException.class, () -> {markChecker.checkMarks(markerOne, markerTwo);});	
		assertEquals("Invalid number of marks submitted.", exception.getMessage());
	}
	
	// Arrays with 3,4,6 and 7 scores - valid values
	static Stream<Arguments> generateInvalidNumberOfMarks() {
		return Stream.of(
				Arguments.of( new int[] {1, 2, 3}, new int[] {1, 2, 3, 4, 5}),
				Arguments.of( new int[] {1, 2, 3, 4, 5}, new int[] {1, 2, 3, 4}),
				Arguments.of( new int[] {1, 2, 3, 4, 5}, new int[] {1, 2, 3, 4, 5, 6}),
				Arguments.of( new int[] {1, 2, 3, 4, 5, 6, 7}, new int[] {1, 2, 3, 4, 5})
				);
	}
	
	@ParameterizedTest (name = "{0} should flag as an out of range mark")
	@MethodSource("generateOutOfRangeMarks")
	public void testInvalidMarks(int invalid, int[] markerOne, int[] markerTwo) {
		Throwable exception=assertThrows(IllegalArgumentException.class, () -> {markChecker.checkMarks(markerOne, markerTwo);});	
		assertEquals("Marker has submitted an invalid mark: [" + invalid + "]", exception.getMessage());
				
	}
	
	static Stream<Arguments> generateOutOfRangeMarks(){
		return Stream.of(
				Arguments.of(-2, new int[] {1, -2, 3, 4, 5}, new int[] {0, 2, 3, 4, 5}),
				Arguments.of(-1, new int[] {1, 2, 3, 4, 5}, new int[] {-1, 2, 3, 4, 5}),
				Arguments.of(21, new int[] {1, 2, 3, 4, 21}, new int[] {0, 2, 3, 4, 19}),
				Arguments.of(22, new int[] {1, 2, 3, 4, 20}, new int[] {1, 2, 3, 4, 22})
				);
	}
	
	@ParameterizedTest (name = "M1: {0} ; M2: {1} - {2}")
	@MethodSource("generateIndScoresDiffAbove3")
	public void testIndividualScoreDiffAbove3(int[] markerOne, int[] markerTwo, String description) {	
		assertEquals(1, markChecker.checkMarks(markerOne, markerTwo));
				
	}
	
	static Stream<Arguments> generateIndScoresDiffAbove3(){
		return Stream.of(
				Arguments.of(new int[] {10, 10, 10, 10, 10}, new int[] {10, 10, 10, 10, 14}, "Individual Difference of 4"),
				Arguments.of(new int[] {5, 5, 5, 10, 5}, new int[] {5, 5, 5, 5, 5}, "Individual Difference of 5")
				);
	}
	
	@ParameterizedTest (name = "M1: {0} ; M2: {1} - {2}")
	@MethodSource("generateTotalScoresDiffAbove10")
	public void testTotalScoreDiffAbove10(int[] markerOne, int[] markerTwo, String description) {	
		assertEquals(2, markChecker.checkMarks(markerOne, markerTwo));
				
	}
	
	static Stream<Arguments> generateTotalScoresDiffAbove10(){
		return Stream.of(
				Arguments.of(new int[] {10, 10, 10, 10, 10}, new int[] {13, 13, 13, 12, 10}, "Total Difference of 11"),
				Arguments.of(new int[] {13, 13, 13, 12, 11}, new int[] {10, 10, 10, 10, 10}, "Total Difference of 12")
				);
	}
	
	@ParameterizedTest (name = "M1: {0} ; M2: {1} - {2}")
	@MethodSource("generateValidScoresValidDiffs")
	public void testScoresOK(int[] markerOne, int[] markerTwo, String description) {	
		assertEquals(0, markChecker.checkMarks(markerOne, markerTwo));
				
	}
	
	static Stream<Arguments> generateValidScoresValidDiffs(){
		return Stream.of(
				Arguments.of(new int[] {10, 10, 10, 10, 10}, new int[] {10, 10, 10, 10, 10}, "Ind/Total Difference of 0"),
				Arguments.of(new int[] {10, 10, 10, 10, 10}, new int[] {10, 10, 10, 10, 9}, "Ind/Total Difference of 1"),
				Arguments.of(new int[] {10, 8, 10, 10, 10}, new int[] {10, 10, 10, 10, 10}, "Ind Difference of 2"),
				Arguments.of(new int[] {10, 10, 10, 10, 10}, new int[] {10, 10, 10, 13, 10}, "Ind Difference of 3"),
				Arguments.of(new int[] {12, 8, 12, 8, 11}, new int[] {10, 10, 10, 10, 10}, "Total Difference of 9"),
				Arguments.of(new int[] {10, 10, 10, 10, 10}, new int[] {8, 12, 8, 12, 8}, "Total Difference of 10")
				);
	}
}
