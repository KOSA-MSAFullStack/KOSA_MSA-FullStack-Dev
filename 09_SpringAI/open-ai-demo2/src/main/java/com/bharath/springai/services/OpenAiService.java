package com.bharath.springai.services;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;

/**
 * Spring AI 프레임워크를 사용하여 OpenAI 모델과의 통신을 캡슐화하는 서비스 클래스
 */
@Service
public class OpenAiService {

    // AI 모델과 상호작용하기 위해 사용되는 ChatClient 인스턴스
    private ChatClient chatClient;

    /**
     * ChatClient 빌더의 의존성 주입을 통한 생성자.
     * 이 생성자는 인메모리 채팅 메모리 어드바이저를 사용하여 ChatClient를 설정하며,
     * 요청 간 대화 기록을 저장하고 검색할 수 있도록 지원한다.
     *
     * @param builder Spring AI에서 제공하는 ChatClient 빌더
     */
    public OpenAiService(ChatClient.Builder builder) {
        // 대화 상태를 유지하기 위해 인메모리 채팅 메모리를 사용하는 기본 어드바이저 구성
        chatClient = builder.defaultAdvisors(
                new MessageChatMemoryAdvisor(     // 메모리 기능을 추가하는 어드바이저
                        new InMemoryChatMemory()  // 인메모리 기반의 채팅 메모리 구현체
                )).build();                       // ChatClient 빌드 완료
    }

    /**
     * 주어진 사용자 질문에 대한 AI 모델의 응답을 생성한다.
     * 모델 이름, 창의성(temperature), 응답의 최대 토큰 수 등의
     * 특정 옵션을 설정하여 OpenAI 모델을 구성한다.
     *
     * @param question 사용자로부터 제공된 입력 또는 프롬프트
     * @return ChatResponse 객체로 캡슐화된 AI 모델의 응답
     */
    public ChatResponse generateAnswer(String question) {
        // 모델별 구성 옵션 설정
        OpenAiChatOptions options = new OpenAiChatOptions();
        options.setModel("gpt-4o-mini");     // 사용할 OpenAI 모델 지정
        options.setTemperature(0.7);         // 응답의 무작위성/창의성 조절
        options.setMaxTokens(200);           // 응답의 최대 길이 설정

        // 프롬프트를 모델에 전송하고 응답을 가져옴
        return chatClient
                .prompt(question)            // 입력 프롬프트 설정
                .call()                      // AI 제공자에게 호출 실행
                .chatResponse();             // 채팅 응답 추출 및 반환
    }
}
