package ch4_1_AccessControl_2;

import ch4_1_AccessControl.Foo;

public class Bar extends Foo {
	private int results = 10;
	
	public void resetResult() {
		// Foo 의 result 변경
		this.result = 0;		// Foo.java의 result
	}
	public int getResults() {
		return results;
	}
}// end class
 