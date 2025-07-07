package todoList_Practice;

import java.sql.*;
import java.util.Scanner;
public class DeleteTodo {
   public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
       // Prompt the user to enter the ID of the Todo item to delete
       System.out.print("Enter Todo ID to delete: ");
       int id = sc.nextInt(); // Read the Todo ID as an integer
       // Use try-with-resources to automatically manage JDBC resources
       try (
           // Establish a connection to the Oracle database using configuration from DBConfig
           Connection conn = DriverManager.getConnection(DBConfig.JDBC_URL, DBConfig.USER, DBConfig.PASSWORD);
           // Prepare a CallableStatement to call the stored procedure 'delete_todo'
           CallableStatement stmt = conn.prepareCall("{call delete_todo(?)}")
       ) {
           // Set the input parameter for the stored procedure (the ID of the Todo to delete)
           stmt.setInt(1, id);
           // Execute the stored procedure
           stmt.execute();
           // Print success message to indicate the Todo was deleted
           System.out.println("Todo successfully deleted.");
       } catch (SQLException e) {
           // If a SQL exception occurs, print the stack trace for debugging
           e.printStackTrace();
       }
       // Close the scanner to release system resources
       sc.close();
   }
}

