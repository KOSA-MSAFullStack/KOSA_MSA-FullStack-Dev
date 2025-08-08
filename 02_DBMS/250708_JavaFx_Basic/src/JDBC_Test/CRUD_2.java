package JDBC_Test;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class CRUD_2 {
	public static void main(String[] args) {

		PreparedStatement pstmt = null;// 쿼리문 실행 참조변수 선언
		String sql = null; // sql문을 저장하는 변수
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		String uid = "hr";
		String pwd = "hr";
		ResultSet rs = null;
		String DEPARTMENT_ID ; // 데이터베이스 값 저장할 변수
		String DEPARTMENT_NAME , MANAGER_ID , LOCATION_ID ; //// 데이터베이스 값 저장할 변수
		Scanner scan = new Scanner(System.in);

		// 드라이브 로딩과 db 연결은 try ~ catch 블럭으로 감싼다.
		///try -with-resources Construct
		try (Connection con = DriverManager.getConnection(url, uid, pwd))// 2. db 연결
		{
			sql = " insert into hr.departments " 
					+ " (DEPARTMENT_ID,DEPARTMENT_NAME,MANAGER_ID,LOCATION_ID) "
					+ " values( ?, ?, ?, ?) "; // 3. 쿼리문 먼저 작성
			pstmt = con.prepareStatement(sql);

			System.out.print("부서번호를 입력하세요");
			DEPARTMENT_ID = scan.next();
			pstmt.setString(1, DEPARTMENT_ID);

			System.out.print("부서이름를 입력하세요 ");
			DEPARTMENT_NAME = scan.next();
			pstmt.setString(2, DEPARTMENT_NAME);

			System.out.print("매니저 번호를 입력하세요 ");
			MANAGER_ID = scan.next();
			pstmt.setString(3, MANAGER_ID);

			System.out.print("지역 번호를 입력하세요 ");
			LOCATION_ID = scan.next();
			pstmt.setString(4, LOCATION_ID);
			 // insert문 수행

			if (pstmt.executeUpdate() > 0) { //insert 성공
				System.out.println("새로운 레코드가 추가 되었습니다.");	
				// sql문 작성
				String query = "select * from HR.DEPARTMENTS";
				Statement stmt = con.createStatement();
				rs = stmt.executeQuery(query);
				while (rs.next()) { // 레코드가 있으면 반복, 없으면 중단
					DEPARTMENT_ID = rs.getString("department_id"); // 첫번째 필드 가져옴
					DEPARTMENT_NAME = rs.getString("department_name"); // 두번째 필드 가져옴
					MANAGER_ID = rs.getString("manager_id"); // 세번째 필드 가져옴
					LOCATION_ID = rs.getString("location_id"); // 네번째 필드 가져옴
					System.out.println(DEPARTMENT_ID + " " + DEPARTMENT_NAME +
							" " + MANAGER_ID + " " + LOCATION_ID);
				} // end while	
				
			}//end if	
			
			System.out.println(driver);
			scan.close();
		} catch (Exception e) {
			System.out.println("DB insert에 문제가 있습니다.");
			e.printStackTrace();
		} // end try
	}// end main
}// end class
