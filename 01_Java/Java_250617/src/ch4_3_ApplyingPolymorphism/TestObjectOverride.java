package ch4_3_ApplyingPolymorphism;

public class TestObjectOverride {
	public static void main(String[] args) {
		Employee2 x = new Employee2(1, "Sue", "111-11-1111", 10.0);
		Employee2 y = x;
		System.out.println(x.equals(y) ); 
		System.out.println(x.toString());
		System.out.println(x.hashCode());
		System.out.println(y.toString());
		System.out.println(y.hashCode());
		System.out.println("====================");
		Employee2 z = new Employee2(1, "Sue", "111-11-1111", 10.0);
		System.out.println(x.equals(z)); 
		System.out.println(x.toString());
		System.out.println(x.hashCode());
		System.out.println(z.toString());
		System.out.println(z.hashCode());
	}//end main
}//end class

