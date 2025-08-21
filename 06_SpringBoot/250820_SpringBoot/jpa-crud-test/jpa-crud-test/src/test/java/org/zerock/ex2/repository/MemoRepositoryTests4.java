package org.zerock.ex2.repository;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.ex2.entity.Memo;




@SpringBootTest
public class MemoRepositoryTests4 {
   @Autowired
   MemoRepository memoRepository;


   @Transactional
   @Test
   public void testSelect2(){
       Long mno = 100l; //데이터베이스에 존재하는  mno 열 값
       Memo memo = memoRepository.getById(mno);
       Memo memo2 = memoRepository.getReferenceById(mno);


       System.out.println("=========");
       System.out.println(memo);
       System.out.println(memo2);
   }//end void
}//end class





