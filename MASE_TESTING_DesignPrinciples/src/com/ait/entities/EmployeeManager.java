package com.ait.entities;

import java.util.List;

import com.ait.boundaries.EmployeeDAO;
import com.ait.boundaries.FactoryDAO;

public class EmployeeManager {
	EmployeeDAO database = FactoryDAO.getEmployeeDAO();
	public void addEmployee(final int employeeId, final String employeeName, final String employeeAddress){
		final Employee employee=new Employee(employeeId, employeeName, employeeAddress);
		database.addEmployee(employeeId, employee);	
	}
	
	public Employee getEmployee(final int employeeId){
		return database.getEmployee(employeeId);
	}
	
	public List<Employee> getEmployees(){
		return database.getEmployees();
			
	}

}
