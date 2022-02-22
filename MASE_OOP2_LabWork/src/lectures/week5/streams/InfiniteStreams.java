package lectures.week5.streams;

import java.util.stream.Stream;

public class InfiniteStreams {

	public static void main(String[] args) {
		//generate();
		generateWithLimit();
		//iterate();
		iterateWithLimit();
	}
	
	private static void generate() {
		/* infinite stream of random unordered numbers
		 * between 0 .. 9 inclusive
		 * 		Stream<T> generate(Supplier<T> s)
		 * 			Supplier is a functional interface:
		 * 				T get()
		 */
		Stream<Integer> infStream = Stream.generate(() -> {
			return (int) (Math.random() * 10);
		});
		
		// keeps generating random Integers until the process is killed
		infStream.forEach(System.out::println);
	}
	
	private static void generateWithLimit() {
		/* finite stream of unordered numbers
		 * between 0 .. 9 inclusive
		 * 		Stream<T> generate(Supplier<T> s)
		 * 			Supplier is a functional interface:
		 * 				T get()
		 */
		Stream
			.generate(() -> {
					return (int) (Math.random() * 10);
					})
			.limit(10)		// limit() is a short-circuiting stateful
							// intermediate operation
			.forEach(System.out::println);		
	}
	
	private static void iterateWithLimit() {
		/* finite stream of ordered numbers
		 * 2, 4, 6, 8, 10, 12, etc ...
		 * iterate(T seed, UnaryOperator<T> fn)
		 * 		UnaryOperator is-a Function<T, T>
		 * 			T apply(T t)
		 */
		Stream
			.iterate(2, n -> n + 2)
			.limit(10)
			.forEach(System.out::println);
	}
}