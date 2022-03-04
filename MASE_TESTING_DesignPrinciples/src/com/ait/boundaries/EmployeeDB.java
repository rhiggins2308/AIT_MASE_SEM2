package com.ait.boundaries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ait.entities.Employee;

public class EmployeeDB implements EmployeeDAO {
	private final Map<Integer, Employee> employees; 
	private final List<Employee> employeeList;

	protected EmployeeDB(){
		employees = new HashMap<Integer, Employee>();
		employeeList=new ArrayList<Employee>();
	}
	
	@Override
	public Employee addEmployee(final int employeeId, final Employee employee) {
		employees.put(employeeId, employee);
		return employee;
	}
	
	@Override
	public Employee getEmployee(final int employeeId) {
		return employees.get(employeeId);
	}
	
	@Override
	public List<Employee> getEmployees() {
		employeeList.clear();
		for (final Map.Entry employee:employees.entrySet()){
			employeeList.add((Employee) employee.getValue());
		}
		return employeeList;
	}
	
}
