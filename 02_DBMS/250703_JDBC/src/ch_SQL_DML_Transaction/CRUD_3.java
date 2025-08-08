package ch_SQL_DML_Transaction;

import java.util.Scanner;//사용자로부터 입력을 받기위한 기능 추가
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class CRUD_3 {
	public static void main(String[] args) {
		
		PreparedStatement pstmt = null;// 2. 쿼리문 실행 참조변수 선언
		ResultSet rs = null;
		String sql = null; // sql문을 저장하는 변수
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		String uid = "hr";
		String pwd = "hr";
		int department_id ; // 데이터베이스 값 저장할 변수
		String  department_name, manager_id,location_id; //// 데이터베이스 값 저장할 변수	
		int DEPARTMENT_ID=0 ; // 데이터베이스 값 저장할 변수
	
		Scanner scan = new Scanner(System.in);

		try(Connection con =  DriverManager.getConnection(url, uid, pwd); 		) 
		{
			System.out.println("dept 테이블 수정하기");
			System.out.println("수정할 기준이 되는 부서번호 입력=>");
			int num = Integer.parseInt(scan.next());
			// parseInt() 정적메서드는 정수형 숫자로 바꾼다.
			sql = "select DEPARTMENT_ID from HR.DEPARTMENTS where DEPARTMENT_ID = ? ";
			// 번호를 기준으로 디비로 부터 번호값 검색
			pstmt = con.prepareStatement(sql);// 쿼리문 실행 객체 생성
			pstmt.setInt(1, num);
			// 첫번째 물음표에 번호값을 저장
			rs = pstmt.executeQuery();// 검색 select문 실행

			if (rs.next()) {// 검색 번호값이 있을 경우 수정
				System.out.println("수정할 부서이름 입력=>");
				String DEPARTMENT_NAME = scan.next();
				System.out.println("수정할 부서지역 입력=>");
				String LOCATION_ID = scan.next();
				sql = " update HR.DEPARTMENTS "
						+ " set DEPARTMENT_NAME = ? , "
						+ " LOCATION_ID = ? "
						+ "  where DEPARTMENT_ID = ? ";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, DEPARTMENT_NAME);// 1번물음표에 문자열로 이름 저장
				pstmt.setString(2, LOCATION_ID);
				pstmt.setInt(3, num);// 정수형 숫자번호값을 저장
				int re = pstmt.executeUpdate();// 수정문 실행
				if (re == 1) { // 성공시 1 을 반환
					System.out.println("레코드가 수정 되었습니다.");
					// sql문 작성
					String query = "select * from HR.DEPARTMENTS";
					Statement stmt = con.createStatement();
					rs = stmt.executeQuery(query);
					while (rs.next()) { // 레코드가 있으면 반복, 없으면 중단
						department_id = rs.getInt("department_id"); // 첫번째 필드 가져옴
						department_name = rs.getString("department_name"); // 두번째 필드 가져옴
						manager_id = rs.getString("manager_id"); // 세번째 필드 가져옴
						location_id = rs.getString("location_id"); // 네번째 필드 가져옴
						System.out.println(department_id + " " + department_name +
								" " + manager_id + " " + location_id);
					} // end while	
				} else {
					System.out.println("업데이트 실패~");
				} // end if
			} else {
				System.out.println("번호값이 없어서 수정할 수 없습니다.");
			} // end if
		
			System.out.println(driver);
			System.out.println(DEPARTMENT_ID);
			scan.close();
		} catch (Exception e) {
			e.printStackTrace();
		}//end try
	}//end main
}//end class
