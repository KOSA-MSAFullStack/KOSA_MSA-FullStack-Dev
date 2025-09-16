package org.zerock.club.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.zerock.club.dto.ClubMember;
import org.zerock.club.dao.ClubMemberDao;
import java.sql.SQLException;


@SpringBootTest
public class ClubMemberTest {
   @Autowired
   private ClubMemberDao ClubMemberDao;
   //암호화된 데이터 입력 위해
   @Autowired
   private PasswordEncoder passwordEncoder;


   @Test
   public void insertDummies() throws SQLException{
       //패스워드는 1111 로 고정
       for(int i =1; i <= 101; i++) {
           ClubMember clubMember = new ClubMember();
           clubMember.setEmail("user" + i + "@zerock.org");
           clubMember.setName("사용자" + i);
           clubMember.setFrom_social(0);
           clubMember.setPassword(passwordEncoder.encode("1111"));  
           ClubMemberDao.insertClubMember(clubMember);
       };//end int


       System.out.println("입력 성공");
   }//end insert..
}//end class

