package com.bharath.springai.speech; // 패키지 선언

import java.io.IOException; // 입출력 예외 처리 임포트
import org.springframework.beans.factory.annotation.Autowired; // 스프링 의존성 주입 어노테이션 임포트
import org.springframework.http.HttpHeaders; // HTTP 헤더 클래스 임포트
import org.springframework.http.HttpStatus; // HTTP 상태 코드 클래스 임포트
import org.springframework.http.ResponseEntity; // HTTP 응답 객체 임포트
import org.springframework.stereotype.Controller; // 스프링 MVC 컨트롤러 어노테이션 임포트
import org.springframework.web.bind.annotation.GetMapping; // GET 요청 매핑 어노테이션 임포트
import org.springframework.web.bind.annotation.RequestParam; // 요청 파라미터 매핑 어노테이션 임포트
import com.bharath.springai.services.OpenAiService; // OpenAI 서비스 클래스 임포트

@Controller // 스프링 MVC 컨트롤러로 등록
public class TextToSpeechController {

    // OpenAI 텍스트-음성 변환 서비스를 스프링 컨테이너에서 주입받음
    @Autowired
    private OpenAiService service;

    // 텍스트 입력 폼을 보여주는 메서드
    @GetMapping("/showTextToSpeech")
    public String showUploadForm() throws IOException {
        return "textToSpeech"; // textToSpeech.html 뷰 반환
    }

    // 입력된 텍스트를 받아서 MP3 오디오 스트림을 반환하는 메서드
    @GetMapping("/textToSpeech")
    public ResponseEntity<byte[]> streamAudio(@RequestParam String text) throws IOException {

        // 응답 헤더 설정: 오디오 스트리밍(MP3) 형식으로 브라우저에 전송
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "audio/mpeg"); // 콘텐츠 타입: MP3
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=output.mp3"); 
        // 브라우저에서 다운로드 대신 바로 실행되도록 inline 지정

        // 디버깅을 위해 입력된 텍스트 콘솔 출력
        System.out.println(text);

        // OpenAiService를 호출해 생성된 오디오 데이터를 가져와 응답 본문으로 반환
        return new ResponseEntity<>(service.textToSpeech(text), headers, HttpStatus.OK);
    }
}
