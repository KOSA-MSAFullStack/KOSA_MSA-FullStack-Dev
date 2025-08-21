package org.zerock.ex2.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.ex2.entity.Memo;

import java.util.List;


@SpringBootTest
public class MemoRepositoryTests10 {
   @Autowired
   MemoRepository2 memoRepository2;

   @Test
   public void testQuery(){
       List<Memo> list = memoRepository2.
               findByMnoBetweenOrderByMnoDesc(70L,80L);
       for( Memo memo : list){
           System.out.println(memo);
       }//end for
      
       //이것도 가능
       //list.forEach( i -> System.out.println(i));

   }//end void
}//end class



