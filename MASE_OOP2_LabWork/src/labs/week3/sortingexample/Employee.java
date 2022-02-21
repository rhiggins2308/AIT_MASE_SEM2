//https://www.digizol.com/2008/07/java-sorting-comparator-vs-comparable.html

package labs.week3.sortingexample;

public class Employee implements Comparable<Employee>{
    private int empId;
    private String name;
    private int age;

    public Employee(int empId, String name, int age) {
        this.empId = empId;
        this.name = name;
        this.age = age;
    }
 

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee{" + "empId=" + empId + ", name=" + name + ", age=" + age + '}';
    }
/**
    * Compare a given Employee with this object.
    * If employee id of this object is 
    * greater than the received object,
    * then this object is greater than the other.
    */
    @Override
    public int compareTo(Employee otherEmployee) {
        return this.empId - otherEmployee.empId ;
    }    
}