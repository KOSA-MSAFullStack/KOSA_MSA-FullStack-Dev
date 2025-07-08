package ch_JDBC_StoredProcedure;

import java.sql.*;
import java.util.Scanner;
public class CallStoredProcedureExample {
   public static void main(String[] args) {
       // JDBC URL for connecting to Oracle database using SID
       final String jdbcUrl = "jdbc:oracle:thin:@localhost:1521/xepdb1";
       // Oracle database username and password
       final String username = "hr";
       final String password = "hr";
       // Scanner to read input from the console
       Scanner scanner = new Scanner(System.in);
       // Prompt user to input employee ID
       System.out.print("Enter Employee ID: ");
       int empId = scanner.nextInt();
       // Declare Connection and CallableStatement outside try block for scope
       Connection conn = null;
       CallableStatement cstmt = null;
       try {
           // Load the Oracle JDBC driver
           Class.forName("oracle.jdbc.driver.OracleDriver");
           // Establish a connection to the database
           conn = DriverManager.getConnection(jdbcUrl, username, password);
           // Prepare the SQL call to the stored procedure
           // Note: The procedure has 1 IN parameter and 2 OUT parameters
           String sql = "{ call query_emp(?, ?, ?) }";
           cstmt = conn.prepareCall(sql);
           // Set input parameter (employee_id)
           cstmt.setInt(1, empId);
           // Register output parameters (last_name and salary)
           cstmt.registerOutParameter(2, Types.VARCHAR); // p_name
           cstmt.registerOutParameter(3, Types.NUMERIC); // p_salary
           // Execute the stored procedure
           cstmt.execute();
           // Retrieve the output parameter values
           String lastName = cstmt.getString(2);
           double salary = cstmt.getDouble(3);
           // Print the results to the console
           System.out.println("Employee Last Name: " + lastName);
           System.out.println("Employee Salary: " + salary);
       } catch (ClassNotFoundException e) {
           // Handle error when JDBC driver class is not found
           System.out.println("Oracle JDBC Driver not found.");
           e.printStackTrace();
       } catch (SQLException e) {
           // Handle SQL-related exceptions
           System.out.println("Database error occurred.");
           e.printStackTrace();
       } finally {
           // Close resources in reverse order of their creation
           try {
               if (cstmt != null) cstmt.close();
               if (conn != null) conn.close();
               scanner.close();
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }
   }
}

