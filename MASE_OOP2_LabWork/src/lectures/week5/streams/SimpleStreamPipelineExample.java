package lectures.week5.streams;

import java.util.Arrays;
import java.util.List;

public class SimpleStreamPipelineExample {

	public static void main(String[] args) {
		List<Double> temps = Arrays.asList(98.4, 100.2, 87.9, 102.8);
		System.out.println("Number of temps > 100 is " + 
				temps								// 1. data source
					.stream()						// 2. create the data stream
					.peek(System.out::println)		// 3. intermediate operation ... show the value
					.filter(temp -> temp > 100)		// 4. intermediate operation ... filter the value based on lambda predicate (test)
					.peek(System.out::println)		// 5. intermediate operation ... show the value
					.count()						// 6. terminal operation ... count the values that made it through the intermediate operations
													//		required in order for the stream to do anything at all
													//		no terminal operation => no execution
				);		
	}
}