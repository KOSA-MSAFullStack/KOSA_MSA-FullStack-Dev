// ArticleService.java
// Service Layer 작성

package net.developia.service;

import java.util.List;

import net.developia.dto.ArticleDTO;

public interface ArticleService {
   
    // insert
    void insertArticle(ArticleDTO articleDTO) throws Exception;    
    //리스트처리
    List<ArticleDTO> getArticleList() throws Exception;
    //상세보기
    ArticleDTO getDetail(long no) throws Exception;
    //글 삭제
    void deleteArticle(ArticleDTO articleDTO) throws Exception;
    //업데이트전 글찾기
    ArticleDTO getArticle(long no) throws Exception;
    //글변경
    void updateArticle(ArticleDTO articleDTO) throws Exception;
}

