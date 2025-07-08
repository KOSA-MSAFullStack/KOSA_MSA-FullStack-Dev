package ch7_2_JavaCollectionFramework;

import java.util.ArrayDeque;
import java.util.Deque;

public class TestStack {
	public static void main(String[] args) {
		Deque<String> stack = new ArrayDeque<>();
		//넣고
		stack.push("one");
		stack.push("two");
		stack.push("three");
		
		//old way
		int size = stack.size() - 1;
		while (size >= 0) {
			System.out.println(stack.pop()); //빼고
			size--;
		}//end while
		
		System.out.println("===========================");
		//추가
		Deque<String> stack2 = new ArrayDeque<>();
		stack2.add("one");
		stack2.add("two");
		stack2.add("three");
		
		// (1)foreach 출력
		for (String i : stack2) {
			System.out.println(i);
		}//end for
		
		// (2) Lambda Expressions 출력
		stack2.forEach( i ->{
			System.out.println(i);
		});
	}// end main
}// end class

