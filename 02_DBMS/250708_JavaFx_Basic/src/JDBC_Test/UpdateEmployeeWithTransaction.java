package JDBC_Test;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.sql.SQLException;


public class UpdateEmployeeWithTransaction {
	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		String username = "hr";
		String password = "hr";

		try (Connection con = DriverManager.getConnection(url, username, password)) {

			// SQL to update AGE (using it instead of 'position' since EMPLOYEE has no
			// 'position' column)
			String updateAgeSql = "UPDATE EMPLOYEE SET AGE = ? WHERE EMP_ID = ?";
			PreparedStatement pstmt = con.prepareStatement(updateAgeSql);
			pstmt.setInt(1, 35); // new age
			pstmt.setInt(2, 1); // emp_id = 1

			// SQL to update SALARY
			String updateSalarySql = "UPDATE EMPLOYEE SET SALARY = ? WHERE EMP_ID = ?";
			PreparedStatement pstmt2 = con.prepareStatement(updateSalarySql);
			pstmt2.setDouble(1, 3000); // new salary
			pstmt2.setInt(2, 1); // emp_id = 1

			// Store current auto-commit setting
			boolean autoCommit = con.getAutoCommit();

			try {
				// Begin transaction
				con.setAutoCommit(false);

				// Execute both updates
				pstmt.executeUpdate();
				pstmt2.executeUpdate();

				// Commit the transaction
				con.commit();
				System.out.println("Update successful and committed.");
			} catch (SQLException exc) {
				// Rollback on error
				con.rollback();
				System.out.println("Transaction rolled back due to error.");
				exc.printStackTrace();
			} finally {
				// Restore original auto-commit mode
				con.setAutoCommit(autoCommit);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
