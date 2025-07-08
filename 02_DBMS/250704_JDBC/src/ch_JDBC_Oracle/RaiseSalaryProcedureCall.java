package ch_JDBC_Oracle;

import java.sql.*;
import java.util.Scanner;
public class RaiseSalaryProcedureCall {
   public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
       // Get input from console
       System.out.print("Enter Employee ID: ");
       int employeeId = scanner.nextInt();
       System.out.print("Enter Raise Percentage: ");
       double raisePercent = scanner.nextDouble();
       scanner.close();
       Connection conn = null;
       CallableStatement cstmt = null;
       try {
           // Load Oracle JDBC driver
           Class.forName("oracle.jdbc.driver.OracleDriver");
           // Connect to Oracle DB
           conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xepdb1", "hr", "hr");
           // Prepare the callable statement
           String sql = "{call raise_salary(?, ?)}";
           cstmt = conn.prepareCall(sql);
           // Set input parameters
           cstmt.setInt(1, employeeId);
           cstmt.setDouble(2, raisePercent);
           // Execute stored procedure
           cstmt.execute();
           System.out.println("Salary updated successfully for employee ID: " + employeeId);
       } catch (ClassNotFoundException e) {
           System.out.println("Oracle JDBC Driver not found.");
           e.printStackTrace();
       } catch (SQLException e) {
           System.out.println("Database error occurred.");
           e.printStackTrace();
       } finally {
           // Close resources
           try {
               if (cstmt != null) cstmt.close();
               if (conn != null) conn.close();
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }
   }
}

