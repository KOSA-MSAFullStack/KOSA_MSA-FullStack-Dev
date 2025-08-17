package net.developia.dao;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import net.developia.dto.ArticleDTO;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests5 {	
	@Autowired
	private ArticleDAO mapper;
	
	@Test
	public void testUpdate() throws SQLException {
		ArticleDTO board = new ArticleDTO();
		//실행전 존재하는 번호인지 확인
		board.setNo(1);
		board.setName("수정 이름");
		board.setTitle("수정된 제목");
		board.setContent("수정된 내용");
		board.setPassword("1234");		
		int count = mapper.updateArticle(board);
		log.info("Update COUNT" + count);				
	}	
}//end class
