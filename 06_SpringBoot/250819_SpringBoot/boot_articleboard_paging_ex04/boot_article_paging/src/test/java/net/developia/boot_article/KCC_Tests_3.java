package net.developia.boot_article;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import net.developia.boot_article.dao.ArticleDAO;
import net.developia.boot_article.dto.Criteria;

@SpringBootTest
public class KCC_Tests_3 {

	@Autowired
	private ArticleDAO service;

	@Test
	public void testGetList() {
		service.getListWithPaging2(
				new Criteria(1, 5))
		.forEach(board -> System.out.println(board));
	}// end test

}
