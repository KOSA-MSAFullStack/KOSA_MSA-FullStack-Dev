// DB 연결 테스트 - DB정보, 사용자 ID, Password 맞는지 확인

package ch18_0_BuildingDBApp;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcTest2 {
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
		
		final String driver="oracle.jdbc.driver.OracleDriver";			// ojdbc8.jar 파일이 제대로 로드되었는지 확인
		final String url="jdbc:oracle:thin:@localhost:1521/xepdb1";		// DB 정보(DB위치 및 이름) 맞는지 확인
		
		// 오라클 드라이버 정보를 이용하여 간단한 연동 테스트
		try {
			//클래스 로드 ojdbc8.jar java8부터는 필수 아님
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url, "hr", "hr");	// DB정보, 사용자 ID, Password
			System.out.println("oracle 연결 성공!!");
			conn.close();		// oracle 연결 끊기, Singleton Pattern, 명시적 표현
		} catch (Exception e) {
			System.out.println("oracle 연결 실패!!");
			e.printStackTrace();
		}
	}
}


