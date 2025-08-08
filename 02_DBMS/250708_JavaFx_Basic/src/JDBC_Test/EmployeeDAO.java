package JDBC_Test;

import java.util.List;

public interface EmployeeDAO {	

	public void add(Employee emp);
	public void update(Employee emp);
	public void delete(int id);
	public Employee findById(int id);
	public List<Employee> getAllEmployee();
	
}//end inter..
