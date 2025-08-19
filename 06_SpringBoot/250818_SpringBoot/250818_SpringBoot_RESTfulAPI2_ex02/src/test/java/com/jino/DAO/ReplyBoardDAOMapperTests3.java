package com.jino.DAO;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.jino.DTO.ReplyDTO;

@SpringBootTest
public class ReplyBoardDAOMapperTests3 {
	
	@Autowired
	private ReplyBoardDAO mapper;
	
	@Test
	public void testUpdate() {
		ReplyDTO board = new ReplyDTO() ;
		//실행전 존재하는 번호인지 확인
		board.setRno(5);
		board.setReplytext("5수정된 내용");
		board.setReplyer("user00");		
		int count = mapper.update(board);
		System.out.println("Update COUNT" + count);				
	}
}//end class