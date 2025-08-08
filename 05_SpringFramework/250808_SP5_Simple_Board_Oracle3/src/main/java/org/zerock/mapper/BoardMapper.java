// BoardMapper.java

package org.zerock.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Select;
import org.zerock.domain.BoardVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMapper {
    @Select("select * from TBL_BOARD where bno>0")
    public List<BoardVO> getList2();
   
    public List<BoardVO> getList();
   
    public void insert(BoardVO board);  
    public void insertSelectKey(BoardVO board);
   
    public BoardVO read(Long bno);
   
    public int delete (Long bno);
   
    public int update(BoardVO board);
}
