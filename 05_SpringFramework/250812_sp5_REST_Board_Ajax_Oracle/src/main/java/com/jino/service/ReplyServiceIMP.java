package com.jino.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jino.domain.ReplyVO;
import com.jino.mapper.ReplyBoard;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class ReplyServiceIMP  implements ReplyService{
	
	@Autowired
	private ReplyBoard mapper;
	
	@Override
	public List<ReplyVO> list(int bno) {
		log.info("get List.............");
		return mapper.list(bno);
	}//end list

	@Override
	public void insert(ReplyVO vo) {
		log.info("insert.............");
		mapper.create(vo);
	}//end insert

	@Override
	public int update(ReplyVO vo) {
		log.info("update.............");		
		
		return mapper.update(vo);
	}//end updtae

	@Override
	public int delete(int rno) {
		log.info("delete.............");	
		return mapper.delete(rno);
	}//end delete

}//end class