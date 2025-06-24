package ch8_1_CollectionAndLambda;

import java.util.List;
import java.util.function.Predicate;

public class RoboCallTest08 {
   public static void main(String[] args) {
       // Create a list of Person objects using the static factory method
       List<Person> pl = Person.createShortList();
       // Create an instance of RoboCall05 for contacting people
       RoboCall05 robo = new RoboCall05();
       // Define a Predicate for filtering pilots (ages 23 to 65)
       Predicate<Person> allPilots =
           p -> p.getAge() >= 23 && p.getAge() <= 65;
       // Use the stream API to filter and call all pilots
       System.out.println("\n=== Calling all Drivers Variable ===");
       pl.stream()
           .filter(allPilots) // Apply the pilot filter
           .forEach(p -> robo.roboCall(p)); // Call each person matching the criteria
      
       System.out.println("\n=== Calling all Drivers Variable for ===");
       for (Person p : pl) { // pl은 Person 객체들의 리스트라고 가정
           if (allPilots.test(p)) { // allPilots는 Predicate<Person>으로 가정
               robo.roboCall(p); // 조건을 만족하는 경우 호출
           }//end if
       }//end for
   }//end main
  
}//end RoboCallTest08

