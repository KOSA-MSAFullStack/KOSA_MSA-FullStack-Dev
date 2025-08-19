package net.developia.boot_article;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import net.developia.boot_article.dao.ArticleDAO;



@SpringBootTest
public class KCC_Tests_1 {
	@Autowired
	private  ArticleDAO mapper;
	
	@Test
	public void testGetList() {
		mapper.getListWithPaging(2)
		.forEach( i -> System.out.println(i) )	;
	}//end void

}
