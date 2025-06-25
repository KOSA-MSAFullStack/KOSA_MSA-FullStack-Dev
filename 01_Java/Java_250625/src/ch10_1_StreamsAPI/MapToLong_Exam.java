package ch10_1_StreamsAPI;

import java.util.Arrays;
import java.util.List;
public class MapToLong_Exam {
	// Driver code
	public static void main(String[] args) {
		System.out.println("The stream after applying " + "the function is : ");
		// Creating a list of Strings
		List<String> list = Arrays.asList("25", "225", "1000", "20", "15");
		// Using Stream mapToLong(ToLongFunction mapper)
		// and displaying the corresponding LongStream
		list.stream().mapToLong(num -> Long.parseLong(num))
					.filter(num -> Math.sqrt(num) / 5 == 3)
					.forEach(System.out::println);
		// Equivalent for-loop for the above stream operation
	     System.out.println("The for-loop after applying the function is : ");
	     for (String numStr : list) {
	    	    // Parse the string to a long
	    	    long num = Long.parseLong(numStr);
	    	    // Check the condition: square root divided by 5 equals 3
	    	    if (Math.sqrt(num) / 5 == 3) {
	    	        // Print the number
	    	        System.out.println(num);
	    	    }
	    	}
	}//end main
}//end class

