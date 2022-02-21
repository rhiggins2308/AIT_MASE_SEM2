package lectures.week3.sortingviacomparablecomparator;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BinarySearch {

	public static void main(String[] args) {
		searchStrings();
		searchCats();
	}
	
	private static void searchStrings() {
		List<String> names = Arrays.asList("John", "Martin", "Paula", "Ann");
		Collections.sort(names);		// natural order sort - alphabetic for Strings
		System.out.println(names);		// [Ann, John, Martin, Paula]
		// if found, return index
		System.out.println(Collections.binarySearch(names, "John"));	// 1
		// if not found, return: - (indexItWouldHaveIfPresent) -1
		// "Laura" this is: -(2) -1 = -2 -2 = -3
		System.out.println(Collections.binarySearch(names, "Laura"));
	}
	
	private static void searchCats() {
		Cat fluffy = new Cat("Fluffy", 1);
		Cat bella = new Cat("Bella", 5);
		
		List<Cat> catList = Arrays.asList(fluffy, bella);
		
		Collections.sort(catList);		// must sort first by natural order - name ascending
		System.out.println(catList);	// [Cat{name=Bella, age=5}, Cat{name=Fluffy, age=1}]
		
		/* API: Searches the specified list for the specified object using the binary search algorithm.
		 		The list must be sorted into ascending order, according to the natural ordering of it's elements
		 		(as by the sort(List) method) prior to making this call. If it is not sorted, the results are undefined.
		 		If the List contains multiple elements equal to the specified object, there is no guarantee which one will be found */
		System.out.println("Bella is at index: " + Collections.binarySearch(catList, bella));	// 0
		
		// set up the Comparator<T>
		//		int compare(T o1, To2)
		Comparator<Cat> byAge = (cat1, cat2) -> cat1.getAge() - cat2.getAge();
		Collections.sort(catList, byAge);	// sort by age ascending
		System.out.println(catList);		// [Cat{name=Fluffy, age=1}, Cat{name=Bella, age=5}]
		
		/* API: Searches the specified list for the specified object using the binary search algorithm.
 		The list must be sorted into ascending order, according to the specified comparator
 		(as by the sort(List) method) prior to making this call. If it is not sorted, the results are undefined.
 		If the List contains multiple elements equal to the specified object, there is no guarantee which one will be found */
		System.out.println("Bella is at index: " + Collections.binarySearch(catList,bella, byAge));	// 1
	}
}