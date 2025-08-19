package com.jino.service;

import java.util.List;
import com.jino.DTO.ReplyDTO;

public interface ReplyService {

public List<ReplyDTO> list( int bno) ;
	
    public void insert(ReplyDTO vo) ;	
	public int update(ReplyDTO vo) ;	
	public int delete (int rno) ;	

}//end int

