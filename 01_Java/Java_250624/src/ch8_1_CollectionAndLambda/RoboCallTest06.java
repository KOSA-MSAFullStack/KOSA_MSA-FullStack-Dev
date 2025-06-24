package ch8_1_CollectionAndLambda;

import java.util.List;
public class RoboCallTest06 {
   public static void main(String[] args) {
       List<Person> pl = Person.createShortList();
       
       System.out.println("\n=== foreach Print List ===");
       pl.forEach( p -> System.out.println(p));
      
       System.out.println("\n=== for Print List ===");
       for (Person p : pl) {			
        System.out.println(p);
       }//end for     
    }
}//end RoboCallTest06

