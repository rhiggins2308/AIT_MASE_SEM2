package mase.oop2.code1.cormac;

public class Person implements Comparable<Person>{

	public Person(String theName, Integer theAge) {
		super();
		this.theName = theName;
		this.theAge = theAge;
	}
	private String theName;
	private Integer theAge;
	public String getTheName() {
		return theName;
	}
	public Integer getTheAge() {
		return theAge;
	}
	@Override
	public String toString() {
		return "Person [theName=" + theName + ", theAge=" + theAge + "]";
	}
	
	@Override
	public int compareTo(Person person) {
		// TODO Auto-generated method stub
		return this.getTheName().compareTo(person.getTheName());
	}
	
	
}
