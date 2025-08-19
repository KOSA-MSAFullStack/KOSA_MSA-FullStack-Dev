package com.jino.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/replies")
public class WebController {
	
	@GetMapping(value = "test")
	public String insert() {
		log.info("test");
		return "replies/test";
	}//end insert

}

