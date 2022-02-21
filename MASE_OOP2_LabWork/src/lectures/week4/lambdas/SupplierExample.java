package lectures.week4.lambdas;

import java.time.LocalTime;
import java.util.function.Supplier;

public class SupplierExample {

	public static void main(String[] args) {
		supplier();
	}

	private static void supplier() {
		// Supplier<T> is a functional interface i.e. one abstract method:
		//		T get();
		Supplier<StringBuilder> supSB = () -> new StringBuilder();
		System.out.println("Supplier SB: " + supSB.get().append("RH"));	 	// Supplier SB: RH
		
		Supplier<LocalTime> supTime = () -> LocalTime.now();
		System.out.println("Supplier time: " + supTime.get());	 			// Supplier time: 19:51:53.152087
		
		Supplier<Double> supRandom = () -> Math.random();
		System.out.println("Supplier random double: " + supRandom.get());	// Supplier random double: 0.1587076330585373
	}
}
