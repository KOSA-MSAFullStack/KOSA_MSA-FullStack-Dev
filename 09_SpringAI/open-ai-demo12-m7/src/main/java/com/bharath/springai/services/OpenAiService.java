package com.bharath.springai.services; // 패키지 선언

import org.springframework.ai.chat.client.ChatClient; // OpenAI와 상호작용하는 ChatClient 클래스 임포트
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor; // 대화 메모리 어드바이저 임포트
import org.springframework.ai.chat.memory.InMemoryChatMemory; // 인메모리 대화 메모리 구현체 임포트
import org.springframework.stereotype.Service; // 스프링 서비스 컴포넌트 어노테이션 임포트
import org.springframework.util.MimeTypeUtils; // MIME 타입 유틸리티 임포트
import org.springframework.core.io.FileSystemResource; // 파일 시스템 리소스(파일 경로 기반 리소스) 임포트

@Service // 스프링 서비스 빈으로 등록
public class OpenAiService { // OpenAiService 클래스 선언

    // OpenAI의 챗 모델과 상호작용할 ChatClient 인스턴스
    private ChatClient chatClient;

    // 생성자: InMemoryChatMemory를 사용하여 ChatClient를 빌드
    public OpenAiService(ChatClient.Builder builder) {
        chatClient = builder.defaultAdvisors( // ChatClient 빌더를 이용하여 기본 어드바이저 설정
                new MessageChatMemoryAdvisor( // 대화 메모리 어드바이저 추가
                        new InMemoryChatMemory())).build(); // 인메모리 대화 메모리를 적용하고 ChatClient 빌드 완료
    }

    /**
     * 업로드된 이미지를 주어진 프롬프트와 함께 설명하는 메서드
     *
     * @param prompt AI 설명을 유도할 텍스트 프롬프트
     * @param path 업로드된 이미지의 로컬 파일 시스템 경로
     * @return AI 모델이 생성한 텍스트 설명
     */
    public String explainImage(String prompt, String path) {
        String explanation = chatClient.prompt() // 프롬프트 빌더 시작
                .user(u -> u.text(prompt) // 사용자 입력에 텍스트 프롬프트 추가
                           .media(MimeTypeUtils.IMAGE_JPEG, new FileSystemResource(path))) // 이미지(JPEG) 파일을 미디어로 첨부
                .call() // OpenAI API 호출
                .content(); // 응답에서 텍스트 콘텐츠 추출
        return explanation; // 결과 설명 문자열 반환
    }

} // end class
