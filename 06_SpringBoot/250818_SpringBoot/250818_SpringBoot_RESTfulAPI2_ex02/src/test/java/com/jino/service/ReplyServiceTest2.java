package com.jino.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.jino.DTO.ReplyDTO;


@SpringBootTest
public class ReplyServiceTest2 {
	
	@Autowired
	private ReplyService  service;
	
	@Test
	public void testExists() {
		System.out.println(service);
		ReplyDTO board = new ReplyDTO() ;
		board.setBno(11); //db에서 확인후 진행		
		board.setReplytext("서비스 새로작성하는글");
		board.setReplyer("newbie");
		service.insert(board);
		System.out.println(board);
	}//end test

}//end clas

