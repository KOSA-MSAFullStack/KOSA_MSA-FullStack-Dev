package net.developia.boot_article.dao;

import java.sql.SQLException;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import net.developia.boot_article.dto.ArticleDTO;

@Mapper
public interface ArticleDAO {
	// insert
	void insertArticle(ArticleDTO articleDTO) throws SQLException; 
	
	List<ArticleDTO> getArticleList() throws SQLException;	

	ArticleDTO getDetail(long no) throws SQLException;
	
	int deleteArticle(ArticleDTO articleDTO) throws SQLException;
	
	int updateArticle(ArticleDTO articleDTO) throws SQLException;


}//end int..