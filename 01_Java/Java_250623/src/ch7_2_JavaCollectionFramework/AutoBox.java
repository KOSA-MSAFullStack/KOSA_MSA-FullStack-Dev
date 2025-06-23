package ch7_2_JavaCollectionFramework;

public class AutoBox {
   public static void main(String[] args) {
       // Initializing Integer object and int primitive
       Integer intObject = Integer.valueOf(1); // Preferred over 'new Integer()'
       int intPrimitive = 2;
       Integer tempInteger;
       int tempPrimitive;
       // Manual boxing and unboxing
       tempInteger = Integer.valueOf(intPrimitive); // Manual boxing
       tempPrimitive = intObject.intValue();        // Manual unboxing
       // Autoboxing and auto-unboxing
       tempInteger = intPrimitive;  // Autoboxing
       tempPrimitive = intObject;   // Auto-unboxing
       // Print results
       System.out.println("Manual Boxing: " + tempInteger);
       System.out.println("Manual Unboxing: " + tempPrimitive);
       System.out.println("Autoboxing: " + tempInteger);
       System.out.println("Auto-unboxing: " + tempPrimitive);
   }
}

