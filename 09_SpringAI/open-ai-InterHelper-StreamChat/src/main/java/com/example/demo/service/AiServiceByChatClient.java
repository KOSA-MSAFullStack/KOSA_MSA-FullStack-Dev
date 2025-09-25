package com.example.demo.service;

import java.util.Map;
import org.springframework.ai.chat.client.ChatClient; // ChatClient 관련 클래스 import
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor; // 대화 메모리 어드바이저 import
import org.springframework.ai.chat.memory.InMemoryChatMemory; // 인메모리 대화 메모리 구현체 import
import org.springframework.ai.chat.prompt.Prompt; // 프롬프트 객체 import
import org.springframework.ai.chat.prompt.PromptTemplate; // 프롬프트 템플릿 객체 import
import org.springframework.stereotype.Service; // Spring 서비스 빈 선언용 어노테이션 import
import lombok.extern.slf4j.Slf4j; // 로깅을 위한 Lombok Slf4j import
import reactor.core.publisher.Flux; // Reactive Streams Flux import

@Service // Spring 서비스 컴포넌트 등록
@Slf4j   // Slf4j 로깅 지원
public class AiServiceByChatClient {
  
  // ##### 필드 #####
  private ChatClient chatClient; // ChatClient 인스턴스를 담을 필드
  
  // ##### 생성자 #####
  public AiServiceByChatClient(ChatClient.Builder builder) { // ChatClient.Builder를 주입받는 생성자
	  // Set up the ChatClient with memory advisor to preserve conversation context
	  // 대화 맥락을 보존하기 위해 메모리 어드바이저와 함께 ChatClient 설정
      chatClient = builder.defaultAdvisors(
              new MessageChatMemoryAdvisor(     // 대화 메모리 어드바이저 등록
                      new InMemoryChatMemory()  // 인메모리 대화 메모리 구현체 사용
              )).build();                       // ChatClient 빌드 완료
  }

  // ##### 메소드 #####
  
  // Method to get AI-generated interview response
  // AI가 생성한 면접 답변을 스트리밍으로 가져오는 메소드
  public Flux<String> generateStreamText(String company, String jobTitle, String strength, String weakness) {
      // PromptTemplate 정의: 면접 준비 조언을 생성할 프롬프트 템플릿
      PromptTemplate promptTemplate = new PromptTemplate(
      	    "당신은 구직자를 도와주는 전문 취업 코치입니다.\n"
      	    	    + "지원자는 {company} 회사의 {jobTitle} 직무에 지원하려고 합니다.\n"
      	    	    + "지원자가 밝힌 강점은 다음과 같습니다: {strength}.\n"
      	    	    + "지원자가 밝힌 약점은 다음과 같습니다: {weakness}.\n"
      	    	    + "이 정보를 기반으로 다음을 포함한 자세한 면접 준비 조언을 제공하세요:\n"
      	    	    + "1. 강점을 효과적으로 설명하는 방법과 예시 답변\n"
      	    	    + "2. 약점을 솔직하면서도 긍정적으로 표현하는 방법과 예시 답변\n"
      	    	    + "3. 해당 회사와 직무에 적합한 태도나 역량을 어필하는 전략\n"
      	    	    + "4. 실제 면접에서 사용할 수 있는 모범 답변을 포함한 조언을 제공하세요.\n"
      	    	    + "응답은 자연스럽고 실제 면접 대화처럼 구성해 주세요."
      	);

      // PromptTemplate에 실제 값들을 바인딩하여 Prompt 객체 생성
      Prompt prompt = promptTemplate.create(
          Map.of(
              "company", company,     // 회사명 전달
              "jobTitle", jobTitle,   // 직무명 전달
              "strength", strength,   // 강점 전달
              "weakness", weakness    // 약점 전달
          )
      );

      // ChatClient를 이용하여 프롬프트를 전달하고 스트리밍 방식으로 응답을 받음
      return chatClient
    	        .prompt(prompt)   // 프롬프트 설정
    	        .stream()         // 스트리밍 방식으로 결과 수신
    	        .content();       // Flux<String> 형태로 응답 내용 반환
  }

}//end class
