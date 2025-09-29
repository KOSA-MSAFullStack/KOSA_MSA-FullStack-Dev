package com.bharath.springai.services; // 패키지 선언

import org.springframework.ai.chat.client.ChatClient; // AI 모델과 상호작용하는 핵심 클라이언트 클래스 임포트
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor; // 대화 메모리를 연결하는 어드바이저 임포트
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor; // VectorStore 기반 RAG 질의응답 어드바이저 임포트
import org.springframework.ai.chat.memory.InMemoryChatMemory; // 인메모리 대화 메모리 구현체 임포트
import org.springframework.ai.vectorstore.VectorStore; // 임베딩 유사도 검색을 위한 벡터 스토어 인터페이스 임포트
import org.springframework.beans.factory.annotation.Autowired; // 스프링 의존성 주입 어노테이션 임포트
import org.springframework.stereotype.Service; // 서비스 컴포넌트 지정 어노테이션 임포트

import reactor.core.publisher.Flux; // 비동기 스트림(조각 단위 출력)을 위한 Flux 타입 임포트

@Service // 스프링 서비스 빈으로 등록
public class OpenAiService { // OpenAiService 클래스 시작

	// ChatClient is used to interact with the AI model // AI 모델과 상호작용하기 위한 ChatClient 필드
	private ChatClient chatClient; // ChatClient 인스턴스 보관 필드

	// Initialize the ChatClient with default chat memory // 기본 대화 메모리를 사용하여 ChatClient 초기화하는 생성자
	public OpenAiService(ChatClient.Builder builder) { // ChatClient.Builder를 주입받는 생성자 시작
		chatClient = builder.defaultAdvisors( // 기본 어드바이저(Advisor)들을 등록
				new MessageChatMemoryAdvisor( // 대화 이력을 관리하는 메모리 어드바이저 추가
						new InMemoryChatMemory())).build(); // 인메모리 메모리를 사용하고 ChatClient 빌드
	} // 생성자 끝

	// Inject the VectorStore to perform similarity searches // 유사도 검색을 수행하기 위한 VectorStore 주입
	@Autowired // 스프링이 VectorStore 구현체를 자동 주입
	private VectorStore vectorStore; // 벡터 스토어 참조 필드

	// Ask a question and get an AI-generated answer using a vector store advisor // VectorStore 어드바이저를 사용해 질문에 대한 스트리밍 응답 반환
	public Flux<String> answer(String query) { // 질의 문자열을 받아 Flux<String>으로 스트리밍 응답을 반환하는 메서드
		return chatClient.prompt(query) // 사용자 질의를 프롬프트로 설정
				.advisors(new QuestionAnswerAdvisor(vectorStore)) // 벡터 스토어 기반 RAG 어드바이저 추가(관련 문서 검색)
				.stream().content(); // 스트리밍 실행 후 콘텐츠만 추출하여 Flux<String>으로 반환
	} // answer 메서드 끝

} // end class // OpenAiService 클래스 끝
