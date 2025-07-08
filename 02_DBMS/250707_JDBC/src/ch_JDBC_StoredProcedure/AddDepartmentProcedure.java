package ch_JDBC_StoredProcedure;

import java.sql.*;
import java.util.Scanner;
public class AddDepartmentProcedure {
   public static void main(String[] args) {
       // JDBC URL, username, and password for Oracle DB
       final String jdbcUrl = "jdbc:oracle:thin:@localhost:1521/xepdb1";
       final String username = "hr";
       final String password = "hr";
       // Scanner for console input
       Scanner scanner = new Scanner(System.in);
       try {
           // Prompt the user for department details
           System.out.print("Enter Department ID (integer): ");
           int deptId = scanner.nextInt();
           scanner.nextLine(); // Consume newline
           System.out.print("Enter Department Name: ");
           String deptName = scanner.nextLine();
           System.out.print("Enter Location ID (integer): ");
           int locId = scanner.nextInt();
           // Load Oracle JDBC driver
           Class.forName("oracle.jdbc.driver.OracleDriver");
           // Connect to the Oracle database
           try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password)) {
               // Prepare the call to the stored procedure
               String sql = "{ call add_dept(?, ?, ?) }";
               try (CallableStatement cstmt = conn.prepareCall(sql)) {
                   // Set input parameters
                   cstmt.setInt(1, deptId);        // p_id
                   cstmt.setString(2, deptName);   // p_name
                   cstmt.setInt(3, locId);         // p_loc
                   // Execute the stored procedure
                   cstmt.execute();
                   // Output confirmation
                   System.out.println("Department successfully added.");
               }
           }
       } catch (ClassNotFoundException e) {
           System.out.println("Oracle JDBC Driver not found.");
           e.printStackTrace();
       } catch (SQLException e) {
           System.out.println("Database error occurred.");
           e.printStackTrace();
       } finally {
           scanner.close(); // Close the scanner resource
       }
   }
}

