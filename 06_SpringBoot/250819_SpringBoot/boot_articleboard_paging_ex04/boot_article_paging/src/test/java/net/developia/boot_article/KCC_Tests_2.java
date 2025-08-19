package net.developia.boot_article;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import net.developia.boot_article.dao.ArticleDAO;
import net.developia.boot_article.dto.ArticleDTO;
import net.developia.boot_article.dto.Criteria;

@SpringBootTest
public class KCC_Tests_2 {
	@Autowired
	private ArticleDAO mapper;

	@Test
	public void testGetList() {

		Criteria cri = new Criteria();
		cri.setPageNum(1);
		cri.setAmount(5);
		List<ArticleDTO> list = mapper.getListWithPaging2(cri);
		list.forEach(board -> System.out.println(board.getNo()));
	}// end void

}
