package labs.week5.streams.advancedstreams;

import java.util.OptionalDouble;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

/* Generate a DoubleStream using the of() method consisting of the numbers 0, 2 and 4.
 * 
 * Note that this stream is a stream of primitives and not a stream of types.
 * Filter the stream to filter in odd numbers only.
 * Sum the remaining stream. You should get 0.
 * 
 * Why does the sum() operation return double but average() return OptionalDouble?
 * 
 * (QID 2.2024)
 */
public class sumReturnsPrimitive_avgReturnsOptional {
    public static void main(String[] args) {
        // 1. sum()
        DoubleStream is = DoubleStream.of(0, 2, 4); //1 
        double sum = is.filter( i->i%2 != 0 ).sum(); // sum() is a terminal operation (reduction also) 
        System.out.println("sum is "+sum); // 0.0

        // 2. average()
        OptionalDouble od = Stream.of(1.0,3.0)
                // DoubleStream mapToDouble(ToDoubleFunction)
                //      ToDoubleFunction
                //          double applyAsDouble(T value);
                                .mapToDouble(n->n.doubleValue())
                                .filter(n -> n%2 == 0)
                                .average();// terminal operation (reduction also)
        if(od.isPresent()){
            System.out.println("average == "+od.getAsDouble());
        }
    }
    
}
