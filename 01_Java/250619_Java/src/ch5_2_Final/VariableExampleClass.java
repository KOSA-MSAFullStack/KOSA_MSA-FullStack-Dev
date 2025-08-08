package ch5_2_Final;

public class VariableExampleClass {
   // A final instance variable must be assigned exactly once.
   // It can be initialized directly or inside the constructor.
   private final int field;
  
   // A public static final variable is a constant.
   // Its value is fixed and cannot be changed.
   public static final int JAVA_CONSTANT = 10;		// 상수
  
   // Constructor for VariableExampleClass.
   // The final instance variable 'field' is assigned a value here.
   public VariableExampleClass() {
       field = 100;
       System.out.println(field);
   }
  
   /**
    * This method demonstrates that final parameters and final local variables
    * cannot be reassigned after their initial assignment.
    *
    * @param param a final parameter whose value cannot be changed within this method.
    */
   public void changeValues(final int param) {
       // Attempting to reassign a final parameter will cause a compile-time error.
       // The following line is commented out to avoid the error:
       // param = 1; // ERROR: Cannot assign a value to final variable 'param'
      
       // Declare a local final variable.
       final int localVar;
      
       // Initialize the local final variable. This assignment is allowed only once.
       localVar = 42;
      
       // Attempting to reassign a final local variable will also cause a compile-time error.
       // The following line is commented out to avoid the error:
       // localVar = 43; // ERROR: Cannot assign a value to final variable 'localVar'
      
       // Display the values to demonstrate that the variables remain unchanged.
       System.out.println("Final parameter value (unchanged): " + param);
       System.out.println("Final local variable value: " + localVar);
   }
  
   /**
    * The main method serves as the entry point for the Java application.
    *
    * @param args Command-line arguments (not used in this example).
    */
   public static void main(String[] args) {
       // Create an instance of VariableExampleClass.
       VariableExampleClass example = new VariableExampleClass();
      
       // Display the static final constant.
       System.out.println("Java constant: " + JAVA_CONSTANT);
      
       // Call the changeValues method with a sample value.
       // Since 'param' is declared final inside the method, its value cannot be modified.
       example.changeValues(5);
      
       // The final instance variable 'field' is set in the constructor.
       // Although it's private, its value remains constant after initialization.
       // For demonstration purposes, we might add a method to retrieve it if needed.
   }
}

