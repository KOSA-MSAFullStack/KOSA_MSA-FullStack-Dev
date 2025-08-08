// BoardServiceTest5.java

package org.zerock.service;

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
public class BoardServiceTest5 {
   
    @Autowired
    private BoardService service;
   
    @Test
    public void testDelete() {
        //게시물의 번호 존재를 확인하고 테스트
        log.info("Remove RESULT..."+service.remove(2L));
       
    }//end test

    @Test
    public void testUpdate() {
        BoardVO board = service.get(1L);
        if(board == null) {
            return;
        }
        board.setTitle("제목 수정합니다.");
        log.info(""+service.modify(board));    
    }
}
