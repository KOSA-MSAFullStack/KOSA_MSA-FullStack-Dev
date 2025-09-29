package com.bharath.springai.imageprocessing; // 패키지 선언

import java.io.IOException; // 입출력 예외 처리 임포트
import java.net.URLEncoder; // URL 인코딩 유틸 임포트
import java.nio.charset.StandardCharsets; // 문자셋 상수 임포트
import java.nio.file.Files; // 파일 유틸 임포트
import java.nio.file.Path; // 경로 타입 임포트
import java.nio.file.Paths; // 경로 유틸 임포트
import java.nio.file.StandardOpenOption; // 파일 열기 옵션 임포트
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource; // 리소스 타입 임포트
import org.springframework.core.io.UrlResource; // URL 기반 리소스 임포트
import org.springframework.http.HttpHeaders; // HTTP 헤더 상수 임포트
import org.springframework.http.MediaType; // 미디어 타입 임포트
import org.springframework.http.ResponseEntity; // 응답 엔티티 임포트
import org.springframework.stereotype.Controller; // 컨트롤러 애노테이션 임포트
import org.springframework.ui.Model; // 모델 객체 임포트
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable; // 경로 변수 임포트
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam; // 요청 파라미터 임포트
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile; // 멀티파트 파일 임포트
import com.bharath.springai.services.OpenAiService; // 사용자 정의 OpenAiService 임포트
import reactor.core.publisher.Flux; // 리액터 Flux 임포트

@Controller // 스프링 MVC 컨트롤러 지정
public class DietHelperController { // DietHelperController 클래스 선언

	private static final String UPLOAD_DIR = "C:\\upload3"; // 업로드 디렉터리 절대 경로 상수

	@Autowired // 스프링이 OpenAiService 빈 주입
	private OpenAiService service; // AI 서비스 필드

	@GetMapping("/showDietHelper") // 업로드 폼 페이지 진입 GET 매핑
	public String showUploadForm() { // 업로드 폼 뷰 반환 메서드
		return "dietHelper"; // Thymeleaf 템플릿 이름 반환
	} // 메서드 종료

	@PostMapping("/dietHelper") // 업로드 및 분석 처리 POST 매핑
	public String dietHelper( // 업로드 처리 메서드 선언
			@RequestParam("prompt") String prompt, // 식단 관련 질의 프롬프트 파라미터
			@RequestParam("file1") MultipartFile file1, // 첫 번째 이미지 파일
			@RequestParam("file2") MultipartFile file2, // 두 번째 이미지 파일
			Model model) { // 뷰로 데이터 전달할 모델

		if (file1.isEmpty() || file2.isEmpty()) { 
			// 두 파일 모두 선택되었는지 검사
			model.addAttribute("message", "Please select a file to upload"); 
			// 업로드 요청 메시지 추가
			return "dietHelper"; // 폼 페이지로 복귀
		} // 검사 종료

		try { // 예외 처리 시작
			Path uploadDir = Paths.get(UPLOAD_DIR); // 업로드 디렉터리 경로 객체 생성
			if (Files.notExists(uploadDir)) { // 디렉터리가 없으면
				Files.createDirectories(uploadDir); // 디렉터리 생성
			} // 생성 종료

			String original1 = file1.getOriginalFilename(); // 파일1 원본 파일명
			String original2 = file2.getOriginalFilename(); // 파일2 원본 파일명

			Path path1 = uploadDir.resolve(original1); // 파일1 저장 경로 계산
			Path path2 = uploadDir.resolve(original2); // 파일2 저장 경로 계산

			Files.write(path1, file1.getBytes(), StandardOpenOption.CREATE); 
			// 파일1 저장
			Files.write(path2, file2.getBytes(), StandardOpenOption.CREATE); 
			// 파일2 저장

			String url1 = "/image/raw/" + 
			URLEncoder.encode(original1, StandardCharsets.UTF_8);
			// 이미지1 접근 URL 생성
			String url2 = "/image/raw/" + 
			URLEncoder.encode(original2, StandardCharsets.UTF_8);
			// 이미지2 접근 URL 생성

			model.addAttribute("imageUrl1", url1); // 이미지1 URL 모델 추가
			model.addAttribute("imageUrl2", url2); // 이미지2 URL 모델 추가
			model.addAttribute("prompt", prompt); 
			// 입력 프롬프트 모델 추가
			model.addAttribute("message", "Upload success"); 
			// 성공 메시지 추가

			System.out.println(prompt); // 디버깅: 프롬프트 출력
			System.out.println("============================"); // 구분선 출력

			// 업로드 이후 프론트에서 NDJSON 스트림을 받을 때 사용할 파일명도 함께 내려주면 편리 // 안내 주석
			model.addAttribute("fileName1", original1); 
			// 파일1 이름 모델 추가
			model.addAttribute("fileName2", original2); 
			// 파일2 이름 모델 추가
		} catch (IOException e) { // 파일 저장/접근 예외 처리
			e.printStackTrace(); // 스택 트레이스 출력
			model.addAttribute("message", "Failed to upload file"); // 실패 메시지 설정
		} // 예외 처리 종료

		return "dietHelper"; // 같은 페이지에서 렌더링
	} // 메서드 종료

	@PostMapping( // NDJSON 스트리밍 전용 엔드포인트 매핑
			value = "/dietHelper/ndjson", // 경로 지정
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, // 폼 URL 인코딩 소비
			produces = MediaType.APPLICATION_NDJSON_VALUE // NDJSON 생산(라인 단위 JSON)
	) // 애노테이션 종료
	@ResponseBody // ← 추가
	public Flux<String> dietHelperNdjson( // NDJSON 문자열 라인 스트림 반환
			@RequestParam("prompt") String prompt, // 프롬프트 파라미터
			@RequestParam("file1") String file1Name, // 서버에 저장된 첫 번째 파일명
			@RequestParam("file2") String file2Name // 서버에 저장된 두 번째 파일명
	) { // 메서드 시작
		try { // 예외 처리 시작
			Path uploadDir = Paths.get(UPLOAD_DIR); // 업로드 디렉터리 
			Path path1 = uploadDir.resolve(file1Name); // 파일1 절대 경로 계산
			Path path2 = uploadDir.resolve(file2Name); // 파일2 절대 경로 계산

			Flux<String> result = service.getDietAdvice( // AI 식단 조언 텍스트 스트림 호출
					prompt, // 프롬프트 전달
					path1.toString(), // 파일1 경로
					path2.toString() // 파일2 경로
			);
			result.subscribe(System.out::println); // 스트림이 흘러올 때마다 한 줄씩 출력
			return result;

		} catch (Exception e) { // 상위 예외 처리
			return Flux.just(" getDietAdvice error"); // 즉시 에러 JSON 라인 방출
		} // 예외 처리 종료
	} // 메서드 종료

	@GetMapping("/image/raw/{filename:.+}") // 브라우저의 이미지 직접 요청 매핑
	public ResponseEntity<Resource> serveImage( // 이미지 스트리밍 응답 메서드
			@PathVariable("filename") String filename) { // 경로 변수로 파일명 받기
		try { // 예외 처리 시작
			Path uploadDir = Paths.get(UPLOAD_DIR); // 업로드 디렉터리 경로 참조
			Path target = uploadDir.resolve(filename);
			// 대상 파일의 절대 경로 계산
			Resource fileResource = new UrlResource(target.toUri());
			// 파일을 URL 리소스로 래핑
			String contentType = Files.probeContentType(target);
			// MIME 타입 추론
			if (contentType == null) { // null 방지
				contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE; 
				// 기본 바이너리 타입 지정
			} // null 처리 종료
			return ResponseEntity.ok() // 200 OK 빌더 시작
					.header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" 
			+ fileResource.getFilename() + "\"") // 인라인																														// 표시
					.contentType(MediaType.parseMediaType(contentType)) // 콘텐츠 타입 지정
					.body(fileResource); // 파일 리소스 본문으로 반환
		} catch (Exception e) { // URL 변환 실패 예외 처리
			e.printStackTrace(); // 스택 트레이스 출력
			return ResponseEntity.internalServerError().build(); // 500 서버 오류 반환
		} // 예외 처리 종료
	} // 메서드 종료

} // 클래스 종료
