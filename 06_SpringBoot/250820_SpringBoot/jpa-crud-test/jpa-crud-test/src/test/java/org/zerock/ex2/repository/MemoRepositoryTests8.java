package org.zerock.ex2.repository;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.zerock.ex2.entity.Memo;




@SpringBootTest
public class MemoRepositoryTests8 {
   @Autowired
   MemoRepository memoRepository;


   @Test
   public void testPageDefault2(){


       Pageable pageable = PageRequest.of(0,10);
       Page<Memo> result = memoRepository.findAll(pageable);
       System.out.println(result);
       System.out.println("---1번째 방법------------");


       for(Memo memo : result.getContent() ){
           System.out.println(memo);
       }//end for
       System.out.println("---2번째 방법----------");
       result.get().forEach(i -> {
           System.out.println(i);
       });
       System.out.println("--------------------");
   }//end void
}//end class



