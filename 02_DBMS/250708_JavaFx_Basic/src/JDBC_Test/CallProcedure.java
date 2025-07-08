package JDBC_Test;

import java.sql.*;

public class CallProcedure {
	public static void main(String[] args) {
		try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xepdb1", "hr", "hr")) {

			// 프로시저 호출
			CallableStatement cs = con.prepareCall("{call find_ename(?,?)}");
			// 입력 파라메터
			cs.setInt(1, 107);
			// 출력 파라메터
			cs.registerOutParameter(2, java.sql.Types.VARCHAR);
			// 실행
			cs.execute();
			System.out.println(cs.getString(2));
			
		} catch (Exception e) {
			e.printStackTrace();
		} // end try
	}// end main
}// end clas