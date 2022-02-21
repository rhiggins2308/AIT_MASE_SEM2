package lectures.week4.lambdas;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public class LambdaEffectivelyFinal {

	String name = "";
	
	public static void main(String[] args) {
		ArrayList<String> al = new ArrayList<>();
		al.add("John");
		
		int x = 12;	// final or effectively final
		// Lambdas take a snapshot/picture of local variables;
		// these local variables MUST NOT change
		Predicate<String> lambda = s -> {
			//x++;
			new LambdaEffectivelyFinal().name = "Higgins";	// 	instance/class vars are okay to change,
															//	since they are accessible by the entire class
			System.out.println("x == " + x);
			return s.isEmpty() && x%2 == 0;
		};
		
		filterData(al, lambda);	// lambda views 'x' as 12
		System.out.println(al);
		
		new LambdaEffectivelyFinal().name = "Robbie";	// 	instance/class vars are okay to change,
														//	since they are accessible by the entire class
		
		// If 'x' was allowed to change, then the method and the lambda would
		// have two differet views of 'x'!
		// x++;
		
		filterData(al, lambda); // lambda views 'x' as 12
		// some code ...
	}

	private static void filterData(List<String> list, Predicate<String> lambda) {
		Iterator<String> i = list.iterator();
		
		while (i.hasNext()) {
			if (lambda.test(i.next())) {	//	executing lambda here
				i.remove();
			}
		}		
	}
}