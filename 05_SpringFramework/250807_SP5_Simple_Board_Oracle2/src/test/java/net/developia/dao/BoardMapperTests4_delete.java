// BoardMapperTests4.java
// Mapper DELETE

package net.developia.dao;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import lombok.extern.log4j.Log4j;
import net.developia.dto.ArticleDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests4_delete {
   
    @Autowired
    private ArticleDAO mapper;
   
    @Test
    public void testDelete() throws SQLException {
        //db에 해당데이터가 존재해야함
        ArticleDTO board = new ArticleDTO();
        board.setNo(4);
        board.setPassword("1234");
        log.info("Delete count:" + mapper.deleteArticle(board));
    }
}
