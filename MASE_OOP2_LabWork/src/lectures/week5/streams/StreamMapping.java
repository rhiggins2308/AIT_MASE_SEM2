package lectures.week5.streams;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class StreamMapping {

	public static void main(String[] args) {
		mappingObjectStreams();
		mappingIntStreams();
	}
	
	private static void mappingObjectStreams() {
		
		// Stream<T> to Stream<T>
		Stream.of("ash", "beech", "sycamore")
					.map(tree -> tree.toUpperCase())
					.forEach(System.out::println);
		
		
		// Stream<T> to DoubleStream
		DoubleStream dblStream = Stream.of("ash", "beech", "sycamore")
						.mapToDouble(tree -> tree.length());
		
		dblStream.forEach(System.out::println);
		
		
		// Stream<T> to IntStream
		IntStream intStream = Stream.of("ash", "beech", "sycamore")
						.mapToInt(tree -> tree.length());
				
		intStream.forEach(System.out::println);
		
				
		// Stream<T> to LongStream
		LongStream longStream = Stream.of("ash", "beech", "sycamore")
						.mapToLong(tree -> tree.length());
				
		intStream.forEach(System.out::println);
	}
	
	private static void mappingIntStreams() {
		
		// IntStream to Stream<T>
		Stream<String> streamAges = IntStream.of(1, 2, 3)
							.mapToObj(n -> "Number: " + n);
		
		streamAges.forEach(System.out::println);
		
		
		// IntStream to DoubleStream
		DoubleStream dblStream = IntStream.of(1, 2, 3)
						.mapToDouble(n -> (double) n);
		
		dblStream.forEach(System.out::println);
		
		
		// IntStream to IntStream
		IntStream.of(1, 2, 3)
						.map(n -> n*2)
						.forEach(System.out::println);
		
				
		// IntStream to LongStream
		IntStream.of(1, 2, 3)
						.mapToLong(n -> (long) n)
						.forEach(System.out::println);	
	}
}