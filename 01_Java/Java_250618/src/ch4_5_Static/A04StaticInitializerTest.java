package ch4_5_Static;

public class A04StaticInitializerTest {
   // Static boolean array with 5 elements initialized to false by default
   private static final boolean[] switches = new boolean[5];
  
   // Static initializer block to initialize the switches array
   static {
       System.out.println("Initializing...");
       for (int i = 0; i < 5; i++) {
           switches[i] = true; // Set all switches to true
       }
   }
  
   // Main method to execute the program
   public static void main(String[] args) {
       switches[1] = false; // Set the second switch to false
       switches[2] = false; // Set the third switch to false
      
       System.out.print("Switch settings: ");
      
       // Iterate through the switches array and print their states
       for (boolean curSwitch : switches) {
           if (curSwitch) {
               System.out.print("1"); // Print 1 if the switch is true
           } else {
               System.out.print("0"); // Print 0 if the switch is false
           }
       }
   }
}

