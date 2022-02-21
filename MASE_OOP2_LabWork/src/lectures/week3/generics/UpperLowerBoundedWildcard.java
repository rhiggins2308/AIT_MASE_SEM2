package lectures.week3.generics;

import java.util.ArrayList;
import java.util.List;


class Animal {}
class Doog extends Animal {}
class Terrier extends Doog {}
class Caat extends Animal {}
class Manx extends Caat {}

public class UpperLowerBoundedWildcard {

	public static void addAnimal(Animal[] animals) {
		animals[0] = new Doog();
		animals[1] = new Caat();		//	ArrayStoreException generated - JVM knows the type
	}
	
	public static void addAnimal(List<Animal> animals) {
		animals.add(new Doog());
	}
	
	public static void main(String[] args) {
		/* 1a. Polymorphic assignments.
		 * Generics came in in Java 5. Type erasure required to support legacy code.
		 * Thus, generics offer compile time protection.
		 * Arrays have similar issues (polymorphic assignments) but the difference is
		 * in how the compiler and JVM behave. Due to type erasure with generics, 
		 * the JVM does not know the types. With arrays, the JVM does. */
		
		// First, let's look at arrays
//		Doog[] dogs0 = {new Dog(), new Dog()};
//		addAnimal(dogs0);
		
		// polymorphism ok for the base type; 			List 	->	ArrayList
		List<Caat> cats1 = new ArrayList<>();
				
		// polymorphism is not ok for the generic type;	Animal 	-> 	Cat
//		List<Animal> animals = new ArrayList<Cat>();
		List<Caat> cats2 = new ArrayList<Caat>();	// generic types on both sides must match
		List<Caat> cats3 = new ArrayList<>();	// or be inferred, in the object, by the reference type
		
		/* As the JVM does not know the types (stripped out during type erasure), the
		 * compiler has to step in. */
//		addAnimal(cats2);
		
		/* 1b. 'extends' - polymorphic assignments
		 * Note: extends is read-only */
		List<? extends Animal> animals1 = new ArrayList<Animal>();
//		animals.add(new Animal());	// read-only
		List<? extends Animal> animals2 = new ArrayList<Doog>();
		List<? extends Animal> animals3 = new ArrayList<Terrier>();
		List<? extends Animal> animals4 = new ArrayList<Caat>();
		List<? extends Animal> animals5 = new ArrayList<Manx>();
//		List<? extends Animal> animals6 = new ArrayList<Object>();	// Animal is a subclass of Object, so cannot add an Object .. up the chain
		
		// 1c. 'super' - polymorphic assignments
		List<? super Doog> dogs1 = new ArrayList<Doog>();
		dogs1.add(new Doog());	//	now, can add to the list
		List<? super Doog> dogs2 = new ArrayList<Animal>();
		List<? super Doog> dogs3 = new ArrayList<Object>();
//		List<? super Doog> dogs4 = new ArrayList<Terrier>();		// Terrier is a subclass of Doog, so cannot add Terrier .. down the chain

		// 2. declarations for 'extends' and 'super' examples
		List<Object> objects = new ArrayList<>();	objects.add(new Object());
		List<Animal> animals = new ArrayList<>();	animals.add(new Animal());
		List<Caat> cats = new ArrayList<>();	cats.add(new Caat());
		List<Manx> manxcats = new ArrayList<>();	manxcats.add(new Manx());
		List<Doog> dogs = new ArrayList<>();	dogs.add(new Doog());
		List<Terrier> terriers = new ArrayList<>();	terriers.add(new Terrier());
		
		
		// 3. extends
		//			ext(List<? extends Animal> list)	=>	read-only
		ext(animals);	//	Animal is-an Animal			-	OK
		ext(cats);		//	Caat is-an Animal			-	OK
		ext(manxcats);	//	Manx is-an Animal			-	OK
		ext(dogs);		//	Doog is-an Animal			-	OK
		ext(terriers);	//	Terrier is-an Animal		-	OK
//		ext(objects);	//	Object is-not-an Animal		-	OK
		
		
		// 4. super
		//		spr(List<? super Cat> list)		=>	modifiable
		spr(cats);		// Caat is-a Caat					-	OK
		spr(animals);	// Animal is-a supertype of Caat	-	OK
		spr(objects);	// Object is-a supertype of  Caat	-	OK
//		spr(dogs);		// Doog is-not Caat or a supertype of Caat		-	NOT OK
//		spr(terriers);	// Terrier is-not Caat or a supertype of Caat	-	NOT OK
//		spr(manxcats);	// Manx Doog is-not Caat or a supertype of Caat	-	NOT OK

		
	}
	
	public static void ext(List<? extends Animal> list) {	//	"upper-bound" is Animal
		/* 'extends' implies read-only
		   IN: List<Animal>, List<Caat>, List<Manx>, List<Doog>, List<Terrier>
		   If 'extends' allowed us to add to 'list', then we could take in
		   a List of Caat and add a Dog to it - thereby breaking type safety. */
//		   list.add(new Caat());	//	compiler error if we try to modify 'list'
//		   list.add(new Doog());	//	compiler error if we try to modify 'list'
//		   list.add(new Animal());	//	compiler error if we try to modify 'list'
		   
//		   for(Dog dog : list) {	//	compiler errore reading - 'list' could be a list of Caat
//			   System.out.println(dog);
//		   }
		   
		   // No compiler errors reading once we treat them as Animal; whether this method receives
		   // a List of Animal, Caat, Manx, Doog or Terrier; they are *all* Animal.
		   for (Animal animal : list) {
			   System.out.println(animal);
		   }		   
	}	
	
	public static void spr(List<? super Caat> list) {	//	"lower-bound" is Caat
		/* IN: Caat, Animal, Object
		 * 
		 * The only objects that can safely be ADDED are any type of Caat (including subtypes)
		 * because the method could be getting in a list of Animals or Objects (or Caats). */
		list.add(new Caat());		//	Caat is-a Caat (Caat is-an Animal, Caat is-an Object)
		list.add(new Manx());		//	Manx is-a Caat (Manx is-an Animal, Manx is-an Object)
		
//		list.add(new Doog());		// compiler error - Doog is not a Caat
//		list.add(new Animal());		// compiler error - Animal is not a Caat (Caat is an Animal)
		
//		for(Caat cat : list) {		// compiler errors when reading = 'list' passed in could be Objects
//			System.out.println(cat);
//		}

//		for(Animal animal : list) {		// compiler errors when reading = 'list' passed in could be Objects
//			System.out.println(animal);
//		}

		for(Object obj : list) {		// OK - the only thing we can safely say is that the 'list'
			System.out.println(obj);	// coming in can all be treated as Objects
		}								// Caat is-an Object, Animal is-an Object, Object is-an Object
	}
}