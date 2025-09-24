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
 * "무엇이든 물어보세요" 기능을 위한 HTTP 요청을 처리하는 컨트롤러 클래스.
 * 사용자가 질문을 제출하고 AI가 생성한 응답을 받을 수 있도록 한다.
 */
@Controller
public class AnswerAnyThingController {

    // AI 백엔드와 통신하기 위해 OpenAiService를 주입
    @Autowired
    private OpenAiService service;

    /**
     * 질문 입력 폼을 표시하는 GET 요청을 처리한다.
     *
     * @return 입력 폼을 표시하는 뷰(HTML 페이지)의 이름
     */
    @GetMapping("/showAskAnything")
    public String showAskAnything() {
        // askAnything.html 뷰(Thymeleaf 템플릿)를 반환
        return "askAnything";
    }

    /**
     * 사용자의 질문을 AI 서비스로 전송하고 응답을 받는 POST 요청을 처리한다.
     *
     * @param question 사용자가 폼에 입력한 질문
     * @param model 뷰로 데이터를 전달하는 데 사용되는 모델 객체
     * @return 질문과 생성된 답변을 포함한 동일한 뷰 이름
     */
    @PostMapping("/askAnything")
    public String askAnything(@RequestParam("question") String question, Model model) {
        // OpenAiService를 호출하여 AI가 생성한 응답을 가져옴
        ChatResponse response = service.generateAnswer(question);

        // 전체 응답을 콘솔에 출력 (디버깅/로그용)
        System.out.println(response);

        // 원본 질문을 모델에 추가
        model.addAttribute("question", question);

        // AI가 생성한 답변을 추출해 모델에 추가
        model.addAttribute("answer", response.getResult().getOutput().getText());

        // 동일한 뷰를 반환하여 질문과 답변을 화면에 표시
        return "askAnything";
    }
}
