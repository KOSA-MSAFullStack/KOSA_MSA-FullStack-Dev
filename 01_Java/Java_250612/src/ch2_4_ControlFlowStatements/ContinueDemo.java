package ch2_4_ControlFlowStatements;
class ContinueDemo {
   public static void main(String[] args) {
       // Define the string to search
       String searchMe = "peter piper picked a " + "peck of pickled peppers";
       // Determine the length of the string
       int max = searchMe.length();
       // Counter to track the number of 'p' characters found
       int numPs = 0;
       // Iterate through each character in the string
       for (int i = 0; i < max; i++) {
           // Skip characters that are not 'p'
           if (searchMe.charAt(i) != 'p')
               continue;
           // Increment the counter for each 'p' found
           numPs++;
       }
       // Print the total count of 'p' characters found
       System.out.println("Found " + numPs + " p's in the string.");
   }
}

