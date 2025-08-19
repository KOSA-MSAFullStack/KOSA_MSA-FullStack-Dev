package com.jino.DAO;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.jino.DTO.ReplyDTO;

@Mapper
public interface ReplyBoardDAO {	
	public List<ReplyDTO> list( int bno) ;	
              public void create(ReplyDTO vo) ;	
	public int update(ReplyDTO vo) ;	
	public int delete (int rno) ;
}//end inter...

