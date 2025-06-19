package ch2_4_ControlFlowStatements;
public class BreakDemoW3 {
   public static void main(String[] args) {
       // A for-loop that iterates from 0 to 9
       for (int i = 0; i < 10; i++) {
           // If i equals 4, exit the loop using the break statement
           if (i == 4) {
               break; // Terminates the loop when i is 4
           }
           System.out.println(i); // Print the current value of i
       }
   }
}

