package ch4_3_ApplyingPolymorphism;

//Main class to execute the Java program
public class TestGoodBonus {
	public static void main(String[] args) {
	    // Creating an Employee object
	    Employee emp = new Employee(101, "John Doe", "123-45-6789", 50000);
	    // Creating a Manager object
	    Manager mgr = new Manager(102, "Jane Smith", "987-65-4321", 75000, "IT");
	   
	    // Displaying employee and manager details
	    System.out.println(emp.getDetails());
	    System.out.println("Salary: $" + emp.getSalary());
	    System.out.println("Bonus Percentage: " + (GoodBonus.getBonusPercent(emp) * 100) + "%");
	   
	    System.out.println(); // Line break
	   
	    System.out.println(mgr.getDetails());
	    System.out.println("Salary: $" + mgr.getSalary());
	    System.out.println("Bonus Percentage: " + (GoodBonus.getBonusPercent(mgr) * 100) + "%");
	}
}

