package com.bharath.springai.services; // 패키지 선언

import org.springframework.ai.audio.transcription.AudioTranscriptionPrompt; 
// 오디오 전사 프롬프트 관련 클래스 임포트
import org.springframework.ai.openai.OpenAiAudioTranscriptionOptions; 
// OpenAI 전사 옵션 클래스 임포트
import org.springframework.ai.openai.api.OpenAiAudioApi.TranscriptResponseFormat; 
// 전사 결과 형식 enum 임포트
import org.springframework.beans.factory.annotation.Autowired; 
// 스프링 의존성 주입 어노테이션 임포트
import org.springframework.stereotype.Service; 
// 스프링 서비스 컴포넌트 임포트
import org.springframework.core.io.FileSystemResource; 
// 파일 시스템 리소스 임포트
import org.springframework.ai.openai.OpenAiAudioTranscriptionModel; 
// OpenAI 오디오 전사 모델 임포트

@Service // 서비스 컴포넌트로 등록
public class OpenAiService {

	// OpenAI 오디오 전사 모델을 스프링 컨테이너로부터 주입받음
	@Autowired
	private OpenAiAudioTranscriptionModel openaiAudioTranscriptionModel;

	/**
	 * 오디오 파일을 OpenAI 전사 모델을 이용하여 텍스트로 변환하는 메서드
	 *
	 * @param path 오디오 파일의 전체 경로
	 * @param lan  전사할 언어 코드 (예: "ko", "en")
	 * @return VTT(WebVTT) 형식의 전사 결과 텍스트
	 */
	public String speechToText(String path, String lan) {
		// 전사 옵션 생성: 언어와 VTT 출력 포맷 지정
		OpenAiAudioTranscriptionOptions options = 
				OpenAiAudioTranscriptionOptions.builder().language(lan) // 언어 지정
				.responseFormat(TranscriptResponseFormat.VTT) 
				// WebVTT 자막 형식으로 결과 반환
				.build();

		// 파일 경로와 전사 옵션을 포함한 전사 프롬프트 생성
		AudioTranscriptionPrompt transcriptionPrompt = 
				new AudioTranscriptionPrompt(new FileSystemResource(path), // 파일리소스 전달
				options // 번역 옵션 전달
		);

		// 모델을 호출하여 전사 결과를 받아 반환
		return openaiAudioTranscriptionModel.call(transcriptionPrompt) // 프롬프트 전달하여 호출
				.getResult() // 결과 객체 가져오기
				.getOutput(); // 최종 출력 텍스트 반환
	}

} // end class
