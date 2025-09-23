package com.jino.DAO;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.jino.DTO.BoardDTO;
@SpringBootTest
public class BoardDAO_MapperTests_1 {
	@Autowired
	private BoardDAO mapper;
	
	@DisplayName("XML 파일과 DAO 확인")
	@Test
	public void testGetList() {
		System.out.println("1번째 방법");
		mapper.list().forEach(i -> System.out.println(i));
		System.out.println("2번째 방법");
		List<BoardDTO> BoardList = mapper.list();
		for (BoardDTO i : BoardList) {
			System.out.println(i.toString());
		} // end for
	}// end void
}// end class


