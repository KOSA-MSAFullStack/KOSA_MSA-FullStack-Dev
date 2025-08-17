		package net.developia.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import net.developia.dao.ArticleDAO;
import net.developia.dto.ArticleDTO;
import net.developia.dto.Criteria;

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
		}
	}

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
		}
	}
	
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
		}
	}
	
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
		}
	}
	
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
		}
	}


	// 페이징 적용한 게시글 목록 조회
	@Override
	public List<ArticleDTO> getArticleList(Criteria cri) throws Exception {
		try {
			// DB에서 페이징 처리된 게시글 목록 반환
			return articleDAO.getListWithPaging(cri);
		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		}
	}

	// 게시글 총 개수 조회
	@Override
	public int getTotal(Criteria cri) throws Exception {
		log.info("get total count");
		return articleDAO.getTotalCount(cri);
	}
}
