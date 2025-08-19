package net.developia.boot_article;

import net.developia.boot_article.dto.ArticleDTO;
import net.developia.boot_article.service.ArticleService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

@SpringBootTest
public class testlist {
   @Autowired
   private ArticleService articleService;
   

   @DisplayName("list 확인")
   @Test
   public void test1() throws Exception {

       System.out.println("test");
       List<ArticleDTO> replyBoardList = articleService.getArticleList();
       System.out.println("1번째 방법");
       replyBoardList.forEach(
               i ->
                       System.out.println(i.toString())
       );//end forEach
       System.out.println("2번째 방법");
       for (ArticleDTO i : replyBoardList) {
           System.out.println(i.toString());
       }//end for

   }//end test
}//end class