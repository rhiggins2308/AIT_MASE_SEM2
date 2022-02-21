package lectures.week3.sortingviacomparablecomparator;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sorting_Comparable_Comparator {

	public static void main(String[] args) {
		// comparable sorts by the "natural ordering
		// in this case, the name of the Dog
		comparable(new Dog[] {new Dog("Spot", 2), new Dog("Rover", 7)});		// just-in-time array
		comparable(Arrays.asList(new Dog("Spot", 2), new Dog("Rover", 7)));
		
		// comparator 
		comparator(new Dog[] {new Dog("Rover", 7), new Dog("Spot", 2)});
		comparator(Arrays.asList(new Dog("Rover", 7), new Dog("Spot", 2)));
	}

	private static void comparable(Dog[] dogArray) {
		Arrays.sort(dogArray);
		System.out.println(Arrays.toString(dogArray)); // [Dog{name=Rover, age=7}, Dog{name=Spot, age=2]
	}
	
	private static void comparable(List<Dog> dogList) {
		Collections.sort(dogList);
		System.out.println(dogList); 					// [Dog{name=Rover, age=7}, Dog{name=Spot, age=2]
	}

	private static void comparator(Dog[] dogArray) {
		// sorts ascending by age
		Comparator<Dog> byAge = Comparator.comparing(dog -> dog.getAge());
		Arrays.sort(dogArray, byAge);
		System.out.println(Arrays.toString(dogArray));	// [Dog{name=Spot, age=2, Dog{name=Rover, age=7}]
	
		Comparator<Dog> byAgeReversed = Comparator.comparing(Dog::getAge).reversed();
		Arrays.sort(dogArray, byAgeReversed);
		System.out.println(Arrays.toString(dogArray));	// [Dog{name=Rover, age=7}, Dog{name=Spot, age=2]
	}

	private static void comparator(List<Dog> dogList) {
		// sorts ascending by age
		Comparator<Dog> byAge = Comparator.comparing(Dog::getAge);
		Collections.sort(dogList,  byAge);
		System.out.println(dogList); 					// [Dog{name=Spot, age=2, Dog{name=Rover, age=7}]
		
		Comparator<Dog> byAgeReversed = Comparator.comparing(Dog::getAge).reversed();
		Collections.sort(dogList,  byAgeReversed);
		System.out.println(dogList); 					// [Dog{name=Rover, age=7}, Dog{name=Spot, age=2]
	}
}

class Dog implements Comparable<Dog> {
	
	private String name;
	private Integer age;
	
	public Dog(String name, Integer age) {
		this.name = name;
		this.age = age;
	}
	
	public Integer getAge() {
		return this.age;
	}
	
	public String getName() {
		return this.name;
	}
	
	@Override
	public String toString() {
		return "Dog { name=" + this.name + ", age=" + this.age + " }";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Dog) {
			Dog otherDog = (Dog) obj;
			if (this.name.equals(otherDog.name)) {
				return true;
			}
		}
		return false;
	}
	/* API:
	 	The natural ordering for a class C is said to be consistent with equals if and only if
	 	e1.compareTo(e2) == 0 has the same boolean value as e1.equals(e2), for every e1 and e2 of class C.
	 	
	 	It is strongly recommended (though not required) that natural orderings be consistent with equals.
	 	This is so because sorted sets (and sorted maps) without explicit comparators behave "strangely" when
	 	they are used with elements (or keys) whose natural ordering is inconsistent with equals.
	 */
	@Override
	public int compareTo(Dog otherDog) {	// specifies "natural ordering" for Dog
		// delegate to String which implements Comparable<String>
		return this.name.compareTo(otherDog.getName());
	}
}