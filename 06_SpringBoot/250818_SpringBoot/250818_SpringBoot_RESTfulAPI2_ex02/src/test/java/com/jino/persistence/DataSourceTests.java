package com.jino.persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DataSourceTests {
  static {
      try {
          Class.forName("oracle.jdbc.OracleDriver");
      } catch (ClassNotFoundException e) {
          e.printStackTrace();
      }
  }//end static
  @Test
  public void TestConnection() {
      try {
          Connection con =
                  DriverManager.getConnection(
                          "jdbc:oracle:thin:@localhost:1521/xepdb1"
                          ,"book_ex","book_ex");
          System.out.println(con);
      } catch (SQLException e) {
          System.out.println(e.getMessage());
      }//end try
  }//end test
}//end class



