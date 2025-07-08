package ch_UserDefinedFunction;

import java.sql.*;
public class ViewV1Test {
   // JDBC configuration
   private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521/xepdb1";
   private static final String USER = "hr";
   private static final String PASSWORD = "hr";
   public static void main(String[] args) {
       // SQL to select from view
       String sql = "SELECT employee_id, last_name, salary, tax FROM v1";
       // Try-with-resources for automatic resource management
       try (Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
           // Print header
           System.out.printf("%-10s %-15s %-10s %-10s%n", "ID", "Last Name", "Salary", "Tax");
           // Iterate and print results
           while (rs.next()) {
               int id = rs.getInt("employee_id");
               String name = rs.getString("last_name");
               double salary = rs.getDouble("salary");
               double tax = rs.getDouble("tax");
               System.out.printf("%-10d %-15s %-10.2f %-10.2f%n", id, name, salary, tax);
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }
   }
}

