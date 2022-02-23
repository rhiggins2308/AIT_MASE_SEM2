package mase.oop2.codePrep2020;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class CodeOneSample {

	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		// sort();
		//lambdas();
		generics();

	}

	public static void lambdas() {
		List<Dog> dogs = new ArrayList<>();

		dogs.add(new Dog(3, "Spot"));
		dogs.add(new Dog(2, "Rex"));
		dogs.add(new Dog(5, "Tyson"));
		dogs.add(new Dog(8, "Brownie"));

		// lambda
		dogs.sort((d1,d2) -> d1.getAge().compareTo(d2.getAge()));
		
		
		for (Dog dog : dogs) {
			System.out.println(dog);
		}
	}

	public static void generics() {
		//Other<String> otherS = new Other<>("Jack");
		Other<String> otherS = new Other<>();
		Other<Integer> otherI = new Other<>();
		Other<Dog> otherD = new Other<>();
		
		otherS.add("SK");
		System.out.println(otherS.get());
		
		otherI.add(3); //new Integer(3) boxed as an Integer
		System.out.println(otherI.get());
		
		otherD.add(new Dog(4,"Captain"));
		System.out.println(otherD.get());
	}

	public static void sort() {

		Set<Dog> dogs = new TreeSet<>();

		dogs.add(new Dog(3, "Spot"));
		dogs.add(new Dog(2, "Rex"));
		dogs.add(new Dog(5, "Tyson"));
		dogs.add(new Dog(8, "Brownie"));

		for (Dog dog : dogs) {
			System.out.println(dog);
		}
	}
}
