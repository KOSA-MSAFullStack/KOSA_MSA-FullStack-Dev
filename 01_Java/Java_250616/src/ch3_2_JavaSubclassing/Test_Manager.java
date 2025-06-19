package ch3_2_JavaSubclassing;

public class Test_Manager {
	public static void main(String[] args) {
		Manager mgr = new Manager(102, "Barbara Jones", "107-99-9078", 109345.67, "Marketing");
		// 모든 Employee 메소드를 Manager에 사용할 수 있습니다.
		System.out.println(mgr.name);
		System.out.println(mgr.salary);
		mgr.raiseSalary(10000.00);
		System.out.println(mgr.name);
		System.out.println(mgr.salary);
		
		// Manager 클래스는 새 메소드를 정의하여 부서 이름을 가져옵니다.
		String dept = mgr.getDeptName();
		
		System.out.println(dept);
		System.out.println();
	}// end main
}// end class

