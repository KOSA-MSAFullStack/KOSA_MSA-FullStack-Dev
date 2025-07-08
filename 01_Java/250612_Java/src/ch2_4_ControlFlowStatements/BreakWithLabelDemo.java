package ch2_4_ControlFlowStatements;
class BreakWithLabelDemo {
   public static void main(String[] args) {
       // Define a 2D array of integers
       int[][] arrayOfInts = {
           { 32, 87, 3, 589 },
           { 12, 1076, 2000, 8 },
           { 622, 127, 77, 955 }
       };
      
       // Value to search for in the array
       int searchfor = 12;
       // Variables to track the indices and the search status
       int i; // Row index
       int j = 0; // Column index, initialized to 0
       boolean foundIt = false; // Flag to indicate if the value was found
       // Label the outer loop as "search"
   search:{
       for (i = 0; i < arrayOfInts.length; i++) { // Iterate through rows
           for (j = 0; j < arrayOfInts[i].length; j++) { // Iterate through columns
               if (arrayOfInts[i][j] == searchfor) { // Check if the current element matches the search value
                   foundIt = true; // Set the flag to true if found
                   break search; // Exit both loops using the label
               }//end if
           }//end for
       }//end for
   }//end search
       // Print the result of the search
       if (foundIt) {
           System.out.println("Found " + searchfor + " at " + i + ", " + j); // Print the indices if the value was found
       } else {
           System.out.println(searchfor + " not in the array"); // Print a message if the value was not found
       }//end if
       
   }//end main
}//end clas

