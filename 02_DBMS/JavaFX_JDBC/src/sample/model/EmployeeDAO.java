package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.util.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDAO {

	// Search a single employee by ID
	public static Employee searchEmployee(String empId) throws SQLException, ClassNotFoundException {
		String selectStmt = "SELECT * FROM employees WHERE employee_id=" + empId;
		try {
			ResultSet rsEmp = DBUtil.dbExecuteQuery(selectStmt);
			Employee employee = getEmployeeFromResultSet(rsEmp);
			return employee;
		} catch (SQLException e) {
			System.out.println("While searching an employee with " + empId + " id, an error occurred: " + e);
			throw e;
		}
	}

	// Convert a single ResultSet row into an Employee object
	private static Employee getEmployeeFromResultSet(ResultSet rs) throws SQLException {
		Employee emp = null;
		if (rs.next()) {
			emp = new Employee();
			emp.setEmployeeId(rs.getInt("EMPLOYEE_ID"));
			emp.setFirstName(rs.getString("FIRST_NAME"));
			emp.setLastName(rs.getString("LAST_NAME"));
			emp.setEmail(rs.getString("EMAIL"));
			emp.setPhoneNumber(rs.getString("PHONE_NUMBER"));
			emp.setHireDate(rs.getDate("HIRE_DATE"));
			emp.setJobId(rs.getString("JOB_ID"));
			emp.setSalary(rs.getInt("SALARY"));
			emp.setCommissionPct(rs.getDouble("COMMISSION_PCT"));
			emp.setManagerId(rs.getInt("MANAGER_ID"));
			emp.setDepartmantId(rs.getInt("DEPARTMENT_ID"));
		}
		return emp;
	}

	// Get all employees as an ObservableList
	public static ObservableList<Employee> searchEmployees() throws SQLException, ClassNotFoundException {
		String selectStmt = "SELECT * FROM employees";
		try {
			ResultSet rsEmps = DBUtil.dbExecuteQuery(selectStmt);
			ObservableList<Employee> empList = getEmployeeList(rsEmps);
			return empList;
		} catch (SQLException e) {
			System.out.println("SQL select operation has been failed: " + e);
			throw e;
		}
	}

	// Convert a ResultSet into a list of Employee objects
	private static ObservableList<Employee> getEmployeeList(ResultSet rs) throws SQLException, ClassNotFoundException {
		ObservableList<Employee> empList = FXCollections.observableArrayList();
		while (rs.next()) {
			Employee emp = new Employee();
			emp.setEmployeeId(rs.getInt("EMPLOYEE_ID"));
			emp.setFirstName(rs.getString("FIRST_NAME"));
			emp.setLastName(rs.getString("LAST_NAME"));
			emp.setEmail(rs.getString("EMAIL"));
			emp.setPhoneNumber(rs.getString("PHONE_NUMBER"));
			emp.setHireDate(rs.getDate("HIRE_DATE"));
			emp.setJobId(rs.getString("JOB_ID"));
			emp.setSalary(rs.getInt("SALARY"));
			emp.setCommissionPct(rs.getDouble("COMMISSION_PCT"));
			emp.setManagerId(rs.getInt("MANAGER_ID"));
			emp.setDepartmantId(rs.getInt("DEPARTMENT_ID"));
			empList.add(emp);
		}
		return empList;
	}

	// Update employee email by ID
	public static void updateEmpEmail(String empId, String empEmail) throws SQLException, ClassNotFoundException {
		String updateStmt = "BEGIN\n" +
		                    "   UPDATE employees\n" +
		                    "      SET EMAIL = '" + empEmail + "'\n" +
		                    "    WHERE EMPLOYEE_ID = " + empId + ";\n" +
		                    "   COMMIT;\n" +
		                    "END;";
		try {
			DBUtil.dbExecuteUpdate(updateStmt);
		} catch (SQLException e) {
			System.out.print("Error occurred while UPDATE Operation: " + e);
			throw e;
		}
	}

	// Delete employee by ID
	public static void deleteEmpWithId(String empId) throws SQLException, ClassNotFoundException {
		String updateStmt = "BEGIN\n" +
		                    "   DELETE FROM employees\n" +
		                    "         WHERE employee_id = " + empId + ";\n" +
		                    "   COMMIT;\n" +
		                    "END;";
		try {
			DBUtil.dbExecuteUpdate(updateStmt);
		} catch (SQLException e) {
			System.out.print("Error occurred while DELETE Operation: " + e);
			throw e;
		}
	}

	// Insert a new employee with minimum required fields
	public static void insertEmp(String name, String lastname, String email)
			throws SQLException, ClassNotFoundException {
		String updateStmt = "BEGIN\n" +
		                    "   INSERT INTO employees\n" +
		                    "   (EMPLOYEE_ID, FIRST_NAME, LAST_NAME, EMAIL, HIRE_DATE, JOB_ID)\n" +
		                    "   VALUES (sequence_employee.nextval, '" + name + "', '" + lastname + "', '" + email + "', SYSDATE, 'IT_PROG');\n" +
		                    "END;";
		try {
			DBUtil.dbExecuteUpdate(updateStmt);
		} catch (SQLException e) {
			System.out.print("Error occurred while INSERT Operation: " + e);
			throw e;
		}
	}
}
