package net.developia.boot_article;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import net.developia.boot_article.dto.ArticleDTO;
import net.developia.boot_article.service.ArticleService;


@SpringBootTest
public class testdelete {
   @Autowired
   private ArticleService articleService;
   @Test
   public void inserttest() throws Exception {
       ArticleDTO articleDTO = new ArticleDTO();
       //DB에 데이터 존재해야함
       articleDTO.setNo(4);
       articleDTO.setPassword("1234");


       System.out.println(articleDTO.toString());
       articleService.deleteArticle(articleDTO);
   }//end insert..
}//end class



