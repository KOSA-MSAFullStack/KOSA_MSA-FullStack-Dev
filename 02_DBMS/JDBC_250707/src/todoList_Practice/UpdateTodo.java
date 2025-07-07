package todoList_Practice;

import java.sql.*;
import java.util.Scanner;

public class UpdateTodo {
   public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
       // Prompt user to input the Todo ID that needs to be updated
       System.out.print("Enter Todo ID to update: ");
       int id = sc.nextInt(); sc.nextLine(); // Read the ID and consume the remaining newline
       // Prompt user for new values to update the Todo
       System.out.print("New Title: ");
       String title = sc.nextLine(); // Read new title
       System.out.print("New Description: ");
       String desc = sc.nextLine(); // Read new description
       System.out.print("New Status: ");
       String status = sc.nextLine(); // Read new status (e.g., pending, done)
       System.out.print("New Due Date (yyyy-mm-dd): ");
       String dueDate = sc.nextLine(); // Read new due date in string format
       // Use try-with-resources to ensure JDBC resources are automatically closed
       try (
           // Establish database connection using credentials from DBConfig
           Connection conn = DriverManager.getConnection(DBConfig.JDBC_URL, DBConfig.USER, DBConfig.PASSWORD);
           // Prepare a CallableStatement to execute the stored procedure 'update_todo'
           CallableStatement stmt = conn.prepareCall("{call update_todo(?, ?, ?, ?, ?)}")
       ) {
           // Set the input parameters for the stored procedure
           stmt.setInt(1, id);                         // Todo ID to update
           stmt.setString(2, title);                   // New title
           stmt.setString(3, desc);                    // New description
           stmt.setString(4, status);                  // New status
           stmt.setDate(5, Date.valueOf(dueDate));     // New due date converted to SQL Date
           // Execute the stored procedure
           stmt.execute();
           // Print confirmation message
           System.out.println("Todo successfully updated.");
       } catch (SQLException e) {
           // Print detailed error message in case of SQL failure
           e.printStackTrace();
       }
       // Close the scanner to release system resources
       sc.close();
   }
}

