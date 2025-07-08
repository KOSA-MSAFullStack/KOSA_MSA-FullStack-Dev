package JDBC_Test;

import java.sql.Connection;
import java.sql.DriverManager;

public class jdbc_test2 {
	/**
	 * 자바에서 오라클 연동 테스트
	 * - 주요 오라클 관련 정보
	 *   SID : xepdb1, PORT : 1521
	 *   HOST : 127.0.0.1
	 *   USER : hr/hr
	 *   URL : jdbc:oracle:thin:@localhost:1521/xepdb1
	 *   DRIVER : oracle.jdbc.driver.OracleDriver
	 */
	

	public static void main(String[] args) {
	
		// 오라클 드라이버와 URL 정보
		Connection conn = null;		
		String driver="oracle.jdbc.driver.OracleDriver";
		String url="jdbc:oracle:thin:@localhost:1521/xepdb1";	
		
		// 오라클 드라이버 정보를 이용하여 간단한 연동 테스트
		try {
			//클래스 로드 ojdbc8.jar java8부터는 필수 아님
			Class.forName(driver);
			//
			conn = DriverManager.getConnection(url, "hr", "hr");
			System.out.println("oracle 연결 성공!!");
			conn.close();
		} catch (Exception e) {
			System.out.println("oracle 연결 실패!!");
			e.printStackTrace();
		} // end try
	}// end main
}// end class
