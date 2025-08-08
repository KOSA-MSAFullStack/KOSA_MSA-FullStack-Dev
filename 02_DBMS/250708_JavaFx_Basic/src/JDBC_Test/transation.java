package JDBC_Test;

//import java.io.BufferedReader;
//import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class transation {
	/*
	 * DELETE문으로 레코드 삭제하기
	 */
	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;

		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521/xepdb1";
		String uid = "hr";
		String pwd = "hr";
		Scanner scan = new Scanner(System.in);
		try {

			System.out.println("부서 테이블 레코드 삭제");
			System.out.println("삭제할 부서 번호 입력=>");
			int num = Integer.parseInt(scan.next());

			Class.forName(driver);
			con = DriverManager.getConnection(url, uid, pwd);
			con.setAutoCommit(false);

			sql = "select DEPARTMENT_ID from HR.DEPARTMENTS where DEPARTMENT_ID = ? ";
			// 번호를 기준으로 디비로 부터 번호 검색
			// 번호를 기준으로 디비로 부터 번호값 검색
			pstmt = con.prepareStatement(sql);// 쿼리문 실행 객체 생성
			pstmt.setInt(1, num);// 첫번째 물음표에 번호값을 저장
			rs = pstmt.executeQuery();// 검색 select문 실행

			if (rs.next()) {
				sql = "delete from HR.DEPARTMENTS  where DEPARTMENT_ID=?";;
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, num);
				int re = pstmt.executeUpdate();
				// 수정,삭제,저장은 executeUpdate()
				// 메서드를 사용. 삭제 성공시 정수형 1 반환

				if (re == 1) {
					con.commit();
					con.close();
					System.out.println("레코드가 삭제되었습니다.");
					
				} else {
					System.out.println("레코드 삭제 실패~");
					con.rollback();
				} // end if
			} else {
				System.out.println("해당 번호가 없어서 삭제할 수 없습니다.");
			} // end if
			scan.close();
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		} // end tri

	}// end main
}// end class
