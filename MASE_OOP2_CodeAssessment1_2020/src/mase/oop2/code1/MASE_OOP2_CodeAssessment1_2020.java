package mase.oop2.code1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Predicate;

public class MASE_OOP2_CodeAssessment1_2020 {

	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		int choice = 0;
		
		do {
			choice = userChoice();
			
			switch (choice) {
				case 1: lambdas();
						break;
				case 2: generics();
						break;
				case 3: collections();
						break;
				default: System.out.println("Enter an option from 1 to 4");
				 		break;
			}
		} while (choice != 4);
		
		

	}
	
	public static void lambdas() {
		List<Person> people = new ArrayList<>();
		people.add(new Person("John", 25));
		people.add(new Person("Mary", 21));
		people.add(new Person("Tom", 30));
		people.add(new Person("Niamh", 27));
		
		System.out.println("\nNormal insertion order:");
		people.forEach(System.out::println);
		
		people.sort(Comparator.comparing(Person::getTheAge).reversed());
		System.out.println("\nSorted by Age (descending order):");
		people.forEach(System.out::println);
		
		
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
		
	}

	public static void collections() {
		Map<String, Integer> slamWinners = new LinkedHashMap<>();
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