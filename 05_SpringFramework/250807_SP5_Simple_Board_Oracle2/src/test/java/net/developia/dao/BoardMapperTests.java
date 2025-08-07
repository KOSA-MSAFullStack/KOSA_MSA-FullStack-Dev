// BoardMapperTests.java
// Mapper List

package net.developia.dao;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import net.developia.dto.ArticleDTO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {
   
    @Autowired
    private ArticleDAO mapper;
   
   
    @Test
    public void testGetList() throws SQLException {
        log.info("----1번째 방법----");
       
        List<ArticleDTO> list = mapper.getArticleList();    
        for (ArticleDTO i : list) {
            log.info(i);
        }//end for
       
        log.info("----2번째 방법----");
        mapper.getArticleList().forEach(board -> log.info(board));      
    }
}
