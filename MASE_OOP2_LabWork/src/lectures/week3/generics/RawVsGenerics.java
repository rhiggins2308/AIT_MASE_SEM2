package lectures.week3.generics;

import java.util.ArrayList;
import java.util.List;

public class RawVsGenerics {

	public static void main(String[] args) {
		// a raw collection can hold any type of Object (except a primitive).
		List myList = new ArrayList();	//	can't enforce a type
		myList.add("Fred");				// will hold Strings
		myList.add(new Dog());			// and Dogs
		myList.add(43);					// and Integers (autoboxing)
		
		/* As everything is treated as an Object, when you are getting something out of
		 * the collection, all you have are Objects - therefore a cast was required to
		 * get your String back */
		String s = (String) myList.get(0);	// cast requires, or else compiler error.
		/* and as we could not guarantee that what was coming out
		 * whas really a String (as we were allowed to put anything in),
		 * this cast could fail at runtime */
		//String s1 = (String) myList.get(1);	//	classCastException at runtime, because a Dog is not a String
		
		/* Generics takes care of both ends (putting in and getting out)
		 * by enforcing the type of your collections.
		 * Note: generic syntax means putting the type in angle brackets */
		
		List<String> myList2 = new ArrayList<>();
		myList2.add("Fred");			// will hold Strings
//		myList.add(new Dog());			// compiler error
		/* Because what is going IN is guaranteed, what is coming OUT is
		 * also guaranteed => no need for the cast */
		String s2 = myList2.get(0);
	}

}

class Dog {
	public Dog () {
		
	}
}