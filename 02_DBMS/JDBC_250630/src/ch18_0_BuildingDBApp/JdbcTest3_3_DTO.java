package ch18_0_BuildingDBApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

// 부서 정보를 저장하는 DTO 클래스
class DepartmentDTO {
	private int departmentId;		// 부서 ID
	private String departmentName;	// 부서 이름
	private String managerId;		// 매니저 ID
	private String locationId;		// 위치 ID

	// 생성자
	public DepartmentDTO(int departmentId, String departmentName, String managerId, String locationId) {
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.managerId = managerId;
		this.locationId = locationId;
	}

	// getter 메서드들
	public int getDepartmentId() { return departmentId; }
	public String getDepartmentName() { return departmentName; }
	public String getManagerId() { return managerId; }
	public String getLocationId() { return locationId; }

	// 객체 정보를 문자열로 출력
	@Override
	public String toString() {
		return departmentId + " " + departmentName + " " + managerId + " " + locationId;
	}
}

public class JdbcTest3_3_DTO {
	public static void main(String[] args) {
		// DB 연결 및 SQL 실행을 위한 기본 정보 설정
		final String sql;
		final String driver = "oracle.jdbc.driver.OracleDriver";
		final String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		final String uid = "hr";
		final String pwd = "hr";

		// 부서 DTO 리스트 생성
		List<DepartmentDTO> dept = new ArrayList<>();
		DepartmentDTO departmentDTO = null;

		try {
			// JDBC 드라이버 로드
			Class.forName(driver);

			// DB 연결
			Connection con = DriverManager.getConnection(url, uid, pwd);
			// 쿼리 실행을 위한 Statement 생성
			Statement stmt = con.createStatement();

			// 부서 정보 조회 쿼리
			sql = "SELECT * FROM HR.DEPARTMENTS";

			// SQL 실행 결과를 ResultSet으로 받음
			ResultSet rs = stmt.executeQuery(sql);

			System.out.println("DB에서 값 가져오면서 출력");
			System.out.println("==============================");

			// ResultSet을 순회하면서 DTO 객체 생성 및 리스트에 추가
			while (rs.next()) {
				departmentDTO = new DepartmentDTO(
					rs.getInt("department_id"),
					rs.getString("department_name"),
					rs.getString("manager_id"),
					rs.getString("location_id")
				);
				dept.add(departmentDTO); // 리스트에 추가
				System.out.println(departmentDTO.toString()); // 바로 출력
			}

			// 리소스 정리
			rs.close();
			stmt.close();
			con.close();
		} catch (Exception e) {
			System.out.println("DB 연결에 문제가 있습니다.");
			e.printStackTrace();
		}

		// DB 연결 종료 후 전체 리스트 출력
		System.out.println("DB 연결 종료");
		System.out.println("==============================");
		for (DepartmentDTO i : dept) {
			System.out.println(i);
		}

		// 리스트 전체 toString 출력
		System.out.println("toString");
		System.out.println(dept.toString());
	}
}
