package todoList_Practice;

import java.sql.*;
import java.util.Scanner;
public class ReadTodo {
   public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
       // Prompt the user to enter the Todo ID they want to read
       System.out.print("Enter Todo ID to read: ");
       int id = sc.nextInt(); // Read the integer ID from user input
       // Use try-with-resources to automatically manage JDBC resources
       try (
           // Establish a connection to the Oracle database using DBConfig constants
           Connection conn = DriverManager.getConnection(DBConfig.JDBC_URL, DBConfig.USER, DBConfig.PASSWORD);
           // Prepare a callable statement to invoke the 'read_todo' stored procedure
           CallableStatement stmt = conn.prepareCall("{call read_todo(?, ?, ?, ?, ?)}")
       ) {
           // Set the input parameter (Todo ID)
           stmt.setInt(1, id);
           // Register the OUT parameters to receive values from the stored procedure
           stmt.registerOutParameter(2, Types.VARCHAR); // OUT parameter for title
           stmt.registerOutParameter(3, Types.VARCHAR); // OUT parameter for description
           stmt.registerOutParameter(4, Types.VARCHAR); // OUT parameter for status
           stmt.registerOutParameter(5, Types.DATE);    // OUT parameter for due date
           // Execute the stored procedure
           stmt.execute();
           // Retrieve and print the values returned by the OUT parameters
           System.out.println("Title: " + stmt.getString(2));
           System.out.println("Description: " + stmt.getString(3));
           System.out.println("Status: " + stmt.getString(4));
           System.out.println("Due Date: " + stmt.getDate(5));
       } catch (SQLException e) {
           // Print the stack trace if an SQL exception occurs
           e.printStackTrace();
       }
       // Close the scanner to free system resources
       sc.close();
   }
}

