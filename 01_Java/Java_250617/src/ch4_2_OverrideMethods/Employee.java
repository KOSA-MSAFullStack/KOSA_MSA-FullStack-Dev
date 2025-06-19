package ch4_2_OverrideMethods;

public class Employee {
	// fields
	public int empId;
	public String name;
	public String ssn;
	public double salary;
	
	// constructor
	public Employee() {
	}
	public Employee(int empId, String name, String ssn, double salary) {
		super();
		this.empId = empId;
		this.name = name;
		this.ssn = ssn;
		this.salary = salary;
	}
	
	// methods
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public int getEmpId() {
		return empId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public double getSalary() {
		return salary;
	}
	public String getDetails() {
		return "Employee id:  " + empId + " Employee name:" + name;
	}
}// end class

