package ch7_2_JavaCollectionFramework;

import java.util.Set;
import java.util.TreeSet;
import java.util.Comparator;

public class SetExample {
   public static void main(String[] args) {
       Set<String> set = new TreeSet<>(Comparator.reverseOrder());
       set.add("one");
       set.add("two");
       set.add("three");
       set.add("three"); // Duplicate
       for (String item : set) {
           System.out.println("Item: " + item);
       }
   }
}

