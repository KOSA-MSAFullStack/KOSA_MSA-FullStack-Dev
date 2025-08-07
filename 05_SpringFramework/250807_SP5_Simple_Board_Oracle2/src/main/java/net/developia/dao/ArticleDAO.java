// ArticleDAO.java

package net.developia.dao;

import java.sql.SQLException;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import net.developia.dto.ArticleDTO;

@Mapper
public interface ArticleDAO {
    // insert
    void insertArticle(ArticleDTO articleDTO) throws SQLException;
    
    //list
    List<ArticleDTO> getArticleList() throws SQLException;
    
    //상세보기
    ArticleDTO getDetail(long no) throws SQLException;
    
    //게시글 삭제
    int deleteArticle(ArticleDTO articleDTO) throws SQLException;
    
    //게시글 수정
    int updateArticle(ArticleDTO articleDTO) throws SQLException;
}
