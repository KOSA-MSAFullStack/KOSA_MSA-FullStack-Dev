package ch4_2_OverrideMethods_2;

public class Test_Virtual_Function {
	public static void main(String[] args) {
		// Virtual_Function
		Employee e = new Manager(102, "Joan Kern", "012-23-4567", 110_450.54, "Marketing");
		
		System.out.println(e.getDetails());
		System.out.println("========");
		
		// constructor
		Employee e2 = new Employee(102, "Joan Kern", "012-23-4567", 110_450.54);
		System.out.println(e2.getDetails());
	}// end main
}// end class
