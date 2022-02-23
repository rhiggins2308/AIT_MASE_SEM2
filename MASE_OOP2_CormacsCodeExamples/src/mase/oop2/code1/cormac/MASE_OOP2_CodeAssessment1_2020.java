package mase.oop2.code1.cormac;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import mase.oop2.codePrep2020.Dog;
import mase.oop2.codePrep2020.Other;

public class MASE_OOP2_CodeAssessment1_2020 {

	private static Scanner sc = new Scanner(System.in);
	static int choice = 0;

	public static void main(String[] args) {

		while (choice != 4) {
			menuOptions();
			System.out.println();
			switch (choice) {
			case 1:
				lambdas();
				break;
			case 2:
				generics();
				break;
			case 3:
				collections();
				break;
			case 4:
				break;
			default:
				System.out.println("invalid option entered, please enter 1-4");
				break;
			}

		}
	}

	static public int menuOptions() {

		System.out.println("\nWhat do you want to do?\n1. Lambdas\n2. Generics\n3. Collections\n4. Exit");
		System.out.print("Enter choice -->");
		choice = sc.nextInt();
		return choice;
	}

	public static void lambdas() {
		List<Person> persons = new ArrayList<>();

		persons.add(new Person("John", 25));
		persons.add(new Person("Mary", 21));
		persons.add(new Person("Tom", 30));
		persons.add(new Person("Niamh", 27));
		
		System.out.println("Normal insertion order:");
		for (Person person : persons) {
			System.out.println(person);
		}

		persons.sort((p1, p2) -> p2.getTheAge().compareTo(p1.getTheAge()));

		System.out.println("\nSorted by age(descending order):");
		for (Person person : persons) {
			System.out.println(person);
		}
	}

	public static void generics() {
		Other<Person> otherP = new Other<>();
		
		otherP.add(new Person("Michael",34));
		System.out.println(otherP.get());
	}

	public static void collections() {
		System.out.println("in collections");
	}

	public static boolean check(Object o1, Object o2) {
		return false;

	}
}
