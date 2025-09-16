package org.zerock.club.controller;


import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.club.security.dto.ClubAuthMemberDTO;


@Controller
@Log4j2
@RequestMapping("/sample/") // @PreAuthorize 사용 가능 하게 세팅
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class SampleController {


   @PreAuthorize("permitAll()")
   @GetMapping("/all")
   public void exALL(){
       log.info("exAll.....");
   }//end ex..


   @PreAuthorize("hasRole('USER')")
   @GetMapping("/member")
   public void exMember(
           @AuthenticationPrincipal ClubAuthMemberDTO clubAuthMemberDTO){
       log.info("exMember.....");
       log.info("--------------");
       log.info(clubAuthMemberDTO);
   }//end ex..


   @PreAuthorize("hasRole('ADMIN')")
   @GetMapping("/admin")
   public void exAdmin(){
       log.info("exAdmin.....");
   }//end ex..


   @PreAuthorize("hasRole('USER')")
   @GetMapping("/modify")
   public void exmodify(
           @AuthenticationPrincipal ClubAuthMemberDTO clubAuthMemberDTO
   ){
       log.info("exModify.....");
       log.info("--------------");
       log.info(clubAuthMemberDTO);
   }//end ex..
   
 //user95@zerock.org 만 접근 가능
   @PreAuthorize(" #clubAuthMemberDTO != null " +
           " && #clubAuthMemberDTO.username eq \"user95@zerock.org\" ")
   @GetMapping("/exOnly")
   public void exMemebrOnly(
           @AuthenticationPrincipal ClubAuthMemberDTO clubAuthMemberDTO){
       log.info("exMemberOnly-------");
       log.info(clubAuthMemberDTO);
   }//end exM..


}//end class

