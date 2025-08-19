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
		ArticleDTO articleDTO = new ArticleDTO();
		articleDTO.setNo(1000000);
		articleDTO.setPassword("1111");
		articleDTO.setTitle("test title");
		articleDTO.setContent("test content");
		articleDTO.setName("test User");		
		articleService.insertArticle(articleDTO);		
	}//end insert..
	
}//end class
