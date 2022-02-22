package lectures.week5.streams;

import java.util.List;
import java.util.stream.Stream;

public class ParallelStreams {

	public static void main(String[] args) {
		parallelStream();
		parallel();
		sequentialVsParallel();

	}

	private static void parallelStream() {
		Stream<String> animalsStream = List.of("sheep", "pigs", "horses")
											.parallelStream();
	}
	
	private static void parallel() {
		Stream<String> animalsStream = Stream.of("sheep", "pigs", "horses")
				.parallel();
	}
	
	private static void sequentialVsParallel() {
		//Sequential Stream
		int sum = Stream.of(10, 20, 30, 40, 50, 60)
						.mapToInt(n -> n.intValue())
				//		.mapToInt(Integer::intValue)
				//		.mapToInt(n -> n)
						.sum();
		
		System.out.println("Sum == " + sum);
		
		
		// Parallel version
		int sum2 = Stream.of(10, 20, 30, 40, 50, 60)
				.parallel()
				.mapToInt(Integer::intValue)
				.sum();

		System.out.println("Sum == " + sum2);
		
	}
}
