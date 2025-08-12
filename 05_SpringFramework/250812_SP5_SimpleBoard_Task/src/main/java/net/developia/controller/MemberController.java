// MemberController.java

package net.developia.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import net.developia.dto.MemberDTO;
import net.developia.service.MemberService;

// 회원 관련 웹 요청 처리하는 Spring MVC 컨트롤러
// 로그인, 로그아웃 등의 기능 담당
@Slf4j
@Controller
@RequestMapping("member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    // 로그인 페이지 표시
    // Get 요청, member/login 경로에 접근 시 호출
    @GetMapping("login")
    public String login() {
        return "member/login";
    }

    // 사용자 로그인 요청 처리
    // POST 요청, /member/login 경로에 접근 시 호출
    /**
     * @param memberDTO 로그인 정보 담고 있는 MemberDTO 객체
     * @param session   HTTP 세션 객체 (로그인 정보 저장용)
     * @param model     View로 데이터를 전달하기 위한 Model 객체
     * @return          로그인 성공 시 게시글 목록으로 리다이렉트, 실패 시 에러 메시지와 함께 결과 페이지로 이동
    */
    @PostMapping("login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session, Model model) {
        log.info(memberDTO.toString()); // 수신된 회원 정보 로깅
        try {
            // MemberService를 통해 로그인 처리 및 회원 정보 조회
            MemberDTO loginMember = memberService.login(memberDTO);

            // 로그인 성공 시, 세션에 회원 정보 저장
            session.setAttribute("loginMember", loginMember);

            // 게시글 목록 페이지로 리다이렉트
            return "redirect:/list";
        } catch (Exception e) {
            // 로그인 실패 시, 에러 메시지를 모델에 추가하고 결과 페이지로 이동
            model.addAttribute("msg", e.getMessage());
            model.addAttribute("url", "javascript:history.back();"); // 이전 페이지로 돌아가는 스크립트
            return "article/result"; // 에러 메시지 보여줄 뷰 경로
        }
    }

    /**
     * 사용자 로그아웃 요청 처리
     * GET 요청, /member/logout 경로에 접근 시 호출
     *
     * @param session   HTTP 세션 객체
     * @return          로그아웃 후 게시글 목록으로 리다이렉트
     */
    @GetMapping("logout")
    public String logout(HttpSession session) {
        // 세션 무효화하여 로그인 정보 삭제 및 로그아웃 처리
        session.invalidate();

        // 게시글 목록 페이지로 리다이렉트
        return "redirect:/list";
    }
}