package ch3_1_Encapsulation;
public class EXAM_Encapsulation {
	// Main method for testing
   public static void main(String[] args) {
       // Creating an Employee object
       Employee emp = new Employee(101, "John Doe", "123-45-6789", 50000);
      
       // Displaying initial details
       System.out.println("Employee ID: " + emp.getEmpId());
       System.out.println("Employee Name: " + emp.getName());
       System.out.println("Employee Salary: " + emp.getSalary());
      
       // Changing name
       emp.changeName("Jane Doe");
       System.out.println("Updated Name: " + emp.getName());
      
       // Raising salary
       emp.raiseSalary(5000);
       System.out.println("Updated Salary: " + emp.getSalary());
   }
}

