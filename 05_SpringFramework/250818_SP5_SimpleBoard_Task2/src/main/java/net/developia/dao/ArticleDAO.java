package net.developia.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import net.developia.dto.ArticleDTO;
import net.developia.dto.Criteria;

@Mapper
public interface ArticleDAO {
	// 게시글 등록
	void insertArticle(ArticleDTO articleDTO) throws SQLException; 

	// 게시글 목록
	List<ArticleDTO> getArticleList() throws SQLException;

	// 상세보기
	ArticleDTO getDetail(long no) throws SQLException;

	// 게시글 삭제
	int deleteArticle(ArticleDTO articleDTO) throws SQLException;

	// 게시글 수정
	int updateArticle(ArticleDTO articleDTO) throws SQLException;

	
	// 페이징을 적용한 게시글 목록
	List<ArticleDTO> getListWithPaging(Criteria cri) throws SQLException;

	// 게시글 총 개수
	int getTotalCount(Criteria cri) throws SQLException;
}

