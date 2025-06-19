package ch4_3_ApplyingPolymorphism;

class Statistics2 {
	float average(int... nums) {
		int sum = 0;
		for (int x : nums) { // iterate int array nums
			sum += x;
		}//end for
		return ((float) sum / nums.length);
	}//end av...
}//end class
public class VarargsTest2 {
	public static void main(String[] args) {
		Statistics2 stats2 = new Statistics2();
		float avg1 = stats2.average(100, 200);
		float avg2 = stats2.average(100, 200, 300);
		float avg3 = stats2.average(100, 200, 300, 400);
		System.out.println(avg1);
		System.out.println(avg2);
		System.out.println(avg3);
	}// end main
}// end class


