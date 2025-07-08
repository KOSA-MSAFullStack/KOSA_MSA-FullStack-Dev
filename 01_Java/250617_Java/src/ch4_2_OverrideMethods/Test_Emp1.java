package ch4_2_OverrideMethods;

public class Test_Emp1 {
	public static void main(String[] args) {
		Employee e = new Employee(101, "Jim Smith", "011-12-2345", 100_000.00);
		Manager m =	new Manager(102, "Joan Kern", "012-23-4567", 110_450.54, "Marketing");
		
		//Employee getDetails
		System.out.println(e.getDetails());
		// Manager getDetails
		System.out.println(m.getDetails());
	}
}// end class

