package labs.week4.lambdas.functionalinterfaces;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

//a functional interface i.e. it has only one abstract method 
interface Printable<T> {
 void print(T t); // similar to Consumer
}

interface Retrievable<T> {
 T retrieve();// similar to Supplier
}

interface Evaluate<T> {
 boolean isNegative(T t);// similar to Predicate
}

interface Functionable<T, R> {
 R applyThis(T t);// similar to Function
}

public class BasicLambdas {

	public static void main(String[] args) {
		
		BasicLambdas bl = new BasicLambdas();
		
		System.out.println("**** Printable Interface vs java Consumer ****"
						+ "\n**********************************************");
		bl.consumer();

		System.out.println("\n**** Retrievable Interface vs java Supplier ****"
				+ "\n**********************************************");
		
		bl.supplier();
		
		System.out.println("\n**** Evaluate Interface vs java Predicate ****"
				+ "\n**********************************************");
		
		bl.predicate();
		
		System.out.println("\n**** Functionable Interface vs java Function ****"
				+ "\n**********************************************");
		
		bl.function();
		
		List<Person> listPeople = bl.getPeople();
		sortAge(listPeople);
		sortName(listPeople);
		sortHeight(listPeople);
	}
	
	public void consumer() {
		
		// *** Custom ***
		// Printable<T> is a functional interface ... i.e. one abstract method:
		//		void print(T t); // similar to java.util.function.Consumer
		
		Printable<String> lambda = s -> System.out.println(s);
		lambda.print("Printable lambda");
		
		// *** In the Java API ***
		// Consumer is a functional interface ... i.e. one abstract method:
		//		void accept(T t)
		Consumer<String> consumer = s -> System.out.println(s); // lambda
		consumer.accept("Consumer lambda");
		
		Consumer<String> consumerMR = System.out::println; // method reference
		consumerMR.accept("Consumer method reference");
	}
	
	public void supplier() {
		
		// *** Custom ***
		// Retrievable<T> is a functional interface ... i.e. one abstract method:
		//		T retrieve(); // similar to java.util.function.Supplier
		Retrievable<Integer> lambda = () -> 77;
		System.out.println("Retrievable: " + lambda.retrieve());
		
		// *** In the Java API ***
		// Supplier<T> is a functional interface ... i.e. one abstract method:
		//		T get()
		Supplier<Integer> supplier = () -> 77;
		System.out.println("Supplier: " + supplier.get());
	}

	public void predicate() {
		
		// *** Custom ***
		// Evaluate<T> is a functional interface ... i.e. one abstract method:
		//		boolean isNegative(T t); // similar to java.util.function.Predicate
		Evaluate<Integer> lambda = i -> i < 0;
		System.out.println("Evaluate: " + lambda.isNegative(-1));
		System.out.println("Evaluate: " + lambda.isNegative(+1));
		
		// *** In the Java API ***
		// Predicate<T> is a functional interface i.e. one abstract method:
		//		boolean test(T t);
		Predicate<Integer> predicate = i -> i < 0;
		System.out.println("Predicate: " + predicate.test(-1));
		System.out.println("Predicate: " + predicate.test(+1));
		
		int x = 4;
		System.out.println("Is " + x + " even? " + check(x, n -> n % 2 == 0));
		x = 7;
		System.out.println("Is " + x + " even? " + check(x, n -> n % 2 == 0));
		
		String name = "Mr. Joe Bloggs";
		System.out.println("Does " + name + " start with Mr. ? " + check(name, s -> s.startsWith("Mr.")));

		name = "Ms. Ann Bloggs";
		System.out.println("Does " + name + " start with Mr. ? " + check(name, s -> s.startsWith("Mr.")));

		Person person = new Person("Mike", 33, 1.8);
		System.out.println("Is " + person.getName() + " an adult? " + check(person, p -> p.getAge() >= 18));

		person = new Person("Ann", 13, 1.4);
		System.out.println("Is " + person.getName() + " an adult? " + check(person, p -> p.getAge() >= 18));
	}
	
	public static <T> boolean check(T t, Predicate<T> lambda) {
		return lambda.test(t);
	}
		
 	public void function() {
	 	
 		// *** Custom ***
 		// Functionable<T, R> is a functional interface i.e. one abstract method:
	 	//		R applyThis(T t);	// similar to java.util.function.Function
 		Functionable<Integer, String> lambda = i -> "Number is: " + i;
 		System.out.println("Functionable: " + lambda.applyThis(25));
 		
 		// *** In the Java API ***
 		// Function<T, R> is a functional interface i.e. one abstract method:
 		//		R apply(T t);
 		Function<Integer, String> function = i -> "Number is: " + i;
 		System.out.println("Function: " + function.apply(25));
 	 		
	}

 	private static void sortAge(List<Person> listPeople) {
		listPeople.sort(Comparator.comparing(p -> p.getAge())); 	// lambda syntax
		Comparator.comparing(Person::getAge); 						// method reference
		
		System.out.println("After Sort By Age");
		listPeople.forEach(s -> System.out.println(s)); 			// lambda
		listPeople.forEach(System.out::println); 					// method reference
	}
 	
	private static void sortName(List<Person> listPeople) {
		// Note: In Java 8, the List interface supports the sort method directly,
		//		so no need to use Collections.sort anymore
		
		listPeople.sort(Comparator.comparing(p -> p.getName())); 	// lambda syntax
		Comparator.comparing(Person::getName); 						// method reference
		System.out.println("After Sort By Name");
		
		listPeople.forEach(s -> System.out.println(s)); 			// lambda syntax
		listPeople.forEach(System.out::println); 					// method reference
	}

	
	
	private static void sortHeight(List<Person> listPeople) {
		listPeople.sort(Comparator.comparing(p -> p.getHeight()));
		System.out.println("After Sort By Height");
		
		listPeople.forEach(s -> System.out.println(s)); // lambda syntax
		listPeople.forEach(System.out::println); // method reference
	}

	private List<Person> getPeople() {
	
		List<Person> result = new ArrayList<>();
		result.add(new Person("Mike", 33, 1.8));
		result.add(new Person("Mary", 25, 1.4));
		result.add(new Person("Alan", 34, 1.7));
		result.add(new Person("Zoe", 30, 1.5));
		return result;
	}

}

class Person {
	private Integer age;
	private String name;
	private Double height;
	
	public Person(String name, Integer age, Double height) {
		this.age = age;
		this.name = name;
		this.height = height;
	}

	public Integer getAge() {
		return age;
	}

	public String getName() {
		return name;
	}

	public Double getHeight() {
		return height;
	}

	@Override
    public String toString() {
        return "Person{" + "age=" + age + ", name=" + name + ", height=" + height + '}';
    }
}