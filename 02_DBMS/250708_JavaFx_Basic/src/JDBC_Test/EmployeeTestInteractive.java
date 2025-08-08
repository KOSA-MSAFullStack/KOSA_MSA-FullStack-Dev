package JDBC_Test;

import java.util.List;

public class EmployeeTestInteractive {
	
	public static void main(String[] args) {
		EmployeeDAO dao = EmployeeDAOJDBCImpl.getInstance();
		
		List<Employee> list =dao.getAllEmployee();
		list.forEach( i -> {
			System.out.println(i);
		});
		
	}//end class

}//end 
