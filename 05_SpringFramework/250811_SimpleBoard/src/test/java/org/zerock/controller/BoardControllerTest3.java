// BoardControllerTest3.java

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
@WebAppConfiguration
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
    "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@Log4j
public class BoardControllerTest3 {
   
    @Autowired
    private WebApplicationContext ctx;
       
    private MockMvc mockMvc;
   
    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();    
    }//end setup
   
    @Test
    public void testGet() throws Exception {
        log.info(
            Objects.requireNonNull(
                mockMvc.perform(
                MockMvcRequestBuilders.get("/board/get")
                .param("bno", "1")              
                ).andReturn()
                .getModelAndView()
            ).getModelMap()
        );      
    }
}
