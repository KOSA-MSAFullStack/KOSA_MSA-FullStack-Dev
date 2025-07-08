package JDBC_Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class UpdateSalary {
    public static void main(String[] args) {
        // Database connection information
        String url = "jdbc:oracle:thin:@localhost:1521/xepdb1"; // JDBC URL for Oracle DB
        String username = "hr"; // Database username
        String password = "hr"; // Database password

        // SQL UPDATE query using placeholders for parameters
        String updateString = "UPDATE EMPLOYEE SET SALARY = ? WHERE EMP_NAME LIKE ?";

        // Array of new salary values to update
        int[] salary = {1750, 1500, 6000, 1550, 9050};

        // Array of employee names to match for updating salary
        String[] names = {"David", "Tom", "Nick", "Harry", "Mark"};

        // try-with-resources block ensures automatic closing of resources
        try (
            // Establishing connection to the database
            Connection con = DriverManager.getConnection(url, username, password);

            // Preparing the SQL statement with parameters
            PreparedStatement updateEmp = con.prepareStatement(updateString)
        ) {
            // Looping through each employee to update their salary
            for (int i = 0; i < names.length; i++) {
                // Setting the salary parameter (first placeholder '?')
                updateEmp.setInt(1, salary[i]);

                // Setting the employee name parameter (second placeholder '?')
                updateEmp.setString(2, names[i]);

                // Executing the update query
                updateEmp.executeUpdate();
            }

            // Print success message after all updates are done
            System.out.println("Salary update completed.");
        } catch (Exception e) {
            // Print detailed error information if an exception occurs
            e.printStackTrace();
        }
    }
}
