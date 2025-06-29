// Java에서 DB로 SQL 보내는 코드

package ch18_0_BuildingDBApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;		// result set
import java.sql.Statement;		// SQL문 담기 위해 필요

public class JdbcTest3_1 {
	public static void main(String[] args) {
		Connection con = null; 	// 1. 오라클 데이터베이스 연결 참조변수 선언
		Statement stmt = null;	// 2. 쿼리문 실행 참조변수 선언
		ResultSet rs = null;	// 3. 쿼리문 실행 결과를 저장하는 참조변수 선언
		String sql = null; 		// sql문을 저장하는 변수
		String driver = "oracle.jdbc.driver.OracleDriver";			// ojdbc8.jar 파일이 제대로 로드되었는지 확인
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";		// DB 정보(DB위치 및 이름) 맞는지 확인
		String uid = "hr";
		String pwd = "hr";
		int deptno; 				// 데이터베이스 값 저장할 변수
		String dname, m_id,l_id; 	// 데이터베이스 값 저장할 변수
		
		// 드라이브 로딩과 db 연결은 try ~ catch 블럭으로 감싼다.
		try {
			// 1. jdbc 드라이버 로딩
			Class.forName(driver);
			
			// 2. db 연결
			con = DriverManager.getConnection(url, uid, pwd);
			
			// 3. 쿼리문 실행 객체 생성 (SQL 객체 생성)
			// DB한테 SQL문 보내는 객체 
			stmt = con.createStatement();
			
			// 4. sql문 작성
			// 코드 실행 전 DB 정상 실행되는지 확인 
			sql = "select * from HR.DEPARTMENTS";
			
			// 5. 쿼리문 실행(select문 executeQuery()사용)
			rs = stmt.executeQuery(sql);
		    rs.next(); 									// 레코드가 있으면 읽음 (한 번에 한 행만 가져옴)
			deptno = rs.getInt("department_id"); 		// 첫번째 필드 가져옴
			dname = rs.getString("department_name"); 	// 두번째 필드 가져옴
			m_id = rs.getString("manager_id"); 			// 세번째 필드 가져옴
			l_id = rs.getString("location_id"); 		// 네번째 필드 가져옴
			
			// 6. 쿼리문 결과 출력
			System.out.println(deptno + " " + dname + " " + m_id + " " + l_id);			
			
			// 7. DB연결 후 작업이 종료되면 연결 객체을 닫아준다.
			rs.close();		// result set, DB와 자바 연결 끊음
			stmt.close();	// Sentance??? 끊음
			con.close();	// DB의 연결 끊음
		} catch (Exception e) {
			System.out.println("DB 연결에 문제가 있습니다.");
			e.printStackTrace();
		} // end try
	}// end mami
}// end class

