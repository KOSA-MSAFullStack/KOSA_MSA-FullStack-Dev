package JDBC_Test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOJDBCImpl implements EmployeeDAO, AutoCLoseable {

	// 싱글턴 처리
	private EmployeeDAOJDBCImpl() {
	} 	
	private static EmployeeDAOJDBCImpl instance = new EmployeeDAOJDBCImpl();
	public static EmployeeDAOJDBCImpl getInstance() {
		return instance;
	}
	// DB 연결 정보
	static String driver = "oracle.jdbc.driver.OracleDriver";
	static String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
	static String username = "hr";
	static String password = "hr";
	PreparedStatement pstmt = null;
	CallableStatement cstmt = null;
	Connection conn = null;

	//DB 연결
	public static Connection getConnection() {
		Connection con = null;
		try {
			//클래스 로드 ojdbc6.jar
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
			System.out.println("oracle 연결 성공!!");
		} catch (Exception e) {
			e.printStackTrace();
		} // end try
		return con;
	}// end get..

	@Override
	public void add(Employee emp) {
		// TODO Auto-generated method stub
	}

	@Override
	public void update(Employee emp) {
		// TODO Auto-generated method stub
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
	}

	@Override
	public Employee findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getAllEmployee() {

		List  EmployeeList = new ArrayList();
		try {
			// sql --> table --> view
			String query = "select * from HR.EMPLOYEES";
			System.out.println(query);			
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {						
				int EMPLOYEE_ID = rs.getInt("EMPLOYEE_ID");
				String FIRST_NAME = rs.getString("FIRST_NAME");
				String LAST_NAME = rs.getString("LAST_NAME");
				String EMAIL = rs.getString("EMAIL");
				String PHONE_NUMBER = rs.getString("PHONE_NUMBER");
				Date HIRE_DATE = rs.getDate(("HIRE_DATE"));
				String JOB_ID = rs.getString("JOB_ID");
				float SALARY = rs.getFloat("SALARY");
				float COMMISSION_PCT = rs.getFloat("COMMISSION_PCT");
				String MANAGER_ID = rs.getString("MANAGER_ID");
				int  DEPARTMENT_ID = rs.getInt("DEPARTMENT_ID");
				
				Employee emp = new Employee();
				emp.setEMPLOYEE_ID(EMPLOYEE_ID);
				emp.setFIRST_NAME(FIRST_NAME);
				emp.setLAST_NAME(LAST_NAME);
				emp.setEMAIL(EMAIL);
				emp.setPHONE_NUMBER(PHONE_NUMBER);
				emp.setHIRE_DATE(HIRE_DATE);
				emp.setSALARY(SALARY);
				emp.setCOMMISSION_PCT(COMMISSION_PCT);
				emp.setMANAGER_ID(MANAGER_ID);
				emp.setDEPARTMENT_ID(DEPARTMENT_ID);			
				
				EmployeeList.add(emp);
			} // end while
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} // end try
		return EmployeeList;
	}//end getAllEmployee

	@Override
	public void close(Connection conn, Statement stmt, ResultSet rs) {
		// TODO Auto-generated method stub
		try {
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} // end try
	}// end close..

	@Override
	public void close(Connection conn, Statement stmt) {
		try {
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} // end try
	}// end cl..

}// end class
