// BoardMapperTests2_insert.java
// Insert 기능 테스트

package org.zerock.mapper;

//import java.util.List;

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
public class BoardMapperTests2_insert {
   
    @Autowired
    private BoardMapper mapper;
   
    @Test
    public void testInsert() {
        BoardVO board = new BoardVO();
        board.setTitle("새로작성하는글");
        board.setContent("새로작성하는글");
        board.setWriter("newbie");
        mapper.insert(board);
        log.info(board);
    }
}
