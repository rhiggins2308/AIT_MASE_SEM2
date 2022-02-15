package labs.week1.creatingjavaclasses.domain;

public class Manager extends Employee {

	private String deptName;
	private Employee[] staff;
	private int employeeCount = 0;

	public Manager(int empId, String name, String ssn, double salary, String deptName) {
		
		super(empId, name, ssn, salary);
		this.deptName = deptName;
		this.staff = new Employee[20];
	}

	public String getDeptName() {
		
		return this.deptName;
	}
	
	public boolean addEmployee(Employee emp) {
		
		if (findEmployee(emp) != -1) return false;
		
		this.staff[this.employeeCount] = emp;
		this.employeeCount++;
		return true;	
	}	

	public int findEmployee(Employee emp) {
		
		for (int e = 0; e < this.employeeCount; e++) {
			if (emp.equals(staff[e])) {
				return e;
			}
		}
		
		return -1;
	}
	
	public boolean removeEmployee(Employee e) {
		
		boolean empPartOfStaff = false;
		Employee[] tempStaff = new Employee[20];
		int tempEmpCount = 0;
		
		for (int i = 0; i < this.employeeCount; i++) {
			if (e.getEmpId() == staff[i].getEmpId()) {
				empPartOfStaff = true;
			} else {
				tempStaff[tempEmpCount] = staff[i];
				tempEmpCount++;
			}
		}
		
		if (empPartOfStaff) {
			this.staff = tempStaff;
			this.employeeCount = tempEmpCount;
		}
		
		return empPartOfStaff;
	}
	
	public void printStaffDetails() {
		
		if (this.employeeCount > 0) {
			System.out.println();
			System.out.println("\nStaff of " + this.getName() + ":");
			System.out.println("*************");
			
			for (int i = 0; i < this.employeeCount; i++) {
				System.out.println("Name:\t" + staff[i].getName() + "\tEmployee ID:\t" + staff[i].getEmpId());		
			}
		}
	}
	
	@Override
	public String toString() {
		return super.toString() + "\nDepartment:\t\t" + getDeptName();
	}
}