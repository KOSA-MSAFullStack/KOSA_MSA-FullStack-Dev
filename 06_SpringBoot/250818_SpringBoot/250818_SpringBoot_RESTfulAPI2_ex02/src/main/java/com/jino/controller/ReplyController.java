package com.jino.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.jino.service.ReplyService;
import lombok.extern.log4j.Log4j2;
import com.jino.DTO.ReplyDTO;

@Log4j2
@RestController
@RequestMapping("/replies/")
public class ReplyController {

	@Autowired
	private ReplyService service;

	@PostMapping(value = "")
	public ResponseEntity<String> register(@RequestBody ReplyDTO vo) {
		ResponseEntity<String> entry = null;
		try {
			service.insert(vo);
			entry = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entry = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} // end try
		return entry;
	}// end register..

	@GetMapping(value = "all/{bno}")
	public ResponseEntity<List<ReplyDTO>> list(@PathVariable("bno") int bno) {
		ResponseEntity<List<ReplyDTO>> entry = null;

		try {
			entry = new ResponseEntity<List<ReplyDTO>>(service.list(bno), HttpStatus.OK);
			log.info(entry);
		} catch (Exception e) {
			e.printStackTrace();
			entry = new ResponseEntity<List<ReplyDTO>>(HttpStatus.BAD_REQUEST);
		} // end try
		return entry;
	}// end list

	@RequestMapping(value = "/{rno}", method = { RequestMethod.PUT, RequestMethod.PATCH })
	public ResponseEntity<String> update(@PathVariable("rno") int rno, @RequestBody ReplyDTO vo) {
		ResponseEntity<String> entry = null;
		try {
			vo.setRno(rno);
			service.update(vo);
			log.info("update");
			entry = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entry = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} // end try
		return entry;
	}// end update

	@DeleteMapping("/{rno}")
	public ResponseEntity<String> remove(@PathVariable("rno") int rno) {
		ResponseEntity<String> entry = null;
		try {
			service.delete(rno);
			log.info("delete....");
			entry = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entry = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entry;
	}// end remove

}// end class
