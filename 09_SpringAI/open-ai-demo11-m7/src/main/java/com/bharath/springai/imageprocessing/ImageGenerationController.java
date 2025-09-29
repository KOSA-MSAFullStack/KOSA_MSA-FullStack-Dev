package com.bharath.springai.imageprocessing; // 패키지 선언

import org.springframework.beans.factory.annotation.Autowired; // 의존성 주입을 위한 Autowired import
import org.springframework.stereotype.Controller; // 컨트롤러 선언용 어노테이션 import
import org.springframework.ui.Model; // 뷰에 데이터 전달을 위한 Model import
import org.springframework.web.bind.annotation.GetMapping; // GET 요청 매핑 어노테이션 import
import org.springframework.web.bind.annotation.PostMapping; // POST 요청 매핑 어노테이션 import
import org.springframework.web.bind.annotation.RequestParam; // 요청 파라미터 바인딩 어노테이션 import

import com.bharath.springai.services.OpenAiService; // OpenAiService 클래스 import

@Controller // 이 클래스를 Spring MVC 컨트롤러로 등록
public class ImageGenerationController { // ImageGenerationController 클래스 선언

	// OpenAiService 주입받아 이미지 생성 기능 사용
	@Autowired
	private OpenAiService service; // 이미지 생성 서비스 객체

	/**
	 * 이미지 생성 입력 폼 페이지를 보여주는 메소드
	 *
	 * @return 렌더링할 Thymeleaf 템플릿 이름
	 */
	@GetMapping("/showImageGenerator") // GET 요청을 /showImageGenerator 경로와 매핑
	public String showImageGenerator() { // 폼 페이지를 보여주는 메소드
		return "imageGenerator"; // imageGenerator.html 템플릿 반환
	}

	/**
	 * 이미지 생성 폼 제출을 처리하는 메소드
	 *
	 * @param prompt 사용자가 입력한 이미지 설명 텍스트
	 * @param model 뷰로 데이터 전달을 위한 Model 객체
	 * @return 렌더링할 뷰 이름
	 */
	@PostMapping("/imageGenerator") // POST 요청을 /imageGenerator 경로와 매핑
	public String imageGenerator(@RequestParam String prompt, Model model) { // 이미지 생성 요청 처리 메소드

		// 서비스 호출하여 프롬프트 기반으로 이미지 생성
		String response = service.generateImage(prompt);

		// 콘솔에 디버깅 정보 출력
		System.out.println(prompt); // 사용자가 입력한 프롬프트 출력
		System.out.println("============================"); // 구분선 출력
		System.out.println(response); // 생성된 이미지 URL 출력

		// 모델에 생성된 이미지 URL 추가
		model.addAttribute("response", response);

		// 렌더링할 뷰 이름 반환
		return "imageGenerator";
	}
} // ImageGenerationController 클래스 끝
