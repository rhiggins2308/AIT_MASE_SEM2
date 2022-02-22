package lectures.week5.streams;

import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectUsingApiCollectors {

	public static void main(String[] args) {
		collectorsJoining();
		collectorsAveragingInt();
		collectorsToMap();
		collectorsToMapDuplicateKey();
		collectorsToSortedMap();
	}

	private static void collectorsJoining() {
		String s = Stream.of("cake", "biscuits", "apple tart")
						.collect(Collectors.joining(", "));
		
		System.out.println(s);
	}

	private static void collectorsAveragingInt() {
		Double avg = Stream.of("cake", "biscuits", "apple tart")
						// averagingInt(ToIntFunction) functional method is:
						//		int applyAsInt(T value);
				.collect(Collectors.averagingInt(s -> s.length()));
		
		System.out.println(avg);
	}
	
	private static void collectorsToMap() {
		/* Here, we want a Map: dessert name -> no. of characters in dessert name
		 * Output:
		 * 		{ biscuits=8, cake=4, apple tart=10 }
		 */
		
		Map<String, Integer> map =
				Stream.of("cake", "biscuits", "apple tart")
				.collect(
					Collectors.toMap(s -> s, 			// Function for the key
									 s -> s.length())	// Function for teh value
				);
		
		System.out.println(map);
	}
	
	private static void collectorsToMapDuplicateKey() {
		Map<Integer, String> map =
				Stream.of("cake", "biscuits", "tart")
				.collect(
					Collectors.toMap(s -> s.length(),	// key is the length of the word
									 s -> s,			// value is the dessert name
									 (s1, s2) -> s1 + ", " + s2)	// Merge function - what to
																// do if we have duplicate keys
																// - append the values
				);
		
		System.out.println(map);
	}
	
	private static void collectorsToSortedMap() {
		TreeMap<String, Integer> map =
				Stream.of("cake", "biscuits", "apple tart", "cake")
				.collect(
					Collectors.toMap(s -> s,						// key is the dessert name
									 s -> s.length(),				// value is the length of the name
									 (len1, len2) -> len1 + len2,	// Merge function - what to
									 								// do if we have duplicate keys
																	// - add the values
									() -> new TreeMap<>())		// TreeMap::new works here as method ref
				);
		System.out.println(map);
		System.out.println(map.getClass());
	}
}