package com.bharath.springai.services;

import java.util.Map;
import java.util.Optional;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

@Service
public class OpenAiService {

    private ChatClient chatClient;

    public OpenAiService(ChatClient.Builder builder) {
        // 대화 상태를 유지하기 위해 메모리 기능을 가진 기본 Advisor 설정
        chatClient = builder.defaultAdvisors(
                new MessageChatMemoryAdvisor(     // 메모리 기능을 추가하는 Advisor
                        new InMemoryChatMemory()  // 메모리를 인메모리 방식으로 구현
                )).build();                       // ChatClient 빌드 완료
    }

    public String getTravelGuidance(String city, String month, String language, String budget) {
        // 도시, 월, 언어, 예산을 위한 플레이스홀더가 포함된 프롬프트 템플릿 생성
        PromptTemplate promptTemplate = new PromptTemplate("Welcome to the {city} travel guide!\n"
                + "If you're visiting in {month}, here's what you can do:\n"
                + "1. Must-visit attractions.\n"
                + "2. Local cuisine you must try.\n"
                + "3. Useful phrases in {language}.\n"
                + "4. Tips for traveling on a {budget} budget.\n"
                + "Enjoy your trip!");

        // 프롬프트 템플릿에 실제 값 채워 넣기
        Prompt prompt = promptTemplate.create(Map.of(
                "city", city,
                "month", month,
                "language", language,
                "budget", budget
        ));

        // 프롬프트를 ChatClient에 전달하고 생성된 응답 텍스트 반환
        return Optional.ofNullable(chatClient.prompt(prompt).call().chatResponse())
               .map(r -> r.getResult().getOutput().getText())
               .orElse("No response received from AI service.");
    }
}
