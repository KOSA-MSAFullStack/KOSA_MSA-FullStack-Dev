// BoardMapperTests3.java
// Mapper SELECT

package net.developia.dao;

import java.sql.SQLException;

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
public class BoardMapperTests3_select {
   
    @Autowired
    private ArticleDAO mapper;
   
    @Test
    public void testRead() throws SQLException {
        //db에 해당 데이터가 잇어야함
        ArticleDTO board = mapper.getDetail(1);
        log.info(board);
    }
}
