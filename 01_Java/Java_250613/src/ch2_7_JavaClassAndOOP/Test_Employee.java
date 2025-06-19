package ch2_7_JavaClassAndOOP;
//import org.junit.jupiter.api.Test;
class Test_employee {
	//@Test
	void test() {
		//object 생성
				Employee emp = new Employee ();
				
				//public 일때는 가능
				emp.empId = 101;  
				// but not good OO practice		
				// set method 사용 권장
				
				emp.setEmpId(101);
				emp.setName("John Smith");
				emp.setSsn ("011-22-3467");
				emp.setSalary(120345.27);
				
				//object test print		
				System.out.println(emp.getEmpId());
				System.out.println(emp.getName());
				System.out.println(emp.getSalary());
				System.out.println(emp.getClass());	
	}//end test
}//end class

