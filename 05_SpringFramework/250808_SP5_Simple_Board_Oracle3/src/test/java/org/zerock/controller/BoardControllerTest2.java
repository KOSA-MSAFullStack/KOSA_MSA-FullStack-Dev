// BoardControllerTest2.java

package org.zerock.controller;

import java.util.Objects;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
//import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@Log4j
@WebAppConfiguration
@ContextConfiguration({
    "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
    "file:src/main/webapp/WEB-INF/spring/root-context.xml"
})
public class BoardControllerTest2 {
   
    @Autowired
    private WebApplicationContext ctx;
   
    private MockMvc mockMvc;
   
    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }
   
    @Test
    public void testRegister() throws Exception {
       
        String resultPage = 
            Objects.requireNonNull(
                mockMvc.perform(
                MockMvcRequestBuilders.post("/board/register")
                .param("title", "새글 테스트 제목")
                .param("content", "테스트 새글 내용")
                .param("writer", "user00")
                ).andReturn()
                .getModelAndView()
            ).getViewName();
        log.info(resultPage);      
    }
}
