package labs.week5.streams.advancedstreams;

import java.util.Arrays;
import java.util.List;

/* Given the Person class (in the zip file),  declare a List typed for Person with the following Person’s:
 * 		a.	name=”Bob”, age=31
 * 		b.	name=”Paul”, age=32
 * 		c.	name=”John”, age=33
 * 
 * Pipeline the following where the return type is double:
 * 		stream the people,
 * 		filter the stream so that only Person’s whose age is >= 30 remain 
 * 		(i.e. filter out if age < 30)
 * 		map to int primitives
 * 		and calculate the average.
 * 
 * This should generate a NoSuchElementException.
 * Fix the pipeline so that no exception is generated i.e. 0.0 is returned.
 * 
 * (QID 2.1810)
 */
public class Handle_OptionalDoubleEmpty_orElse {
    public static void main(String[] args) {
        
        List<Person> friends = 
                Arrays.asList(  new Person("Bob", "Kelly", 31),
                                new Person("Paul", "Landers", 32),                                 
                                new Person("John", "Paters", 33)); 
        double averageAge = friends.stream()
                                // Stream<Person> filter(Predicate)
                                .filter(person->person.getAge()<30)     
                                // IntStream mapToInt(ToIntFunction)
                                //    ToIntFunction
                                //       int applyAsInt(T t)
                                .mapToInt(person->person.getAge())   
                                // OptionalDouble average()
                                .average()  
//                                .getAsDouble(); // NoSuchElementException: No value present
                                .orElse(0.0);//0.0
        System.out.println(averageAge);    }
    
}
