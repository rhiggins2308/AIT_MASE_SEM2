package lectures.week3.equalshashcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MutableFieldsTest {

	public static void main(String[] args) {
		// Dog has a hashCode() method
		Dog spot = new Dog("Spot");
		Map<Dog, String> dogs = new HashMap<>();
		
		dogs.put(spot, "Great Dane");
		System.out.println("spotInmap " + spot.hashCode() + " in map? : " + dogs.containsKey(spot));  	// 419, true
		
		/* API: "Note: great care must be exercised if mutable objects are used as map keys.
		   The behaviour of a map is not specified if the value of an object is changed in a manner
		   that affects equals comparisons while the object is a key in the map." */
		
		// change the object state (the dogs name); note that the dogs name IS USED in the 
		// calculation of the hash value (and is used in equals() also).
		spot.setName("Rex");
		
		// assuming we have overridden hashCode(), it is false as the object state has changed
		// i.e. the hash code is re-calculated and is going to be differenr because the 'name' is
		// now different - this is dangerous!
		System.out.println("spotInMap " + spot.hashCode() + " in map? : " + dogs.containsKey(spot));  	// 418, false
	}
}

class Dog {

	private String name;

	public Dog(String name) {
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Dog) {
			Dog otherDog = (Dog) obj;
			return this.name.equals(otherDog.name);
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		int hash = 5;
		hash = 83 * hash + this.name.length();
		return hash;
	}

	
	
	
}