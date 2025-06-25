package ch10_1_StreamsAPI;

import java.util.Arrays;
import java.util.List;
public class MapToInt_Exam {
	  // Driver code
   public static void main(String[] args)
   {
        // Creating a list of Strings
       List<String> list = Arrays.asList("3", "6", "8", "14", "15");
        // Using Stream mapToInt(ToIntFunction mapper)
       // and displaying the corresponding IntStream
       System.out.println("The stream after applying the function is : ");
       list.stream().mapToInt(num -> Integer.parseInt(num))
                    .filter(num -> num % 3 == 0)
                    .forEach(System.out::println);
    // Equivalent for-loop for the above stream operation
    System.out.println("The for-loop after applying the function is : ");
    for (String numStr : list) {
        // Parse string to integer
        int num = Integer.parseInt(numStr);
        // Check if the number is divisible by 3
        if (num % 3 == 0) {            
            System.out.println(num);// Print the number
        }
    }//end for
      
    // Creating a list of Strings
       List<String> list2 = Arrays.asList("Geeks", "for", "gfg", "GeeksforGeeks", "GeeksQuiz");  
        // Using Stream mapToInt(ToIntFunction mapper)
       // and displaying the corresponding IntStream
       // which contains length of each element in given Stream
       System.out.println("The stream after applying the function is : ");
       list2.stream().mapToInt(str -> str.length()).forEach(System.out::println);
       // Equivalent for-loop for the above stream operation
       System.out.println("The for-loop after applying the function is : ");
       for (String str : list2) {
           // Calculate the length of the string
           int length = str.length();
           // Print the length
           System.out.println(length);
       }
      
      
   } //end main
}//end class

