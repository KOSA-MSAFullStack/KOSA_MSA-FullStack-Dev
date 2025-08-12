package com.jino.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.jino.domain.ReplyVO;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyServiceTest2 {
	
	@Autowired
	private ReplyService  service;
	
	@Test
	public void testExists() {
		log.info(service);
		ReplyVO board = new ReplyVO() ;
		board.setBno(11); //db에서 확인후 진행		
		board.setReplytext("서비스 새로작성하는글");
		board.setReplyer("newbie");
		service.insert(board);
		log.info(board);
	}//end test

}//end clas
