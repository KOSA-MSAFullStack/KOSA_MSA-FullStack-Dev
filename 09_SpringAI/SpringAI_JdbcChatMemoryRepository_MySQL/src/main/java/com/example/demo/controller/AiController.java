package com.example.demo.controller; // 패키지 선언

import org.springframework.beans.factory.annotation.Autowired; // 스프링 의존성 주입 애노테이션 임포트
import org.springframework.http.MediaType; // HTTP 미디어 타입 상수 임포트
import org.springframework.web.bind.annotation.PostMapping; // POST 요청 매핑 애노테이션 임포트
import org.springframework.web.bind.annotation.RequestMapping; // 요청 경로 매핑 애노테이션 임포트
import org.springframework.web.bind.annotation.RequestParam; // 요청 파라미터 바인딩 애노테이션 임포트
import org.springframework.web.bind.annotation.RestController; // REST 컨트롤러 애노테이션 임포트

import com.example.demo.service.AiService; // AI 서비스 클래스 임포트
import jakarta.servlet.http.HttpSession; // 세션 객체 사용을 위한 HttpSession 임포트
import lombok.extern.slf4j.Slf4j; // 로그 출력을 위한 Lombok 애노테이션 임포트

@RestController // REST 컨트롤러임을 선언 (JSON, 문자열 등 직접 반환)
@RequestMapping("/ai") // 기본 요청 경로를 "/ai"로 설정
@Slf4j // 로그 기능 자동 생성 (log 변수 사용 가능)
public class AiController { // AiController 클래스 선언

  // ##### 필드 ##### 
  @Autowired // 스프링이 AiService 빈을 주입
  private AiService aiService;  

  // ##### 요청 매핑 메소드 #####
  @PostMapping( // POST 요청을 "/ai/chat" 경로에 매핑
    value = "/chat", // 요청 경로
    consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, // 요청 본문 타입: application/x-www-form-urlencoded
    produces = MediaType.TEXT_PLAIN_VALUE // 응답 타입: text/plain
  )
  public String jdbcChatMemory(
      @RequestParam("question") String question, // 클라이언트에서 전달한 "question" 파라미터 값
      HttpSession session) { // 현재 사용자의 세션 정보
      String answer = aiService.chat(question, session.getId()); // AiService 호출, 세션 ID를 conversationId로 전달
      return answer; // 생성된 답변을 클라이언트에 문자열로 반환
  }    
}
