package ch_Trigger;

import java.sql.*;
public class UpdateMileageTest {
   // Database configuration: JDBC URL, username, and password
   private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521/xepdb1";
   private static final String USER = "hr";
   private static final String PASSWORD = "hr";
   public static void main(String[] args) {
       // Target member ID and new mileage value to update
       int targetMemberId = 3;
       int newMileage = 11000;
       // Try-with-resources to automatically close the connection and statements
       try (Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement(
                "UPDATE members SET mileage = ? WHERE member_id = ?")) {
           // Set parameters for the update query
           pstmt.setInt(1, newMileage);           // New mileage value
           pstmt.setInt(2, targetMemberId);       // Target member ID
           // Execute the update statement
           int rows = pstmt.executeUpdate();
           System.out.println("Rows updated: " + rows);
           // Verify the result by selecting updated member information
           try (PreparedStatement selectStmt = conn.prepareStatement(
                   "SELECT name, mileage, grade FROM members WHERE member_id = ?")) {
               selectStmt.setInt(1, targetMemberId);  // Set the member ID to query
               ResultSet rs = selectStmt.executeQuery();
               // Print member information if found
               if (rs.next()) {
                   System.out.println("Name: " + rs.getString("name"));
                   System.out.println("Mileage: " + rs.getInt("mileage"));
                   System.out.println("Grade: " + rs.getString("grade"));
               }
           }
       } catch (SQLException e) {
           // Handle SQL exceptions
           e.printStackTrace();
       }
   }
}

