package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class JDBC_MySQL {
  static {
      try {
          Class.forName("com.mysql.cj.jdbc.Driver");
      } catch (ClassNotFoundException e) {
          e.printStackTrace();
      }
  }//end static
  @Test
  public void TestConnection() {
      try {
          Connection con =
                  DriverManager.getConnection(
                          "jdbc:mysql://localhost:3306/bootex"
                          ,"bootuser","bootuser");
          System.out.println(con);
      } catch (SQLException e) {
          System.out.println(e.getMessage());
      }//end try
  }//end test
}//end class

