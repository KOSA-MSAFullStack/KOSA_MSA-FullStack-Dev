package ch10_1_StreamsAPI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
public class Strams_Map {
	// Driver code
	public static void main(String[] args) {
		System.out.println("The stream after applying the function is : ");
		// Creating a list of Integers
		List<Integer> list = Arrays.asList(3, 6, 9, 12, 15);
		// Using Stream map(Function mapper) and
		// displaying the corresponding new stream
		list.stream().map(number -> number * 3).forEach(System.out::println);
		// Equivalent for-loop for the above stream operation
		System.out.println("The for-loop after applying the function is : ");
		for (Integer number : list) {
			System.out.println(number * 3);
		}
		System.out.println("The stream after applying  the function is : ");
		// Creating a list of Integers
		List<String> list2 = Arrays.asList("geeks", "gfg", "g", "e", "e", "k", "s");
		// Using Stream map(Function mapper) to
		// convert the Strings in stream to
		// UpperCase form
		List<String> answer = list2.stream().map(String::toUpperCase).collect(Collectors.toList());
		// displaying the new stream of UpperCase Strings
		System.out.println(answer);
		System.out.println("The stream after applying " + "the function is : ");
		// Equivalent for-loop for the above stream operation
		System.out.println("The for-loop after applying the function is : ");
		List<String> upperCaseList = new ArrayList<>();
		for (String str : list2) {
			upperCaseList.add(str.toUpperCase());
		}
		System.out.println(upperCaseList);
		
		// Creating a list of Strings
		List<String> list3 = Arrays.asList("Geeks", "FOR", "GEEKSQUIZ", "Computer", "Science", "gfg");
		// Using Stream map(Function mapper) and
		// displaying the length of each String
		list3.stream().map(str -> str.length()).forEach(System.out::println);
		
		// Equivalent for-loop for the above stream operation
		System.out.println("The for-loop after applying the function is : ");
		for (String str : list3) {
			System.out.println(str.length());
		}
	}// end main
}// end class

