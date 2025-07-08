package ch8_1_CollectionAndLambda;

import java.util.List;
public class RoboCallTest07 {
   public static void main(String[] args) {
       // Create a list of Person objects using the static factory method
       List<Person> pl = Person.createShortList();
       // Create an instance of RoboCall05 for contacting people
       RoboCall05 robo = new RoboCall05();
       // Using lambda expressions and streams to filter and contact drivers
       System.out.println("\n=== Calling all Drivers Lambda===");
       pl.stream()
           .filter(p -> p.getAge() >= 23 && p.getAge() <= 65) // Filter for persons aged 23 to 65
           .forEach(p -> robo.roboCall(p)); // Call each person who matches the filter
       // Using a traditional for-loop to filter and contact drivers
       System.out.println("\n=== Calling all Drivers  for ===");
       for (Person p : pl) {
           if (p.getAge() >= 23 && p.getAge() <= 65) {
               robo.roboCall(p); // Call each person who matches the condition
           }
       }
   } // end main
} // end RoboCallTest07

