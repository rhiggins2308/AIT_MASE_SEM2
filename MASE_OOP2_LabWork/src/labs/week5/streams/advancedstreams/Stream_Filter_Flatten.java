package labs.week5.streams.advancedstreams;
// Stream is a sequence of objects
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/* Generate a Stream<List<String>> using the Stream.of(Arrays.asList(“a”, “b”), Arrays.asList(“a”, “c”)) method call.
 * Filter the stream so that only list’s that contain “c” make it through the filter.
 * 
 * Flatten the Stream<List<String>> to a Stream<String> using the flatMap() operation.
 * 
 * Use forEach() to kick off the whole operation and output the stream.
 * 
 * (QID 2.1787)
 */

public class Stream_Filter_Flatten {
	
    public static void main(String[] args) {
    
    // Simple example
   
    	// The Stream of Lists, contains two lists of type String
    	Stream<List<String>> sl = Stream.of(
    				Arrays.asList("a", "b"),
    				Arrays.asList("a", "c")
    		);
    	
    	/* .filter() takes each list s, from Stream sl
    	 *		checks if the List contains "c"
    	 *
    	 *	First list does not contain "c"
    	 *	Second list contains "c"
    	 *
    	 *	result of .filter() is passed forward to .flatMap()
    	 *		List containing "a" and "c" is passed to .flatMap()
    	 */	
    	Stream<String> news = sl.filter(s -> s.contains("c"))
    			/*	.flatMap() flattens the Stream of String Lists into a Stream of Strings
    	    	 *		effectively removes the List structure and leaves the String objects in a flat Stream
    	    	 *
    	    	 *	Flat Stream is stored in news Stream<String> object
    	    	 *		news contains a Stream of the String elements "a" and "c"	
    	    	 */
    			.flatMap(olds -> olds.stream());
    	
    	/* Each element of news Stream object is passed to System.out.print
    	 * 	So contents of Stream is printed as follows:
    	 * 		ac
    	 */
    	news.forEach(System.out::print);
    	
    	
    // More complex example	
    	Stream<List<String>> streamOfLists = Stream.of(
                Arrays.asList("a", "b"),
                Arrays.asList("d", "c"),
                Arrays.asList("a", "c"));

        streamOfLists
                // From Java API
        		// 	   Return Type	   method(parameter type)
        		// Stream<List<String>> filter(Predicate)   	Predicate takes in a test function, returns boolean
                .filter(list -> list.contains("c"))
                
                // From Java API
        		// 	   Return Type	   method(parameter type)
                // Stream<List<String>> peek(Consumer)			Consumer takes in a parameter, returns nothing
                .peek(list -> System.out.println("\n"+list)) // 1. [d,c]    2. [a,c]
                
                // From Java API
        		//  Return Type	  method(parameter type)
                // Stream<String> flatMap(Function)  			Function takes an argument and returns somethins from the apply() method
                //      Function<T,R> == R apply(T t)
                //          Stream<String> apply(List<String> l)
                .flatMap(list -> list.stream())
//                .flatMap(List::stream)
               //     forEach() is a terminal operation
                .forEach(str -> System.out.print(str+ " ")); // 1.  d c     2.  a c  
        System.out.println();

//            List<String> list = Arrays.asList("a", "b", "c");
//            list.forEach(s -> System.out.println(s)); // Consumer == void accept(T t)
//            Stream<String> stream = list.stream();
//            stream.forEach(System.out::println);
    }
}