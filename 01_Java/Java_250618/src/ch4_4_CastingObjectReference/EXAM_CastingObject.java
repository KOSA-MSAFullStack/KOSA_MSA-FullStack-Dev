package ch4_4_CastingObjectReference;

/**
* Demonstrates object casting in Java.
* This program creates an Employee reference pointing to a Manager object
* and uses `instanceof` to check if the reference can be safely cast to a Manager.
*/
public class EXAM_CastingObject {
   public static void main(String[] args) {
       // Create an Employee reference pointing to a Manager object
       Employee e = new Manager(102, "Joan Kern", "012-23-4567", 110_450.54, "Marketing");
       
       // Check if the Employee object is actually an instance of Manager
       if (e instanceof Manager) {
           // Perform explicit downcasting from Employee to Manager
           Manager m = (Manager) e;
           
           // Modify the department name of the Manager
           m.setDeptName("HR");
           // Print the updated details of the Manager
           System.out.println(m.getDetails());
       }
   }
}

