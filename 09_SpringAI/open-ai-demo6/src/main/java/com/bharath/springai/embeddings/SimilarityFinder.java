package com.bharath.springai.embeddings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bharath.springai.services.OpenAiService;

@Controller
public class SimilarityFinder {

    // 임베딩 및 유사도 로직을 처리하는 서비스를 주입
    @Autowired
    private OpenAiService service;

    /**
     * 유사도 찾기 UI를 보여주는 GET 요청을 처리합니다.
     *
     * @return 유사도 입력을 위한 Thymeleaf 템플릿 이름
     */
    @GetMapping("/showSimilarityFinder")
    public String showSimilarityFinder() {
        return "similarityFinder";
    }

    /**
     * 두 텍스트 사이의 코사인 유사도를 계산하고 표시하는 POST 요청을 처리합니다.
     *
     * @param text1 첫 번째 입력 텍스트
     * @param text2 두 번째 입력 텍스트
     * @param model 뷰에 속성을 전달하기 위해 사용되는 Spring Model
     * @return 결과와 함께 렌더링할 Thymeleaf 템플릿 이름
     */
    @PostMapping("/similarityFinder")
    public String findSimilarity(@RequestParam String text1,
                                 @RequestParam String text2,
                                 Model model) {
        // 유사도를 계산하기 위해 서비스 메서드 호출
        double response = service.findSimilarity(text1, text2);
        // 디버깅을 위해 결과를 콘솔에 출력
        System.out.println(response);

        // 뷰에서 표시하기 위해 결과를 모델에 추가
        model.addAttribute("response", response);

        return "similarityFinder";
    }

}
