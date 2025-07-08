package ch7_2_JavaCollectionFramework;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OldStyleArrayList {
   public static void main(String[] args) {
     
       List partList = new ArrayList(3);
       // Add Integer objects to the list
       partList.add(1111); // Autoboxing
       partList.add(2222);
       partList.add(3333);
       partList.add("Oops a string!");
       // Iterating through the list using an Iterator
       Iterator<Integer> elements = partList.iterator();
       while (elements.hasNext()) {
       	Integer partNumberObject = (Integer)(elements.next());       
           int partNumber = partNumberObject.intValue();      // error?       
           System.out.println("Part number: " + partNumber);
       }
   }
}

