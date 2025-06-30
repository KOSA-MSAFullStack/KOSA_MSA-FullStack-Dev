package ch18_0_BuildingDBApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

// 사원 정보를 저장하는 DTO 클래스
class EmployeeSalDTO5 {
	private String name;         // 사원의 전체 이름 (last_name + first_name)
	private double annualSalary; // 연봉 (월급 * 12)
	private String hireDate;     // 입사일 (문자열 YYYY-MM-DD 형식)

	// 생성자
	public EmployeeSalDTO5(String name, double annualSalary, String hireDate) {
		this.name = name;
		this.annualSalary = annualSalary;
		this.hireDate = hireDate;
	}

	// getter 메서드
	public String getName() { return name; }
	public double getAnnualSalary() { return annualSalary; }
	public String getHireDate() { return hireDate; }

	// 객체를 문자열로 출력하는 메서드
	@Override
	public String toString() {
		return name + " | " + annualSalary + " | " + hireDate;
	}
} // end EmployeeSalDTO5

// 메인 클래스
public class jdbc_test4_2_DTO_fun {
	public static void main(String[] args) {
		// JDBC 관련 설정
		final String sql; // 실행할 SQL 문
		final String driver = "oracle.jdbc.driver.OracleDriver"; // Oracle JDBC 드라이버 클래스
		final String url = "jdbc:oracle:thin:@localhost:1521/xepdb1"; // 접속할 DB URL
		final String uid = "hr"; // 사용자명
		final String pwd = "hr"; // 비밀번호

		// 사용자로부터 입사년도 입력 받기
		Scanner scanner = new Scanner(System.in);
		System.out.print("조회할 입사년도(YYYY 이상)를 입력하세요: ");
		String inputYear = scanner.next(); // 예: 1995

		// 조회 결과를 담을 리스트 준비
		List<EmployeeSalDTO5> empList = new ArrayList<>();
		EmployeeSalDTO5 employeeSalDTO5 = null;

		try {
			// JDBC 드라이버 로드
			Class.forName(driver);

			// DB 연결
			Connection con = DriverManager.getConnection(url, uid, pwd);

			// 사용자 입력을 바인딩할 SQL문 정의
			sql = "SELECT last_name || ' ' || first_name AS name, " +
			      "salary * 12 AS AnnualSal, " +
			      "TO_CHAR(hire_date, 'YYYY-MM-DD') AS hdate " +
			      "FROM hr.employees " +
			      "WHERE TO_CHAR(hire_date, 'YYYY') >= ?";

			// PreparedStatement 객체 생성 및 사용자 입력 바인딩
			PreparedStatement pStmt = con.prepareStatement(sql);
			pStmt.setString(1, inputYear); // 입력된 연도 바인딩

			// SQL 실행 및 결과셋(ResultSet) 반환
			ResultSet rs = pStmt.executeQuery();

			// 출력 메시지
			System.out.println(inputYear + "년 이후 입사자 목록 출력");
			System.out.println("=====================================");

			// 결과셋을 순회하며 DTO에 저장 후 리스트에 추가
			while (rs.next()) {
				employeeSalDTO5 = new EmployeeSalDTO5(
					rs.getString("name"),        // 이름
					rs.getDouble("AnnualSal"),   // 연봉
					rs.getString("hdate")        // 입사일
				);
				empList.add(employeeSalDTO5);      // 리스트에 추가
				System.out.println(employeeSalDTO5.toString()); // 한 줄씩 출력
			}

			// 사용한 자원 해제
			rs.close();
			pStmt.close();
			con.close();
			scanner.close();

		} catch (Exception e) {
			System.out.println("DB 연결에 문제가 있습니다.");
			e.printStackTrace(); // 예외 정보 출력
		}

		// 전체 리스트 출력
		System.out.println("DB 연결 종료");
		System.out.println("=====================================");
		for (EmployeeSalDTO5 emp : empList) {
			System.out.println(emp); // toString 자동 호출
		}

		// 리스트 전체를 한 번에 출력 (디버깅용)
		System.out.println("toString");
		System.out.println(empList.toString());
	}
}
