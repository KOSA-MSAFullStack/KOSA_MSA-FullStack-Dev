package com.bharath.springai.embeddings;

import java.util.List;
import org.springframework.ai.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.bharath.springai.services.OpenAiService;

@Controller
public class JobSearchHelper {

	// 벡터 검색 작업을 수행하는 서비스를 주입
	@Autowired
	private OpenAiService service;

	// 채용 검색 도우미 폼 페이지 표시
	@GetMapping("/showJobSearchHelper")
	public String showJobSearchHelper() {
		return "jobSearchHelper";
	}

	// 폼 제출을 처리하고 유사도 검색 수행
	@PostMapping("/jobSearchHelper")
	public String jobSearchHelper(@RequestParam String query, Model model) {
		// 서비스를 사용하여 유사한 문서 검색
		List<Document> response = service.searchJobs(query);
		
		// 디버깅을 위해 콘솔에 입력과 결과 출력
		System.out.println(query);
		System.out.println(response);
		System.out.println("============================");

		// 검색 결과를 모델에 추가하여 뷰에 표시
		model.addAttribute("response", response);

		return "jobSearchHelper";
	}

} // end class
