package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired; // 의존성 주입을 위한 어노테이션 import
import org.springframework.http.MediaType; // HTTP 미디어 타입 상수 import
import org.springframework.web.bind.annotation.PostMapping; // POST 요청 매핑 어노테이션 import
import org.springframework.web.bind.annotation.RequestMapping; // 클래스 레벨 매핑 어노테이션 import
import org.springframework.web.bind.annotation.RequestParam; // 요청 파라미터 매핑 어노테이션 import
import org.springframework.web.bind.annotation.RestController; // REST 컨트롤러 선언 어노테이션 import
import com.example.demo.service.AiServiceByChatClient; // 서비스 클래스 import
import lombok.extern.slf4j.Slf4j; // 로깅을 위한 Lombok Slf4j import
import reactor.core.publisher.Flux; // Reactive Streams Flux import

@RestController // REST API 컨트롤러 선언
@RequestMapping("/ai") // 기본 요청 경로를 "/ai"로 설정
@Slf4j // Slf4j 로깅 지원
public class AiController {
  
  // ##### 필드 #####
  @Autowired // Spring이 AiServiceByChatClient 빈을 자동 주입
  private AiServiceByChatClient aiService; // 서비스 객체 참조

  // ##### 요청 매핑 메소드 #####

  // (Optional) NDJSON streaming for interview coaching
  // 면접 코칭 응답을 NDJSON 형태로 스트리밍하는 엔드포인트
  @PostMapping(
       value = "/interview-stream", // 요청 경로: /ai/interview-stream
       consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, // 요청 데이터 타입: x-www-form-urlencoded
       produces = MediaType.APPLICATION_NDJSON_VALUE // 응답 데이터 타입: NDJSON (Newline Delimited JSON)
  )
  public Flux<String> interviewStream(
		   @RequestParam("company") String company,     // 요청 파라미터: 회사명
		   @RequestParam("jobTitle") String jobTitle,   // 요청 파라미터: 직무명
		   @RequestParam("strength") String strength,   // 요청 파라미터: 강점
		   @RequestParam("weakness") String weakness) { // 요청 파라미터: 약점
    
    // 서비스 계층의 generateStreamText 호출하여 Flux<String> 반환
    return aiService.generateStreamText(company, jobTitle, strength, weakness);
  }
}
