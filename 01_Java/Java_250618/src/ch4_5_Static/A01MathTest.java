package ch4_5_Static;

public class A01MathTest {
   public static void main(String[] args) {
       // Print a random number between 0.0 and 10.0
       System.out.println("Random: " + Math.random() * 10);
       // Print the square root of 9.0
       System.out.println("Square root: " + Math.sqrt(9.0));
       // Print a random number between 0 and 100, rounded to the nearest integer
       System.out.println("Rounded random: " + Math.round(Math.random() * 100));
       // Print the absolute value of -9
       System.out.println("Abs: " + Math.abs(-9));
   }
}
