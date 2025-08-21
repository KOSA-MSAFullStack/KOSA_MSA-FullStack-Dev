package org.zerock.ex2.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.ex2.entity.Memo;


@SpringBootTest
public class MemoRepositoryTests6 {
   @Autowired
   MemoRepository memoRepository;

   @Test
   public void testDelete(){
       Long mno = 100L;
       memoRepository.deleteById(mno);
   }//end void
}//end class



