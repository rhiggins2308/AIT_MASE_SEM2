package mase.oop2.codePrep2020;

public class Dog implements Comparable<Dog> {

	public Dog(Integer age,String name) {
		super();
		this.name = name;
		this.age = age;
	}

	private String name;
	private Integer age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Dog [name=" + name + ", age=" + age + "]";
	}

	@Override
	public int compareTo(Dog otherDog) {
		return this.getName().compareTo(otherDog.getName());
	}
}
