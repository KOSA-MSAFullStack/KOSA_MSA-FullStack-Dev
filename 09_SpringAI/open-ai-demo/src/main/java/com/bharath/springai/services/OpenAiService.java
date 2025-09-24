package com.bharath.springai.services;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.stereotype.Service;

/**
 * Spring AI의 ChatClient를 사용하여 AI 모델과 통신하는 서비스 클래스
 */
@Service
public class OpenAiService {

    // AI 제공자와 상호작용하는 데 사용되는 ChatClient 인스턴스
    private ChatClient chatClient;

    /**
     * ChatClient 빌더의 생성자 기반 의존성 주입.
     * 빌더는 ChatClient 인스턴스를 생성하는 데 사용된다.
     *
     * @param builder Spring AI에서 제공하는 ChatClient 빌더
     */
    public OpenAiService(ChatClient.Builder builder) {
        // 제공된 빌더를 사용하여 ChatClient를 생성하고 초기화
        chatClient = builder.build();
    }

    /**
     * 프롬프트를 AI 모델에 전송하고 생성된 채팅 응답을 반환.
     *
     * @param question AI가 응답할 입력 프롬프트나 질문
     * @return AI 모델이 반환한 채팅 응답
     */
    public ChatResponse generateAnswer(String question) {
        // 질문을 AI 모델에 전송하고 응답을 가져옴
        return chatClient
                .prompt(question)   // 입력 질문으로 프롬프트 준비
                .call()             // 요청 실행
                .chatResponse();    // 채팅 응답을 추출하여 반환
    }
}
