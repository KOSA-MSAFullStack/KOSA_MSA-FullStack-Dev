package org.zerock.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.SampleVO;
import org.zerock.domain.Ticket;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/sample")
@Log4j
public class SampleController {
	
	@PostMapping("/ticket")
	public Ticket convert(@RequestBody Ticket ticket) {
		log.info("convert....ticket" + ticket);
		return ticket;		
	}//end convert
	
	@GetMapping(value = "/product/{cat}/{pid}"
			,produces = {MediaType.APPLICATION_JSON_UTF8_VALUE
					,MediaType.APPLICATION_XML_VALUE} 	)
	public String[] getPath(
			@PathVariable("cat") String cat
			,@PathVariable("pid") String pid) {
		
		return new String[] {"category: " +cat,"productid: "+pid};		
	}//end getPath

	
	@GetMapping(value = "/check" , params = {"height","weight"})
	public ResponseEntity<SampleVO> check(Double height, Double weight){		
		SampleVO vo = new SampleVO(0, ""+height, ""+ weight);		
		ResponseEntity<SampleVO> result = null;		
		if(height < 150) { //502
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo);
		}else { //200
			result = ResponseEntity.status(HttpStatus.OK).body(vo);
		}//end if		
		return result;		
	}// end check
	
	@GetMapping(value = "/getMap")
	public Map<String , SampleVO> getMap(){		
		Map<String , SampleVO> map = new HashMap<>();
		map.put("First", new SampleVO(111,"그루트","주니어"));			
		return  map;		
	}// endgetMap
	
	@GetMapping(value = "/getList")
	public List<SampleVO> getList(){	
		return IntStream.range(1, 10)
			.mapToObj( i -> new SampleVO( i, i+"First" , i + "Last"))
			.collect(Collectors.toList())	;		
	}//end getList
	
	@GetMapping(value = "/getSample2") //produces 생략 가능
	public SampleVO getSample2() {
		return new SampleVO(113,"로켓","하쿤");		
	}//end getSample2
	
	@GetMapping(value = "/getSample"
			,produces = {MediaType.APPLICATION_JSON_UTF8_VALUE
					,MediaType.APPLICATION_XML_VALUE})
	public SampleVO getSample() {
		return new SampleVO(112,"스타","로드");		
	}//end getSample
	
	@GetMapping(value = "/getText", produces = "text/plain; charset=UTF-8")
	public String getText() {		
		log.info("MIME TYPE: " + MediaType.TEXT_PLAIN_VALUE);
		return "안녕하세요";		
	}//end getText

}//end class