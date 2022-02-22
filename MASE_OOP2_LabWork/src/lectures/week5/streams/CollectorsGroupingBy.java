package lectures.week5.streams;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorsGroupingBy {

	public static void main(String[] args) {
		groupingByStringLength();
		groupingByWithoutDuplication();
		groupingByDefineMapTypeReturned();
	}
	
	private static void groupingByStringLength() {
		Stream<String> names = Stream.of("Joe", "Tom", "Tom", "Alan", "Peter");
		Map<Integer, List<String>> map =
				names.collect(
					// passing in a Function that determines
					// key in the Map
					Collectors.groupingBy(String::length)	// s -> s.length()	
				);
		
		System.out.println(map);
	}
	
	private static void groupingByWithoutDuplication() {
		Stream<String> names = Stream.of("Joe", "Tom", "Tom", "Alan", "Peter");
		Map<Integer, Set<String>> map =
				names.collect(
					Collectors.groupingBy(
							String::length,			// key Function (length of name)
							Collectors.toSet())		// what to do with the values	
				);
		
		System.out.println(map);
	}
	
	private static void groupingByDefineMapTypeReturned() {
		Stream<String> names = Stream.of("Joe", "Tom", "Tom", "Alan", "Peter");
		TreeMap<Integer, List<String>> map =
				names.collect(
					Collectors.groupingBy(
							String::length,			// key Function (length of name)
							TreeMap::new,			// map type Supplier
							Collectors.toList())	// downstream Collector	
				);
		
		System.out.println(map);
		System.out.println(map.getClass());
	}
}