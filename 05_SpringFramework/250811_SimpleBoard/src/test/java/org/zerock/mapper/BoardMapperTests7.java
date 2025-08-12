// BoardMapperTest7.java

package org.zerock.mapper;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests7 {
   
    @Autowired
    private BoardMapper mapper;
   
    @Test
    public void testPaging() {
        Criteria cri = new Criteria();
        cri.setPageNum(1);
        cri.setAmount(10);      
        List<BoardVO> list = mapper.getListWithPaging(cri);
        //출력
        list.forEach(board -> log.info(board ));        
    }
}
