package lectures.week4.lambdas;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class PredicateExample {

	public static void main(String[] args) {
		predicate();
		biPredicate();
	}

	private static void predicate() {
		// Predicate<T> is a functional interface i.e. one abstract method:
		//		boolean test(T t);
		Predicate<String> pStr = s -> s.contains("City");
		System.out.println(pStr.test("Vatican City"));	// true		
	}
	
	private static void biPredicate() {
		// BiPredicate<T, U> is a functional interface i.e. one abstract method:
		//		boolean test(T t, U u);
		BiPredicate<String, Integer> checkLength = (str, len) -> str.length() == len;
		System.out.println(checkLength.test("Vatican City", 8));	// false	(length is 12)		
	}
}
