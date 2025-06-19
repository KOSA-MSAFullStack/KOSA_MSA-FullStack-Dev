package ch4_5_Static;

import static java.lang.Math.random;
//import static java.lang.Math.*;

public class StaticImport {
	public static void main(String[] args) {
		double d = random();
		System.out.println(d);
		System.out.println("==============");
		
		double d2 = Math.random();
		System.out.println(d2);
		System.out.println("==============");
		
		double d3 = java.lang.Math.random();
		System.out.println(d3);		
		System.out.println("==============");
		
	}//end class
}// end class

