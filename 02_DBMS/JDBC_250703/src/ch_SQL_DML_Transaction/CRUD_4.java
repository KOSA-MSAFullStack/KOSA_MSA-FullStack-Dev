package ch_SQL_DML_Transaction;

import java.util.Scanner;//사용자로부터 입력을 받기위한 기능 추가
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class CRUD_4 {
	public static void main(String[] args) {
		Connection con = null; // 1. 오라클 데이터베이스 연결 참조변수 선언
		PreparedStatement pstmt = null;// 2. 쿼리문 실행 참조변수 선언
		String sql = null; // sql문을 저장하는 변수
		ResultSet rs = null;
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		String uid = "hr";
		String pwd = "hr";
		String DEPARTMENT_ID = null; // 데이터베이스 값 저장할 변수
		String DEPARTMENT_NAME = null, MANAGER_ID = null, LOCATION_ID = null; //// 데이터베이스 값 저장할 변수
		Scanner scan = new Scanner(System.in);

		try {
			System.out.println("dept 테이블 삭제하기");
			System.out.println("삭제기준이 되는 부서번호 입력=>");
			int num = Integer.parseInt(scan.next());
			// parseInt() 정적메서드는 정수형 숫자로 바꾼다.

			Class.forName(driver); // 오라클 드라이버 로딩
			con = DriverManager.getConnection(url, uid, pwd);
			sql = "select DEPARTMENT_ID from HR.DEPARTMENTS where DEPARTMENT_ID = ? ";
			// 번호를 기준으로 디비로 부터 번호값 검색
			pstmt = con.prepareStatement(sql);// 쿼리문 실행 객체 생성
			pstmt.setInt(1, num);// 첫번째 물음표에 번호값을 저장
			rs = pstmt.executeQuery();// 검색 select문 실행

			if (rs.next()) {// 검색 번호값이 있을 경우 수정
				sql = "delete from HR.DEPARTMENTS  where DEPARTMENT_ID=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, num);// 1번물음표에 문자열로 이름 저장
				int re = pstmt.executeUpdate();// 수정문 실행

				if (re == 1) { // 성공시 1 을 반환
					System.out.println("레코드가 삭제 되었습니다.");
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
					
				} else {
					System.out.println("삭제 실패~");
				}//end if
			} else {
				System.out.println("번호값이 없어서 삭제할 수 없습니다.");
			}//end if
			rs.close();
			pstmt.close();
			con.close();
			scan.close();
		} catch (Exception e) {
			e.printStackTrace();
		} // end try
	}// end ain
}// end class
