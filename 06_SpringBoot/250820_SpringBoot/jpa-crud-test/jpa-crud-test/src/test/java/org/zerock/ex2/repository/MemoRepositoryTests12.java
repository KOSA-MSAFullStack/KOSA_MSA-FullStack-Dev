package org.zerock.ex2.repository;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;




@SpringBootTest
public class MemoRepositoryTests12 {
   @Autowired
   MemoRepository2 memoRepository2;


   @Commit
   @Transactional
   @Test
   public void testQueryMethod3(){
       //Mno 10 이하 삭제
       memoRepository2.deleteMemoByMnoLessThan(10l);
   }//end void
}//end class




