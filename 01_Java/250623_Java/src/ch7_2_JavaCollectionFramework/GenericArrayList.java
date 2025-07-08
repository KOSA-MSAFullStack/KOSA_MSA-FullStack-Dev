package ch7_2_JavaCollectionFramework;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GenericArrayList {
   public static void main(String[] args) {
       // Create a generic ArrayList for Integer objects
       List<Integer> partList = new ArrayList<>(3);
       // Add Integer objects to the list
       partList.add(1111); // Autoboxing
       partList.add(2222);
       partList.add(3333);
      // partList.add("Bad Data");  // compiler error now
       // Iterating through the list using an Iterator
       Iterator<Integer> elements = partList.iterator();
       while (elements.hasNext()) {
           Integer partNumberObject = elements.next(); // No need for casting
           int partNumber = partNumberObject;          // Auto-unboxing
           System.out.println("Part number: " + partNumber);
       }
   }
}

