package ch4_1_AccessControl;

public class Foo2 {
	private int result = 20;
	
	// 메소드로 접근가능하게 수정
	protected void setResult(int result) {
		this.result = result;
	}
	public int getResult() {
		return result;
	}
}// end class
