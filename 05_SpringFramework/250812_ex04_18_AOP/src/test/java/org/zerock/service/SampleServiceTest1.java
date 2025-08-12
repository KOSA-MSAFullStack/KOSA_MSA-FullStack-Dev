package org.zerock.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import lombok.Setter;
import lombok.extern.log4j.Log4j;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@Log4j
public class SampleServiceTest1 {
   
    @Autowired
    private SampleService service;
   
    @Test
    public void testClass() { //정상 생성 확인
        log.info(service);
        log.info(service.getClass().getName());    
    }
   
    @Test
    public void testADD()  throws Exception{ //정상 생성 확인
        log.info(service.doAdd("123", "456"));      
    }
}//end class

