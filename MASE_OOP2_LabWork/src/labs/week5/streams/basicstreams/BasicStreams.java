package labs.week5.streams.basicstreams;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;

class Purchase{
    String item;
    double price; // NB: this is a double and NOT a Double
    Purchase(String item, double price) {
        this.item = item; 
        this.price = price;
    }
}

public class BasicStreams {
	public static void main(String []args){
        // a.
        List<Integer> nums = Arrays.asList(9, 10, 13, 76, 8, -9);
        System.out.println("Number of numbers > 10 is: "+
                nums
                    .stream() // create a stream
                    // filter(Predicate) - Predicate takes a value, returns a boolean    
                    .filter(num -> num > 10)   
                    .count());// 2

        // b.
        List<String> names = Arrays.asList("Andrew", "Brian", "Charlie", "Aaron", "Ed");
        System.out.println("Number of names that begin with 'A' is: "+
                names
                    .stream() // create a stream
                    .map(name -> name.toUpperCase())  // IN:Function                  
                    .filter(name -> name.startsWith("A"))  // IN:Predicate 
                    .count());// 2
        
        // c.
        List<Double> temps = Arrays.asList(98.4, 100.2, 87.9, 102.8);
        //Output of next line is: Number of temps > 100 is: 2
        System.out.println("Number of temps > 100 is: "+
                temps
                    .stream() // create a stream
                    .filter(temp -> temp > 100)   
                    .count());// 2

        // d.
        // stream() is a default method in the Collection interface and therefore 
        // is inherited by all classes that implement Collection. Map is NOT one 
        // of those i.e. Map is not a Collection. To bridge between the two, we 
        // use the Map method entrySet() to return a Set view of the Map (Set 
        // IS-A Collection).
        Map<String, Integer> myMap = new HashMap<>();
        myMap.put("Spot", 1);myMap.put("Rex", 4);myMap.put("Charlie", 8);
        //Output of next line is: Number of dogs older than 4 is: 1
        System.out.println("Number of dogs older than 4 is: "+
                myMap
                    .entrySet() // get a Set (i.e. Collection) view of the Map
                    .stream()   // stream() is a default method in Collection
                    .filter(key -> key.getValue() > 4)   
                    .count());  // 1   

        // e.
        List<String> moreNames = Arrays.asList("Andrew", "Brian", "Charlie", "Aaron", "Ed", "Sean");
        
        boolean b = moreNames
                    .stream() // create a stream
                    .map(name -> name.toUpperCase())                    
                    .anyMatch(name -> name.startsWith("S")); // IN:Predicate
        if(b){
            System.out.println("At least one name begins with 'S'");
            long count = moreNames
                        .stream() // re-create the stream
                        .map(name -> name.toUpperCase())                    
                        .filter(name -> name.startsWith("S"))   
                        .count();// 1
            System.out.println("\tThere is/are "+count+" name(s) that begin with 'S'.");
        }else{
            System.out.println("No names begin with 'S'");
        }
        
        // f.
        List<Purchase> purchases = Arrays.asList(
                new Purchase("Shirt",100.1), 
                new Purchase("Shoes",149.9), 
                new Purchase("Jumper",90.3) ); 
        // Optionals are new to Java 8 and help in situations where we may/may not have
        // a value at the end. For example, what if we have filtered out all the values;
        // we won't know this until we get to the terminal operation as streams are lazy.
        // If we are doing an average, we don't want to divide by 0; this is where Optionals help.
        OptionalDouble avgPrice = purchases
                .stream()   // returns a Stream<Purchase>
                // DoubleStream mapToDouble(ToDoubleFunction m)
                //     ToDoubleFunction's functional method is:   *double* applyAsDouble(T t)
                // DoubleStream is a stream of double primitive values whereas
                // Stream<T> e.g. Stream<Double> is a stream of object references.        
                .mapToDouble(purchase -> purchase.price)// take in a Purchase and returning a double primitive
                .filter(price -> price >= 100.0 && price <= 200.0)// (i) FILTER A
//                .filter(price -> price >= 10.0 && price <= 20.0)    // (ii) FILTER B
                .average(); // terminal operation
 //       System.out.println(avgPrice); // OptionalDouble[125.0]
 //       System.out.println(avgPrice.getAsDouble()); // 125.0
        
        // what happens if I filter for >= 10 && <= 20 (FILTER B above)? 
        // This means that no Purchase passes the filter...
        // note: Getting back null is inappropriate as we are getting back a double primitive 
        //       and 0.0 could be a valid average...
        // Result: "getAsDouble()" returns: java.util.NoSuchElementException: No value present
        if(avgPrice.isPresent()){    // solves the Exception issue
            System.out.println(avgPrice.getAsDouble()); // FILTER A output: 100.4
        }else{
            System.out.println("Empty optional!"); // FILTER B output
        }
        
    }
}