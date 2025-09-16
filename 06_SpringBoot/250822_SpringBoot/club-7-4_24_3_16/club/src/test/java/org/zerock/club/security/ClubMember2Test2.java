package org.zerock.club.security;


import java.sql.SQLException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.club.dto.ClubMember2;
import org.zerock.club.dao.ClubMemberDao;


@SpringBootTest
public class ClubMember2Test2 {
   @Autowired
   private ClubMemberDao clubMemberDao;


   @Test
   public void testRead() throws SQLException{
       //찾을 데이터 이메일 "user95@zerock.org"
       ClubMember2 reult
               = clubMemberDao.findByEmail("user95@zerock.org",0);
       ClubMember2 clubMember2 = reult;
       System.out.println(clubMember2);
   }//end testRead..
}//end class

