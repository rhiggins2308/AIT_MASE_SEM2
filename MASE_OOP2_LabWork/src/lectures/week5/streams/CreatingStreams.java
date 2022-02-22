package lectures.week5.streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;



public class CreatingStreams {

	public static void main(String[] args) {
		arrayStream();
		collectionStream();
		streamOf();
		streamFromFile();
	}

	private static void arrayStream() {
		
		System.out.println("**** Array Stream ****");
		
		Double[] numbers = {1.1, 2.2, 3.3};
		/* Arrays.stream() creates a streat from the array 'numbers'.
		 * The array is considered the source of the stream and while the
		 * data is flowing through the stream, we have an opportunity to
		 * operate on the data.
		 */
		Stream<Double> stream1 = Arrays.stream(numbers);
		
		/* let's perform an operation on the data
		 * note that count() is a 'terminal operation' - this means that
		 * you cannot perform any more operations on the stream.
		 */
		long n = stream1.count();
		System.out.println("Number of elements: " + n);
	}
	
	private static void collectionStream() {
		
		System.out.println("**** Collection Stream ****");

		List<String> animalList = Arrays.asList("cat", "dog", "sheep");
		
		// using stream() which is a default method in Collection interface
		Stream<String> streamAnimals = animalList.stream();
		System.out.println("Number of elements: " + streamAnimals.count()); 	// 3
		
		/* stream() is a default method in the Collection interface and therefore
		 * in inherited by all classes that implement Collection. Map is NOT one
		 * of those, i.e. Map is not a Collection. To bridge between the two, we
		 * use the Map method entrySet() to return a Set view of the Map
		 * Set IS a Collection.
		 */
		Map<String, Integer> namesToAges = new HashMap<>();
		namesToAges.put("Mike", 22);
		namesToAges.put("Mary", 24);
		namesToAges.put("Alice", 31);
		System.out.println("Number of entries: " + 
				namesToAges
					.entrySet()		// get a Set (i.e. a Collection) view of the Map
					.stream()		// stream() is a default method in Collection
					.count()		// 3
				);
	}
	
	private static void streamOf() {
		
		System.out.println("**** Stream.of() ****");

		Stream<Integer> streamI = Stream.of(1, 2, 3);
		System.out.println(streamI.count());
		
		Stream<String> streamS = Stream.of("a", "b", "c", "d");
		System.out.println(streamS.count());
		
		Stream<Dog> streamD = Stream.of(new Dog());
		System.out.println(streamD.count());		
	}
	
	private static List<Cat> loadCats(String fileName) {
	
		List<Cat> cats = new ArrayList<>();
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
			stream.forEach(line -> {
				String[] catsArray = line.split("/");
				cats.add(new Cat(catsArray[0], catsArray[1]));
			});
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		return cats;
	}
	
	private static void streamFromFile() {
		List<Cat> cats = loadCats("files/Cats.txt");
		cats.forEach(System.out::println);
	}
}

class Dog {}

class Cat {
	private String name, colour;

	public Cat(String name, String colour) {
		super();
		this.name = name;
		this.colour = colour;
	}
	
	@Override
	public String toString() {
		return "Cat{ name=" + name + ", colour=" + colour + " }";
	}
}