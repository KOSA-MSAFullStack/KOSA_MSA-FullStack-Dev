package ch4_3_ApplyingPolymorphism;

public class Manager extends Employee {
	private String deptName;
	
	// Constructors
	public Manager(int empId, String name, String ssn, double salary, String dept) {
		// employees2 constructor call
		super(empId, name, ssn, salary);
		this.deptName = dept;
	}// end E..
	
	public String getDeptName() {
		return deptName;
	}// end Gett
	// Method Overriding
	@Override
	public String getDetails() {
		return super.getDetails() + " Department: " + deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
}// end class


