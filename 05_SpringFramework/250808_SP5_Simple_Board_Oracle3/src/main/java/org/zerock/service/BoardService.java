// BoardService.java

package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.domain.BoardVO;

@Service
public interface BoardService {
   
    public void register(BoardVO board);
   
    public BoardVO get(Long bno);
   
    public boolean modify(BoardVO board);
   
    public boolean remove(Long bno);
   
    public List<BoardVO> getList();    

}
