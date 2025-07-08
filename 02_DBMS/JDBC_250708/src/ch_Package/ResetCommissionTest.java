package ch_Package;

import java.sql.*;
public class ResetCommissionTest {
   // JDBC connection information
   private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521/xepdb1"; // Update if needed
   private static final String USER = "hr";
   private static final String PASSWORD = "hr";
   public static void main(String[] args) {
       double newCommission = 0.25; // New commission to set
       try (Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            CallableStatement stmt = conn.prepareCall("{call comm_pkg.reset_comm(?)}")) {
           // Set the input parameter for the stored procedure
           stmt.setDouble(1, newCommission);
           // Execute the stored procedure
           stmt.execute();
           System.out.println("Commission reset successfully to " + newCommission);
       } catch (SQLException e) {
           // Print basic error message
           System.err.println("SQL Error occurred: " + e.getMessage());
           // Handle Oracle-specific user-defined exception (e.g., -20210)
           if (e.getErrorCode() == 20210) {
               System.err.println("Application error: Invalid commission value. Must be between 0.0 and the current maximum.");
           }
           // Print full error information
           System.err.println("Error Code   : " + e.getErrorCode());
           System.err.println("SQL State    : " + e.getSQLState());
       }
   }
}

