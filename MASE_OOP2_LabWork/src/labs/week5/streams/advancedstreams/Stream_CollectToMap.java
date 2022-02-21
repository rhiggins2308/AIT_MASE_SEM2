package labs.week5.streams.advancedstreams;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import labs.week5.streams.advancedstreams.Student.Grade;

/* Given the Student class
 * declare a List typed for Student with the following Student’s:
 * 		name=”S1” grade=A (note: grade is a static enum in Student)
 * 		name=”S2” grade=A
 * 		name=”S3” grade=C
 * 
 * Stream the list and
 * collect the information from the stream
 * into a Map
 * so that it appears as follows:
 * 		{C=[S3], A=[S1,S2]}
 * 
 * (QID Q2.1804)
 */

class Student {      
    
	public static enum Grade{ A, B , C, D, F}         
    
	private String name;     
    private Grade grade;     
    
    Student(String name, Grade grade){         
        this.name = name;         
        this.grade = grade;     
    }     
    
    public String getName() {
        return name;
    }
    
    public Grade getGrade() {
        return grade;
    }
    
    @Override
    public String toString(){         
        return name + ":" + grade;     
    }     
}

public class Stream_CollectToMap {
    public static void main(String[] args) {
        List<Student> ls = Arrays.asList(
                new Student("S1", Student.Grade.A), 
                new Student("S2", Student.Grade.A), 
                new Student("S3", Student.Grade.C)); 
       
	// .groupingBy() function returns a Map object
			
	/*		
		// Option 1
		Map<Student.Grade, List<Student>> grouping = ls.stream().collect(
				Collectors.groupingBy(Student::getGrade),
				Collectors.groupingBy(Student::getName, Collectors.toList()));
	*/
		/*	Invalid arguments to the collect method.
		*	
		*	Remember that Stream has only two overloaded collect methods:
		*		One that takes a Collector as an argument
		*		One that takes a Supplier, BiConsumer and BiConsumer
		*
		*	In this option, it is trying to pass two Collectors to the collect method.
		*	Therefore, it will not compile.
		*/
			
	/*
		// Option 2
		Map<Student.Grade, List<String>> grouping = ls.stream().collect(
				Collectors.groupingBy(Student::getGrade,
										Collectors.groupingBy(Student::getName, Collectors.toList());
	*/
		/*	The right hand side of = is actually okay from a compilation perspective.
		*	It is trying to group the elements of the stream by Grade
		*	and then it is again trying to group the elements of each grade by name.
		*
		*	So technically, the return type of this expression would be:
		*		Map<Student.Grade, Map<String, List<Student>>>
		*
		*	Even if you change the left hand side declaration as described, it will only print
		*		{C={S3=[S3:C]}, A={S1[S1:A], S2=[S2:A]}}
		*
		*	which is not what is required*
		*/

		// Option 3
		Map<Student.Grade, List<String>> grouping = ls.stream().collect(
			
			/* first parameter passed to groupingBy must be a key
			 * 		the Student getGrade() method returns the key that we want in this example
			 * 		i.e. Student.Grade
			 */
			Collectors.groupingBy(Student::getGrade,
			
			/* second parameter passed to groupingBy() is a DOWNSTREAM COLLECTOR
			 * which manipulates/processes the values to be returned by groupingBy()
			 * 
			 * .mapping() method maps one object into another (in this case Student -> String .. given by the function Student.getName())
			 * 
			 * .toList() tells .mapping() to return all students with a given grade as a List
			 */
			Collectors.mapping(Student::getName, Collectors.toList())));

		/*	This code illustrates how to cascade Collectors.
		 * 	
		 * 	Here, you are first grouping the elements by Grade
		 * 	and then collecting each element of a particular grade into a list
		 * 	after mapping it to a String.
		 * 	
		 * 	This will produce the required output
		 */
        				

	 /*
		// Option 4
		Map<Student.Grade, List<String>> grouping = ls.parallelStream().collect(
	    			Collectors.groupingBy(Student::getGrade,
	    			Collectors.mapping(Student::getName))
	   	);
	 */
		/*	Collectors.mapping() method requires two arguments
		 * 		1 - A Function that maps one element type into another (here, you are mapping Student to String, which is good)
		 * 		2 - an appropriate Collector, in which you can hold the result ... missing in this option
		 * 
		 * 	Since parameter 2 is missing, the code will not compile
		 */
		System.out.println(grouping); // 
    }
        
}

/*
        Map<Student.Grade, List<String>> grouping = 
                ls.stream()
                  .collect(
                          // Function gives us the key in the Map
                          // The values in the Map are the entries that match the key.
                    Collectors.groupingBy(Student::getGrade,
                        Collectors.mapping(Student::getName, Collectors.toList())));
        System.out.println(grouping); // 
*/