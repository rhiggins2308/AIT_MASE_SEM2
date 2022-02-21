package lectures.week3.sortingviacomparablecomparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortingMultipleFields {

	public static void main(String[] args) {
		
		List<Cat> cats = new ArrayList<>();
		cats.add(new Cat("Trixy", 5));
		cats.add(new Cat("Bella", 7));
		cats.add(new Cat("Bella", 2));

		Comparator<Cat> compCat = Comparator
										.comparing(Cat::getName)
										.thenComparingInt(Cat::getAge);
		
		Collections.sort(cats, compCat);
		System.out.println(cats); 	//	[Cat{name=Bella, age=2}, Cat{name=Bella, age=7}, Cat{name=Trixie, age=5}]
	}
}