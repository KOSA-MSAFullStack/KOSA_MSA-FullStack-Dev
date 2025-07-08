// CRUD 종합 실행

package todoList_Practice;

import java.sql.*;
import java.util.Scanner;
public class TodoProcedureJDBC {
   // JDBC configuration
   private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521/xepdb1";
   private static final String USER = "hr";
   private static final String PASSWORD = "hr";
   // Singleton connection instance
   private static Connection conn;
   // Method to get singleton connection
   public static Connection getConnection() throws SQLException {
       try {
           if (conn == null || conn.isClosed()) {
               Class.forName("oracle.jdbc.driver.OracleDriver");
               conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
           }
       } catch (ClassNotFoundException e) {
           e.printStackTrace();
       }
       return conn;
   }
   public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
       try {
           while (true) {
               // Display menu
               System.out.println("\n1. Create\n2. Read\n3. Update\n4. Delete\n5. Exit");
               System.out.print("Choose operation: ");
               int choice = sc.nextInt();
               // Execute selected option
               switch (choice) {
                   case 1 : createTodo(sc);
                   break;
                   case 2 : readTodo(sc);
                   break;
                   case 3 : updateTodo(sc);
                   break;
                   case 4 : deleteTodo(sc);
                   break;
                   case 5 : {
                       sc.close();
                       if (conn != null && !conn.isClosed()) conn.close();
                       return;
                   }
                   default : System.out.println("Invalid option.");
               }
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
   }
   // CREATE operation using stored procedure
   public static void createTodo(Scanner sc) {
       try (CallableStatement stmt = getConnection().prepareCall("{call create_todo(?, ?, ?, ?, ?)}")) {
           // Get user input
           System.out.print("Enter Todo ID: ");
           int id = sc.nextInt();
           sc.nextLine();
           System.out.print("Enter Title: ");
           String title = sc.nextLine();
           System.out.print("Enter Description: ");
           String desc = sc.nextLine();
           System.out.print("Enter Status: ");
           String status = sc.nextLine();
           System.out.print("Enter Due Date (yyyy-mm-dd): ");
           String dueDate = sc.nextLine();
           // Set procedure parameters
           stmt.setInt(1, id);
           stmt.setString(2, title);
           stmt.setString(3, desc);
           stmt.setString(4, status);
           stmt.setDate(5, Date.valueOf(dueDate));
           // Execute procedure
           stmt.execute();
           System.out.println("Todo created.");
       } catch (SQLException e) {
           e.printStackTrace();
       }
   }
   // READ operation using stored procedure with ORA-01403 check
   public static void readTodo(Scanner sc) {
       try (CallableStatement stmt = getConnection().prepareCall("{call read_todo(?, ?, ?, ?, ?)}")) {
           System.out.print("Enter Todo ID: ");
           int id = sc.nextInt();
           stmt.setInt(1, id);
           stmt.registerOutParameter(2, Types.VARCHAR);
           stmt.registerOutParameter(3, Types.VARCHAR);
           stmt.registerOutParameter(4, Types.VARCHAR);
           stmt.registerOutParameter(5, Types.DATE);
           stmt.execute();
           System.out.println("Title: " + stmt.getString(2));
           System.out.println("Description: " + stmt.getString(3));
           System.out.println("Status: " + stmt.getString(4));
           System.out.println("Due Date: " + stmt.getDate(5));
       } catch (SQLException e) {
           if (e.getMessage().contains("ORA-01403")) {
               System.out.println("Todo not found.");
           } else {
               e.printStackTrace();
           }
       }
   }
   // UPDATE operation using stored procedure with ORA-01403 check
   public static void updateTodo(Scanner sc) {
       try (CallableStatement stmt = getConnection().prepareCall("{call update_todo(?, ?, ?, ?, ?)}")) {
           System.out.print("Enter Todo ID to update: ");
           int id = sc.nextInt();
           sc.nextLine();
           System.out.print("New Title: ");
           String title = sc.nextLine();
           System.out.print("New Description: ");
           String desc = sc.nextLine();
           System.out.print("New Status: ");
           String status = sc.nextLine();
           System.out.print("New Due Date (yyyy-mm-dd): ");
           String dueDate = sc.nextLine();
           stmt.setInt(1, id);
           stmt.setString(2, title);
           stmt.setString(3, desc);
           stmt.setString(4, status);
           stmt.setDate(5, Date.valueOf(dueDate));
           stmt.execute();
           System.out.println("Todo updated.");
       } catch (SQLException e) {
           if (e.getMessage().contains("ORA-01403")) {
               System.out.println("Todo not found.");
           } else {
               e.printStackTrace();
           }
       }
   }
   // DELETE operation using stored procedure with ORA-01403 check
   public static void deleteTodo(Scanner sc) {
       try (CallableStatement stmt = getConnection().prepareCall("{call delete_todo(?)}")) {
           System.out.print("Enter Todo ID to delete: ");
           int id = sc.nextInt();
           stmt.setInt(1, id);
           stmt.execute();
           System.out.println("Todo deleted.");
       } catch (SQLException e) {
           if (e.getMessage().contains("ORA-01403")) {
               System.out.println("Todo not found.");
           } else {
               e.printStackTrace();
           }
       }
   }
}

