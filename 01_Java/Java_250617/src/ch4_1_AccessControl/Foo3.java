package ch4_1_AccessControl;

//Class Foo3 defined in package demo
public class Foo3 {
	//Private field that stores a result value
	private int result = 20;

	//Protected method to return the value of result
	//This method can be accessed by subclasses or classes in the same package
	protected int getResult() {
		return this.result;
	}
}

