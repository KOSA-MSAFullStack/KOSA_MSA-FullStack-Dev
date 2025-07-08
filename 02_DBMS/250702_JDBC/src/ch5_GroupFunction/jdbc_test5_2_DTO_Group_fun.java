package ch5_GroupFunction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

// 입사년도별/월별 급여 통계를 저장하는 DTO 클래스
class HireStatsDTO {
	private String hireYear;      // 입사 연도
	private String hireMonth;     // 입사 월
	private int avgSalary;        // 평균 급여 (반올림 정수)
	private double maxSalary;     // 최고 급여
	private double minSalary;     // 최저 급여
	private double totalSalary;   // 총 급여

	// 생성자
	public HireStatsDTO(String hireYear, String hireMonth, int avgSalary, double maxSalary, double minSalary, double totalSalary) {
		this.hireYear = hireYear;
		this.hireMonth = hireMonth;
		this.avgSalary = avgSalary;
		this.maxSalary = maxSalary;
		this.minSalary = minSalary;
		this.totalSalary = totalSalary;
	}

	// Getter 메서드
	public String getHireYear() { return hireYear; }
	public String getHireMonth() { return hireMonth; }
	public int getAvgSalary() { return avgSalary; }
	public double getMaxSalary() { return maxSalary; }
	public double getMinSalary() { return minSalary; }
	public double getTotalSalary() { return totalSalary; }

	// 출력용 toString 메서드
	@Override
	public String toString() {
		return hireYear + "-" + hireMonth + " | 평균: " + avgSalary +
		       " | 최고: " + maxSalary + " | 최저: " + minSalary +
		       " | 합계: " + totalSalary;
	}
} // end HireStatsDTO

// 메인 클래스
public class jdbc_test5_2_DTO_Group_fun {
	public static void main(String[] args) {
		// JDBC 접속 정보
		final String sql;
		final String driver = "oracle.jdbc.driver.OracleDriver";
		final String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		final String uid = "hr"; // 사용자 ID
		final String pwd = "hr"; // 비밀번호

		// 사용자로부터 조회할 입사 연도 입력 받기
		Scanner scanner = new Scanner(System.in);
		System.out.print("조회할 입사 연도(예: 1987~2000): ");
		String inputYear = scanner.next(); // 예: "2005"

		// 결과 저장용 리스트
		List<HireStatsDTO> statsList = new ArrayList<>();
		HireStatsDTO dto = null;

		try {
			// JDBC 드라이버 로드
			Class.forName(driver);

			// DB 연결
			Connection con = DriverManager.getConnection(url, uid, pwd);

			// SQL 정의: 연도와 월별로 그룹화하여 급여 통계 조회
			sql = "SELECT TO_CHAR(hire_date, 'YYYY') AS hire_year, " +
				      "TO_CHAR(hire_date, 'MM') AS hire_month, " +
				      "ROUND(AVG(salary)) AS avg_salary, " +
				      "MAX(salary) AS max_salary, " +
				      "MIN(salary) AS min_salary, " +
				      "SUM(salary) AS total_salary " +
			      "FROM hr.employees " +
			      "WHERE TO_CHAR(hire_date, 'YYYY') = ? " + // 사용자 입력 연도 조건
			      "GROUP BY TO_CHAR(hire_date, 'YYYY'), TO_CHAR(hire_date, 'MM') " +
			      "ORDER BY hire_year ASC, hire_month ASC";

			// PreparedStatement 생성 및 입력값 바인딩
			PreparedStatement pStmt = con.prepareStatement(sql);
			pStmt.setString(1, inputYear); // 연도 입력값 바인딩

			// SQL 실행
			ResultSet rs = pStmt.executeQuery();

			// 결과 출력
			System.out.println("[" + inputYear + "]년도 입사자 월별 급여 통계:");
			System.out.println("===================================================");

			// 결과 처리: DTO로 변환 후 리스트에 저장
			while (rs.next()) {
				dto = new HireStatsDTO(
					rs.getString("hire_year"),
					rs.getString("hire_month"),
					rs.getInt("avg_salary"),
					rs.getDouble("max_salary"),
					rs.getDouble("min_salary"),
					rs.getDouble("total_salary")
				);
				statsList.add(dto);             // 리스트에 추가
				System.out.println(dto);       // 한 줄 출력
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
		System.out.println("=====================================");
		for (HireStatsDTO emp : statsList) {
			System.out.println(emp); // toString 자동 호출
		}
		// 리스트 전체를 한 번에 출력 (디버깅용)
		System.out.println("===================================================");
		System.out.println("전체 통계 (toString):");
		System.out.println(statsList.toString());
	}
}
