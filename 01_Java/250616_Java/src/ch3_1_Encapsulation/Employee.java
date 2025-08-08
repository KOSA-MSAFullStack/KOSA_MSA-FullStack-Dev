package ch3_1_Encapsulation;
public class Employee {
   // Private fields to store employee details
   private int empId; // Employee ID
   private String name; // Employee Name
   private String ssn; // Social Security Number
   private double salary; // Employee Salary
   // Default constructor
   // Initializes an Employee object with default values
   public Employee() {}
   // Parameterized constructor
   // Initializes an Employee object with specified values
   public Employee(int empId, String name, String ssn, double salary) {
       this.empId = empId;
       this.name = name;
       this.ssn = ssn;
       this.salary = salary;
   }
   // Setter method to update employee ID
   public void setEmpId(int empId) {
       this.empId = empId;
   }
   // Getter method to retrieve employee ID
   public int getEmpId() {
       return empId;
   }
   // Setter method to update employee name
   public void setName(String name) {
       this.name = name;
   }
   // Getter method to retrieve employee name
   public String getName() {
       return name;
   }
   // Setter method to update employee social security number
   public void setSsn(String ssn) {
       this.ssn = ssn;
   }
   public String getSsn() {
       return ssn;
   }
   // Setter method to update employee salary
   public void setSalary(double salary) {
       this.salary = salary;
   }
   // Getter method to retrieve employee salary
   public double getSalary() {
       return salary;
   }
   // Method to change the employee's name
   // Accepts a new name as a parameter and updates the name field
   public void changeName(String newName) {
       this.name = newName;
   }
   // Method to increase the employee's salary
   // Accepts an increase amount as a parameter and adds it to the salary
   public void raiseSalary(double increase) {
       if (increase > 0) { // Ensure salary increase is positive
           this.salary += increase;
       }
   }
   // Main method for testing the Employee class
   public static void main(String[] args) {
       // Creating an Employee object with initial details
       Employee emp = new Employee(101, "John Doe", "123-45-6789", 50000);
      
       // Displaying initial employee details
       System.out.println("Employee ID: " + emp.getEmpId());
       System.out.println("Employee Name: " + emp.getName());
       System.out.println("Employee Salary: " + emp.getSalary());
      
       // Changing employee's name
       emp.changeName("Jane Doe");
       System.out.println("Updated Name: " + emp.getName());
      
       // Increasing employee's salary
       emp.raiseSalary(5000);
       System.out.println("Updated Salary: " + emp.getSalary());
   }
}

