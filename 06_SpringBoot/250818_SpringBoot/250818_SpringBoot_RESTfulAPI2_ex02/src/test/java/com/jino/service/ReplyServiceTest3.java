package com.jino.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.jino.DTO.ReplyDTO;

@SpringBootTest
public class ReplyServiceTest3 {
	
	@Autowired
	private ReplyService  service;
	
	@Test
	public void testExists() {
		ReplyDTO board = new ReplyDTO() ;
		//실행전 존재하는 번호인지 확인
		board.setRno(6);
		board.setReplytext("service 수정된 내용");
		board.setReplyer("user00");		
		int count = service.update(board);
		System.out.println("Update COUNT : " + count);
	}//end test

}//end clas

