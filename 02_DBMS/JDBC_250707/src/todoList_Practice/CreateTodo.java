package todoList_Practice;

import java.sql.*;
import java.util.Scanner;

public class CreateTodo {
   public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
       // Prompt user for input to gather data for the new Todo item
       System.out.print("Enter Todo ID: ");
       int id = sc.nextInt(); sc.nextLine(); // Read integer ID and consume leftover newline
       System.out.print("Enter Title: ");
       String title = sc.nextLine(); // Read the title of the Todo
       System.out.print("Enter Description: ");
       String desc = sc.nextLine(); // Read the description of the Todo
       System.out.print("Enter Status: ");
       String status = sc.nextLine(); // Read the status of the Todo (e.g., pending, completed)
       System.out.print("Enter Due Date (yyyy-mm-dd): ");
       String dueDate = sc.nextLine(); // Read the due date as a string
       
       // Try-with-resources block to ensure automatic closing of JDBC resources
       try (
           // Establish connection to Oracle database using constants from DBConfig
           Connection conn = DriverManager.getConnection(DBConfig.JDBC_URL, DBConfig.USER, DBConfig.PASSWORD);
           // Prepare a callable statement to call the stored procedure 'create_todo'
           CallableStatement stmt = conn.prepareCall("{call create_todo(?, ?, ?, ?, ?)}"))
       {
           // Set the parameters of the stored procedure
           stmt.setInt(1, id);                         // Set the Todo ID (IN parameter)
           stmt.setString(2, title);                   // Set the title (IN parameter)
           stmt.setString(3, desc);                    // Set the description (IN parameter)
           stmt.setString(4, status);                  // Set the status (IN parameter)
           stmt.setDate(5, Date.valueOf(dueDate));     // Set the due date (IN parameter), converted to SQL Date
           // Execute the stored procedure
           stmt.execute();
           // Notify the user of successful creation
           System.out.println("Todo successfully created.");
       } catch (SQLException e) {
           // Print stack trace in case of SQL error
           e.printStackTrace();
       }
       // Close the scanner to prevent memory leaks
       sc.close();
   }
}

