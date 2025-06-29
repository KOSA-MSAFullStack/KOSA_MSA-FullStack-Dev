package ch10_1_StreamsAPI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
public class Peek_Exam {
	// Main driver method
	public static void main(String[] args) {
		// Creating a list of Integers
		List<Integer> list = Arrays.asList(0, 2, 4, 6, 8, 10);
		// Using peek without any terminal
		// operation does nothing
		 // and displaying the corresponding DoubleStream
       System.out.println("The stream after applying the function is : ");
		list.stream().peek(System.out::println);		
		
		 // and displaying the corresponding DoubleStream
       System.out.println("The stream after applying the function is : ");
       List<String> list2 = Arrays.asList("one", "two", "three", "four");
		   list2.stream()
	         .filter(e -> e.length() > 3)
	         .peek(e -> System.out.println("Filtered value: " + e))
	         .map(String::toUpperCase)
	         .peek(e -> System.out.println("Mapped value: " + e))
	         .collect(Collectors.toList());
		    // Equivalent for-loop for the above stream operation
		     System.out.println("The for-loop after applying the function is : ");
		
		   // 결과를 저장할 리스트 생성
		   List<String> result = new ArrayList<>();
		   for (String e : list2) {
		       // 필터링: 길이가 3보다 큰 문자열만 처리
		       if (e.length() > 3) {
		           System.out.println("Filtered value: " + e);		          
		           // 대문자로 변환
		           String mappedValue = e.toUpperCase();
		           System.out.println("Mapped value: " + mappedValue);		          
		           // 결과 리스트에 추가
		           result.add(mappedValue);
		       }
		   }
	}//end main
}//end class

