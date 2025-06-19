package ch4_1_AccessControl_2;

import ch4_1_AccessControl.Foo2;

public class Bar2 extends Foo2 {
	private int results = 10;
	
	public void resetResult() {
		// this.result=0; 에러
		// Foo2.setResult호출
		setResult(0);
	}
	public int getResults() {
		return results;
	}
}// end class

