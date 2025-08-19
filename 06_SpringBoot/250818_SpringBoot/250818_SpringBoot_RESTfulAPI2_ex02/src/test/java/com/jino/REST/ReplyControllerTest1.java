package com.jino.REST;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@SpringBootTest
@AutoConfigureMockMvc // MockMvc 세팅
public class ReplyControllerTest1 {


   // MockMvc 세팅
   @Autowired
   protected MockMvc mockMvc;
   @Autowired
   private WebApplicationContext context;
   @BeforeEach
   public void mockMvcSetUp() {
       this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
   }
   //end MockMvc 세팅






   @Test
   public void testList() throws Exception {




       System.out.println(
               mockMvc.perform(
                       MockMvcRequestBuilders.get("/replies/all/1")
               ).andDo(print())
                       //.andExpect(MockMvcResultMatchers.status().isOk())


       );


   }//end testList






}//end class



