package ch4_3_ApplyingPolymorphism;

class Statistics {
	float average(int x1, int x2) {
		return (x1 + x2) /2;
	}
	float average(int x1, int x2, int x3) {
		return (x1 + x2 + x3) / 3;
	}
	float average(int x1, int x2, int x3, int x4) {
		return (x1 + x2 + x3 + x4) / 4;
	}
}
public class VarargsTest {
	public static void main(String[] args) {
		Statistics stats = new Statistics();
		float avg1 = stats.average(100, 200);
		float avg2 = stats.average(100, 200, 300);
		float avg3 = stats.average(100, 200, 300, 400);
		System.out.println(avg1);
		System.out.println(avg2);
		System.out.println(avg3);
	}// end main
}// end class

