package org.zerock.ex3.controller;


import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.ex3.dto.SampleDTO;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Controller
@RequestMapping("/sample")
@Log4j2
public class SampleController2 {


   //반복 부분 메서드 처리
   List<SampleDTO>  Sample_list(int count){
       List<SampleDTO> result = IntStream.rangeClosed(1,count).asLongStream()
               .mapToObj( i -> {
                   SampleDTO dto = SampleDTO.builder()
                           .sno(i)
                           .first("First.." + i)
                           .last("Last.." + i)
                           .regTime(LocalDateTime.now())
                           .build();
                   return dto;
               }).collect(Collectors.toList());


       return result;
   }




   @GetMapping({"/exLink1","/exLink2","/exLink3"})
   public void exLink(Model model){
       log.info("exLink......");
       List<SampleDTO> list = Sample_list(10);
       //lit 이라는 이름으로 thymeleaf 에 전달
       model.addAttribute("list",list);
   }


   @GetMapping({"/exnum","/exdate"})
   public void exObject(Model model){
       log.info("exObject......");
       List<SampleDTO> list = Sample_list(10);
       //lit 이라는 이름으로 thymeleaf 에 전달
       model.addAttribute("list",list);
       Date date = new Date();
       model.addAttribute("date",date);


   }




}//end class





