package ch4_3_ApplyingPolymorphism;

import java.util.Objects;
public class Employee2 {
	// fields
	public int empId;
	public String name;
	public String ssn;
	public double salary;
	// constructor
	public Employee2() {
	}
	public Employee2(int empId, String name, String ssn, double salary) {
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
	@Override
	public String toString() {
		return "Employee id:  " + empId + "\n" + "Employee name:" + name;
	}
	@Override
	public boolean equals(Object o) {
		boolean result = false;
		if ((o != null) && (o instanceof Employee2)) {
			Employee2 e = (Employee2) o;
			if ((e.empId == this.empId) && (e.name.equals(this.name)) && (e.ssn.equals(this.ssn))
					&& (e.salary == this.salary)) {
				result = true;
			} // end if
		} // end if
		return result;
	}
	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + this.empId;
		hash = 83 * hash + Objects.hashCode(this.name);
		hash = 83 * hash + Objects.hashCode(this.ssn);
		hash = 83 * hash + (int) (Double.doubleToLongBits(this.salary) ^ (Double.doubleToLongBits(this.salary) >>> 32));
		return hash;
	}//end ha..
}// end class

