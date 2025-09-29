package com.bharath.springai.rag; // 패키지 선언: RAG 관련 컨트롤러가 포함된 패키지

import com.bharath.springai.services.OpenAiService; // OpenAiService를 사용하기 위한 임포트
import org.springframework.beans.factory.annotation.Autowired; // 스프링 의존성 주입 어노테이션 임포트
import org.springframework.http.MediaType; // HTTP 미디어 타입 상수 사용을 위한 임포트
import org.springframework.stereotype.Controller; // MVC 컨트롤러를 선언하기 위한 어노테이션 임포트
import org.springframework.web.bind.annotation.*; // 요청 매핑 어노테이션(@GetMapping, @PostMapping 등) 임포트
import org.springframework.ui.Model; // 뷰로 데이터 전달을 위한 Model 임포트
import reactor.core.publisher.Flux; // 리액티브 스트림 타입 Flux 임포트

@Controller // 이 클래스를 스프링 MVC 컨트롤러로 등록
public class LegalBOT { // LegalBOT 컨트롤러 클래스 선언 시작

    @Autowired // OpenAiService 빈을 의존성 주입
    private OpenAiService service; // AI 스트리밍 응답을 제공하는 서비스 필드

    // Render Thymeleaf page // 타임리프 페이지를 렌더링하는 핸들러
    @GetMapping("/showLegalBOT") // GET /showLegalBOT 요청 매핑
    public String showLegalBOT() { // 타임리프 템플릿 이름을 반환하는 메서드 시작
        return "LegalBOT"; // "LegalBOT.html" 템플릿을 렌더링하도록 뷰 이름 반환
    } // showLegalBOT 메서드 끝

    // (Optional) Non-stream fallback (kept if you still want it) // (선택) 비스트리밍 폴백 엔드포인트
    @PostMapping("/LegalBOT") // POST /LegalBOT 요청 매핑 (일반 폼 제출 처리)
    public String legalBOTFallback(@RequestParam String query, Model model) { // 폼 파라미터 query와 Model 주입받는 메서드 시작
        // You could block here if you want a single string (not recommended for streaming). // 스트리밍이 아닌 단일 응답을 원하면 여기서 블로킹 가능(권장하지 않음)
        model.addAttribute("response", "Streaming endpoint is /LegalBOT/stream"); // 뷰에 안내 메시지 모델 속성으로 추가
        return "LegalBOT"; // 동일한 "LegalBOT.html" 뷰로 이동
    } // legalBOTFallback 메서드 끝

    // Streaming endpoint (NDJSON): returns chunks as they are generated // 스트리밍 엔드포인트(NDJSON): 생성된 조각을 실시간 전송
    @PostMapping( // POST 매핑 시작
        value = "/LegalBOT/stream", // 엔드포인트 경로 지정
        consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, // 폼 URL 인코딩된 입력을 소비
        produces = MediaType.APPLICATION_NDJSON_VALUE // NDJSON 형태로 스트림을 생산
    ) // @PostMapping 설정 끝
    @ResponseBody // 반환 값을 HTTP 응답 바디로 직접 전송
    public Flux<String> legalBOTStream(@RequestParam String query) { // 스트리밍 응답을 제공하는 메서드 시작(쿼리 문자열 입력)
        return service.answer(query); // OpenAiService에서 Flux<String> 스트림을 받아 그대로 반환
    } // legalBOTStream 메서드 끝
} // LegalBOT 컨트롤러 클래스 끝
