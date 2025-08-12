// BoardMapperTests4.java
// Delete 처리 테스트

package org.zerock.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests4 {
   
    @Autowired
    private BoardMapper mapper;
   
    @Test
    public void testDelete() {
        //db에 해당데이터가 존재해야함
        log.info("Delete count:" + mapper.delete(3L));
    }
}
