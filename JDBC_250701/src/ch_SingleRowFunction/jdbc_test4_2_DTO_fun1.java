package ch_SingleRowFunction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.Date; // 입사일(hire_date)을 처리하기 위한 java.sql.Date 사용

// 사원 정보를 저장하는 DTO(Data Transfer Object) 클래스
class EmployeeSalDTO6 {
	private String name;           // 사원의 전체 이름 (성 + 이름)
	private double annualSalary;   // 연봉 (월급 * 12)
	private Date hireDate;         // 입사일
	private int departmentId;      // 부서 ID
	private String workingYears;   // 재직 기간 (예: "5년")

	// 생성자
	public EmployeeSalDTO6(String name, double annualSalary, Date hireDate, int departmentId, String workingYears) {
		this.name = name;
		this.annualSalary = annualSalary;
		this.hireDate = hireDate;
		this.departmentId = departmentId;
		this.workingYears = workingYears;
	}

	// Getter 메서드들
	public String getName() { return name; }
	public double getAnnualSalary() { return annualSalary; }
	public Date getHireDate() { return hireDate; }
	public int getDepartmentId() { return departmentId; }
	public String getWorkingYears() { return workingYears; }

	// 객체를 문자열로 표현 (출력용)
	@Override
	public String toString() {
		return name + " | " + annualSalary + " | " + hireDate + " | " + departmentId + " | " + workingYears;
	}
} // end EmployeeSalDTO6

// 메인 실행 클래스
public class jdbc_test4_2_DTO_fun1 {
	public static void main(String[] args) {
		// DB 연결을 위한 설정 정보
		final String sql; // SQL 문장 변수 선언
		final String driver = "oracle.jdbc.driver.OracleDriver"; // Oracle JDBC 드라이버 클래스명
		final String url = "jdbc:oracle:thin:@localhost:1521/xepdb1"; // DB 접속 URL
		final String uid = "hr"; // 사용자 계정
		final String pwd = "hr"; // 비밀번호

		// 사용자로부터 부서번호 입력 받기
		Scanner scanner = new Scanner(System.in);
		System.out.print("조회할 부서번호(department_id)를 입력하세요: ");
		int inputDept = scanner.nextInt(); // 예: 90

		// 결과를 저장할 리스트 생성
		List<EmployeeSalDTO6> empList = new ArrayList<>();
		EmployeeSalDTO6 employeeSalDTO6 = null;

		try {
			// JDBC 드라이버 로드
			Class.forName(driver);

			// DB 연결
			Connection con = DriverManager.getConnection(url, uid, pwd);

			// SQL문 정의 (부서번호로 필터링, 재직기간 계산 포함)
			sql = "SELECT last_name || ' ' || first_name AS name, " +
			      "salary * 12 AS AnnualSal, " +
			      "hire_date AS hire_date, " +
			      "department_id, " +
			      "ROUND(MONTHS_BETWEEN(SYSDATE, hire_date) / 12) || '년' 재직기간 " +
			      "FROM hr.employees " +
			      "WHERE department_id = ?";

			// PreparedStatement 생성 및 사용자 입력값 바인딩
			PreparedStatement pStmt = con.prepareStatement(sql);
			pStmt.setInt(1, inputDept); // ? 자리에 부서번호 값 설정

			// SQL 실행 후 결과 집합(ResultSet) 가져오기
			ResultSet rs = pStmt.executeQuery();

			// 출력 시작
			System.out.println("부서번호 " + inputDept + "의 사원 목록 출력");
			System.out.println("==================================================");

			// 결과 집합을 한 행씩 읽어 DTO 객체로 변환 후 리스트에 추가
			while (rs.next()) {
				employeeSalDTO6 = new EmployeeSalDTO6(
					rs.getString("name"),             // 전체 이름
					rs.getDouble("AnnualSal"),        // 연봉
					rs.getDate("hire_date"),          // 입사일 (java.sql.Date)
					rs.getInt("department_id"),       // 부서번호
					rs.getString("재직기간")           // 재직기간 (문자열)
				);
				empList.add(employeeSalDTO6);        // 리스트에 추가
				System.out.println(employeeSalDTO6.toString()); // 즉시 출력
			}

			// 사용한 리소스 닫기
			rs.close();
			pStmt.close();
			con.close();
			scanner.close();

		} catch (Exception e) {
			System.out.println("DB 연결에 문제가 있습니다.");
			e.printStackTrace(); // 에러 로그 출력
		}

		// 전체 결과 출력 (반복문)
		System.out.println("DB 연결 종료");
		System.out.println("==================================================");
		for (EmployeeSalDTO6 emp : empList) {
			System.out.println(emp); // toString() 자동 호출
		}

		// 리스트 전체 출력 (디버깅용)
		System.out.println("toString");
		System.out.println(empList.toString());
		System.out.println(empList.get(0).getHireDate().toString());
	}
}
