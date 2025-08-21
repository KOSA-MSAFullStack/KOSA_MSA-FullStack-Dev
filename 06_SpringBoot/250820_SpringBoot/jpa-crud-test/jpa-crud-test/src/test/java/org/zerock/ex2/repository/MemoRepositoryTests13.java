package org.zerock.ex2.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.ex2.entity.Memo;
import java.util.List;

@SpringBootTest
public class MemoRepositoryTests13 {
   @Autowired
   MemoRepository2 memoRepository2;


   @Test
   public void testGetListDesc(){
       List<Memo> listDesc = memoRepository2.getListDesc();
       List<Memo> listDesc2 = memoRepository2.getListDesc2();


       System.out.println("---1번째 방법 JPQL------------");
       //Java Stream forEach
       listDesc.forEach(i -> {
           System.out.println(i);
       }); //foreach


       System.out.println("---2번째 방법 ORACLE SQL------------");
       //이것도 가능
       for(Memo memo : listDesc2 ){
           System.out.println(memo);
       }//end for


   }//end void
}//end class



