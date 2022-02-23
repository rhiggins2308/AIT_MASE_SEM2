package mase.oop2.code1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MASE_OOP2_CodeAssessment1_2020 {

	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		int userChoice = 0;
		
		do {
			userChoice = userChoice();
			
			switch (userChoice) {
				case 1: lambdas();
						break;
				case 2: generics();
						break;
				case 3: collections();
						break;
				default: System.out.println("Enter an option from 1 to 4");
				 		break;
			}
		} while (userChoice != 4);
	}
	
	public static void lambdas() {
		List<Person> people = new ArrayList<>();
		//List<Person> people = new ArrayList<>();
		people.add(new Person("John", 25));
		people.add(new Person("Mary", 21));
		people.add(new Person("Tom", 30));
		people.add(new Person("Niamh", 27));
		
		System.out.println("\nNormal insertion order:");
		/*
		 	for (Person person : people) {
				System.out.println(person);
			}
		 */
		
		people.forEach(System.out::println);
		/*
		 	people.sort((p1, p2) -> p2.getTheAge().compareTo(p1.getTheAge())); 
		 */		
		
		people.sort(Comparator.comparing(Person::getTheAge).reversed());
		System.out.println("\nSorted by Age (descending order):");
		people.forEach(System.out::println);
		/*
		 	for (Person person : persons) {
				System.out.println(person);
			}
		 */
		
		int x = 4;
		System.out.println("Is " + x + " even? " + check(x, n -> n % 2 == 0));
		x = 7;
		System.out.println("Is " + x + " even? " + check(x, n -> n % 2 == 0));
		
		String name = "Mr. Joe Bloggs";
		System.out.println("Does " + name + " start with Mr. ? " + check(name, s -> s.startsWith("Mr.")));

		name = "Ms. Ann Bloggs";
		System.out.println("Does " + name + " start with Mr. ? " + check(name, s -> s.startsWith("Mr.")));

		Person person = new Person("Mike", 33);
		System.out.println("Is " + person.getTheName() + " an adult? " + check(person, p -> p.getTheAge() >= 18));

		person = new Person("Ann", 13);
		System.out.println("Is " + person.getTheName() + " an adult? " + check(person, p -> p.getTheAge() >= 18));
	}

	public static void generics() {
		SomeClass<Person> otherP = new SomeClass<>();
		otherP.add(new Person("Michael", 34));
		System.out.println(otherP.get());
		
		SomeClass<String> otherS = new SomeClass<>();
		otherS.add("Higgins");
		System.out.println(otherS.get());
		
		SomeClass<Integer> otherI = new SomeClass<>();
		otherI.add(10);
		System.out.println(otherI.get());	
	}

	public static void collections() {

		Map<String, Integer> slamWinners = new HashMap<>();
		slamWinners.put("Djokovic", 17);
		slamWinners.put("Federer", 20);
		slamWinners.put("Nadal", 19);
		slamWinners.put("Thiem", 0);
		
		slamWinners
			.entrySet()
			.stream()
			
			
		
		System.out.println(map);
	}

	public static <T> boolean check(T t, Predicate<T> lambda) {
		return lambda.test(t);
	}

	public static int userChoice() {
		
		System.out.print("\nWhat do you want to do ?"
							+ "\n1. Lambdas"
							+ "\n2. Generics"
							+ "\n3. Collections"
							+ "\n4. Exit"
							+ "\nEnter choice --> ");
		
		return sc.nextInt();
	}	
}