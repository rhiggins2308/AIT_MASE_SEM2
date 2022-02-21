package labs.week5.streams.advancedstreams;

// Why Optionals:
// https://www.oracle.com/technical-resources/articles/java/java8-optional.html


/* Stream a list of int primitives
 * between the range of 0 (inclusive) and 5 (exclusive).
 * Calculate and output the average.
 * 
 * (QID 2.2023)
 */

import java.util.OptionalDouble;
import java.util.stream.IntStream;

public class intStreamRangeVsRangeClosed {
    public static void main(String[] args) {

    	/* IntStream range(a, b)
    	 * 
    	 * returns a sequentially ordered IntStream object
    	 * from start (inclusive) to end (exclusive) 
    	 */
    	IntStream is1 = IntStream.range(0,5);       //0..4
        is1.forEach(System.out::println);           // forEach is a terminal operation
        
        /* IntStream rangeClosed(a, b)
    	 * 
    	 * returns a sequentially ordered IntStream object
    	 * from start to end (both inclusive) 
    	 */
    	IntStream is2 = IntStream.rangeClosed(0,5); //0..5
        is2.forEach(System.out::println); 

        
        // This is why it returns OptionalDouble and not double
        OptionalDouble avg = IntStream.range(0,0).average();
        System.out.println(avg);// OptionalDouble.empty
        
        IntStream is3 = IntStream.range(0, 0);    // 0,1,2,3,4 = 5 numbers
        
        /*
         * .orElse() provides an alternative return if the value is present
         * this is why OptionalDouble is not needed here
         * because a value will always be returned
         * even if the average() method returns no value
         */
        double avg2 = is3.average().orElse(0);  // 0+1+2+3+4 = 10/5 = 2.0
        System.out.println(avg2);
    }            
}
