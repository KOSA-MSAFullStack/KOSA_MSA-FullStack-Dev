package org.zerock.ex2.repository;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.Arrays;




@SpringBootTest
public class MemoRepositoryTests17 {
   @Autowired
   MemoRepository2 memoRepository2;


   @Test
   public void testGetListQueryObject(){
       Pageable pageable = PageRequest.of(0, 10, Sort.by("memoText").ascending());
       Page<Object[]> listObject = memoRepository2.getListWithObject(30L, pageable);


       System.out.println("---1번째 방법------------");
       listObject.forEach(i -> {
           Arrays.stream(i).forEach(j -> {
               System.out.print(j.toString() + " ");
           });// end Arrays.stream(i).forEach
           System.out.println("");
       });//listObject.forEach


       System.out.println("---2번째 방법------------");
       for (  Object[] i : listObject.getContent() ){
           for( Object j  : Arrays.stream(i).toArray()){
               System.out.print(j.toString() + " ");
           }//end for
           System.out.println("");
       }//end for


   }//end void
}//end class



