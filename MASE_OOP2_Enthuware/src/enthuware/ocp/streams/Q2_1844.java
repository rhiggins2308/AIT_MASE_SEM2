package enthuware.ocp.streams;

import java.util.stream.Stream;

public class Q2_1844 {

	public static void main(String[] args) {
		Stream<String> names = Stream.of("Sarah Adams", "Suzy Pinnell", "Paul Basgall");
		Stream<String> firstNames = 
									names.map(e->e.split(" ")[0]);
		/* public <R> Stream<R> map(Function<? super T,? extends R> mapper) 
		 * 	Returns a stream consisting of the results of applying the given function to the elements of this stream.
		 */
		
//		names.reduce(e->e.split(" ")[0]);
		/* Stream has several flavors of reduce methods.
		 * These methods are used to reduce the stream into one value
		 * using a BiFunction or BinaryOperator. It does not return a Stream.
		 * Note: BinaryOperator extends BiFunction.
		 * BinaryOperator is just a specialization of BiFunctionfor situations
		 * where types of the parameters and the return value are same.
		 */
		
//		names.filter(e->e.split(" ")[0]);
		/* filter method expects a Predicate object as argument.
		 * It is meant to filter the elements of the stream based on the given Predicate.
		 * In other words, filtering a stream implies
		 * that you are reducing the number of elements in the resulting stream.
		 * You are not modifying the elements.
		 */
		
//		names.forEach(e->e.split(" ")[0]);
		/* forEach method expects a Consumer object as argument.
		 * It is meant to process each element but doesn't return anything.
		 */
	}

}
