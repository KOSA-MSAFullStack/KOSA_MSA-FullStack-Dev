package ch3_2_JavaSubclassing;

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
		this.empId = empId;
		this.name = name;
		this.ssn = ssn;
		this.salary = salary;
	}// end class
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
	public void raiseSalary(double increase) {
		this.salary = salary + increase;
	}
}// end class

