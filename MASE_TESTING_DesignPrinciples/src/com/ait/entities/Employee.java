package com.ait.entities;

public class Employee {
			
	private final int employeeId;
	private final String name;
	private final String address;

	public Employee(final int employeeId, final String name, final String address) {
		this.employeeId = employeeId;
		this.name = name;
		this.address = address;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public String getEmployeeName() {
		return name;
	}
	public String getEmployeeAddress() {
		return address;
	}
}
