package JDBC_Test;

import java.sql.SQLException;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;

public class JdbcRowSet1 {

	public static void main(String[] args) {
		/**
		 * HR.DEPARTMENTS 테이블을 대상으로 데이터를 가져와서 콘솔에 출력하기
		 */

		final String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		final String userid = "hr";
		final String passwd = "hr";

		try (JdbcRowSet jdbcRs = RowSetProvider.newFactory().createJdbcRowSet()) {
			jdbcRs.setUrl(url);
			jdbcRs.setUsername(userid);
			jdbcRs.setPassword(passwd);
			jdbcRs.setCommand("select * from HR.DEPARTMENTS ");
			jdbcRs.execute();

			while (jdbcRs.next()) {// 레코드가 있으면 반복, 없으면 중단
									// 필드의 자료형이 정수형인 경우 getInt()메소드로 가져옴
				int deptno = jdbcRs.getInt(1); // 인덱스1
				String dname = jdbcRs.getString(2);
				String loc = jdbcRs.getString(3);
				// 하나의 레코드를 필드 단위로 가져온 후 콘솔에 출력한다.
				System.out.println(deptno + " " + dname + " " + loc);
			} // end while

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DB 연결에 문제가 있습니다.");
		} // end try

	}// end main

}// end class
