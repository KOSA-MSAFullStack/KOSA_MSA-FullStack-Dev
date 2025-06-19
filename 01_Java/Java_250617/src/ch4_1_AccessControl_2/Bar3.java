package ch4_1_AccessControl_2;

import ch4_1_AccessControl.Foo3;

//Class Bar3 extends Foo3 and resides in package test
public class Bar3 extends Foo3 {
	// Private field sum initialized with value 10
	private int sum = 10;

	// Method to add getResult() value from Foo3 to sum
	public void reportSum() {
	    sum += getResult(); // Accessing protected method from parent class
	    System.out.println("Sum: " + sum); // Display the updated sum
	}
	
	// Main method for testing
	public static void main(String[] args) {
	    // Creating an object of Bar3
	    Bar3 obj = new Bar3();
	    // Calling reportSum method to compute and display sum
	    obj.reportSum();
	}
}

