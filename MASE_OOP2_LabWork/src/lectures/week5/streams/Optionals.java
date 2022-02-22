package lectures.week5.streams;

import java.util.Optional;

public class Optionals {

	public static void main(String[] args) {
		
		Optional<Double> optAvg = calcAverage(50, 60, 70);
		
		// print option 1
		if (optAvg.isPresent()) {
			System.out.println(optAvg);
		}
		
		// print option 2
		System.out.println(optAvg.orElse(Double.NaN));
		
		// print option 3
		optAvg.ifPresent(System.out::println);
		
		Optional<Double> optAvg2 = calcAverage();
		System.out.println(optAvg2.orElse(Double.NaN));
		System.out.println(optAvg2.orElseGet(() -> Math.random()));		
	}

	private static Optional<Double> calcAverage(int... scores) {
		
		if (scores.length == 0) {
			return Optional.empty();
		}
		int sum = 0;
		
		for(int score : scores) {
			sum += score;
		}
		
		return Optional.of((double) sum / scores.length);
	}
}
