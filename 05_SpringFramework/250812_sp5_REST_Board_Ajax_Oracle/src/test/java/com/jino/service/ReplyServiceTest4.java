package com.jino.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyServiceTest4 {
	
	@Autowired
	private ReplyService  service;
	
	@Test
	public void testExists() {
		log.info(service);
		//디비에 있는지 확인
		int count = service.delete(4);
		log.info("Update COUNT" + count);
	}//end test

}//end clas
