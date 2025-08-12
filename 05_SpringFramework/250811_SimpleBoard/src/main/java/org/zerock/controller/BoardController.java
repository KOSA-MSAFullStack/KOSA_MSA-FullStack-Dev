// BoardController.java

package org.zerock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageDTO;
import org.zerock.service.BoardService;

import lombok.AllArgsConstructor;
//import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {  
   
    @Autowired
    private BoardService service;
   
    @GetMapping("/register")
    public void register() {
        log.info( "register-----");
    }
   
    @GetMapping("/list")
    public void list(Criteria cri,  Model model) {
        log.info("list" + cri);
        model.addAttribute("list", service.getList(cri));
        //PageDTO 구성하기 위해 전체데이터 수 필요해서 임의의값 123 지정
        model.addAttribute("pageMaker", new PageDTO(cri, 123));
    }


   
    @PostMapping("/register")
    public String register(BoardVO board, RedirectAttributes rttr) {
       
        log.info( "register: " +board);    
        service.register(board);        
        rttr.addFlashAttribute("result", board.getBno());      
        return "redirect:/board/list";      
    }

    @GetMapping({"/get","/modify"})
    public void get(@RequestParam("bno") Long bno, Model model) {
        log.info("/get or Modify");
        model.addAttribute("board", service.get(bno));
    }
   
   
    @PostMapping("/modify")
    public String modify(BoardVO board, RedirectAttributes rttr) {
        log.info("modify :" + board);
        if(service.modify(board)) {
            rttr.addFlashAttribute("result", "succes");
        }
        return "redirect:/board/list";      
    }
   
    @PostMapping("/remove")
    public String remove(@RequestParam("bno") Long bno,RedirectAttributes rttr ) {
       
        log.info("remove..." + bno);
        if(service.remove(bno)) {
            rttr.addFlashAttribute("result", "success");
        }
        return "redirect:/board/list";      
    }
}
