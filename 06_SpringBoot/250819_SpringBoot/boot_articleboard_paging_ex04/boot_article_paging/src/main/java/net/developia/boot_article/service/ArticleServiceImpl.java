package net.developia.boot_article.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import net.developia.boot_article.dao.ArticleDAO;
import net.developia.boot_article.dto.ArticleDTO;
import net.developia.boot_article.dto.Criteria;

@Service
@Log4j2
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleDAO articleDAO;

	@Override
	public void insertArticle(ArticleDTO articleDTO) throws Exception {
		try {
			articleDAO.insertArticle(articleDTO);
			log.info("게시물 입력 성공");
		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		} // end try
	}// end insert..

	@Override
	public List<ArticleDTO> getArticleList() throws Exception {
		try {
			return articleDAO.getArticleList();
		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		} // end try
	}// end getAList...

	@Override
	public ArticleDTO getDetail(long no) throws Exception {
		try {
			ArticleDTO articleDTO = articleDAO.getDetail(no);
			if (articleDTO == null) {
				throw new RuntimeException("없는 게시물이거나 접근 권한이 없습니다.");
			}
			return articleDTO;
		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		} // end try
	}// end getDe..

	@Override
	public void deleteArticle(ArticleDTO articleDTO) throws Exception {
		try {
			if (articleDAO.deleteArticle(articleDTO) == 0) {
				throw new RuntimeException("비밀번호가 일치하지 않습니다.");
			}
		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		} // end try
	}// end delete..

	@Override
	public ArticleDTO getArticle(long no) throws Exception {
		try {
			ArticleDTO articleDTO = articleDAO.getDetail(no);
			if (articleDTO == null) {
				throw new RuntimeException("없는 게시물이거나 접근 권한이 없습니다.");
			}
			return articleDTO;
		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		} // end try
	}// end getArt..

	@Override
	public void updateArticle(ArticleDTO articleDTO) throws Exception {
		try {
			if (articleDAO.updateArticle(articleDTO) == 0) {
				throw new RuntimeException("게시물이 존재하지 않거나 비밀번호가 일치하지 않습니다.");
			}
		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		}//end try
	}//end updateA..

	@Override
	public List<ArticleDTO> getListWithPaging(int page) {
		try {
			return articleDAO.getListWithPaging(page);
		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		} // end try
	}

	@Override
	public List<ArticleDTO> getListWithPaging2(Criteria cri) {
		log.info("get List with criteria..." + cri);
		return articleDAO.getListWithPaging2(cri);

	}

	@Override
	public int getTotalCount() {
		
		try {
			return articleDAO.totalCount()  ;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

}// end class
