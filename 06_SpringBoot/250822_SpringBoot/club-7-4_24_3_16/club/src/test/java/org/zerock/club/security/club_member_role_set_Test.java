package org.zerock.club.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.club.dto.ClubMemberRole;
import org.zerock.club.dto.ClubMemberRoleSet;
import org.zerock.club.dao.ClubMemberDao;
import java.sql.SQLException;

@SpringBootTest
public class club_member_role_set_Test {
   @Autowired
   private ClubMemberDao ClubMemberDao;
   
   @Test
   public void insertDummies2() throws SQLException{
       //1~80 USER 81~90 USER,MANAGER
       //91~100 USER,MANAGER,ADMIN     
      for(int i=1; i<=101; i++) {       
          ClubMemberRoleSet clubMemberRoleSet = new ClubMemberRoleSet();
          clubMemberRoleSet.setClub_member_email("user" + i + "@zerock.org");         
          String role=null;         
       //기본 권한 user
           role = ClubMemberRole.USER.toString();
           //81번 부터 manager 권한 추가
           if(i >80){
               role= ClubMemberRole.MANAGER.toString();
           }//end if
           //91번 부터 admin권한 추가
           if(i >90){
               role=  ClubMemberRole.ADMIN.toString();
           }//end if
           clubMemberRoleSet.setRole_set(role);
           //db에 저장
           ClubMemberDao.insertClubRoleSet(clubMemberRoleSet);
       };//end for


   }//end insert..
}//end class

