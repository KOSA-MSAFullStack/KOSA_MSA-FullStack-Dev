package ch18_0_BuildingDBApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

// 사원 정보를 담는 DTO 클래스
class EmployeeSalDTO {
	private String name; 			// 사원이름 (성 + 이름)
	private double annualSalary; 	// 연봉 (salary * 12)
	private int departmentId; 		// 부서 ID

	// 생성자
	public EmployeeSalDTO(String name, double annualSalary, int departmentId) {
		this.name = name;
		this.annualSalary = annualSalary;
		this.departmentId = departmentId;
	}

	// getter 메서드
	public String getName() { return name; }
	public double getAnnualSalary() { return annualSalary; }
	public int getDepartmentId() { return departmentId; }

	// 객체 출력 형식 지정
	@Override
	public String toString() {
		return name + " | " + annualSalary + " | " + departmentId;
	}
}

public class jdbc_test3_4_DTO {
	public static void main(String[] args) {
		// JDBC 접속 및 쿼리 실행을 위한 기본 설정
		final String sql;
		final String driver = "oracle.jdbc.driver.OracleDriver";
		final String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		final String uid = "hr";
		final String pwd = "hr";

		// 사원 DTO 리스트 생성
		List<EmployeeSalDTO> empList = new ArrayList<>();
		EmployeeSalDTO EmployeeSalDTO = null;

		try {
			// JDBC 드라이버 로드
			Class.forName(driver);

			// DB 연결
			Connection con = DriverManager.getConnection(url, uid, pwd);

			// SQL 실행용 Statement 생성
			Statement stmt = con.createStatement();

			// 사원 정보 조회 SQL문
			sql = "SELECT last_name || ' ' || first_name AS name, " +
			      "salary * 12 AS AnnualSal, " +
			      "department_id AS deptid FROM hr.employees";

			// SQL 실행 및 결과 받아오기 - result set
			ResultSet rs = stmt.executeQuery(sql);

			System.out.println("DB에서 값 가져오면서 출력");
			System.out.println("=====================================");

			// ResultSet을 순회하면서 DTO에 담고 리스트에 추가
			while (rs.next()) {
				EmployeeSalDTO = new EmployeeSalDTO(
					rs.getString("name"),       // 이름
					rs.getDouble("AnnualSal"),  // 연봉
					rs.getInt("deptid")         // 부서 ID
				);
				empList.add(EmployeeSalDTO);      // 리스트에 추가
				System.out.println(EmployeeSalDTO.toString()); // 개별 출력
			}

			// 자원 해제 (ResultSet, Statement, Connection)
			rs.close();
			stmt.close();
			con.close();

		} catch (Exception e) {
			System.out.println("DB 연결에 문제가 있습니다.");
			e.printStackTrace();
		}

		// DB 연결 종료 후 전체 출력
		System.out.println("DB 연결 종료");
		System.out.println("=====================================");
		for (EmployeeSalDTO emp : empList) {
			System.out.println(emp);
		}

		// 리스트 전체를 toString으로 출력
		System.out.println("toString");
		System.out.println(empList.toString());
	}
}
