package org.zerock.ex3.controller;


import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.ex3.dto.SampleDTO;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Controller
@RequestMapping("/sample")
@Log4j2
public class SampleController {


   @GetMapping("/ex1")
   public void ex1(){
       log.info("ex1......");
   }//end void


   //여러 HTML 페이지 하나의 메소드에서 처리
   @GetMapping({"/ex2","/ex2_1"})
   public void exModel(Model model){
       log.info("ex2......");
       List<SampleDTO> list =  Sample_list(20);
       //lit 이라는 이름으로 thymeleaf 에 전달
       model.addAttribute("list",list);
   }//end void




   //여러 HTML 페이지 하나의 메소드에서 처리
   @GetMapping({"/ex3_1","/ex3_2","/ex3_3","/ex3_4"})
   public void exModel2(Model model){
       log.info("ex3......");
       List<SampleDTO> list = Sample_list(10);
       //lit 이라는 이름으로 thymeleaf 에 전달
       model.addAttribute("list",list);
   }//end void


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


   @GetMapping("/exInline")
   public String exInline(RedirectAttributes redirectAttributes){
       log.info("exInline......");
       SampleDTO dto = SampleDTO.builder()
               .sno(100L)
               .first("First..100")
               .last("last...100")
               .regTime(LocalDateTime.now())
               .build();
       //RedirectAttributes 사용하는 이유는
       //한 컨트롤러 메서드에서 다른 컨트롤러 메서드로 Attributes 를 전달
       //"success" 를 result 로 전달
       redirectAttributes.addFlashAttribute("result","success");
       //dto 전달
       redirectAttributes.addFlashAttribute("dto",dto);
       //요청은 http://localhost:8080/sample/exInline
       //redirect 실행되면  void ex4() 로 이동
       //실행은 ex4.html 에서 진행
       return "redirect:/sample/ex4";
   }//end exIn....


   @GetMapping("ex4")
   public void ex4(){
       log.info("ex4......");
   }




   @GetMapping("ex5")
   public String exblock(Model model){
       log.info("exblock......");
       List<SampleDTO> list = Sample_list(10);
       //lit 이라는 이름으로 thymeleaf 에 전달
       model.addAttribute("list",list);
       return "/sample/ex5";
   }
}//end class





