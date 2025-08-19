package net.developia.boot_article;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import net.developia.boot_article.dao.ArticleDAO;



@SpringBootTest
public class KCC_Tests_1_2 {
	@Autowired
	private  ArticleDAO mapper;
	
	@Test
	public void testGetList() {
		try {
			System.out.println( mapper.totalCount() );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//end void

}
