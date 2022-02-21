package labs.week5.streams.advancedstreams;

import java.util.Optional;

/* Declare an Optional, typed for Double, named ‘price
 * 		using the Optional.ofNullable(20.0).
 * 
 * Output the Optional value for ‘price’ 3 times:
 * 		using ifPresent(Consumer), orElse(T) and orElseGet(Supplier).
 * 
 * (QID 2.1849)
 * 
 * 		a.	declare a new Optional, typed for Double, named ‘price2’.
 * 			Use Optional.ofNullable again but this time, pass in null.
 * 				i.		Output ‘price2’ in a normal System.out.println().
 * 				ii.		check to see if price2 isEmpty() and if so output “empty”.
 * 				iii.	do (ii) again except this time use the more functional “ifPresent(Consumer)” method.
 * 				iv.		initialise a Double x to the return of “price2.orElse(44.0)”. Output and observe the value of x.
 * 
 * 		b.	declare a new Optional, typed for Double, named ‘price3’.
 * 			Use Optional.ofNullable passing in null. 
 * 				i.	initialise a Double z to the return of “price3.orElseThrow(() -> new RuntimeException(“Bad Code”)”.
 * 					Output and observe the value of z.
 */
public class OptionalDouble_Of_OfNullable {
    public static void main(String[] args) {
        // Q asked on YT:
        Optional<Double> price = Optional.ofNullable(null); // null passed in
        System.out.println(price); // Optional.empty
        Double x = price.orElse(null);
        System.out.println(x); // null
        Double x2 = price.orElseGet(()->null);// Supplier
        System.out.println(x2); // null
        
        
        // ofNullable explained:
        //		Optional o = Optional.ofNullable(value)
        //          is the same as:
        //      Optional o = (value == null) ? Optional.empty() : Optional.of(value)
        Optional<Double> price2 = Optional.ofNullable(20.0);
        price2.ifPresent(System.out::println);// 20.0
        Double y = price2.orElse(22.0);
        System.out.println(y);// 20.0
        Double y2 = price2.orElseGet(()->33.0);// Supplier
        System.out.println(y2);// 20.0

        
        // 
 /*
        Optional<Double> price3 = Optional.ofNullable(null); // null passed in
        System.out.println(price3); // Optional.empty
        if (price3.isEmpty()){
            System.out.println("empty");// "empty"
        }
        price3.ifPresent(System.out::println);// nothing
        Double z = price.orElse(44.0);
        System.out.println(z);// 44.0
*/        
        // Option 1 generates a java.lang.NullPointerException; use ofNullable
        //Optional<Double> price4 = Optional.of(null);
        
        // Option 4 does not compile - Optional.of(T t) takes one argument
        // Optional<Double> price5 = Optional.of(21.1, 10.0);

        // Option 5
        //Optional<Double> price6 = Optional.of(null); //  java.lang.NullPointerException
        //Optional<Double> price6 = Optional.ofNullable(null); 
        
        // Note: no "throw" in Supplier in next statement
   /*     Double z = 
          price.orElseThrow(()->new RuntimeException("Bad Code"));//java.lang.RuntimeException: Bad Code
        System.out.println(z);
   */
    }   
}