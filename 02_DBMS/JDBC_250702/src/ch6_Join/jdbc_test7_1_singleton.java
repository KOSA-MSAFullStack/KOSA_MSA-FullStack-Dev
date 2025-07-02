package ch6_Join;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

// 데이터베이스 연결을 위한 싱글턴 클래스 정의
class DBConnection {
	private static DBConnection instance = new DBConnection(); // 유일한 인스턴스 생성
	private final String driver = "oracle.jdbc.driver.OracleDriver"; // JDBC 드라이버 클래스
	private final String url = "jdbc:oracle:thin:@localhost:1521/xepdb1"; // DB 접속 URL
	private final String uid = "hr"; // DB 사용자 계정
	private final String pwd = "hr"; // DB 비밀번호

	// 생성자는 private으로 외부에서 직접 생성 못하게 함 (싱글턴 패턴)
	private DBConnection() {
		try {
			Class.forName(driver); // JDBC 드라이버 로딩
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC 드라이버 로딩 실패");
			e.printStackTrace();
		}
	}

	// 외부에서 인스턴스를 얻는 메서드 (항상 같은 객체 반환)
	public static DBConnection getInstance() {
		return instance;
	}

	// DB 연결 객체(Connection)를 반환하는 메서드
	public Connection getConnection() {
		try {
			return DriverManager.getConnection(url, uid, pwd); // DB 연결 시도
		} catch (Exception e) {
			System.out.println("DB 연결 실패");
			e.printStackTrace();
			return null; // 연결 실패 시 null 반환
		}
	}
}

// 메인 클래스
public class jdbc_test7_1_singleton {
	public static void main(String[] args) {
		Connection con = null; // DB 연결 객체
		Statement stmt = null; // SQL 실행 객체
		ResultSet rs = null;   // SQL 결과 저장 객체

		int deptno;          // 부서 ID
		String dname, m_id, l_id; // 부서명, 매니저 ID, 위치 ID

		try {
			// 싱글턴 객체를 통해 DB 연결 획득
			con = DBConnection.getInstance().getConnection();
			if (con == null)
				return; // 연결 실패 시 종료

			stmt = con.createStatement(); // SQL 실행을 위한 Statement 객체 생성
			String sql = "SELECT * FROM HR.DEPARTMENTS"; // 실행할 SQL 문
			rs = stmt.executeQuery(sql); // SQL 실행 후 결과를 ResultSet에 저장

			// 결과가 있을 때까지 반복하며 출력
			while (rs.next()) {
				deptno = rs.getInt("department_id"); // 부서 ID 추출
				dname = rs.getString("department_name"); // 부서명 추출
				m_id = rs.getString("manager_id"); // 매니저 ID 추출
				l_id = rs.getString("location_id"); // 위치 ID 추출

				// 결과 출력
				System.out.println(deptno + " " + dname + " " + m_id + " " + l_id);
			}

			// 자원 정리
			rs.close();
			stmt.close();
			con.close();
		} catch (Exception e) {
			System.out.println("DB 작업 중 오류 발생");
			e.printStackTrace();
		}
	}
}
