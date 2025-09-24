package com.bharath.springai.services;

import java.util.Map;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import com.bharath.springai.text.prompttemplate.CountryCuisines;

@Service
public class OpenAiService {

    private ChatClient chatClient;

    public OpenAiService(ChatClient.Builder builder) {
        // Set up the ChatClient with memory advisor to preserve conversation context
        chatClient = builder.defaultAdvisors(
                new MessageChatMemoryAdvisor(     // Advisor enabling chat memory
                        new InMemoryChatMemory()  // In-memory implementation of conversation memory
                )).build();                       // Build the ChatClient instance
    }

    public CountryCuisines getCuisines(String country, String numCuisines, String language) {

        PromptTemplate promptTemplate = new PromptTemplate("당신은 전통 요리 전문가입니다.\n"
            + "질문에 답하세요: {country}의 전통 요리는 무엇인가요?\n"
            + "{language}로 {numCuisines}개의 요리를 나열하세요.\n"
            + "특정 국가의 특정 요리에 대한 정보를 제공합니다.\n"
            + "가상의 장소에 대한 정보는 제공하지 마세요.\n"
            + "국가가 허구이거나 존재하지 않는 경우,\n"
            + "요리 없이 국가 이름만 반환하세요.");
    	//new PromptTemplate("You are an expert in traditional cuisines.\n"
		//+ "Answer the question: What is the traditional cuisine of {country}?\n"
		//+ "Return a list of {numCuisines} in {language}.\n" + "You provide information about a specific dish \n"
		//+ "from a specific country.\n" + "Avoid giving information about fictional places.\n"
		//+ "If the country is fictional or non-existent \n" + "return the country with out any cuisines.");

        // Create a prompt using dynamic placeholders
        Prompt prompt = promptTemplate.create(
            Map.of("country", country, "numCuisines", numCuisines, "language", language));

        // Send the prompt to the AI model and map the response to CountryCuisines class
        return chatClient.prompt(prompt).call().entity(CountryCuisines.class);
    }

} // end class
