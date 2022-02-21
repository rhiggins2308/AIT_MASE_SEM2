package lectures.week3.sortingviacomparablecomparator;

public class Cat implements Comparable<Cat>{
	private String name;
	private int age;
	
	public Cat(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public String getName() {
		return this.name;
	}
	
	@Override
	public String toString() {
		return "Cat { name=" + this.name + ", age=" + this.age + " }";
	}
	
	@Override
	public boolean equals (Object obj) {	// consistency with compareTo()
		if (obj instanceof Cat) {
			Cat otherCat = (Cat) obj;
			if (this.name.equals(otherCat.getName())) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int compareTo(Cat otherCat) {	// natural sort order is by name
		return this.name.compareTo(otherCat.getName());
	}
}