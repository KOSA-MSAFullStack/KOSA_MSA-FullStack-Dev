package net.developia.boot_article;


import net.developia.boot_article.dto.ArticleDTO;
import net.developia.boot_article.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class tesUpdate {
   @Autowired
   private ArticleService articleService;
   @Test
   public void updatetest() throws Exception {
       ArticleDTO articleDTO = new ArticleDTO();
       articleDTO.setNo(2);
       articleDTO.setPassword("1111");
       articleDTO.setTitle("test update");
       articleDTO.setContent("test update");
       articleDTO.setName("test update");
       articleService.updateArticle(articleDTO);
   }//end insert..
}



