package lectures.week4.methodreferences;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class MethodRefsAndContext {

	public static void main(String[] args) {
		// No Person being passed in => Supplier
		Supplier<Integer> lambda1 = () -> Person.howMany();
		Supplier<Integer> methRef1 = Person::howMany;
		
		System.out.println(lambda1.get());		// 0
		System.out.println(methRef1.get());		// 0

		// One Person to be passed in => Function
		Function<Person, Integer> lambda2 = (person) -> Person.howMany(person);
		Function<Person, Integer> methRef2 = Person::howMany;
		
		System.out.println(lambda2.apply(new Person()));		// 1
		System.out.println(methRef2.apply(new Person()));		// 1

		// Two Persons to be passed in => BiFunction
		BiFunction<Person, Person, Integer> lambda3 = (p1, p2) -> Person.howMany(p1, p2);
		BiFunction<Person, Person, Integer> methRef3 = Person::howMany;
		
		System.out.println(lambda3.apply(new Person(), new Person()));		// 2
		System.out.println(methRef3.apply(new Person(), new Person()));		// 2
	}
}

class Person {
	public static Integer howMany(Person... people) {
		return people.length;
	}
}