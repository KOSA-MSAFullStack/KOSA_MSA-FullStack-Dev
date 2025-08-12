// BoardMapperTests3.java
// Read 처리 테스트

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
public class BoardMapperTests3 {
   
    @Autowired
    private BoardMapper mapper;
   
    @Test
    public void testRead() {
        //db에 해당 데이터가 잇어야함
        BoardVO board = mapper.read(5L);
        log.info(board);
    }
}
