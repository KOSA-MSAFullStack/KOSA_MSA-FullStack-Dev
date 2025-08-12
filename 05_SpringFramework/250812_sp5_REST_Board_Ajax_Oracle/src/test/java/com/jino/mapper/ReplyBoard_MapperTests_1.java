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
public class ReplyBoard_MapperTests_1 {	
	@Autowired
	private ReplyBoard mapper;	
	@Test
	public void testGetList() {
		mapper.list(1).forEach(vo -> log.info(vo));		
	}//end void	
}//end class