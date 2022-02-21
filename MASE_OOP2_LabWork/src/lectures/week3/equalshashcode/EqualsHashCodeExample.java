package lectures.week3.equalshashcode;

public class EqualsHashCodeExample {
	
	public static void main(String[] args) {
		Foo2 f1 = new Foo2(2);
		Foo2 f2 = new Foo2(2);
		Foo2 f3 = new Foo2(3);
		
		System.out.println(f1.hashCode()); 	// 34
		System.out.println(f2.hashCode()); 	// 34
		System.out.println(f3.hashCode()); 	// 51		
	}
}

class Foo2 {
	private int fooValue;
	
	public Foo2 (int val) {
		this.fooValue = val;
	}
	
	public int getFooValue() {
		return this.fooValue;
	}
	
	@Override
	public boolean equals(Object o) {
		// && short-circuits if 'o' is not of type Foo
		// therefore the downcast will never generate a ClassCastException
		
		if ((o instanceof Foo2) && ((Foo2) o).getFooValue() == this.fooValue) {
			return true;
		} else {
			return false;
		}
		
		// on one line ...
		//		return (o instanceof Foo2) && ((Foo2) o).getFooValue() == this.fooValue;
	}
	
	@Override
	public int hashCode() {
		return fooValue * 17;	// using the same instance var as equals()
	}
	/* The following implementation does NOT violate the contract as two
	   equal objects will return the same hashcode 100.
	   It is legal and even correct, but horibbly inefficient, as all objects (including unequal ones)
	   land in the same bucket.
	   This implementation does not improve the search time, which is what hashcodes are supposed to do.
	 */
//	public int hashCode() {
	//return 100;
//	}
}