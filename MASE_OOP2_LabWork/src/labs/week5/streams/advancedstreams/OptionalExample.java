package labs.week5.streams.advancedstreams;

import java.util.Optional;

public class OptionalExample {

	public static Optional<String> getGrade(int marks){
		
		Optional<String> grade = Optional.empty();
		
		if (marks > 50) {
			grade = Optional.of("PASS");
		} else {
			grade.of("FAIL"); // not being stored, as Optional is immutable, so grade cannot be changed
		}
		
		return grade; // returning empty Optional
	}
	
	public static void main(String[] args) {
	
		Optional<String> grade1 = getGrade(50);
		Optional<String> grade2 = getGrade(55);
		
		System.out.println(grade1.orElse("UNKNOWN"));
		
		if (grade2.isPresent()) {
			grade2.ifPresent(x -> System.out.println(x));
		} else {
			System.out.println(grade2.orElse("Empty"));
		}
	}
}