package com.bharath.springai.text;

import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.bharath.springai.services.OpenAiService;

/**
 * 사용자 입력을 처리하고 OpenAiService를 통해 AI가 생성한 응답을 표시하는
 * Spring MVC 컨트롤러 클래스
 */
@Controller
public class AnswerAnyThingController {

    // AI 통신 로직을 처리하는 OpenAiService를 주입
    @Autowired
    private OpenAiService service;

    /**
     * HTTP GET 요청을 처리하여 초기 입력 페이지를 표시.
     * 이 메서드는 단순히 사용자 질문을 입력할 수 있는
     * Thymeleaf 템플릿의 이름을 반환한다.
     *
     * @return 질문 입력 폼을 표시할 Thymeleaf 뷰 이름
     */
    @GetMapping("/showAskAnything")
    public String showAskAnything() {
        return "askAnything"; // askAnything.html 템플릿 렌더링
    }

    /**
     * 사용자가 질문을 제출했을 때의 HTTP POST 요청을 처리.
     * 질문을 AI 서비스로 전송하고 생성된 답변을 받아,
     * 원래 질문과 함께 모델에 바인딩하여 뷰에 전달한다.
     *
     * @param question 폼에서 제출된 사용자 질문
     * @param model 뷰로 데이터를 전달하기 위한 Spring 모델
     * @return 결과를 표시할 Thymeleaf 뷰 이름
     */
    @PostMapping("/askAnything")
    public String askAnything(@RequestParam("question") String question, Model model) {
        // 질문을 AI 모델로 전송하고 응답을 가져옴
        ChatResponse response = service.generateAnswer(question);

        // 응답을 콘솔에 출력 (디버깅 용도로 유용)
        System.out.println(response);

        // 원본 질문과 AI가 생성한 답변을 모델에 추가
        model.addAttribute("question", question);
        model.addAttribute("answer", response.getResult().getOutput().getText());

        // 동일한 뷰를 반환하여 답변을 표시
        return "askAnything";
    }
}
