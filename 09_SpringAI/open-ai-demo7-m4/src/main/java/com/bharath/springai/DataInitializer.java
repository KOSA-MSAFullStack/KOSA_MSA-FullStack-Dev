package com.bharath.springai;

import java.util.List;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;

@Component
public class DataInitializer {

	// 임베딩을 저장할 VectorStore 주입
	@Autowired
	private VectorStore vectorStore;

	// 빈이 초기화된 후 실행되는 메서드
	@PostConstruct
	public void init() {
		// classpath에서 job_listings.txt 파일 로드
		TextReader jobListReader = new TextReader(new ClassPathResource("job_listings.txt"));

		// 설정과 함께 텍스트 분할기 초기화
		TokenTextSplitter tokenTextSplitter = new TokenTextSplitter(100, 100, 5, 1000, true);

		// 토큰 기반 분할기를 사용하여 텍스트를 문서로 분할
		List<Document> documents = tokenTextSplitter.split(jobListReader.get());

		// 문서가 성공적으로 생성되면 벡터 저장소에 추가
		if (documents != null && !documents.isEmpty()) {
			vectorStore.add(documents);
		} else {
			// 문서가 생성되지 않았을 경우 로그 메시지 출력
			System.out.println("❌ 문서가 생성되지 않았습니다. 입력 파일이나 분할 로직을 확인하세요.");
		}
	}

} // end class
