package ch_UserDefinedFunction;

import java.sql.*;
import java.util.Scanner;
public class GetSalaryFunctionTest {
   // JDBC configuration
   private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521/xepdb1";
   private static final String USER = "hr";
   private static final String PASSWORD = "hr";
   public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
       System.out.print("Enter Employee ID: ");
       int empId = sc.nextInt();
       // Define connection and callable statement
       try (Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            CallableStatement stmt = conn.prepareCall("{ ? = call get_sal(?) }")) {
           // Register the return value
           stmt.registerOutParameter(1, Types.NUMERIC);
           // Set the input parameter
           stmt.setInt(2, empId);
           // Execute the function
           stmt.execute();
           // Retrieve and print the returned salary
           double salary = stmt.getDouble(1);
           System.out.println("Salary for employee ID " + empId + " is: " + salary);
       } catch (SQLException e) {
           e.printStackTrace();
       }
       sc.close();
   }
  
}

