package lectures.week5.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class TerminalOperationsExamples {

	public static void main(String[] args) {
		count();
		min();
		max();
		findAny();
		findFirst();
		allMatch();
		anyMatch();
		noneMatch();
		forEach();
		reduceWithIdentity();
		reduceWithoutIdentity();
		reduceWithDifferentTypes();
		collect();
	}
	
	private static void count() {
		// Is a reduction
		//	- since it must process every element in the stream
		
		long count = Stream.of("dog", "cat").count();
		System.out.println(count); 	// 2
	}

	private static void min() {
		// Is a reduction
		//	- since it must process every element in the stream
		
		Optional<String> min = Stream.of("deer", "horse", "pig")
									.min((s1, s2) -> s1.length() - s2.length());
		
		min.ifPresent(System.out::println);
	}

	private static void max() {
		// Is a reduction
		//	- since it must process every element in the stream
				
		Optional<Integer> max = Stream.of(4, 6, 2, 12, 9)
				.max((i1, i2) -> i1 - i2);

		max.ifPresent(System.out::println);
	}

	private static void findAny() {
		// Not a reduction
		//	- not guaranteed to process every element in the stream
				
		Optional<String> any = Stream.of("John", "Paul")
									.findAny();
		
		any.ifPresent(System.out::println);
	}

	private static void findFirst() {
		// Not a reduction
		//	- not guaranteed to process every element in the stream
				
		Optional<String> first = Stream.of("John", "Paul")
				.findFirst();

		first.ifPresent(System.out::println);
	}

	private static void allMatch() {
		// Not a reduction
		//	- not guaranteed to process every element in the stream
			
		List<String> names = Arrays.asList("Alan", "Brian", "Colin");
		Predicate<String> pred = name -> name.startsWith("A");
		
		System.out.println(names.stream().allMatch(pred));
	}

	private static void anyMatch() {
		// Not a reduction
		//	- not guaranteed to process every element in the stream
		
		List<String> names = Arrays.asList("Alan", "Brian", "Colin");
		Predicate<String> pred = name -> name.startsWith("A");
		
		System.out.println(names.stream().allMatch(pred));
	}

	private static void noneMatch() {
		// Not a reduction
		//	- not guaranteed to process every element in the stream
		
		List<String> names = Arrays.asList("Alan", "Brian", "Colin");
		Predicate<String> pred = name -> name.startsWith("A");
		
		System.out.println(names.stream().noneMatch(pred));
	}

	private static void forEach() {
		// Not a reduction
		//	- no return type
		
		Stream<String> names = Stream.of("Cathy", "Pauline", "Zoe");
		names.forEach(System.out::println);
		
		/* Notes: forEach is also a method in the Collection interface.
		 * Streams cannot be the source of a for-each loop
		 * because streams do not implement the Iterable interface.
		 */
		Stream<Integer> s = Stream.of(1);
//		for (Integer i : s) {}	// error: requires an array or Iterable object
	}

	private static void reduceWithIdentity() {
		// Is a reduction
		//	- since it must process every element in the stream
		
		String name = Stream.of("r", "o", "b", "b", "i", "e")
//						.filter(s -> s.length() > 2)
//						.reduce("nothing", (s, c) -> s + c);
						.reduce("", (s, c) -> s + c);
		
		System.out.println(name);
		
		
		Integer product = Stream.of(2, 3, 4)
						.reduce(1, (a, b) -> a * b);
		
		System.out.println(product);
	}

	private static void reduceWithoutIdentity() {
		// Is a reduction
		//	- since it must process every element in the stream
		
		/* sometimes it is good to know if the stream is EMPTY, as opposed to the case where
		 * there is a value returned from the accumulator
		 * that happens to match the identity
		 * 
		 * In this case, define a BinaryOperator for the reduction operation
		 * do not use an identity value
		 */
		BinaryOperator<Integer> op = (a, b) -> a + b;
		
		Stream<Integer> empty				= Stream.empty();
		Stream<Integer> oneElement			= Stream.of(6);
		Stream<Integer> multipleElements	= Stream.of(3, 4, 5);
		
		empty.reduce(op).ifPresent(System.out::println);				//
		oneElement.reduce(op).ifPresent(System.out::println);			// 6
		multipleElements.reduce(op).ifPresent(System.out::println);		// 12
			
		
		Integer val = Stream.of(1, 1, 1)
				//		.filter(n -> n > 5) 		// val is 1, this way
						.reduce(1, (a, b) -> a);	// val is 1, this way as well
				
		System.out.println(val);
	}

	private static void reduceWithDifferentTypes() {
		Stream<String> stream = Stream.of("car", "bus", "train", "aeroplane");
		
		/* <U> U reduce (U identity, 
		 * 				 BiFunction accumulator,
		 * 				 BinaryOperator combiner)
		 */
		
		int length = stream.reduce(0, 	// 1st param 0 is the identity starting point 
								  (n, str) -> n + str.length(),	// n is an Integer
								  (n1, n2) -> n1 + n2);			// both are Integers
	
		System.out.println(length);
	}
	
	private static void collect() {
		// Is a reduction
		//	- since it must process every element in the stream
		
		/* StringBuilder collect(Supplier<StringBuilder> supplier,
		 * 						 BiConsumer<StringBuilder, String> accumulator,
		 * 						 BiConsumer<StringBuilder, StringBUilder> combiner)
		 */
		StringBuilder word = Stream.of("ad", "jud", "i", "cate")				
									.collect(() -> new StringBuilder(),			// StringBuilder::new
											 (sb, str) -> sb.append(str),		// StringBuilder::append
											 (sb1, sb2) -> sb1.append(sb2));	// StringBuilder::append
		System.out.println(word);	// adjudicate
	}
}