package com.bharath.springai.services;

import java.util.List;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OpenAiService {

	// 유사도 검색을 수행하기 위해 VectorStore 주입
	@Autowired
	private VectorStore vectorStore;

	// 주어진 질의(query)를 기반으로 유사한 채용 문서를 검색
	public List<Document> searchJobs(String query) {
		// 질의 문자열을 사용하여 유사도 검색 수행
		return vectorStore.similaritySearch(SearchRequest.query(query));
	}

} // end class
