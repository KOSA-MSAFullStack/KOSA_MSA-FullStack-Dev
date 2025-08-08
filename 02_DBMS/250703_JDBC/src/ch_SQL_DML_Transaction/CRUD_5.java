package ch_SQL_DML_Transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CRUD_5 {
	public static void main(String[] args) {

		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		String username = "hr";
		String password = "hr";
		int department_id ; // 데이터베이스 값 저장할 변수
		String  department_name, manager_id,location_id; //// 데이터베이스 값 저장할 변수

		// sql문 작성
		String query = "select * from HR.DEPARTMENTS";
		// 드라이브 로딩과 db 연결은 try ~ catch 블럭으로 감싼다.
		//try -with-resources Construct
		try ( Connection con = DriverManager.getConnection(url, username, password);
			  Statement stmt = con.createStatement();
			  ResultSet rs = stmt.executeQuery(query) 
			) //AutoClose
		{		
			while (rs.next()) { // 레코드가 있으면 반복, 없으면 중단
				department_id = rs.getInt("department_id"); // 첫번째 필드 가져옴
				department_name = rs.getString("departments_name"); // 두번째 필드 가져옴
				manager_id = rs.getString("manager_id"); // 세번째 필드 가져옴
				location_id = rs.getString("location_id"); // 네번째 필드 가져옴
				System.out.println(department_id + " " + department_name +
						" " + manager_id + " " + location_id);
			} // end while				
			
			System.out.println(driver);
		} catch (SQLException ex) {
			while(ex != null) {
				System.out.println("SQLState:  " + ex.getSQLState());
				System.out.println("Error Code:" + ex.getErrorCode());
				System.out.println("Message:   " + ex.getMessage());
				Throwable t = ex.getCause();
				while(t != null) {
					System.out.println("Cause:" + t);
					t = t.getCause();
				}
				ex = ex.getNextException();
			}
		} // end try
	}// end main
}// end class
