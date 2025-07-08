package ch4_3_ApplyingPolymorphism;

public class TestObjectEqualsMethod {
	public static void main(String[] args) {
		Employee x = new Employee(1, "Sue", "111-11-1111", 10.0);
		Employee y = x;
		
		System.out.println(x.equals(y) ); // true
		System.out.println(x.toString());
		System.out.println(x.hashCode());
		System.out.println(y.toString());
		System.out.println(y.hashCode());
		System.out.println("====================");
		Employee z = new Employee(1, "Sue", "111-11-1111", 10.0);
		
		System.out.println(x.equals(z)); // false!
		System.out.println(x.toString());
		System.out.println(x.hashCode());
		System.out.println(z.toString());
		System.out.println(z.hashCode());
	}//end main
}//end class
