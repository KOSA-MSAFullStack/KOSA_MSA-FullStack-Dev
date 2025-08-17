package net.developia.service;

import java.util.List;

import net.developia.dto.ArticleDTO;
import net.developia.dto.Criteria;

public interface ArticleService {
	
	// insert
	void insertArticle(ArticleDTO articleDTO) throws Exception;

	// 상세보기
	ArticleDTO getDetail(long no) throws Exception;

	// 글 삭제
	void deleteArticle(ArticleDTO articleDTO) throws Exception;

	// 업데이트전 글찾기
	ArticleDTO getArticle(long no) throws Exception;

	// 글변경
	void updateArticle(ArticleDTO articleDTO) throws Exception;


	// 리스트 처리
	List<ArticleDTO> getArticleList(Criteria cri) throws Exception;

	// 게시물 총 개수
	int getTotal(Criteria cri) throws Exception;
}

