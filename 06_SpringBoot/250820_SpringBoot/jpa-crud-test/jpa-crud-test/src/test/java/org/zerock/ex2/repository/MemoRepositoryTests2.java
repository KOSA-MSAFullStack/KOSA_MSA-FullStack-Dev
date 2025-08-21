package org.zerock.ex2.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.ex2.entity.Memo;

import java.util.stream.IntStream;

@SpringBootTest
public class MemoRepositoryTests2 {
   @Autowired
   MemoRepository memoRepository;

   @Test
   public void testInsertDummies(){
       IntStream.rangeClosed(1,100).forEach( i ->{
           Memo memo = Memo.builder().memoText("Sample.." + i).build();
           memoRepository.save(memo);
       });//end forEach
   }//end void

   //@Test
   public void testInsertDummies2(){
       for(int i = 0; i < 2; i++)   {
           Memo memo = Memo.builder().memoText("Sample.." + i).build();
           memoRepository.save(memo);
       }//end for
   }//end void

}//end class



