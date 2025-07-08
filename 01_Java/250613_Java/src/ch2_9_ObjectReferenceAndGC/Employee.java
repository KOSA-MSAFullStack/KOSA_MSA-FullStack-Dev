package ch2_9_ObjectReferenceAndGC;
public class Employee {
	//fields
	public int empId;
	public String name;
	public String ssn;
	public double salary;
	//constructor
	public Employee() {	}
	//methods
	public void setEmpId(int empId) {
		this.empId = empId;	
	}
	public int getEmpId() {	
		return empId;
	}
	public void setName(String name) {	this.name = name;}
	public String getName() {	return name;}
	public void setSsn(String ssn) {	this.ssn = ssn;	}
	public void setSalary(double salary) {	this.salary = salary;	}
	public double getSalary() {	return salary;	}
}// end class

