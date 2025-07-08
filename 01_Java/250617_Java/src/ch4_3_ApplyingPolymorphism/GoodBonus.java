package ch4_3_ApplyingPolymorphism;

//Class to determine bonus percentage based on employee type
public class GoodBonus {
	// Method to get the bonus percentage based on the type of employee
	public static double getBonusPercent(Employee e) {
		//생각할전 if문 순서에 따라서 결과 변동
	    // Check if the employee is an instance of Manager
	    if (e instanceof Manager) {
	        return 0.05; // Managers receive a 5% bonus
	    }
	    // Check if the employee is an instance of Employee
	    else if (e instanceof Employee) {
	        return 0.03; // Regular employees receive a 3% bonus
	    }
	    // Default case for other types
	    else {
	        return 0.01; // Other types receive a 1% bonus
	    }
	}
}

