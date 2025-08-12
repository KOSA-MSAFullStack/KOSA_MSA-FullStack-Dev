package com.jino.service;
import java.util.List;
import com.jino.domain.ReplyVO;

public interface ReplyService {

public List<ReplyVO> list( int bno) ;	
    public void insert(ReplyVO vo) ;	
	public int update(ReplyVO vo) ;	
	public int delete (int rno) ;		

}//end int
