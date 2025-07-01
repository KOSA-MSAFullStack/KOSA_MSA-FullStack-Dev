package ch_SingleRowFunction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

// 사원 정보를 저장하는 DTO 클래스
class EmployeeSalDTO8 {
	private String lastName;        // 성
	private String jobId;           // 직무 ID
	private double salary;          // 급여
	private String qualifiedSalary; // 급여 등급 (LOW, MID, HIGH, Excellent)

	// 생성자
	public EmployeeSalDTO8(String lastName, String jobId, double salary, String qualifiedSalary) {
		this.lastName = lastName;
		this.jobId = jobId;
		this.salary = salary;
		this.qualifiedSalary = qualifiedSalary;
	}

	// Getter 메서드
	public String getLastName() { return lastName; }
	public String getJobId() { return jobId; }
	public double getSalary() { return salary; }
	public String getQualifiedSalary() { return qualifiedSalary; }

	// toString 메서드
	@Override
	public String toString() {
		return lastName + " | " + jobId + " | " + salary + " | " + qualifiedSalary;
	}
} // end EmployeeSalDTO8

// 메인 클래스
public class jdbc_test4_5_DTO_fun_case_where {
	public static void main(String[] args) {
		final String sql;
		final String driver = "oracle.jdbc.driver.OracleDriver";
		final String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		final String uid = "hr";
		final String pwd = "hr";

		// 사용자로부터 급여 등급 입력 받기
		Scanner scanner = new Scanner(System.in);
		System.out.print("조회할 급여 등급을 입력하세요 (LOW, MID, HIGH, EXCELLENT): ");
		String inputGrade = scanner.next(); // 예: MID

		List<EmployeeSalDTO8> empList = new ArrayList<>();
		EmployeeSalDTO8 EmployeeSalDTO8 = null;

		try {
			// 드라이버 로드 및 DB 연결
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, uid, pwd);

			// SQL 정의: 급여에 따라 등급 계산 후 필터링
			sql = "SELECT last_name, job_id, salary, " +
				      "(CASE " +
					      "WHEN salary < 5000 THEN 'LOW' " +
					      "WHEN salary < 10000 THEN 'MID' " +
					      "WHEN salary < 20000 THEN 'HIGH' " +
					      "ELSE 'Excellent' " +
					      "END) AS qualified_salary " +
			      "FROM hr.employees " +
			      "WHERE " +
				      "(CASE " +
					      "WHEN salary < 5000 THEN 'LOW' " +
					      "WHEN salary < 10000 THEN 'MID' " +
					      "WHEN salary < 20000 THEN 'HIGH' " +
					      "ELSE 'Excellent' " +
					      "END) = UPPER(?)";

			PreparedStatement pStmt = con.prepareStatement(sql);
			pStmt.setString(1, inputGrade); // 사용자 입력 값 바인딩

			ResultSet rs = pStmt.executeQuery();

			System.out.println("급여 등급 '" + inputGrade.toUpperCase() + "' 사원 목록:");
			System.out.println("==================================================");

			while (rs.next()) {
				EmployeeSalDTO8 = new EmployeeSalDTO8(
					rs.getString("last_name"),
					rs.getString("job_id"),
					rs.getDouble("salary"),
					rs.getString("qualified_salary")
				);
				empList.add(EmployeeSalDTO8);
				System.out.println(EmployeeSalDTO8.toString());
			}

			// 자원 해제
			rs.close();
			pStmt.close();
			con.close();
			scanner.close();

		} catch (Exception e) {
			System.out.println("DB 연결에 문제가 있습니다.");
			e.printStackTrace();
		}

		// 전체 리스트 출력
		System.out.println("DB 연결 종료");
		System.out.println("==================================================");
		for (EmployeeSalDTO8 emp : empList) {
			System.out.println(emp);
		}

		System.out.println("toString");
		System.out.println(empList.toString());
	}
}
