package ch_VIEW;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// 데이터베이스 연결을 위한 싱글턴 클래스 정의
class DBConnection2 {
	private static DBConnection2 instance = new DBConnection2(); // 유일한 인스턴스 생성
	private final String driver = "oracle.jdbc.driver.OracleDriver"; // 드라이버 클래스명
	private final String url = "jdbc:oracle:thin:@localhost:1521/xepdb1"; // 접속 URL
	private final String uid = "hr"; // 사용자 계정
	private final String pwd = "hr"; // 비밀번호

	// 생성자는 private으로 외부에서 직접 생성하지 못하게 제한
	private DBConnection2() {
		try {
			Class.forName(driver); // 드라이버 로딩
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC 드라이버 로딩 실패.");
			e.printStackTrace();
		}
	}

	// 인스턴스를 외부에서 사용할 수 있게 반환
	public static DBConnection2 getInstance() {
		return instance;
	}

	// 데이터베이스 연결을 반환하는 메서드
	public Connection getConnection() {
		try {
			return DriverManager.getConnection(url, uid, pwd); // DB 연결 시도
		} catch (Exception e) {
			System.out.println("DB 연결 실패.");
			e.printStackTrace();
			return null;
		}
	}
}

// 사원 정보를 담는 DTO 클래스
class EmpViewDTO {
	private int employeeId;          // 사원 번호
	private String firstName;        // 이름
	private String departmentName;   // 부서명
	private String regionName;       // 지역명
	private String countryName;      // 국가명
	private String city;             // 도시명
	private int locationId;          // 위치 ID

	// 생성자
	public EmpViewDTO(int employeeId, String firstName, String departmentName,
					  String regionName, String countryName, String city, int locationId) {
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.departmentName = departmentName;
		this.regionName = regionName;
		this.countryName = countryName;
		this.city = city;
		this.locationId = locationId;
	}

	// getter 메서드들
	public int getEmployeeId() { return employeeId; }
	public String getFirstName() { return firstName; }
	public String getDepartmentName() { return departmentName; }
	public String getRegionName() { return regionName; }
	public String getCountryName() { return countryName; }
	public String getCity() { return city; }
	public int getLocationId() { return locationId; }

	// 객체를 문자열로 출력하기 위한 toString 오버라이딩
	@Override
	public String toString() {
		return "[ID: " + employeeId + "] " + firstName + " | 부서: " + departmentName +
			   " | 지역: " + regionName + " | 국가: " + countryName + 
			   " | 도시: " + city + " | 위치ID: " + locationId;
	}
}

// 메인 클래스
public class jdbc_test8_1_View_singleton {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in); // 사용자 입력을 받기 위한 스캐너 생성
		System.out.print("조회할 location_id 하한값 입력 (예: 1500): ");
		int locationInput = scanner.nextInt(); // 입력값을 정수로 받음

		List<EmpViewDTO> list = new ArrayList<>(); // 결과 저장용 리스트
		EmpViewDTO dto = null; // DTO 임시 객체

		// SQL 쿼리문: emp_details_view 뷰에서 필요한 컬럼만 선택
		String sql = "SELECT employee_id, first_name, department_name, region_name, " +
		             	"country_name, city, location_id " +
		             "FROM hr.emp_details_view " +
		             "WHERE location_id > ?";

		try (
			// 싱글턴 인스턴스를 통해 DB 연결 얻기
			Connection con = DBConnection2.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
		) {
			pstmt.setInt(1, locationInput); // 사용자가 입력한 location_id 바인딩
			ResultSet rs = pstmt.executeQuery(); // 쿼리 실행

			System.out.println("조회 결과:");
			System.out.println("==============================================================");

			// 결과 셋 처리
			while (rs.next()) {
				dto = new EmpViewDTO(
					rs.getInt("employee_id"),
					rs.getString("first_name"),
					rs.getString("department_name"),
					rs.getString("region_name"),
					rs.getString("country_name"),
					rs.getString("city"),
					rs.getInt("location_id")
				);
				list.add(dto); // 리스트에 추가
				System.out.println(dto); // 한 줄 출력 (toString 자동 호출)
			}

			rs.close(); // ResultSet 닫기
		} catch (Exception e) {
			System.out.println("오류 발생:");
			e.printStackTrace();
		} finally {
			scanner.close(); // Scanner 닫기
		}

		// 리스트 전체 출력
		System.out.println("==============================================================");
		System.out.println("DB 연결 종료");
		System.out.println("=====================================");
		for (EmpViewDTO emp : list) {
			System.out.println(emp); // toString 자동 호출
		}

		// 전체 리스트 디버깅용 출력
		System.out.println("========================================================");
		System.out.println("전체 결과 리스트:");
		System.out.println(list.toString());
	}
}
