package mase.oop2.code1;

public class Person {

	private String theName;
	private Integer theAge;
	
	public Person(String theName, Integer theAge) {
		this.theName = theName;
		this.theAge = theAge;
	}

	public String getTheName() {
		return theName;
	}

	public Integer getTheAge() {
		return theAge;
	}
	
	@Override
	public String toString() {
		return "Person{theName=" + this.theName + ", theAge=" + this.theAge + "}";
	}
	
	@Override
	public int compareTo(Person person) {
		// TODO Auto-generated method stub
		return this.getTheName().compareTo(person.getTheName());
	}	
}