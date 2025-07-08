package ch2_9_ObjectReferenceAndGC;
//Class to demonstrate object passing in Java
public class ObjectPassTest {
public static void main(String[] args) {
    ObjectPassTest test = new ObjectPassTest();
    Employee x = new Employee();
    x.setSalary(120_000.00); // Set initial salary
    test.foo(x); // Pass the Employee object to the method
    System.out.println("Employee salary: " + x.getSalary()); // Print the salary after method call
}
// Method that modifies the Employee object's salary
public void foo(Employee e) {
    e.setSalary(130_000.00); // Modify the salary of the passed object
    e = new Employee(); // Create a new Employee object (local to this method)
    e.setSalary(140_000.00); // Set salary for the new object (does not affect original)
}
}

