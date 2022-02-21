package lectures.week3.equalshashcode;

public class EqualsExample {

	public static void main(String[] args) {
		Foo f1 = new Foo(2);
		Foo f2 = new Foo(2);
		
		System.out.println(f1 == f2); 			// false	Object references are not equal
		System.out.println(f1.equals(f2)); 		// true		Objects are equal, since their fooValue attributes are equal
		System.out.println(f1.equals("SK"));	// false 	No ClassCastException
	}

}

class Foo {
	private int fooValue;
	
	public Foo (int val) {
		this.fooValue = val;
	}
	
	public int getFooValue() {
		return this.fooValue;
	}
	
	@Override
	public boolean equals(Object o) {
		// && short-circuits if 'o' is not of type Foo
		// therefore the downcast will never generate a ClassCastException
		
		if ((o instanceof Foo) && ((Foo) o).getFooValue() == this.fooValue) {
			return true;
		} else {
			return false;
		}
		
		// on one line ...
		//		return (o instanceof Foo) && ((Foo) o).getFooValue() == this.fooValue;
	}
}