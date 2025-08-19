package com.jino.DAO;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class ReplyBoardDAOMapperTests4_delete {
	
	@Autowired
	private ReplyBoardDAO mapper;
	
	@Test
	public void testDelete() {
		//db에 데이터 있는지 확인
		System.out.println("Delete count: " + mapper.delete(3) );
	}//end void	
	
}//end class

