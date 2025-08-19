package net.developia.boot_article;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import net.developia.boot_article.dto.ArticleDTO;
import net.developia.boot_article.service.ArticleService;


@SpringBootTest
public class testInsert {
   @Autowired
   private ArticleService articleService;
   @Test
   public void inserttest() throws Exception {
       ArticleDTO articleDTO = ArticleDTO.builder()
               .name("test User")
               .password("1111")
               .title("test title")
               .content("test content")
               .build();
       articleService.insertArticle(articleDTO);
   }//end insert..
}//end class



