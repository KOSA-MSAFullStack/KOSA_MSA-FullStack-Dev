package com.jino.DAO;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.jino.DTO.ReplyDTO;

@SpringBootTest
public class ReplyBoardDAOMapperTests2_insert {	
	
	@Autowired
	private ReplyBoardDAO mapper;
	
	@Test
	public void testInsert() {
		ReplyDTO board = new ReplyDTO() ;
		board.setBno(10); //db에서 확인후 진행		
		board.setReplytext("새로작성하는글");
		board.setReplyer("newbie");
		mapper.create(board);
		System.out.println(board);
	}//end void
	
}//end class