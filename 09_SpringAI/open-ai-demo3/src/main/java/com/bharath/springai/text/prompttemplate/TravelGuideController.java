package com.bharath.springai.text.prompttemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bharath.springai.services.OpenAiService;

@Controller
public class TravelGuideController {

    // AI와의 상호작용을 처리하기 위해 OpenAiService 주입
    @Autowired
    private OpenAiService service;

    // 여행 가이드 입력 폼을 보여주는 GET 요청 처리
    @GetMapping("/showTravelGuide")
    public String showChatPage() {
        return "travelGuide";  // 뷰 이름 반환
    }

    // AI 모델로부터 여행 가이드 응답을 가져오는 POST 요청 처리
    @PostMapping("/travelGuide")
    public String getChatResponse(
            @RequestParam("city") String city,
            @RequestParam("month") String month,
            @RequestParam("language") String language,
            @RequestParam("budget") String budget,
            Model model) {

        // AI로부터 여행 가이드 응답을 가져오기 위해 서비스 호출
        String response = service.getTravelGuidance(city, month, language, budget);

        // 뷰에 데이터 전달
        model.addAttribute("city", city);
        model.addAttribute("response", response);

        return "travelGuide";  // 결과를 표시하기 위해 같은 뷰 반환
    }

} // end class
