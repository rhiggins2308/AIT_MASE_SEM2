package mase.oop2.codePrep2020;

public class Other<T> {
/*
	public Other() {
		t = null;
	}
	public Other(T t) {
		this.t = t;
	}
*/
	private T t;

	public T get() {
		return t;
	}

	public void add(T t) {
		this.t =t;
	}
}
