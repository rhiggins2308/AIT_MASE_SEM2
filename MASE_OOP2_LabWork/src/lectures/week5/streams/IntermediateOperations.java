package lectures.week5.streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class IntermediateOperations {

	public static void main(String[] args) {
		filter();
		distinct();
		limit();
		map();
		flatMap();
		sortedWithComparator();
		sortedWithoutComparator();
	}

	private static void filter() {
		Stream.of("galway", "mayo", "roscommon")
				.filter(countyName -> countyName.length() > 5)
				.forEach(System.out::print);
	}

	private static void distinct() {
		System.out.println();
		Stream.of("eagle", "eagle", "EAGLE")
				.peek(s -> System.out.print(" 1. " + s))
				.distinct()
				.forEach(s -> System.out.print(" 2. " + s));
	}
	
	private static void limit() {
		System.out.println();
		Stream.of(11, 22, 33, 44, 55, 66, 77, 88, 99)
				.peek(n -> System.out.print(" A - " + n))
				.filter(n -> n > 40)
				.peek(n -> System.out.print(" B - " + n))
				.limit(2)
				.forEach(n -> System.out.print(" C - " + n));
	}
	
	private static void map() {
		System.out.println();
		Stream.of("book", "pen", "ruler")
				.map(s -> s.length())
				.forEach(System.out::print);
	}
	
	private static void flatMap() {
		System.out.println();
		
		List<String> list1 = Arrays.asList("sean", "desmond");
		List<String> list2 = Arrays.asList("mary", "ann");
		Stream<List<String>> streamOfLists = Stream.of(list1, list2);
		
		// flatMap(Function(T, R))
		//		flatMap(List<Strig>, Stream<String>)
		streamOfLists.flatMap(list -> list.stream())
				.forEach(System.out::print);
	}
	
	private static void sortedWithComparator() {
		System.out.println();
		
		Person john = new Person("John", 23);
		Person mary = new Person("Mary", 25);
		
		Stream.of(mary, john)
			//.sorted(Comparator.comparing(Person::getAge))
			.sorted(Comparator.comparing(p -> p.getAge()))
			.forEach(System.out::print);
	}
	
	private static void sortedWithoutComparator() {
		System.out.println();
		
		Stream.of("Tim", "Jim", "Peter", "Ann", "Mary")
			.peek(name -> System.out.print(" 0. " + name))
			.filter(name -> name.length() == 3)
			.peek(name -> System.out.print(" 1. " + name))
			.sorted()
			.peek(name -> System.out.print(" 2. " + name))
			.limit(2)
			.forEach(name -> System.out.print(" 3. " + name));
	}
	
}

class Person {
	private String name;
	private int age;
	
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public int getAge() {
		return age;
	}
}