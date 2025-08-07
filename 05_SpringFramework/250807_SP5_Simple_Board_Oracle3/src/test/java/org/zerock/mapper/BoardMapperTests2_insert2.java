// BoardMapperTests2_insert2.java

package org.zerock.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
//import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests2_insert2 {    
    @Autowired
    private BoardMapper mapper;
   
    @Test
    public void testInsert() {
        BoardVO board = new BoardVO();
        board.setTitle("새로작성하는글_SelectKey");
        board.setContent("새로작성하는글_SelectKey");
        board.setWriter("newbie");
        mapper.insertSelectKey(board);
        log.info(board);
    }
}
