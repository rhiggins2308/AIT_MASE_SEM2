package com.ait.marks;

public class MarkChecker {

	private static final int MIN = 0, MAX = 20, NO_QUESTIONS = 5, SINGLE_MARK_DIFF = 3, TOTAL_MARK_DIFF = 10;
	
	public int checkMarks(int[] markerOne, int[] markerTwo) {
		
		int totalMarkerOne = 0, totalMarkerTwo = 0;
		
		for (int score : markerOne) {
			if (score < MIN || score > MAX) {
				throw new IllegalArgumentException("Marker has submitted an invalid mark: [" + score + "]");
			}
		}
		
		for (int score : markerTwo) {
			if (score < MIN || score > MAX) {
				throw new IllegalArgumentException("Marker has submitted an invalid mark: [" + score + "]");
			}
		}
		
		if (markerOne.length != NO_QUESTIONS || markerTwo.length != NO_QUESTIONS) {
			throw new IllegalArgumentException("Invalid number of marks submitted.");
		}
		
		
		for (int i = 0; i < NO_QUESTIONS; i++) {
			if (Math.abs(markerOne[i]-markerTwo[i]) > SINGLE_MARK_DIFF) {
				return 1;
			}
		}
		
		for (int i = 0; i < NO_QUESTIONS; i++) {
			totalMarkerOne += markerOne[i];
			totalMarkerTwo += markerTwo[i];
		}
		
		if (Math.abs(totalMarkerOne - totalMarkerTwo) > TOTAL_MARK_DIFF) {
			return 2;
		}
		
		return 0;

	}
}
