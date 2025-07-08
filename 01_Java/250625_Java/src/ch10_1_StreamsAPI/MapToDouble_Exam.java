package ch10_1_StreamsAPI;

import java.util.Arrays;
import java.util.List;
public class MapToDouble_Exam {
	 // Driver code
   public static void main(String[] args)
   {
       // Creating a list of Strings
       List<String> list = Arrays.asList("10", "6.548", "9.12", "11", "15");  
       // Using Stream mapToDouble(ToDoubleFunction mapper)
       // and displaying the corresponding DoubleStream
       System.out.println("The stream after applying the function is : ");
      
       list.stream().mapToDouble(num -> Double.parseDouble(num))
                   .filter(num -> (num * num) * 2 == 450)
                   .forEach(System.out::println);
       // Equivalent for-loop for the above stream operation
       System.out.println("The for-loop after applying the function is : ");
       for (String numStr : list) {
           double num = Double.parseDouble(numStr);
           if ((num * num) * 2 == 450) {
               System.out.println(num);
           }
       }
      
       // Creating a list of Strings
       List<String> list2 = Arrays.asList("CSE", "JAVA", "gfg", "C++", "C");
        // Using Stream mapToDouble(ToDoubleFunction mapper)
       // and displaying the corresponding DoubleStream
       // which contains square of length of each element in
       // given Stream
       System.out.println("The stream after applying the function is : ");       
       list2.stream().mapToDouble(str -> str.length() * str.length())
                    .forEach(System.out::println);
       // Equivalent for-loop for the above stream operation
       System.out.println("The for-loop after applying the function is : ");
       for (String str : list2) {
           double squareOfLength = str.length() * str.length();
           System.out.println(squareOfLength);
       }
      
   } //end main
}//end class

