// ReplyBoardDAO_MapperTests_1.java

package org.zerock._SpringBoot_RestfulAPI.DAO;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.jino.DTO.ReplyDTO;
@SpringBootTest
public class ReplyBoardDAO_MapperTests_1 {
	@Autowired
	private ReplyBoardDAO mapper;
	@DisplayName("XML 파일과 DAO 확인")
	@Test
	public void testGetList() {
		System.out.println("1번째 방법");
		mapper.list(1).forEach(i -> System.out.println(i));
		System.out.println("2번째 방법");
		List<ReplyDTO> replyBoardList = mapper.list(1);
		for (ReplyDTO i : replyBoardList) {
			System.out.println(i.toString());
		}
	}
}
