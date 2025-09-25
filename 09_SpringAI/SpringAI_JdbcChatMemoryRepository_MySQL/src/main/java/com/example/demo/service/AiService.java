package com.example.demo.service; // 패키지 선언

import org.springframework.ai.chat.client.ChatClient; // Spring AI ChatClient 임포트
import org.springframework.ai.chat.client.advisor.PromptChatMemoryAdvisor; // 프롬프트 메모리 어드바이저 임포트
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor; // 간단 로거 어드바이저 임포트
import org.springframework.ai.chat.memory.ChatMemory; // ChatMemory 인터페이스 임포트
import org.springframework.ai.chat.memory.MessageWindowChatMemory; // 메시지 윈도우 기반 ChatMemory 구현체 임포트
import org.springframework.ai.chat.memory.repository.jdbc.JdbcChatMemoryRepository; // JDBC 기반 메모리 저장소 임포트
import org.springframework.core.Ordered; // 우선순위 상수 임포트
import org.springframework.stereotype.Service; // 서비스 계층 애노테이션 임포트

import lombok.extern.slf4j.Slf4j; // 로그 출력을 위한 Lombok 애노테이션 임포트

@Service // 스프링 서비스 빈으로 등록
@Slf4j // 로그 기능 자동 생성
public class AiService { // AiService 클래스 선언
  
  // ##### 필드 ##### 
  private ChatClient chatClient; // ChatClient 인스턴스를 저장하는 필드

  // ##### 생성자 #####
  public AiService(
      JdbcChatMemoryRepository chatMemoryRepository, // JDBC 기반 대화 메모리 저장소 주입
      ChatClient.Builder chatClientBuilder) { // ChatClient 빌더 주입
    
    // 메모리 저장소를 활용한 ChatMemory 생성 (최근 100개 메시지 관리)
    ChatMemory chatMemory = MessageWindowChatMemory.builder()
        .chatMemoryRepository(chatMemoryRepository) // JDBC 저장소 연결
        .maxMessages(100) // 저장할 최대 메시지 수 지정
        .build();

    // ChatClient 생성, 메모리 어드바이저와 로깅 어드바이저 적용
    this.chatClient = chatClientBuilder
        .defaultAdvisors(
            PromptChatMemoryAdvisor.builder(chatMemory).build(), // 대화 메모리 어드바이저 등록
            new SimpleLoggerAdvisor(Ordered.LOWEST_PRECEDENCE - 1) // 로깅 어드바이저 등록
        )
        .build();
  }    

  // ##### 대화 처리 메소드 #####
  public String chat(String userText, String conversationId) {
    // ChatClient로 사용자 입력을 전달하고, 대화 메모리에 세션 기반 ID를 저장
    String answer = chatClient.prompt()
      .user(userText) // 사용자 입력 텍스트 설정
      .advisors(advisorSpec -> advisorSpec.param(
        ChatMemory.CONVERSATION_ID, conversationId // 세션 ID를 대화 ID로 사용
      ))
      .call() // AI 호출 실행
      .content(); // 응답 메시지 내용 추출
    
    return answer; // 최종 응답 반환
  }
}
