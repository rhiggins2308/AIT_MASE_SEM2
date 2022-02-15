package labs.week1.creatingjavaclasses.business;

import labs.week1.creatingjavaclasses.domain.Director;
import labs.week1.creatingjavaclasses.domain.Employee;
import labs.week1.creatingjavaclasses.domain.Manager;

public class EmployeeStockPlan {

	 	private final int employeeShares = 10;
	    private final int managerShares = 100;
	    private final int directorShares = 1000;

	    public EmployeeStockPlan() {
	    
	    }

	    public int grantStock(Employee emp) {
	        // Stock is granted based on the employee type
	        // Going from most-specific out (like exception handling)
	        if (emp instanceof Director) {
	            return directorShares;
	        } else {
	            if (emp instanceof Manager) {
	                return managerShares;
	            } else {
	                return employeeShares;
	            }
	        }
	        
//	        if (emp instanceof Manager) {  // bug: gives Directors 100 shares because
//	            return managerShares;      // a Director IS-A Manager
//	        } else {
//	            if (emp instanceof Director) {
//	                return directorShares;
//	            } else {
//	                return employeeShares;
//	            }
//	        }
	    }
	}