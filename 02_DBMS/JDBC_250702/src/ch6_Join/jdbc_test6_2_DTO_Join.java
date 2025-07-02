package ch6_Join;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

// 직원, 부서, 지역 정보를 담는 DTO 클래스
class EmpDeptLocDTO {
	private int employeeId;      // 직원 ID
	private String department;   // 부서명
	private String city;         // 도시명

	// 생성자
	public EmpDeptLocDTO(int employeeId, String department, String city) {
		this.employeeId = employeeId;
		this.department = department;
		this.city = city;
	}

	// Getter 메서드
	public int getEmployeeId() { return employeeId; }
	public String getDepartment() { return department; }
	public String getCity() { return city; }

	// 객체 정보를 출력하기 위한 toString 메서드
	@Override
	public String toString() {
		return "직원ID: " + employeeId + " | 부서: " + department + " | 도시: " + city;
	}
}

// 메인 클래스
public class jdbc_test6_2_DTO_Join {
	public static void main(String[] args) {
		// JDBC 접속 정보
		final String driver = "oracle.jdbc.driver.OracleDriver";
		final String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		final String uid = "hr";
		final String pwd = "hr";

		// 결과를 저장할 리스트
		List<EmpDeptLocDTO> list = new ArrayList<>();
		EmpDeptLocDTO dto = null;

		try {
			// 1. 드라이버 로딩
			Class.forName(driver);

			// 2. DB 연결
			Connection con = DriverManager.getConnection(url, uid, pwd);

			// 3. SQL 정의
			String sql = "SELECT e.employee_id, l.city, d.department_name " +
			             "FROM hr.employees e " +
				             "JOIN hr.departments d ON e.department_id = d.department_id " +
				             "JOIN hr.locations l ON d.location_id = l.location_id";

			// 4. Statement 준비
			PreparedStatement pStmt = con.prepareStatement(sql);

			// 5. 쿼리 실행
			ResultSet rs = pStmt.executeQuery();

			// 6. 결과 출력
			System.out.println("직원 ID, 도시, 부서명 목록");
			System.out.println("===========================================");

			// 7. 결과 처리
			while (rs.next()) {
				dto = new EmpDeptLocDTO(
					rs.getInt("employee_id"),
					rs.getString("department_name"),
					rs.getString("city")
				);
				list.add(dto);
				System.out.println(dto);
			}

			// 8. 자원 해제
			rs.close();
			pStmt.close();
			con.close();

		} catch (Exception e) {
			System.out.println("DB 연결 또는 SQL 실행 중 오류 발생");
			e.printStackTrace();
		}

		// 전체 리스트 출력
				System.out.println("DB 연결 종료");
				System.out.println("=====================================");
				for (EmpDeptLocDTO emp : list) {
					System.out.println(emp); // toString 자동 호출
				}

				// 9. 전체 리스트 디버깅용 출력
				System.out.println("========================================================");
				System.out.println("전체 결과 리스트:");
				System.out.println(list.toString());
	}
}
