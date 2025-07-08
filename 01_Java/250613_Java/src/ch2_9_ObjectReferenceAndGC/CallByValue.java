package ch2_9_ObjectReferenceAndGC;
class Employee3 {};
public class CallByValue {
	public static void main(String[] args) {
		int x = 3;
		int y = x;
		System.out.println( x == y); //true
		System.out.println("=======================");
		
		Employee3 x2 = new Employee3();
		Employee3 y2 = x2;
		//System.out.println(System.identityHashCode(x2));		
		System.out.println( x2 == y2); //true
		System.out.println("=======================");
		
		Employee3 x3 = new Employee3();
		Employee3 y3 = new Employee3();		
		System.out.println( x3 == y3); //false
		System.out.println("=======================");
		
		String s1 ="jino";
		String s2 = s1;
		System.out.println( s1 == s2); //true
		System.out.println("=======================");
		
		String s3 ="jino";
		String s4 = new String("jino");
		System.out.println( s3 == s4); //false
		System.out.println( s3.equals(s4)); //true	
		System.out.println("=======================");	
		
		
	}//end main
}//end class

