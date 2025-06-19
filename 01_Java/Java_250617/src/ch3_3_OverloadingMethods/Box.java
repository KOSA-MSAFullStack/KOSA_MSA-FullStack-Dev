package ch3_3_OverloadingMethods;

public class Box {
   private double length, width, height;
   // Default constructor initializing dimensions to 1
   public Box() {
       this.length = 1;
       this.height = 1;
       this.width = 1;
   }
   // Constructor initializing all dimensions to the same value
   public Box(double length) {
       this.width = this.length = this.height = length;
   }
   // Constructor initializing each dimension separately
   public Box(double length, double width, double height) {
       this.length = length;
       this.height = height;
       this.width = width;
       System.out.println("and the height of " + height + ".");
   }
   // Method to calculate the volume of the box
   double volume() {
       return width * height * length;
   }
   // Main method to test the Box class
   public static void main(String[] args) {
       Box defaultBox = new Box();
       Box cubeBox = new Box(3);
       Box customBox = new Box(2, 3, 4);
       System.out.println("Volume of default box: " + defaultBox.volume());
       System.out.println("Volume of cube box: " + cubeBox.volume());
       System.out.println("Volume of custom box: " + customBox.volume());
   }
}

