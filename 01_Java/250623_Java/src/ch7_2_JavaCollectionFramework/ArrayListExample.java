package ch7_2_JavaCollectionFramework;

import java.util.ArrayList;
import java.util.List;

public class ArrayListExample {
   public static void main(String[] args) {
       // Create an ArrayList with an initial capacity of 3
       List<Integer> partList = new ArrayList<>(3);
       
       // Add elements to the list
       partList.add(1111); // Autoboxing converts int to Integer
       partList.add(2222);
       partList.add(3333);
       partList.add(4444); // ArrayList automatically grows
       // Access and print the first element
       System.out.println("First Part: " + partList.get(0));
       // Insert an element at the beginning of the list
       partList.add(0, 5555);
       // Print the updated list
       System.out.println("Updated List: " + partList);
   }
}

