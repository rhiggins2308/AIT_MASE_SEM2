package labs.week5.streams.advancedstreams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/* 	Which of the following method(s) of the java.util.stream.Stream interface is/are used for reduction? (select 2)
 *  	- filter	NO - depending on the filter applied, the Stream may not be reduced
 *   	- reduce	YES - Combines a sequence of input elements and combines them into a single summary result
 *  	- sum		NO - sum() is not in teh Stream interface ... it is in IntStream / DoubleStream etc
 *  	- max		YES - min()/max() in the Stream interface take a Comparator
 *  	- add		NO - add() method does not exist in Stream interface
 *  
 *	(QID 2.1738)
 *
 *	a.	is sum() defined in the Stream interface?
 *	b.	what does mapToInt() do?
 *	c.	which version of  max() requires a Comparator – Stream or IntStream? Why?
 *	d.	why does the reduce(identity, BinaryOperator) not return an Optional whereas reduce(BinaryOperator) does?
 *			i.	Note: BinaryOperator is a special case of BiFunction where the 2 inputs and the output are all the same type T.
 */
public class Stream_Filter_Reduce_Max {
    public static void main(String[] args) {
        
    	// a. sum() is not in Stream it is in IntStream
        List<Integer> li2 = Arrays.asList(1,2,3);
        
        Integer sum = li2.stream()
                // IntStream mapToInt(ToIntFunction)
                // toIntFunction is a functional interface:
                //      int applyAsInt(T value)
                .mapToInt(i->i) // unboxing
                .sum();
        System.out.println("sum == "+sum);
        
        
        // b. max() in IntStream
        int max = li2.stream().mapToInt(i -> i.intValue()).max().getAsInt();
        System.out.println("max == "+max);

        
        // c. max(Comparator) in Stream
        List<Person> people = Arrays.asList(
                        new Person("Alan", "Burke", 22),
                        new Person("Zoe", "Peters", 20),
                        new Person("Peter", "Castle", 29));
        
        Person oldestPerson = people.stream()
                // Optional<Person> max(Comparator)
                // Comparator Comparator.comparing(Function that returns a Comparable)
                // comparing() takes the functional interface Function:
                //      R apply(T t)
                //      p.getAge() returns an Integer (is a Comparable)
                .max(Comparator.comparing(p->p.getAge())) 
                .get();
        System.out.println(oldestPerson);
                
        List<Integer> li = Arrays.asList(22,13,34);
        Optional<Integer> oi = li.stream()
                .max(Comparator.comparing(i->i));// Integer is a Comparable as Integer implements Comparable

        
        // d. reduce
        List<Integer> ls = Arrays.asList(10, 47, 33, 23);
        int max2 = ls.stream()
                // Optional<Integer> reduce(BinaryOperator<Integer> acc)
                // BinaryOperator<T> extends functional interface BiFunction<T,U,R>
                //    BiFunction's functional method is R apply(T t, U u)
        	//  .reduce((a, b)->a>b?a:b)
                .reduce((a, b)->Integer.max(a, b))
                .get();
        System.out.println(max2);

        int max3 = ls.stream()
                // Integer reduce(Integer identity, BinaryOperator<Integer> acc)
                // The identity element is both the initial value of the reduction 
                // and the default result if there are no elements in the stream. 
                .reduce(Integer.MIN_VALUE, (a, b)->Integer.max(a, b));
        System.out.println(max3);
    }   
}

class Person{
    private String firstName, lastName;
    private Integer age;

    Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person{" + "firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + '}';
    }   
}