package ch5_GroupFunction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

// 메인 클래스
public class jdbc_test5_1_DTO_Group_fun {
	public static void main(String[] args) {
		final String sql; // SQL문을 저장할 변수
		final String driver = "oracle.jdbc.driver.OracleDriver"; // 오라클 JDBC 드라이버 클래스
		final String url = "jdbc:oracle:thin:@localhost:1521/xepdb1"; // 데이터베이스 연결 URL
		final String uid = "hr"; // 사용자 아이디
		final String pwd = "hr"; // 비밀번호
		int avgSalary =  0;       // 평균 급여
		double maxSalary = 0;   // 최고 급여
		double minSalary =   0; // 최저 급여
		double totalSalary = 0;

		// 사용자로부터 부서번호를 입력 받기 위한 Scanner 생성
		Scanner scanner = new Scanner(System.in);
		System.out.print("조회할 부서번호를 입력하세요 (예: 90): ");
		int deptId = scanner.nextInt(); // 입력된 부서번호 저장

		try {
			// JDBC 드라이버 로드
			Class.forName(driver);

			// 데이터베이스에 연결
			Connection con = DriverManager.getConnection(url, uid, pwd);

			// 부서별 평균, 최대, 최소, 합계 급여를 구하는 SQL 정의
			sql = "SELECT " +
				      "ROUND(AVG(salary)) AS avg_salary, " +    // 평균 급여 (반올림)
				      "MAX(salary) AS max_salary, " +           // 최대 급여
				      "MIN(salary) AS min_salary, " +           // 최소 급여
				      "SUM(salary) AS total_salary " +          // 급여 합계
			      "FROM hr.employees " +
			      "WHERE department_id = ?";                // 특정 부서번호에 대해 필터링

			// PreparedStatement를 통해 SQL 실행 준비
			PreparedStatement pStmt = con.prepareStatement(sql);
			pStmt.setInt(1, deptId); // ? 자리에 사용자가 입력한 부서번호 바인딩

			// SQL 실행 및 결과 가져오기
			ResultSet rs = pStmt.executeQuery();

			// 결과 출력 시작
			System.out.println("부서번호 " + deptId + "의 급여 통계:");
			System.out.println("============================================");

			// 결과가 존재할 경우 출력
			if (rs.next()) {
				avgSalary = rs.getInt("avg_salary");         // 평균 급여
				maxSalary = rs.getDouble("max_salary");   // 최고 급여
				minSalary = rs.getDouble("min_salary");   // 최저 급여
				totalSalary = rs.getDouble("total_salary"); // 총 급여

				// 출력
				System.out.println("평균 급여 : " + avgSalary);
				System.out.println("최고 급여 : " + maxSalary);
				System.out.println("최저 급여 : " + minSalary);
				System.out.println("급여 합계 : " + totalSalary);
			} else {
				// 부서에 해당하는 사원이 없는 경우
				System.out.println("해당 부서에 소속된 사원이 없습니다.");
			}

			// 사용한 자원들 해제 (순서 중요: ResultSet → PreparedStatement → Connection → Scanner)
			rs.close();
			pStmt.close();
			con.close();
			scanner.close();

		} catch (Exception e) {
			// 예외 발생 시 출력
			System.out.println("DB 연결에 문제가 있습니다.");
			e.printStackTrace(); // 상세한 에러 메시지 출력
		}
		// 전체 리스트 출력
		System.out.println("DB 연결 종료");	
		// 출력
		System.out.println("평균 급여 : " + avgSalary);
		System.out.println("최고 급여 : " + maxSalary);
		System.out.println("최저 급여 : " + minSalary);
		System.out.println("급여 합계 : " + totalSalary);
	}
}
