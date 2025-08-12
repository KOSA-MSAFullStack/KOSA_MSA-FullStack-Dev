package com.jino.mapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.jino.domain.ReplyVO;
//import com.jino.mapper.ReplyBoard;
import lombok.extern.log4j.Log4j;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyBoardMapperTests2_insert {	
	@Autowired
	private ReplyBoard mapper;	
	@Test
	public void testInsert() {
		ReplyVO board = new ReplyVO() ;
		board.setBno(10); //db에서 확인후 진행		
		board.setReplytext("새로작성하는글");
		board.setReplyer("newbie");
		mapper.create(board);
		log.info(board);
	}//end void	
}//end class