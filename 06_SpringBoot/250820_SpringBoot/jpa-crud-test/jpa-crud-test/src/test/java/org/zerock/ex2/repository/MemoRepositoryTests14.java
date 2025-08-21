package org.zerock.ex2.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
public class MemoRepositoryTests14 {
   @Autowired
   MemoRepository2 memoRepository2;

   @Commit
   @Transactional
   @Test
   public void testUpdateQueryParameter1() {
       memoRepository2.updateMemoText1(11L, "change Test");
   }
}//end class



