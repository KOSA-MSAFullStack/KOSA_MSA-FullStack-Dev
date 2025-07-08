package ch6_Join;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

// 직원 연봉 정보를 담는 DTO 클래스
class EmpDeptJoinDTO {
	private String name;         // 직원 이름 (성 + 이름)
	private String department;   // 부서명
	private double annualSalary; // 연봉 (salary * 12)
	private int locationId;      // 지역 ID

	// 생성자
	public EmpDeptJoinDTO(String name, String department, double annualSalary, int locationId) {
		this.name = name;
		this.department = department;
		this.annualSalary = annualSalary;
		this.locationId = locationId;
	}

	// Getter 메서드
	public String getName() { return name; }
	public String getDepartment() { return department; }
	public double getAnnualSalary() { return annualSalary; }
	public int getLocationId() { return locationId; }

	// 객체 정보를 출력하기 위한 toString 메서드
	@Override
	public String toString() {
		return name + " | 부서: " + department + " | 연봉: " + annualSalary + " | 지역ID: " + locationId;
	}
}

// 메인 클래스
public class jdbc_test6_1_DTO_Join {
	public static void main(String[] args) {
		// JDBC 접속을 위한 기본 정보
		final String driver = "oracle.jdbc.driver.OracleDriver";                     // 오라클 드라이버 클래스명
		final String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";               // DB 접속 URL
		final String uid = "hr";                                                    // 사용자 ID
		final String pwd = "hr";                                                    // 사용자 비밀번호

		// 사용자로부터 location_id의 하한값을 입력받음
		Scanner scanner = new Scanner(System.in);
		System.out.print("조회할 location_id 하한값 입력 (예: 1500): ");
		int locationInput = scanner.nextInt(); // 입력값 예시: 1500

		// 결과를 저장할 리스트 생성
		List<EmpDeptJoinDTO> list = new ArrayList<>();
		EmpDeptJoinDTO dto = null;

		try {
			// 1. JDBC 드라이버 로딩
			Class.forName(driver);

			// 2. 데이터베이스 연결
			Connection con = DriverManager.getConnection(url, uid, pwd);

			// 3. 실행할 SQL 쿼리 정의
			String sql = "SELECT e.last_name || e.first_name AS name, " + // 이름 결합
				             "d.department_name AS dname, " +                 // 부서명
				             "(e.salary * 12) AS YSal, " +                    // 연봉 계산
				             "d.location_id AS Lid " +                        // 지역 ID
			             "FROM hr.employees e INNER JOIN hr.departments d " + // JOIN 수행
			             "ON e.department_id = d.department_id " +
			             "WHERE d.location_id > ?";                       // 조건: location_id가 입력값보다 큰 경우

			// 4. PreparedStatement 생성 및 입력값 바인딩
			PreparedStatement pStmt = con.prepareStatement(sql);
			pStmt.setInt(1, locationInput); // 사용자 입력 location_id 바인딩

			// 5. SQL 실행
			ResultSet rs = pStmt.executeQuery();

			// 6. 결과 출력 시작
			System.out.println("[location_id > " + locationInput + "] 조건의 직원 연봉 목록");
			System.out.println("========================================================");

			// 7. 결과 집합에서 각 레코드를 읽어 DTO에 저장 후 리스트에 추가
			while (rs.next()) {
				dto = new EmpDeptJoinDTO(
					rs.getString("name"),       // 결합된 이름
					rs.getString("dname"),      // 부서명
					rs.getDouble("YSal"),       // 연봉
					rs.getInt("Lid")            // 지역 ID
				);
				list.add(dto);                 // 리스트에 DTO 추가
				System.out.println(dto);      // 각 DTO 정보 출력
			}

			// 8. 자원 해제
			rs.close();
			pStmt.close();
			con.close();
			scanner.close();

		} catch (Exception e) {
			// 예외 발생 시 에러 메시지 출력
			System.out.println("DB 연결 또는 SQL 실행 중 오류 발생");
			e.printStackTrace();
		}
		// 전체 리스트 출력
		System.out.println("DB 연결 종료");
		System.out.println("=====================================");
		for (EmpDeptJoinDTO emp : list) {
			System.out.println(emp); // toString 자동 호출
		}

		// 9. 전체 리스트 디버깅용 출력
		System.out.println("========================================================");
		System.out.println("전체 결과 리스트:");
		System.out.println(list.toString());
	}
}
