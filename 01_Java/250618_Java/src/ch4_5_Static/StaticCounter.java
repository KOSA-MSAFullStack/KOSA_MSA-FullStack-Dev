package ch4_5_Static;

public class StaticCounter {   
   // Static variable to keep track of the counter
   private static int counter = 0;
  
   // Method to get the current value of the counter
   public static int getCount() {
       return counter;
   }
  
   // Method to increment the counter by 1
   public static void increment(){
       counter++;
   }
   
   // Main method to execute the program
   public static void main(String[] args) {
       // Print the initial value of the counter
       System.out.println("Start: " + StaticCounter.getCount());
      
       // Increment the counter twice
       StaticCounter.increment();
       StaticCounter.increment();
      
       // Print the final value of the counter
       System.out.println("End: " + StaticCounter.getCount());
   }
}

