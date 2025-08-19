package net.developia.boot_article.service;

import java.util.List;
import net.developia.boot_article.dto.ArticleDTO;

public interface ArticleService {
	void insertArticle(ArticleDTO articleDTO) throws Exception;
	List<ArticleDTO> getArticleList() throws Exception;
	ArticleDTO getDetail(long no) throws Exception;
	void deleteArticle(ArticleDTO articleDTO) throws Exception;
	ArticleDTO getArticle(long no) throws Exception;
	void updateArticle(ArticleDTO articleDTO) throws Exception;

}//end int