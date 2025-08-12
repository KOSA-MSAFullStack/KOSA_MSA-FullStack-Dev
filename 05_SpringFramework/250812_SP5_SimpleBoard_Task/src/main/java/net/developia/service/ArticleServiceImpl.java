package net.developia.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import net.developia.dao.ArticleDAO;
import net.developia.dto.ArticleDTO;

@Slf4j
@Service
public class ArticleServiceImpl  implements ArticleService{
	
	@Autowired
	private ArticleDAO articleDAO;
	 
	@Override
	public void insertArticle(ArticleDTO articleDTO) throws Exception {
		try {
			//디비에서 insert
			articleDAO.insertArticle(articleDTO);
			log.info("게시물 입력 성공");
		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		}//end try
	}//end insert..
	
	@Override
	public List<ArticleDTO> getArticleList() throws Exception {
		try {
			//디비에서 리스트 반환 작업
			return articleDAO.getArticleList();
		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		}//end try
	}//end getArticleList
	
	@Override
	public ArticleDTO getDetail(long no) throws Exception {
		try {
			//디비에서 no로 검색
			ArticleDTO articleDTO = articleDAO.getDetail(no);
			//글을 못찾으면
			if (articleDTO == null) {
				throw new RuntimeException("없는 게시물이거나 접근 권한이 없습니다.");
			}
			//글을 찾으면 반환
			return articleDTO;
		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		}//end try
	}//end getDetail
	
	@Override
	public void deleteArticle(ArticleDTO articleDTO) throws Exception {
		try {
			//비밀번호 불일치시
			if (articleDAO.deleteArticle(articleDTO) == 0) {
				throw new RuntimeException("비밀번호가 일치하지 않습니다.");
			}
		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		}//end try
	}//end deleteArticle
	
	@Override
	public ArticleDTO getArticle(long no) throws Exception {
		try {
			//디비에서 no로 검색
			ArticleDTO articleDTO = articleDAO.getDetail(no);
			//글을 못찾으면
			if (articleDTO == null) {
				throw new RuntimeException("없는 게시물이거나 접근 권한이 없습니다.");
			}
			return articleDTO;
		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		}//end try
	}//end getArticle
	
	@Override
	public void updateArticle(ArticleDTO articleDTO) throws Exception {
		try {
			if (articleDAO.updateArticle(articleDTO) == 0) {
				throw new RuntimeException(
					"게시물이 존재하지 않거나 비밀번호가 일치하지 않습니다.");
			}
		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		}//end try
	}//end updateArticle



}//end ArticleServiceImpl
