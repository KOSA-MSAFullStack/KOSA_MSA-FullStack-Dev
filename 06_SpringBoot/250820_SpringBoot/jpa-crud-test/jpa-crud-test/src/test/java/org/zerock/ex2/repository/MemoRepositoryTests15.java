package org.zerock.ex2.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.ex2.entity.Memo;


@SpringBootTest
public class MemoRepositoryTests15 {
   @Autowired
   MemoRepository2 memoRepository2;

   @Commit
   @Transactional
   @Test
   public void testUpdateQueryParameter2() {
       //Memo memo = new Memo(12L,"change Test2");
       Memo memo = Memo.builder().mno(12L).memoText("change Test2").build();
       memoRepository2.updateMemoText2(memo);
   }//end void
}//end class



