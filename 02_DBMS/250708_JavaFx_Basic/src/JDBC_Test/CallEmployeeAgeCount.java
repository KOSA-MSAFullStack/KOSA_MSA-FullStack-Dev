package JDBC_Test;



import java.sql.*;

public class CallEmployeeAgeCount {
    public static void main(String[] args) {
        String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
        String username = "hr";
        String password = "hr";

        try (Connection con = DriverManager.getConnection(url, username, password)) {
            // Preparing the stored procedure call with 2 parameters (1 IN, 1 OUT)
            CallableStatement cStmt = con.prepareCall("{CALL EmplAgeCount(?, ?)}");

            int age = 50; // Age threshold
            cStmt.setInt(1, age); // Set the IN parameter
            cStmt.registerOutParameter(2, Types.INTEGER); // Register the OUT parameter

            // Execute the stored procedure
            cStmt.execute();

            // Retrieve the output count value
            int count = cStmt.getInt(2);

            // Display result
            System.out.println("There are " + count +
                    " Employees over the age of " + age);

            // Close the statement
            cStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
