package com.bharath.springai.services;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.chat.model.ChatResponse;

import org.springframework.stereotype.Service;


@Service
public class OllamaService {

    // The ChatClient instance used to interact with the AI model
    private ChatClient chatClient;

    /**
     * Constructor with dependency injection of the ChatClient builder.
     * This sets up the chat client with an in-memory chat memory advisor,
     * which enables storing and retrieving conversational history across requests.
     *
     * @param builder the ChatClient builder provided by Spring AI
     */
    public OllamaService(ChatClient.Builder builder) {
        // Configure default advisors with in-memory chat memory to maintain conversation state
        chatClient = builder.defaultAdvisors(
                new MessageChatMemoryAdvisor(     // Advisor that adds memory capabilities
                        new InMemoryChatMemory()  // In-memory implementation of chat memory
                )).build();                       // Finalize building the ChatClient
    }

    public ChatResponse generateAnswer(String question) {
      

        // Send the prompt to the model and retrieve the response
        return chatClient
                .prompt(question)            // Set the input prompt
                .call()                      // Execute the call to the AI provider
                .chatResponse();             // Extract and return the chat response
    }
}
