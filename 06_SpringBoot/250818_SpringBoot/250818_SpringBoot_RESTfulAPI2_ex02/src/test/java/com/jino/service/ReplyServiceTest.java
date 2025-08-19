package com.jino.service;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jino.DTO.ReplyDTO;

@SpringBootTest
public class ReplyServiceTest {
	
	@Autowired
	private ReplyService  service;
	
	@DisplayName("service list 확인")
	@Test
	public void testExists() {
		System.out.println("1번째 방법");
		System.out.println(service);
		service.list(1).forEach(
				i -> System.out.println(i)
			);	
		System.out.println("2번째 방법");
		List<ReplyDTO> replyBoardList = service.list(1);
		for (ReplyDTO i : replyBoardList) {
			System.out.println(i.toString());
		} // end for
		
	}//end test

}//end clas

