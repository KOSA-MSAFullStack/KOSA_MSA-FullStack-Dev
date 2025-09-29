package com.bharath.springai.imageprocessing; // 패키지 선언

import java.io.IOException; // 입출력 예외 처리 임포트
import java.net.MalformedURLException; // URL 리소스 변환 예외 임포트
import java.nio.file.Files; // 파일 유틸 임포트
import java.nio.file.Path; // 경로 타입 임포트
import java.nio.file.Paths; // 경로 유틸 임포트
import java.nio.file.StandardOpenOption; // 파일 저장 옵션 임포트

import org.springframework.beans.factory.annotation.Autowired; // 의존성 주입 임포트
import org.springframework.core.io.Resource; // 스프링 리소스 인터페이스 임포트
import org.springframework.core.io.UrlResource; // 파일 시스템 리소스를 URL로 감싸는 구현체 임포트
import org.springframework.http.HttpHeaders; // HTTP 헤더 유틸 임포트
import org.springframework.http.MediaType; // 미디어 타입 유틸 임포트
import org.springframework.http.ResponseEntity; // 응답 본문/헤더/상태를 감싸는 타입 임포트
import org.springframework.stereotype.Controller; // 스프링 MVC 컨트롤러 임포트
import org.springframework.ui.Model; // 뷰로 데이터 전달용 모델 임포트
import org.springframework.util.StringUtils; // 문자열 유틸 (파일명 정리 등) 임포트
import org.springframework.web.bind.annotation.GetMapping; // GET 매핑 임포트
import org.springframework.web.bind.annotation.PathVariable; // 경로 변수 바인딩 임포트
import org.springframework.web.bind.annotation.PostMapping; // POST 매핑 임포트
import org.springframework.web.bind.annotation.RequestParam; // 요청 파라미터 바인딩 임포트
import org.springframework.web.multipart.MultipartFile; // 파일 업로드용 타입 임포트
import org.springframework.web.servlet.mvc.support.RedirectAttributes; // 리다이렉트 속성 임포트

import com.bharath.springai.services.OpenAiService; // OpenAI 연동 서비스 임포트

@Controller // 스프링 MVC 컨트롤러 선언
public class ImageAnalyzerController { // 이미지 분석 컨트롤러 클래스 선언

    private static final String UPLOAD_DIR = "C:\\upload3"; // 업로드 디렉터리(윈도우 기준 경로) 상수 선언

    @Autowired // 의존성 주입
    private OpenAiService service; // 이미지 설명 생성을 위한 서비스 빈

    @GetMapping("/showImageAnalyzer") // 업로드 폼을 보여줄 GET 매핑
    public String showUploadForm() { // 업로드 폼 메서드 선언
        return "imageAnalyzer"; // 타임리프 템플릿 이름 반환
    } // 메서드 종료

    @PostMapping("/imageAnalyzer") // 업로드 및 설명 처리용 POST 매핑
    public String uploadImage( // 업로드 처리 메서드 선언
            @RequestParam(name = "prompt", required = false) String prompt, // 사용자가 입력한 프롬프트 파라미터
            @RequestParam("file") MultipartFile file, // 업로드된 이미지 파일 파라미터
            Model model, // 뷰로 데이터 전달을 위한 모델
            RedirectAttributes redirectAttributes) { // 리다이렉트 속성 (현재는 사용 안 함)

        if (file.isEmpty()) { // 파일 비어 있음 검사
            model.addAttribute("message", "Please select a file to upload"); // 에러 메시지 모델에 추가
            return "imageAnalyzer"; // 폼 페이지로 그대로 반환
        } // if 종료

        try { // 예외 처리 시작
            Path uploadDir = Paths.get(UPLOAD_DIR); // 업로드 디렉터리 경로 객체 생성
            if (Files.notExists(uploadDir)) { // 디렉터리가 없으면
                Files.createDirectories(uploadDir); // 디렉터리 생성
            } // 디렉터리 생성 분기 종료

            String cleanedFilename = StringUtils.cleanPath(file.getOriginalFilename()); // 파일명 정규화(불필요한 경로 요소 제거)
            if (cleanedFilename.contains("..")) { // 경로 역참조 방지 검사
                model.addAttribute("message", "Invalid file name"); // 유효하지 않은 파일명 메시지
                return "imageAnalyzer"; // 폼 페이지로 반환
            } // 역참조 검사 종료

            Path target = uploadDir.resolve(cleanedFilename).normalize(); // 저장 경로 결합 후 정규화
            if (!target.startsWith(uploadDir)) { // 업로드 디렉터리 바깥으로 나가는지 최종 방지
                model.addAttribute("message", "Invalid file path"); // 유효하지 않은 파일 경로 메시지
                return "imageAnalyzer"; // 폼 페이지로 반환
            } // 디렉터리 이탈 방지 종료

            Files.write(target, file.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING); // 파일 저장/덮어쓰기

            String detectedContentType = Files.probeContentType(target); // 운영체제/파일시스템 기반 콘텐츠 타입 추론
            if (detectedContentType == null || !detectedContentType.startsWith("image/")) { // 이미지 MIME 타입인지 확인
                model.addAttribute("message", "Only image files are allowed"); // 이미지 파일만 허용 메시지
                Files.deleteIfExists(target); // 이미지가 아니면 저장 파일 삭제
                return "imageAnalyzer"; // 폼 페이지로 반환
            } // MIME 타입 검사 종료

            String response = service.explainImage(prompt, target.toString()); // OpenAI 서비스 호출로 이미지 설명 획득

            model.addAttribute("explanation", response); // 설명 결과 모델에 추가
            model.addAttribute("filename", cleanedFilename); // 브라우저가 직접 받을 수 있도록 파일명 모델에 추가

            System.out.println(prompt); // 디버깅용 프롬프트 출력
            System.out.println("============================"); // 구분선 출력
            System.out.println(response); // 디버깅용 결과 출력

        } catch (IOException e) { // 입출력 예외 처리
            e.printStackTrace(); // 스택 트레이스 출력
            model.addAttribute("message", "Failed to upload file"); // 업로드 실패 메시지 모델에 추가
        } // try-catch 종료

        return "imageAnalyzer"; // 결과를 같은 페이지에서 렌더링
    } // 메서드 종료

    @GetMapping("/image/raw/{filename:.+}") // 브라우저가 이미지를 직접 요청할 GET 매핑 (파일명 경로 변수)
    public ResponseEntity<Resource> serveImage(@PathVariable("filename") String filename) { // 이미지 스트리밍 메서드 선언
        try { // 예외 처리 시작
            Path uploadDir = Paths.get(UPLOAD_DIR); // 업로드 디렉터리 경로 객체 생성
            String cleanedFilename = StringUtils.cleanPath(filename); // 파일명 정규화
            if (cleanedFilename.contains("..")) { // 경로 역참조 방지 검사
                return ResponseEntity.badRequest().build(); // 잘못된 요청 응답
            } // 역참조 검사 종료

            Path target = uploadDir.resolve(cleanedFilename).normalize(); // 요청 파일의 절대 경로 계산
            if (!target.startsWith(uploadDir)) { // 디렉터리 이탈 방지
                return ResponseEntity.badRequest().build(); // 잘못된 요청 응답
            } // 디렉터리 이탈 방지 종료

            if (!Files.exists(target)) { // 파일 존재 여부 확인
                return ResponseEntity.notFound().build(); // 404 응답
            } // 존재 여부 검사 종료

            Resource fileResource = new UrlResource(target.toUri()); // 파일 경로를 UrlResource로 감싸서 리소스 생성
            if (!fileResource.exists() || !fileResource.isReadable()) { // 읽기 가능 여부 검증
                return ResponseEntity.notFound().build(); // 404 응답
            } // 읽기 가능 여부 검증 종료

            String contentType = Files.probeContentType(target); // MIME 타입 탐지
            if (contentType == null) { // MIME 타입을 못 찾은 경우
                contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE; // 일반 바이너리로 기본 설정
            } // MIME 기본 설정 종료

            return ResponseEntity.ok() // 200 OK 응답 생성 시작
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + fileResource.getFilename() + "\"") // 브라우저에 인라인 표시 지시
                    .contentType(MediaType.parseMediaType(contentType)) // 콘텐츠 타입 설정
                    .body(fileResource); // 리소스 본문으로 전송
        } catch (MalformedURLException e) { // URL 변환 실패 예외 처리
            return ResponseEntity.internalServerError().build(); // 500 응답
        } catch (IOException e) { // 파일 접근 중 예외 처리
            return ResponseEntity.internalServerError().build(); // 500 응답
        } // try-catch 종료
    } // 메서드 종료
} // 클래스 종료
