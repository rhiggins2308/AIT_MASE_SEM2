package lectures.week5.streams;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class PrimitiveStreams {

	public static void main(String[] args) {
		intStream();
		doubleStream();
		longStream();
		genericReduce();
		intstreamSum();
		optional();
		summaryStats(IntStream.of(5, 10, 15, 20));
		summaryStats(IntStream.empty());
	}
	
	private static void intStream() {
		int[] ia = {1, 2, 3};
		IntStream iStream1 = Arrays.stream(ia);
		System.out.println(iStream1.count());
		
		IntStream iStream2 = IntStream.of(1, 2, 3);
		System.out.println(iStream2.count());
	}

	private static void doubleStream() {
		double[] da = {1.1, 2.2, 3.3};
		DoubleStream dStream1 = Arrays.stream(da);
		System.out.println(dStream1.count());
		
		DoubleStream dStream2 = DoubleStream.of(1.1, 2.2, 3.3);
		System.out.println(dStream2.count());
	}

	private static void longStream() {
		long[] la = {1L, 2L, 3L};
		LongStream lStream1 = Arrays.stream(la);
		System.out.println(lStream1.count());
		
		LongStream lStream2 = LongStream.of(1L, 2L, 3L);
		System.out.println(lStream2.count());
		
	}
	
	private static void genericReduce() {
		// Using Stream<T> and reduce(identity, accumulator)
		Stream<Integer> numbers = Stream.of(1, 2, 3);
		
		/* Integer reduce(Integer identity, BinaryOperator accumulator)
		 * 		BinaryOperator extends BiFunction<T, T, T>
		 * 			T apply(T, T)
		 * 
		 * starting the accumulator with 0
		 * 		n1	+ 	n2
		 * 		0  	+ 	1	== 1	(n1 now becomes 1)
		 * 		1	+	2	== 3	(n1 now becomes 3)
		 * 		3	+	3	== 6
		 */
		
		System.out.println(numbers.reduce(0, (n1, n2) -> n1 + n2));
	}
	
	private static void intstreamSum() {
		/* Using IntStream and sum()
		 * IntStream mapToInt(ToIntFunction)
		 * 		ToIntFunction is a functional interface:
		 * 			int applyAsInt(T value);
		 */		
		IntStream intS = Stream.of(1, 2, 3)
							.mapToInt(n -> n);	// unboxed
		int total = intS.sum();
		System.out.println(total);
	}
	
	private static void optional() {
		OptionalInt max = IntStream.of(10, 20, 30)
								.max();
		
		max.ifPresent(System.out::println);
		
		
		
		OptionalDouble min = DoubleStream.of(10.0, 20.0, 30.0)
				.min();
		// NoSuchElementException is thrown if no value present
		System.out.println(min.orElseThrow());
		
		
		
		OptionalDouble average = LongStream.of(10L, 20L, 30L)
				.average();
		// NoSuchElementException is thrown if no value present
		System.out.println(average.orElseGet(() -> Math.random()));		
	}
	
	private static void summaryStats(IntStream numbers) {
		IntSummaryStatistics intStats = 
				numbers.summaryStatistics();
		
		int min = intStats.getMin();
		System.out.println(min);
		
		int max = intStats.getMax();
		System.out.println(max);
		
		double avg = intStats.getAverage();
		System.out.println(avg);
		
		long count = intStats.getCount();
		System.out.println(count);
		
		long sum = intStats.getSum();
		System.out.println(sum);		
	}	
}