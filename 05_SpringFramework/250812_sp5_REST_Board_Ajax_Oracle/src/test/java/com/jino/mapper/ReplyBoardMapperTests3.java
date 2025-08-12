package com.jino.mapper;
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
public class ReplyBoardMapperTests3 {	
	@Autowired
	private ReplyBoard mapper;	
	@Test
	public void testUpdate() {
		ReplyVO board = new ReplyVO() ;
		//실행전 존재하는 번호인지 확인
		board.setRno(5);
		board.setReplytext("5수정된 내용");
		board.setReplyer("user00");		
		int count = mapper.update(board);
		log.info("Update COUNT" + count);				
	}	
}//end class
