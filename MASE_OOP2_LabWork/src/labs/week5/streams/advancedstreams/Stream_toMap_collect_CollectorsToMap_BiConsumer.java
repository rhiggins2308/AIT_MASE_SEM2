package labs.week5.streams.advancedstreams;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/* Given the Book class (in the zip file),  declare a List typed for Book with the following Book’s:
 *		a.	title=”Atlas Shrugged”, price=10.0
 *		b.	title=”Freedom at Midnight”, price=5.0
 *		c.	title=”Gone with the wind”, price=5.0
 *
 * Stream the books and instantiate a Map named ‘bookMap’
 * that maps the book title to its price.
 * To do this use the collect(Collectors.toMap(Function fnToGetKey, Function fnToGetValue)).
 * Iterate through ‘bookMap’ (using the Map forEach(BiConsumer) method).
 * The BiConsumer only outputs prices where the title begins with “A”.
 * 
 * (QID 2.1846)
 */

public class Stream_toMap_collect_CollectorsToMap_BiConsumer {
    public static void main(String[] args) {
        List<Boook> books = Arrays.asList(        
                new Boook("Atlas Shrugged", 10.0),         
                new Boook("Freedom at Midnight", 5.0),         
                new Boook("Gone with the wind", 5.0)
        );
        
        /* API: Map 
         *	An object that maps keys to values. 
         *	A map cannot contain duplicate keys; each key can map to at most one value.
         */
        
        /* .forEach()
        		- Map version requires a BiConsumer because Map has key, value pair
        		- List or Set version requires Consumer
         */
        
// OPTION 1 
        Map<String, Double> bookMap = books.stream()
        									.collect(Collectors.toMap(
        												b -> b.getTitle(), 
        												b -> b.getPrice()
        												)
        											);
        BiConsumer<String, Double> func = (a, b) -> {	//define the lambda (not executing yet)
											        	if (a.startsWith("A")) {
											        		System.out.println(b);
										        	}
        };
        bookMap.forEach(func);	// execute lambda;   Map::forEach(BiConsumer) 

/*        
// OPTION 2
 	.toMap() ... NO SUCH METHOD ... should be Collectors.toMap()
 	
        Map<String, Double> bookMap = books.stream().toMap((b -> b.getTitle()), b -> b.getPrice()));
        BiConsumer<String, Double> func = (a, b) -> {
        	if (a.startsWith("A")) {
        		System.out.println(b);
        	}
        };
        bookMap.forEach(func);
*/        
/*
// OPTION 3
 	.toMap() ... NO SUCH METHOD ... should be Collectors.toMap()
 	BiConsumer takes in two parameters ... this example only provides one, i.e. func = (b) -> ...
 																	should be 	func = (a, b) -> ...
 	
        Map<String, Double> bookMap = books.stream().toMap((b -> b.getTitle()), b -> b.getPrice()));
        BiConsumer<Map.Entry> func = (b) -> {
        	if (b.getKey().startsWith("A")) {
        		System.out.println(b.getValue());
        	}
        };
        bookMap.forEach(func);
*/
/*      
 // OPTION 4
        Map<String, Double> bookMap = books.stream().collect(Collectors.toMap((b -> b.getTitle), b -> b.getPrice()));
        Consumer<Map.Entry<String, Double>> func = (e) -> {
        	if (e.getKey().startsWith("A")) {
        		System.out.println(e.getValue());
        	}
        };
        bookMap.forEach(func);
*/       
        
        // option D
        // using a Set here as opposed to a Map ... 
        // enabling the use of a Consumer, rather than BiConsumer
        Set<Entry<String, Double>> bookSet = bookMap.entrySet();
        Consumer<Map.Entry<String, Double>> funcC = (e)->{ 
            if(e.getKey().startsWith("A")){ 
                System.out.println(e.getValue()); 
            } 
        };
        bookSet.forEach(funcC); // Set::forEach(Consumer)
    }
}

class Boook{     
    private String title;     
    private double price;     
    Boook(String title, double price){         
        this.title = title;         
        this.price = price;     
    }     
    public String getTitle() {   return title;}
    public double getPrice() {   return price;}
    public String toString() { return title+ " " + price;}
}