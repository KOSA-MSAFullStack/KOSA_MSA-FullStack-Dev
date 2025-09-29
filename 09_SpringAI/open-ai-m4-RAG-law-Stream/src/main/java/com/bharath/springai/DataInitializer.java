package com.bharath.springai; // 패키지 선언

import java.util.List; // 리스트 컬렉션 사용
import org.springframework.ai.document.Document; // 스프링 AI 문서 모델
import org.springframework.ai.reader.TextReader; // 텍스트 파일을 읽는 유틸리티
import org.springframework.ai.transformer.splitter.TokenTextSplitter; // 토큰 기반 텍스트 분할기
import org.springframework.ai.vectorstore.VectorStore; // 벡터 스토어 인터페이스
import org.springframework.beans.factory.annotation.Autowired; // 의존성 주입 어노테이션
import org.springframework.core.io.ClassPathResource; // 클래스패스 리소스 접근
import org.springframework.stereotype.Component; // 스프링 컴포넌트 등록

import jakarta.annotation.PostConstruct; // 빈 초기화 이후 훅 실행 어노테이션

@Component // 스프링 컴포넌트로 등록
public class DataInitializer { // 데이터 초기화를 담당하는 클래스 시작

	// 임베딩을 저장할 VectorStore 주입
	@Autowired // 스프링이 빈을 자동 주입
	private VectorStore vectorStore; // 벡터 스토어 필드

	// 빈 초기화가 완료되면 실행될 메서드
	@PostConstruct // 컨테이너가 빈 생성 후 호출
	public void init() { // 초기화 로직 시작

		// 클래스패스에서 입력 파일을 읽기 위한 리더 생성
		TextReader productDataReader = new TextReader(new ClassPathResource("Legal_Document_Analysis_Data.txt")); // 법률 데이터 파일 로드

		// 토큰 기반 분할기를 사용해 컨텐츠를 청크로 분리
		// TokenTextSplitter // 아이디어: chunk=400, overlap=60 정도로 시작해서 A/B 튜닝 (권장 초기값 안내)
		TokenTextSplitter tokenTextSplitter = new TokenTextSplitter( // 토큰 분할기 인스턴스 생성
			    400,   // chunk size: 한 청크의 목표 토큰 수(권장 300~600)
			    200,   // 보조 파라미터: 최소 길이/절단 허용 등(버전에 따라 의미 다름)
			    60,    // overlap: 청크 간 겹침 토큰 수(권장 청크의 10~20%)
			    2048,  // 상한: 최대 분할 길이(모델 컨텍스트 고려해 여유 있게)
			    true   // 문장/경계 보존 시도(가능하면 경계 기준으로 분할)
			); // 분할기 생성 끝
		List<Document> documents = tokenTextSplitter.split(productDataReader.get()); // 파일 내용을 분할하여 Document 리스트 생성

		// 문서가 정상 생성되었다면 벡터 스토어에 추가
		if (documents != null && !documents.isEmpty()) { // 널 체크 및 비어있지 않은지 확인
			vectorStore.add(documents); // 생성된 문서들을 벡터 스토어에 업서트/인덱싱
		} else { // 문서 생성 실패 또는 빈 결과일 때
			// 문서가 생성되지 않았음을 로그로 출력
			System.out.println("❌ No documents generated. Check input file or splitting logic."); // 입력 파일/분할 로직 점검 필요 안내
		} // if-else 블록 끝
	} // init 메서드 끝

} // end class // 클래스 정의 끝
