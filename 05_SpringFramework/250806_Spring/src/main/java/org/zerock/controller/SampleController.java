package org.zerock.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.TodoDTO;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/sample/*")
@Log4j
public class SampleController {

    @GetMapping("/ex07")
    public ResponseEntity<String> ex07() {
        log.info("ex07.......");

        // \는 string에 "name" 때문
        String msg = "{ \"name\" : \"홍길동\" }";

        HttpHeaders header = new HttpHeaders();
        header.add("content-type", "application/json;charset=UTF-8");

        //202번
        return new ResponseEntity<>(msg, header, HttpStatus.ACCEPTED);
    }// end ex07

    @GetMapping("/ex06")
    public @ResponseBody SampleDTO ex06() {
        log.info("/ex06..........");
        SampleDTO dto = new SampleDTO();
        dto.setAge(10);
        dto.setName("홍길동");
        return dto;
    }// end SampleDTO

    @GetMapping("/ex05")
    public void ex05() {
        log.info("ex05.................... ");
    }// end ex05

    @GetMapping("/ex04")
    public String ex04(SampleDTO dto, @ModelAttribute("page") int page, Model model) {
        log.info("ex04 dto:" + dto);
        log.info("ex04 page" + page);
        model.addAttribute("dto", dto);
        return "/sample/ex04";
    }// end ex04

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, false));
    }// end initBinder

    @GetMapping("/ex03")
    public String ex03(TodoDTO todo) {
        log.info("ex03 todo:" + todo);
        return "ex03";
    }// end ex03

    @GetMapping("/ex02List")
    public String ex02List(@RequestParam("ids") ArrayList<String> ids) {
        log.info("ex02 ids : " + ids);

        return "ex02List";
    }// end ex02

    @GetMapping("/ex02")
    public String ex02(@RequestParam("name") String name, @RequestParam("age") int age) {
        log.info("ex02 name : " + name);
        log.info("ex02 age : " + age);

        return "ex02";
    }// end ex02

    @GetMapping("ex01")
    public String ex01(SampleDTO dto) {
        log.info("" + dto);
        return "ex01";
    }

    @RequestMapping("")
    public void basic() {
        log.info("Basic....");
    }// end basic

    @RequestMapping(value = "/basic", method = { RequestMethod.GET, RequestMethod.POST })
    public void basicGet() {
        log.info("basic get.........");

    }// end basicGet

    @GetMapping("/basicOnlyGet")
    public void basicGet2() {
        log.info("basic get Only GET 2.........");
    }/// end basicGet2

}// end class


