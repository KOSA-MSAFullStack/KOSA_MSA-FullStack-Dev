package com.jino.mapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import lombok.extern.log4j.Log4j;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyBoardMapperTests4_delete {	
	@Autowired
	private ReplyBoard mapper;	
	@Test
	public void testDelete() {
		//db에 데이터 있는지 확인
		log.info("Delete count: " + mapper.delete(3) );
	}//end void	
	
}//end class