package ch18_0_BuildingDBApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

// 사원 정보를 저장하는 DTO 클래스
class EmployeeSalDTO4 {
	private String name;         // 사원의 전체 이름 (last_name + first_name)
	private double annualSalary; // 연봉 (월급 * 12)
	private String lname;        // 성 (대문자로 변환된 last_name)

	// 생성자
	public EmployeeSalDTO4(String name, double annualSalary, String lname) {
		this.name = name;
		this.annualSalary = annualSalary;
		this.lname = lname;
	}

	// getter 메서드
	public String getName() { return name; }
	public double getAnnualSalary() { return annualSalary; }
	public String getLname() { return lname; }

	// 객체를 문자열로 출력하는 메서드
	@Override
	public String toString() {
		return name + " | " + annualSalary + " | " + lname;
	}
} // end EmployeeSalDTO4

// 메인 클래스
public class jdbc_test4_1_DTO_fun1 {
	public static void main(String[] args) {
		final String sql; // SQL문 선언
		final String driver = "oracle.jdbc.driver.OracleDriver"; // 오라클 JDBC 드라이버
		final String url = "jdbc:oracle:thin:@localhost:1521/xepdb1"; // 오라클 DB URL
		final String uid = "hr"; // 사용자 아이디
		final String pwd = "hr"; // 비밀번호

		// 사용자 입력을 받기 위한 Scanner 객체 생성
		Scanner scanner = new Scanner(System.in);
		System.out.print("검색할 성(last_name)을 입력하세요: ");
		String inputLastName = scanner.next(); // 사용자로부터 성 입력 받기

		// 결과를 저장할 리스트
		List<EmployeeSalDTO4> empList = new ArrayList<>();
		EmployeeSalDTO4 employeeSalDTO4 = null;

		try {
			// JDBC 드라이버 로드
			Class.forName(driver);

			// 데이터베이스 연결
			Connection con = DriverManager.getConnection(url, uid, pwd);

			// 실행할 SQL 문 정의
			sql = "SELECT last_name || ' ' || first_name AS name, " +
			      "salary * 12 AS AnnualSal, " +
			      "UPPER(last_name) AS lname " +
			      "FROM hr.employees " +
			      "WHERE UPPER(last_name) = UPPER(?)";			// ('?': x) --> (?: o)

			// PreparedStatement 객체 생성 및 사용자 입력값 바인딩
			PreparedStatement pStmt = con.prepareStatement(sql);
			pStmt.setString(1, inputLastName); // 대소문자 구분 없이 비교

			// SQL 실행 및 결과 받아오기
			ResultSet rs = pStmt.executeQuery();

			System.out.println("DB에서 값 가져오면서 출력");
			System.out.println("=====================================");

			// 결과셋에서 행을 하나씩 읽어 DTO 객체로 변환하여 리스트에 저장
			while (rs.next()) {
				employeeSalDTO4 = new EmployeeSalDTO4(
					rs.getString("name"),       // 이름
					rs.getDouble("AnnualSal"),  // 연봉
					rs.getString("lname")       // 대문자 성
				);
				empList.add(employeeSalDTO4); // 리스트에 추가
				System.out.println(employeeSalDTO4.toString()); // 화면 출력
			}

			// 자원 해제
			rs.close();
			pStmt.close();
			con.close();
			scanner.close(); // Scanner 객체도 닫기

		} catch (Exception e) {
			System.out.println("DB 연결에 문제가 있습니다.");
			e.printStackTrace(); // 예외 정보 출력
		}

		// 전체 결과 출력
		System.out.println("DB 연결 종료");
		System.out.println("=====================================");
		for (EmployeeSalDTO4 emp : empList) {
			System.out.println(emp); // 각 사원 정보 출력
		}

		// 리스트 전체를 toString()으로 출력
		System.out.println("toString");
		System.out.println(empList.toString());
	}
}
