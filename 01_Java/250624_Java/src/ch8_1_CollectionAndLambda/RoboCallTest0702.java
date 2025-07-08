package ch8_1_CollectionAndLambda;

import java.util.List;
public class RoboCallTest0702 {
   public static void main(String[] args) {
       // Create a list of Person objects using the static factory method
       List<Person> pl = Person.createShortList();
       // Print the entire list of people
       System.out.println("\n=== Print List ===");
       pl.forEach(System.out::println);
       // Using streams to filter and print Drivers (Age > 16)
       System.out.println("\n=== Drivers (Age > 16) ===");
       pl.stream()
               .filter(p -> p.getAge() > 16)
               .forEach(System.out::println);
       // Using a traditional for-loop to print Drivers (Age > 16)
       System.out.println("\n=== Drivers (Age > 16) - For Loop ===");
       for (Person p : pl) {
           if (p.getAge() > 16) {
               System.out.println(p);
           }
       }
       // Using streams to filter and print Draftees (Male, 18-25)
       System.out.println("\n=== Draftees (Male, 18-25) ===");
       pl.stream()
               .filter(p -> p.getGender() == Gender.MALE && p.getAge() >= 18 && p.getAge() <= 25)
               .forEach(System.out::println);
       // Using a traditional for-loop to print Draftees (Male, 18-25)
       System.out.println("\n=== Draftees (Male, 18-25) - For Loop ===");
       for (Person p : pl) {
           if (p.getGender() == Gender.MALE && p.getAge() >= 18 && p.getAge() <= 25) {
               System.out.println(p);
           }
       }
       // Using streams to filter and print Pilots (Age 23-65)
       System.out.println("\n=== Pilots (Age 23-65) ===");
       pl.stream()
               .filter(p -> p.getAge() >= 23 && p.getAge() <= 65)
               .forEach(System.out::println);
       // Using a traditional for-loop to print Pilots (Age 23-65)
       System.out.println("\n=== Pilots (Age 23-65) - For Loop ===");
       for (Person p : pl) {
           if (p.getAge() >= 23 && p.getAge() <= 65) {
               System.out.println(p);
           }
       }
   }
}

