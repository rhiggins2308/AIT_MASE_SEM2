package com.ait.boundaries;

import java.util.List;

import com.ait.entities.*;

public interface EmployeeDAO {
	Employee addEmployee(int employeeId, Employee employee);
	Employee getEmployee(int employeeId);
	List<Employee> getEmployees();
}
