package lectures.week5.streams;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorsPartitioningBy {

	public static void main(String[] args) {
		partitioningByStringTest();
		partitioningByLengthTest();
		partitioningByEmptyList();
		partitioningByListToSet();
	}

	private static void partitioningByStringTest() {
		Stream<String> names = Stream.of("Thomas", "Teresa", "Mike", "Alan", "Peter");
		Map<Boolean, List<String>> map =
				names.collect(
						// pass in a Predicate
						Collectors.partitioningBy(s -> s.startsWith("T"))
				);
		
		System.out.println(map);
	}
	
	private static void partitioningByLengthTest() {
		Stream<String> names = Stream.of("Thomas", "Teresa", "Mike", "Alan", "Peter");
		Map<Boolean, List<String>> map =
				names.collect(
						// pass in a Predicate
						Collectors.partitioningBy(s -> s.length() > 4)
				);
		
		System.out.println(map);
	}
	
	private static void partitioningByEmptyList() {
		Stream<String> names = Stream.of("Thomas", "Teresa", "Mike", "Alan", "Peter");
		Map<Boolean, List<String>> map =
				names.collect(
						// pass in a Predicate
						Collectors.partitioningBy(s -> s.length() > 10)
				);
		
		System.out.println(map);
	}
	
	private static void partitioningByListToSet() {
		Stream<String> names = Stream.of("Thomas", "Teresa", "Mike", "Alan", "Peter");
		Map<Boolean, Set<String>> map =
				names.collect(
						Collectors.partitioningBy(
								s -> s.length() > 4,
								Collectors.toSet())
				);
		
		System.out.println(map);
	}
}
