package ch18_0_BuildingDBApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

// 사원 정보를 담는 DTO 클래스
class EmployeeSalDTO2 {
	private String name; // 사원이름 (성 + 이름)
	private double annualSalary; // 연봉 (salary * 12)
	private int departmentId; // 부서 ID

	// 생성자
	public EmployeeSalDTO2(String name, double annualSalary, int departmentId) {
		this.name = name;
		this.annualSalary = annualSalary;
		this.departmentId = departmentId;
	}

	// getter 메서드
	public String getName() { return name; }
	public double getAnnualSalary() { return annualSalary; }
	public int getDepartmentId() { return departmentId; }

	// 객체를 문자열로 표현하는 메서드 (출력용)
	@Override
	public String toString() {
		return name + " | " + annualSalary + " | " + departmentId;
	}
} // end EmployeeSalDTO2

public class jdbc_test3_5_DTO_where {
	public static void main(String[] args) {
		// JDBC 접속 및 쿼리 실행을 위한 기본 설정
		final String sql;
		final String driver = "oracle.jdbc.driver.OracleDriver";
		final String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		final String uid = "hr";
		final String pwd = "hr";

		// 사원 DTO 리스트 생성
		List<EmployeeSalDTO2> empList = new ArrayList<>();
		EmployeeSalDTO2 EmployeeSalDTO2 = null;

		try {
			// JDBC 드라이버 로드
			Class.forName(driver);

			// DB 연결
			Connection con = DriverManager.getConnection(url, uid, pwd);

			// 연봉이 6000 이상인 사원만 조회하는 SQL문 정의
			sql = "SELECT last_name || ' ' || first_name AS name, " +
			      "salary * 12 AS AnnualSal, " +
			      "department_id AS deptid " +
			      "FROM hr.employees " +
			      "WHERE (salary * 12) > ?";

			// SQL 실행용 PreparedStatement 객체 생성
			PreparedStatement pStmt = con.prepareStatement(sql);
			pStmt.setInt(1, 6000); // ? 자리에 6000 설정 (연봉 필터 조건)

			// SQL 실행 및 결과 받아오기
			ResultSet rs = pStmt.executeQuery();

			System.out.println("DB에서 값 가져오면서 출력");
			System.out.println("=====================================");

			// ResultSet을 순회하면서 DTO 객체로 변환하고 리스트에 추가
			while (rs.next()) {
				EmployeeSalDTO2 = new EmployeeSalDTO2(
					rs.getString("name"),        // 이름
					rs.getDouble("AnnualSal"),   // 연봉
					rs.getInt("deptid")          // 부서 ID
				);
				empList.add(EmployeeSalDTO2);       // 리스트에 추가
				System.out.println(EmployeeSalDTO2.toString()); // 개별 출력
			}

			// 자원 해제 (ResultSet, Statement, Connection)
			rs.close();
			pStmt.close();
			con.close();

		} catch (Exception e) {
			System.out.println("DB 연결에 문제가 있습니다.");
			e.printStackTrace();
		}

		// DB 연결 종료 후 전체 리스트 출력
		System.out.println("DB 연결 종료");
		System.out.println("=====================================");
		for (EmployeeSalDTO2 emp : empList) {
			System.out.println(emp); // 한 줄씩 출력
		}

		// 리스트 전체를 toString 형식으로 출력 (리스트 객체 자체)
		System.out.println("toString");
		System.out.println(empList.toString());
	}
}
