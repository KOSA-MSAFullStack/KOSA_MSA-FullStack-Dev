package sec02.ex01;

import java.sql.Connection;
import java.sql.DriverManager;

public class Jdbc_test {

	public static void main(String[] args) {
	
		Connection con = null;
		try {
			String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
			String uid = "scott";
			String pwd = "tiger";
			String driver = "oracle.jdbc.driver.OracleDriver";
			Class.forName(driver);
			con = DriverManager.getConnection(url, uid, pwd);
			System.out.println("제대로 연결되었습니다.");
			System.out.println(con);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("연결에 실패하였습니다.");
		} // end try

	}// end main

}// end class
