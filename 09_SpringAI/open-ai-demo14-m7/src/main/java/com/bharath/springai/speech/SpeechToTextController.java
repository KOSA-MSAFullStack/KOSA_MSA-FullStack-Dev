package com.bharath.springai.speech; // 패키지 선언

import java.io.IOException; // 입출력 예외 처리 임포트
import java.nio.file.Files; // 파일 처리 유틸 임포트
import java.nio.file.Path; // 파일 경로 클래스 임포트
import java.nio.file.Paths; // 경로 유틸 클래스 임포트
import java.nio.file.StandardOpenOption; // 파일 쓰기 옵션 임포트
import org.springframework.beans.factory.annotation.Autowired; 
// 스프링 의존성 주입 어노테이션 임포트
import org.springframework.stereotype.Controller; 
// 스프링 MVC 컨트롤러 어노테이션 임포트
import org.springframework.ui.Model; // 뷰에 데이터 전달용 Model 임포트
import org.springframework.web.bind.annotation.GetMapping; 
// GET 요청 매핑 어노테이션 임포트
import org.springframework.web.bind.annotation.PostMapping; 
// POST 요청 매핑 어노테이션 임포트
import org.springframework.web.bind.annotation.RequestParam; 
// 요청 파라미터 매핑 어노테이션 임포트
import org.springframework.web.multipart.MultipartFile; 
// 파일 업로드 처리 클래스 임포트
import org.springframework.web.servlet.mvc.support.RedirectAttributes; 
// 리다이렉트 시 속성 전달 클래스 임포트
import com.bharath.springai.services.OpenAiService; // OpenAI 서비스 클래스 임포트

@Controller // 스프링 MVC 컨트롤러로 등록
public class SpeechToTextController {

    // 업로드된 오디오 파일을 저장할 디렉토리 상수 정의
    private static final String UPLOAD_DIR = "C:\\upload3";

    // OpenAI 전사 서비스를 스프링 컨테이너로부터 주입
    @Autowired
    private OpenAiService service;

    // 음성-텍스트 변환 업로드 폼을 보여주는 메서드
    @GetMapping("/showSpeechToText")
    public String showSpeechToText() {
        return "speechToText"; // speechToText.html 뷰 반환
    }

    // 오디오 파일 업로드와 전사 처리 담당 메서드
    @PostMapping("/speechToText")
    public String speechToText(
            @RequestParam("file") MultipartFile file, // 업로드된 파일
            @RequestParam("lan") String lan,          // 언어 코드 (예: "ko", "en")
            Model model,                              // 뷰에 데이터 전달
            RedirectAttributes redirectAttributes) {  // 리다이렉트 시 속성 전달

        // 업로드 파일 유효성 검사
        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a file to upload"); 
            // 오류 메시지 전달
            return "speechToText"; // 업로드 폼으로 다시 이동
        }

        try {
            // 업로드 디렉토리가 존재하지 않으면 생성
            Path uploadDir = Paths.get(UPLOAD_DIR);
            if (Files.notExists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }

            // 업로드된 오디오 파일을 서버에 저장
            Path path = uploadDir.resolve(file.getOriginalFilename()); 
            // 저장 경로 생성
            Files.write(path, file.getBytes(), StandardOpenOption.CREATE);
            // 파일 쓰기            

            // OpenAI 서비스 호출하여 음성을 텍스트로 변환
            String transcription = service.speechToText(path.toString(), lan);

            // 전사 결과 로그 출력 및 뷰에 전달
            System.out.println(transcription);
            model.addAttribute("transcription", transcription);

        } catch (IOException e) {
            e.printStackTrace(); // 예외 스택 출력
            model.addAttribute("message", "Failed to upload file"); // 실패 메시지 전달
        }

        return "speechToText"; // 결과를 보여줄 뷰 반환
    }
}
