package mase.oop2.code1.cormac;

public class SomeClass<T> {
	
	private T t;

	public T get() {
		return t;
	}

	public void add(T t) {
		this.t =t;
	}
}
