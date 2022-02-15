package labs.week1.creatingjavaclasses.domain;

import java.text.NumberFormat;

public class Employee {

	private int empId;
	private String name;
	private String ssn;
	private double salary;
		
	public Employee(int empId, String name, String ssn, double salary) {
		super();
		this.empId = empId;
		this.name = name;
		this.ssn = ssn;
		this.salary = salary;
	}

	public int getEmpId() {
		return this.empId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String empName) {
		if (empName.isEmpty() || empName == null) {
			System.out.println("Updated name must not be an empty string");
		} else {
			this.name = empName;
		}
	}

	public String getSsn() {
		return this.ssn;
	}

	public double getSalary() {
		return this.salary;
	}
	
	public void raiseSalary(double increase) {
		if (increase <= 0) {
			System.out.println("Raise must be a positive amount");
		} else {
			this.salary += increase;
		}
	}
	
	@Override
	public String toString() {
		return "Employee ID:\t\t" + this.getEmpId() + 
			   	"\nEmployee Name:\t\t" + this.getName() + 
				"\nEmployee SSN:\t\t" + this.getSsn() + 
			   	"\nEmployee Salary:\t" + NumberFormat.getCurrencyInstance().format(getSalary());
	}
}