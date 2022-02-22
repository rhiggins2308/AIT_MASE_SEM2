package lectures.week3.generics;

import java.util.ArrayList;
import java.util.List;

public class PolymorphicIssueWithGenerics {

	public static void showList(List<Object> list) {
		for (Object o : list) {
			System.out.println(o);
		}
	}
	
	public static void main(String[] args) {
		List<Double> doubles = new ArrayList<Double>();
		doubles.add(12.3);
		
//		List<Object> objects = doubles;	// compiler error
		// now we have a List reference that is typed for any Object, pointing at an ArrayList that is typed for Doubles
		// this issue now is that when we try to add an Object to the objects List, that is not a Double, this cannot be done.
//		objects.add("This is a String");
		
		// a different variation
		List<String> names = new ArrayList<>();
		names.add("Robbie");
//		showList(names);	// cannot pass a List into a List parameter, where the generic typing is different between source and destination		
	}
}