package com.jino.DAO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.jino.DTO.BoardDTO;
@Mapper
public interface BoardDAO {	
	public List<BoardDTO> list( ) ;	
   public void create(BoardDTO vo) ;	
	public int update(BoardDTO vo) ;	
	public int delete (long idx) ;
}//end inter...

