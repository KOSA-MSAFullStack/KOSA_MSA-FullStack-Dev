package ch9_2_PrimitiveInterface;

import java.util.function.DoubleFunction;
public class A06DoubleFunction {
   public static void main(String[] args) {
       // Create an instance of A06DoubleFunction
       A06DoubleFunction test = new A06DoubleFunction();
       // Define a DoubleFunction to calculate and return a String result
       DoubleFunction<String> calc =
             t -> String.valueOf(t * 3);
       // Apply the DoubleFunction with a value of 20
       String result = calc.apply(20);
       // Print the result
       System.out.println("New value is: " + result);

       System.out.println(test);
   }
}

