package com.jino.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jino.DTO.ReplyDTO;
import com.jino.DAO.ReplyBoardDAO;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@AllArgsConstructor
public class ReplyServiceIMP  implements ReplyService{
	
	@Setter(onMethod_ = @Autowired)
	private ReplyBoardDAO mapper;
	
	@Override
	public List<ReplyDTO> list(int bno) {
		log.info("get List.............");
		return mapper.list(bno);
	}//end list

	@Override
	public void insert(ReplyDTO vo) {
		log.info("insert.............");
		mapper.create(vo);
	}//end insert

	@Override
	public int update(ReplyDTO vo) {
		log.info("update.............");		
		
		return mapper.update(vo);
	}//end updtae

	@Override
	public int delete(int rno) {
		log.info("delete.............");	
		return mapper.delete(rno);
	}//end delete

}//end class

