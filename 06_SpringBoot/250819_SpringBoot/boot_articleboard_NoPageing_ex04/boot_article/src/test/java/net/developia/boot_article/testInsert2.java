package net.developia.boot_article;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import net.developia.boot_article.dto.ArticleDTO;
import net.developia.boot_article.service.ArticleService;

@SpringBootTest
public class testInsert2 {
	
	@Autowired
	private ArticleService articleService;

	@DisplayName("setPassword 로 암호화 확인")
	@Test
	public void inserttest() throws Exception {
		ArticleDTO articleDTO = new ArticleDTO();
		articleDTO.setPassword("1111");
		articleDTO.setTitle("test title2");
		articleDTO.setContent("test content2");
		articleDTO.setName("test User2");		
		articleService.insertArticle(articleDTO);		
	}//end insert..
	
}//end class

