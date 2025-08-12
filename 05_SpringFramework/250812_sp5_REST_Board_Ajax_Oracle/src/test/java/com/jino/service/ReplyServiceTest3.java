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
public class ReplyServiceTest3 {
	
	@Autowired
	private ReplyService  service;
	
	@Test
	public void testExists() {
		ReplyVO board = new ReplyVO() ;
		//실행전 존재하는 번호인지 확인
		board.setRno(7);
		board.setReplytext("service 수정된 내용");
		board.setReplyer("user00");		
		int count = service.update(board);
		log.info("Update COUNT" + count);
	}//end test

}//end clas
