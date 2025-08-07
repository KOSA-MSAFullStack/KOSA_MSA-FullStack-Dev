package ch4_1_AccessControl_2;

public class Test_Bar {
	public static void main(String[] args) {
		Bar bar = new Bar();
		System.out.println(bar.getResult());
		System.out.println(bar.getResults());
		System.out.println("=============");
		bar.resetResult();
		System.out.println(bar.getResult());
		System.out.println(bar.getResults());
	}// end main
}// end class

