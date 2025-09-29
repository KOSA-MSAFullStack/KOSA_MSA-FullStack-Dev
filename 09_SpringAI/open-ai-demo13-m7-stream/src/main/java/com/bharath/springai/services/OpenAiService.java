package com.bharath.springai.services; // 패키지 선언

import org.springframework.ai.chat.client.ChatClient; // ChatClient 임포트
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor; // 메모리 어드바이저 임포트
import org.springframework.ai.chat.memory.InMemoryChatMemory; // 인메모리 메모리 구현체 임포트
import org.springframework.stereotype.Service; // 서비스 컴포넌트 선언용 어노테이션 임포트
import org.springframework.util.MimeTypeUtils; // MIME 타입 유틸 임포트

import reactor.core.publisher.Flux; // 비동기 스트림 Flux 임포트

import org.springframework.core.io.FileSystemResource; // 파일 시스템 리소스 임포트

@Service // 서비스 컴포넌트 등록
public class OpenAiService {

    // OpenAI의 Chat 모델과 상호작용하기 위한 ChatClient 인스턴스
    private ChatClient chatClient;

    // 기본 어드바이저로 InMemoryChatMemory를 사용하는 ChatClient 초기화 생성자
    public OpenAiService(ChatClient.Builder builder) {
        chatClient = builder.defaultAdvisors(
                new MessageChatMemoryAdvisor(
                        new InMemoryChatMemory())).build(); // 메모리 어드바이저 설정 후 ChatClient 빌드
    }

    /**
     * 사용자 입력 프롬프트와 두 개의 이미지 파일을 모델에 전달하고, 생성된 응답을 반환한다.
     *
     * @param prompt 사용자 입력 텍스트
     * @param path1 첫 번째 이미지 파일 경로 (JPEG)
     * @param path2 두 번째 이미지 파일 경로 (JPEG)
     * @return 모델의 텍스트 응답을 Flux<String> 형태로 반환
     */
    public Flux<String> getDietAdvice(String prompt, String path1, String path2) {

        // 시스템 역할 메시지 정의 (AI 동작을 가이드)
        String systemrole = "너는 어떤 질문에도 도움이되는 유용한 어시스턴트다";

        // 사용자 프롬프트와 이미지 파일을 포함하여 요청 생성 후 결과 스트리밍
        Flux<String> explanation = chatClient.prompt()
                .system(systemrole) // 시스템 역할 지정
                .user(u -> u.text(prompt) // 사용자 입력 텍스트 추가
                        .media(MimeTypeUtils.IMAGE_JPEG, new FileSystemResource(path1)) // 첫 번째 이미지 첨부
                        .media(MimeTypeUtils.IMAGE_JPEG, new FileSystemResource(path2))) // 두 번째 이미지 첨부
                .stream() // 스트리밍 모드로 실행
                .content(); // 응답의 텍스트 콘텐츠 추출

        return explanation; // Flux<String> 반환
    }

} // end class
