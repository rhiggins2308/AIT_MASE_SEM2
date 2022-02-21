package labs.week5.streams.advancedstreams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/* Given the Book class (in the zip file),  declare a List typed for Book with the following Book’s:
 * 		a.	title=”Gone with the wind”, price=5.0
 * 		b.	title=”Gone with the wind”, price=10.0
 * 		c.	title=”Atlas shrugged”, price=15.0
 * 
 * In a pipeline which has no return type: 
 * 		stream the books, 
 * 		generate a Map that maps the book title to its price,
 * 		and output the title and price of each entry in the map.
 * 
 * What happened and why?
 * Fix this by using the Collectors.toMap(Function, Function, BinaryOperator) method.
 * 
 * (QID 2.1847)
 */
public class Collect_ToMap_DuplicateKey {
    public static void main(String[] args) {
        List<Book> books = Arrays.asList(         
                new Book("Gone with the wind", 5.0),         
                new Book("Gone with the wind", 10.0),         
                new Book("Atlas Shrugged", 15.0) ); 
        
        // Map cannot have duplicate key
        // In this case "Gone With The Win is in the books List twice
        books.stream()
//                .collect(Collectors.toMap((b->b.getTitle()), b->b.getPrice())) //  IllegalStateException
        
        		// this overloaded version of Collectors.toMap(), provides a mergeFunction as a third parameter
        		// this tells toMap() how to handle values where a duplicate key exists.
        		// in this case, the merge function takes in the two values for the duplicate key, and adds them
                .collect(Collectors.toMap((b->b.getTitle()), b->b.getPrice(), (v1,v2) -> v1+v2))                         
                .forEach((a, b)->System.out.println(a+" "+b)); 
    }
}
