package org.zerock.ex3.controller;


import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/sample")
@Log4j2
public class SampleController3 {
   @GetMapping({"/exLayout1","/exLayout2","/exTemplate"})
   public void exLayout1(){
       log.info("exLayout......");
   } //end void
}

