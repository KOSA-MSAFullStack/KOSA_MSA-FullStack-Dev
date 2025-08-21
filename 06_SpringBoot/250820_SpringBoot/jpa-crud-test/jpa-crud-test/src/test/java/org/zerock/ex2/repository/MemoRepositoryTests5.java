package org.zerock.ex2.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.ex2.entity.Memo;


@SpringBootTest
public class MemoRepositoryTests5 {
   @Autowired
   MemoRepository memoRepository;

   @Test
   public void testupdate(){
	   //entity 생성 
       Memo memo = Memo.builder().mno(100L).memoText("update Text").build();
       
       //DB 반영
       System.out.println( memoRepository.save(memo));
   }//end void
}//end class



