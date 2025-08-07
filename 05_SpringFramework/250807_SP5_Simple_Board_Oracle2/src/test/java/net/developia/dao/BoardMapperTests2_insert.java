// BoardMapperTests2_insert.java
// Mapper INSERT

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
public class BoardMapperTests2_insert {
   
    @Autowired
    private ArticleDAO mapper;
   
    @Test
    public void testInsert() throws SQLException {
        ArticleDTO board = new ArticleDTO();
        board.setName("newbie");
        board.setTitle("새로작성하는글");
        board.setContent("새로작성하는글");
        board.setPassword("1234");
        mapper.insertArticle(board);
        log.info(board);
    }
}
