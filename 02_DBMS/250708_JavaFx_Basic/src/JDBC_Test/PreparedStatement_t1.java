package JDBC_Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.Statement;

public class PreparedStatement_t1 {

	public static void main(String[] args) {

		final String driver = "oracle.jdbc.driver.OracleDriver";
		final String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		final String uid = "hr";
		final String pwd = "hr";
		int employee_id; // 데이터베이스 값 저장할 변수
		float salary;
		String first_name, hire_date; //// 데이터베이스 값 저장할 변수
		Date hire_date2;

		// 드라이브 로딩과 db 연결은 try ~ catch 블럭으로 감싼다.
		try (Connection con = DriverManager.getConnection(url, uid, pwd)) {

			// sql문 작성
			double value = 10_000.00;
			String query = "SELECT * FROM Employees WHERE Salary > ?";
			PreparedStatement pStmt = con.prepareStatement(query);
			pStmt.setDouble(1, value);
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) { // 레코드가 있으면 반복, 없으면 중단
				employee_id = rs.getInt("employee_id"); // 첫번째 필드 가져옴
				first_name = rs.getString("first_name"); // 두번째 필드 가져옴
				salary = rs.getFloat("salary"); // 세번째 필드 가져옴
				hire_date = rs.getString("hire_date"); // 네번째 필드 가져옴
				hire_date2 = rs.getDate("hire_date");
				System.out.println(employee_id + " " + first_name + " " + salary + " " + hire_date + " " + hire_date2);
			} // end while

			System.out.println(driver);
		} catch (Exception e) {
			System.out.println("DB 연결에 문제가 있습니다.");
			e.printStackTrace();
		} // end try
	}// end mami
}// end class
