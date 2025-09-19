package org.zerock.ex2.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;


@SpringBootTest
public class MemoRepositoryTests18 {
   @Autowired
   MemoRepository2 memoRepository2;

   @Test
   public void testGetListQueryObject(){
       List<Object[]> listObject = memoRepository2.getNativeResult();

       listObject.forEach( i ->{
           Arrays.stream(i).forEach( j ->{
               System.out.print(j.toString() + " ");
           }); // Arrays.stream(i).forEach
           System.out.println("");
       });//end  listObject.forEach

       System.out.println(" 과거 문법=========================");
       for (  Object[] i : listObject ){
           for( Object j : Arrays.stream(i).toArray()){
               System.out.print(j.toString() + " ");
           }//end for
           System.out.println("");
       }//end for

   }//end void
}//end class



