package labs.week1.creatingjavaclasses;

import java.text.NumberFormat;

import labs.week1.creatingjavaclasses.business.EmployeeStockPlan;
import labs.week1.creatingjavaclasses.domain.Admin;
import labs.week1.creatingjavaclasses.domain.Director;
import labs.week1.creatingjavaclasses.domain.Employee;
import labs.week1.creatingjavaclasses.domain.Engineer;
import labs.week1.creatingjavaclasses.domain.Manager;

public class EmployeeTest {

	public static void main(String[] args) {

		Engineer eng = new Engineer(101, "Jane Smith", "012-34-4567", 120_345.27);
		Manager mgr = new Manager(207, "Barbara Johnson", "054-12-2367", 109_501.36, "US Marketing");
		Admin adm = new Admin(304, "Bill Monroe", "108-23-6509", 75_002.34);
		Director dir = new Director(12, "Susan Wheeler", "099-45-2340", 120_567.36, "Blobal Marketing", 1_000_000.00);
	
		// Test the EmployeeStockPlan class
        EmployeeStockPlan esp = new EmployeeStockPlan();
        printEmployee(eng, esp);
        printEmployee(adm, esp);
        printEmployee(mgr, esp);
        printEmployee(dir, esp);
        
		System.out.println("\nTesting raiseSalary and setName on Manager:");
        mgr.setName ("Barbara Johnson-Smythe");
        mgr.raiseSalary(10_000.00);
        printEmployee(mgr, esp);
		
		/*
		if(mgr.addEmployee(eng)) System.out.println("Success: added eng");
		if(mgr.addEmployee(adm)) System.out.println("Success: added adm");
		mgr.printStaffDetails();
		
		System.out.println("\nTesting raiseSalary and setName on Manager:");
        mgr.setName ("Barbara Johnson-Smythe");
        mgr.raiseSalary(10_000.00);
        printEmployee(mgr, esp);
		*/
	}
	
	public static void printEmployee(Employee emp) {
		System.out.println();
        System.out.println("EMPLOYEE DETAILS\n****************");
		System.out.println("Employee Type:\t\t" + emp.getClass().getSimpleName());
		System.out.println(emp);
	}
	
	public static void printEmployee(Employee emp, EmployeeStockPlan esp) {
        printEmployee (emp);
        System.out.println("Stock Options:\t\t" + esp.grantStock(emp));
    }
}
