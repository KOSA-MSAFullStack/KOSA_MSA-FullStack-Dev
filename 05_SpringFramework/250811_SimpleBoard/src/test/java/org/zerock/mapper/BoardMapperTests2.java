// BoardMapperTests2.java
// Mapper 파일 테스트

package org.zerock.mapper;

import java.util.List;
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
public class BoardMapperTests2 {    
    @Autowired
    private BoardMapper mapper;
   
    @Test
    public void testGetList() {
        log.info("----1번째 방법----");
        List<BoardVO> list = mapper.getList();  
        for (BoardVO i : list) {
            log.info(i);
        }

        log.info("----2번째 방법----");
        mapper.getList().forEach(board -> log.info(board));    
    }
}
