package labs.week5.streams.advancedstreams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/* Given the Item class (in the zip file), declare a List typed for Item with the following Item’s:
 * 	id=1 name=”Screw”
 * 	id=2 name=”Nail”
 * 	id=3 name=”Bolt”
 * 
 * Stream the list and sort it so that it outputs “BoltNailScrew”
 * i.e. alphabetic name order. 
 * 
 * Use Stream’s forEach method to output the names 
 * (use the method reference version for the required Consumer lambda).
 * 
 * (QID Q2.1762)
 */
public class Stream_Sort{     
    
	private int id;     
    private String name;     
    
    public Stream_Sort(int id, String name){         
        this.id = id;         
        this.name = name;     
    }     
    
    public Integer getId() {         
        return id;     
    }      
    
    public void setId(int id) {         
        this.id = id;     
    }      
    
    public String getName() {         
        return name;     
    }      
    
    public void setName(String name) {         
        this.name = name;     
    }     
    
    public String toString(){    // outputs the name   
        return name;     
    } 
}

class Test {
	public static void main(String[] args) {
		List<Stream_Sort> l = Arrays.asList(
				new Stream_Sort(1, "Screw"),
				new Stream_Sort(2, "Nail"),
				new Stream_Sort(3, "Bolt")
		);
		
		/* Option 1
		 * Sorted by id. 
		 * Prints	ScrewNailBolt
		 */
		l.stream()
		.sorted((a, b) -> a.getId().compareTo(b.getId()))
		.forEach(System.out::print);

		/* Option 2
		 * Sorted by name
		 * Prints	BoltNailScrew
		 */
		l.stream()
//		Stream<Item> sorted(Comparator) 
		.sorted(Comparator.comparing(a -> a.getName())).map((i) -> i.getName())
//      .map((item)->item.getName()) // mapping to a Stream<String>     
//      .map(Item::getName) // method reference version     
		.forEach(System.out::print);
		
		/* Option 3
		 * Not sorted
		 * Prints	ScrewNailBolt
		 */
		l.stream()
		.map((i) -> i.getName())
		.forEach(System.out::print);
		
		/* Option 4
		 * sorted by natural order
		 * Prints	BoltNailScrew
		 */
		l.stream()
		.map((i) -> i.getName())
		.sorted()
		.forEach(System.out::print);
	}
}
