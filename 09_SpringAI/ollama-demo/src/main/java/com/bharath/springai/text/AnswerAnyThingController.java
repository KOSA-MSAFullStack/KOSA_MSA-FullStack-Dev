package com.bharath.springai.text;

import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bharath.springai.services.OllamaService;

/**
 * Spring MVC controller that handles user input for asking questions
 * and displays AI-generated responses using the OpenAiService.
 */
@Controller
public class AnswerAnyThingController {

    // Injecting the OpenAiService to handle AI communication logic
    @Autowired
    private OllamaService service;

    /**
     * Handles HTTP GET requests to show the initial input page.
     * This method simply returns the name of the Thymeleaf template
     * that contains the input form where users can enter a question.
     *
     * @return the name of the Thymeleaf view to display the question form
     */
    @GetMapping("/showAskAnything")
    public String showAskAnything() {
        return "askAnything"; // Renders the askAnything.html template
    }

    /**
     * Handles HTTP POST requests when a user submits a question.
     * It sends the question to the AI service, receives the generated answer,
     * and binds both question and answer to the model for rendering in the view.
     *
     * @param question the user's submitted question from the form
     * @param model the Spring Model to pass data to the view
     * @return the name of the Thymeleaf view to display the result
     */
    @PostMapping("/askAnything")
    public String askAnything(@RequestParam("question") String question, Model model) {
        // Send question to the AI model and get the response
        ChatResponse response = service.generateAnswer(question);

        // Log the response to the console (useful for debugging)
        System.out.println(response);

        // Add the original question and the AI-generated answer to the model
        model.addAttribute("question", question);
        model.addAttribute("answer", response.getResult().getOutput().getText());

        // Return the same view to show the answer
        return "askAnything";
    }
}
