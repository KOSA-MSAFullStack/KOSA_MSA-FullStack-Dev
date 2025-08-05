// TimeMapperTests.java

package org.zerock.persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
//테스트위해서
import org.zerock.mapper.TimeMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class TimeMapperTests {
   
    @Autowired
    private TimeMapper timeMapper;
   
    @Test
    public void testGetTime() {
        log.info(timeMapper.getClass().getName());
        log.info(timeMapper.getTime());
       
    }

}
