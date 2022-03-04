package com.ait.boundaries;

public class FactoryDAO {
	private static EmployeeDAO eDAO = new EmployeeDB();

	public static EmployeeDAO getEmployeeDAO() {
		return eDAO;
	}
}
