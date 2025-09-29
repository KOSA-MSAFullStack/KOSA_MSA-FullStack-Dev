package com.bharath.springai.services; // 패키지 선언

import org.springframework.ai.image.ImagePrompt; // 이미지 프롬프트 클래스 import
import org.springframework.ai.image.ImageResponse; // 이미지 응답 클래스 import
import org.springframework.ai.openai.OpenAiImageModel; // OpenAI 이미지 모델 클래스 import
import org.springframework.ai.openai.OpenAiImageOptions; // OpenAI 이미지 옵션 클래스 import
import org.springframework.beans.factory.annotation.Autowired; // 의존성 주입을 위한 Autowired import
import org.springframework.stereotype.Service; // 서비스 컴포넌트 선언용 어노테이션 import

@Service // 해당 클래스를 Spring 서비스 컴포넌트로 등록
public class OpenAiService { // OpenAiService 클래스 선언

	// OpenAI 이미지 생성을 담당하는 모델을 주입받음
	@Autowired
	private OpenAiImageModel openaiImageModel; // OpenAI 이미지 모델 객체

	/**
	 * 주어진 프롬프트를 기반으로 OpenAI의 이미지 생성 API를 사용하여 이미지를 생성하는 메소드
	 *
	 * @param prompt 생성할 이미지에 대한 텍스트 설명
	 * @return 생성된 이미지의 URL
	 */
	public String generateImage(String prompt) { // 이미지 생성 메소드 선언
		// 사용자 프롬프트와 옵션을 포함한 ImagePrompt 객체 생성
		ImageResponse response = openaiImageModel.call(new ImagePrompt(
			prompt, // 생성할 이미지 설명
			OpenAiImageOptions.builder() // 이미지 옵션 빌더 시작
				.withQuality("hd") // 이미지 품질을 HD로 설정
				.withHeight(1024) // 이미지 높이 1024px로 설정
				.withWidth(1024) // 이미지 너비 1024px로 설정
				.withN(1) // 생성할 이미지 개수를 1개로 설정
				.build() // 옵션 빌드 완료
		));

		// 생성된 이미지의 URL 반환
		return response.getResult().getOutput().getUrl(); 
	} // generateImage 메소드 끝

} // OpenAiService 클래스 끝
