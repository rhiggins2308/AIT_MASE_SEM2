package lectures.week5.streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamsLazyEvaluation {

	public static void main(String[] args) {
	
	// 1st Stream	
		Stream.of("Alex", "David", "April", "Edward")				// 1 and 2. ... Stream of data source
			.filter(s -> {											// 3. intermediate filter() operation using lambda
				System.out.println("filter:\t\t" + s);
				return true;
			})
			.forEach(s -> System.out.println("forEach:\t" + s)
		);															// 4. terminal operation to conclude the processing for each element that has made it to the end of the stream 
		/* Each element moves along the chain vertically,
		 * and is processed completely, before moving on to the next
		 * 
		 * 		filter: 	Alex
		 * 		forEach:	Alex
		 * 		filter: 	David
		 * 		forEach:	David
		 * 		filter: 	April
		 * 		forEach:	April
		 * 		filter: 	Edward
		 * 		forEach:	Edward
		 */
		
		
	// 2nd Stream ... cannot use previous stream once completed.
	// must re-create from scratch
		Stream.of("Alex", "David", "April", "Edward")				// 1 and 2. ... Stream of data source
		.map(s -> {													// 3. intermediate map() operation using lambda
			System.out.println("map:\t\t" + s);
			return s.toUpperCase();
		})
		.anyMatch(s -> {											// 4. terminal operation to conclude the processing for each element that has made it to the end of the stream 
			System.out.println("anyMatch:\t" + s);					//		ends when first 'true' is returned (Alex)	
			return s.startsWith("A");
		});		
		/* Each element moves along the chain vertically,
		 * and is processed completely, before moving on to the next
		 * only processes as much as is required by the intermediate/terminal operations
		 * 
		 * 		map: 		Alex
		 * 		anyMatch:	ALEX
		 */
	
		
	// 3rd example
		List<String> names = 										// 1. define data source ... a List of String, in this example
				Arrays.asList("April", "Ben", "Charlie", 
						" David", " Benildus", "Christian");
		
		names.stream()												// 2. stream the data into a Stream of String
			.peek(System.out::println)								// 3. intermediate operations to display
			.filter(s -> {											//				then filter based on startying with B or C
				System.out.println("filter1: " + s);
				return s.startsWith("B") || s.startsWith("C");
			})
			.filter(s -> {											//				then filter those results, beased on length of 3
				System.out.println("filter2: " + s);
				return s.length() > 3;
			})
			.limit(1)												//				then limit the number of results we want to allow through
																	//				before the stream processing completes
			.forEach(System.out::println);							// 4. terminal forEach() to iterate through the data
	}																//	  that has made it through the stream
}