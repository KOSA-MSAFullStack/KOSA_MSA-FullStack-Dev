package ch_SingleRowFunction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

// 사원 정보를 저장하는 DTO 클래스
class EmployeeSalDTO7 {
	private String lastName;       // 성
	private String jobId;          // 직무 ID
	private double salary;         // 원래 급여
	private String label;          // 직무 ID 앞 2자리 (대문자)
	private double revisedSalary;  // 수정된 급여

	// 생성자
	public EmployeeSalDTO7(String lastName, String jobId, double salary, String label, double revisedSalary) {
		this.lastName = lastName;
		this.jobId = jobId;
		this.salary = salary;
		this.label = label;
		this.revisedSalary = revisedSalary;
	}

	// Getter 메서드
	public String getLastName() { return lastName; }
	public String getJobId() { return jobId; }
	public double getSalary() { return salary; }
	public String getLabel() { return label; }
	public double getRevisedSalary() { return revisedSalary; }

	// toString 메서드
	@Override
	public String toString() {
		return lastName + " | " + jobId + " | " + salary + " | " + label + " | " + revisedSalary;
	}
}

// 메인 클래스
public class jdbc_test4_4_DTO_fun_case {
	public static void main(String[] args) {
		final String sql;
		final String driver = "oracle.jdbc.driver.OracleDriver";
		final String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		final String uid = "hr";
		final String pwd = "hr";

		// 사용자로부터 job_id 앞 2자리 입력 받기
		Scanner scanner = new Scanner(System.in);
		System.out.print("조회할 직무 ID 앞 2자리를 입력하세요 (예: sa, it): ");
		String inputLabel = scanner.next(); // 예: "sa"

		// 결과 저장할 리스트 생성
		List<EmployeeSalDTO7> empList = new ArrayList<>();
		EmployeeSalDTO7 EmployeeSalDTO7 = null;

		try {
			// 드라이버 로드 및 DB 연결
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, uid, pwd);

			// SQL 문 정의
			sql = "SELECT last_name, job_id, salary, " +
				      "UPPER(SUBSTR(job_id, 1, 2)) AS label, " +
				      "(CASE job_id " +
					      "WHEN 'IT_PROG' THEN 1.10 * salary " +
					      "WHEN 'ST_CLERK' THEN 1.15 * salary " +
					      "WHEN 'SA_REP' THEN 1.20 * salary " +
					      "ELSE salary END) AS REVISED_SALARY " +
			      "FROM hr.employees " +
			      "WHERE UPPER(SUBSTR(job_id, 1, 2)) = UPPER(?)";

			PreparedStatement pStmt = con.prepareStatement(sql);
			pStmt.setString(1, inputLabel); // 입력값 바인딩

			ResultSet rs = pStmt.executeQuery();

			System.out.println("직무 앞 코드 '" + inputLabel.toUpperCase() + "'인 사원 목록:");
			System.out.println("=======================================================");

			while (rs.next()) {
				EmployeeSalDTO7 = new EmployeeSalDTO7(
					rs.getString("last_name"),
					rs.getString("job_id"),
					rs.getDouble("salary"),
					rs.getString("label"),
					rs.getDouble("REVISED_SALARY")
				);
				empList.add(EmployeeSalDTO7);
				System.out.println(EmployeeSalDTO7.toString());
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
		System.out.println("=======================================================");
		for (EmployeeSalDTO7 emp : empList) {
			System.out.println(emp);
		}

		// 디버깅용 전체 리스트 출력
		System.out.println("toString");
		System.out.println(empList.toString());
	}
}
