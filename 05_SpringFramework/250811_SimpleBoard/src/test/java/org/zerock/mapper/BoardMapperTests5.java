// BoardMapperTests5.java
// Update 처리 테스트

package org.zerock.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests5 {    
    @Autowired
    private BoardMapper mapper;
   
    @Test
    public void testUpdate() {
        BoardVO board = new BoardVO();
        //실행전 존재하는 번호인지 확인
        board.setBno(5L);
        board.setTitle("수정된 제목");
        board.setContent("수정된 내용");
        board.setWriter("user00");      
        int count = mapper.update(board);
        log.info("Update COUNT" + count);              
    }  
}
