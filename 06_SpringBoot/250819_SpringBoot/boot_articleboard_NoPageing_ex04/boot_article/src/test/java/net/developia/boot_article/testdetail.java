package net.developia.boot_article;




import net.developia.boot_article.dto.ArticleDTO;
import net.developia.boot_article.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;


@SpringBootTest
public class testdetail {
   @Autowired
   private ArticleService articleService;




   @Test
   public void test1() throws Exception {


       System.out.println("test");
       ArticleDTO replyBoardList = articleService.getDetail(1);
       System.out.println(replyBoardList.toString());


   }
}



