package ch4_5_Static;

import java.time.LocalDate;

public class StaticHelper {
   // Static method to print a message with the current date
   public static void printMessage(String message) {
       // Print the message along with the current date
       System.out.println("Message for " + LocalDate.now() + ": " + message);
   }
   
   // Main method to execute the program
   public static void main(String[] args) {
       // Call the static method with a sample message
       printMessage("Hello, this is a test message!");
   }
}
