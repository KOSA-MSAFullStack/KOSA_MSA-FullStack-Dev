package org.zerock.ex2.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.ex2.entity.Memo;

import java.util.List;


@SpringBootTest
public class MemoRepositoryTests11 {
   @Autowired
   MemoRepository2 memoRepository2;

   @Test
   public void testQueryMethod2(){
       Pageable pageable = PageRequest.of(0,10, Sort.by("mno").descending());
       Page<Memo> result = memoRepository2.findByMnoBetween(10L,50L,pageable);
      
       result.get().forEach( i -> {
           System.out.println(i);
       });//end foreach
      
       System.out.println("-------------------");
       //같은 출력 다른 방법
       for (Memo memo : result.getContent()){
           System.out.println(memo);
       }//end for

   }//end void
}//end class

