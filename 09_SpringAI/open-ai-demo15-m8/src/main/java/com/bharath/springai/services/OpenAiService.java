package com.bharath.springai.services; // 패키지 선언

import org.springframework.beans.factory.annotation.Autowired; // 스프링 의존성 주입 어노테이션 임포트
import org.springframework.stereotype.Service; // 서비스 컴포넌트 어노테이션 임포트
import org.springframework.ai.openai.OpenAiAudioSpeechModel; // OpenAI 음성 합성 모델 클래스 임포트
import org.springframework.ai.openai.OpenAiAudioSpeechOptions; // 음성 합성 옵션 클래스 임포트
import org.springframework.ai.openai.api.OpenAiAudioApi; // OpenAI 오디오 API 임포트
import org.springframework.ai.openai.audio.speech.SpeechPrompt; // 음성 프롬프트 클래스 임포트
import org.springframework.ai.openai.audio.speech.SpeechResponse; // 음성 응답 클래스 임포트

@Service // 해당 클래스를 스프링 서비스 빈으로 등록
public class OpenAiService {

    // OpenAI 텍스트-음성 변환 모델을 스프링 컨테이너에서 주입받음
    @Autowired
    private OpenAiAudioSpeechModel openaiAudioSpeechModel;

    /**
     * 입력된 텍스트를 음성으로 변환하는 간단한 메서드.
     * 기본 call() 메서드를 사용하여 별도의 옵션 없이 실행됨.
     * (음성, 형식, 속도와 같은 옵션 제어 불가)
     *
     * @param text 음성으로 변환할 입력 텍스트
     * @return 변환된 음성 데이터를 담은 byte 배열
     */
    public byte[] textToSpeech(String text) {
        // OpenAI 모델을 호출하여 기본 설정으로 텍스트를 음성으로 변환
        return openaiAudioSpeechModel.call(text);
    }

    /**
     * 입력된 텍스트를 OpenAI 음성 합성 모델을 이용하여
     * 사용자 정의 옵션(목소리, 오디오 형식, 속도 등)으로 변환하는 메서드.
     *
     * @param text 음성으로 변환할 입력 텍스트
     * @return 변환된 MP3 음성 데이터를 담은 byte 배열
     */
    public byte[] textToSpeech2(String text) {
        // 음성 합성 옵션을 빌드하여 구성
        OpenAiAudioSpeechOptions speechOptions = OpenAiAudioSpeechOptions.builder()
                .model("tts-1") // 사용할 음성 합성 모델 지정 (예: tts-1)
                .voice(OpenAiAudioApi.SpeechRequest.Voice.ASH) // 음성 선택 (예: ASH)
                .responseFormat(OpenAiAudioApi.SpeechRequest.AudioResponseFormat.MP3) // 출력 형식: MP3
                .speed(1.0f) // 재생 속도 설정 (1.0 = 기본 속도)
                .build();

        // 입력 텍스트와 옵션을 이용해 음성 프롬프트 생성
        SpeechPrompt speechPrompt = new SpeechPrompt(text, speechOptions);

        // OpenAI 모델 호출 후 음성 응답 객체 반환
        SpeechResponse response = openaiAudioSpeechModel.call(speechPrompt);

        // 응답 객체에서 최종 음성 데이터를 byte 배열로 추출
        byte[] responseAsBytes = response.getResult().getOutput();
        return responseAsBytes;
    }

} // end class
