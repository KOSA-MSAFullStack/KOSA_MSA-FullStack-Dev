package org.zerock.ex2.repository;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.ex2.entity.Memo;




@SpringBootTest
public class MemoRepositoryTests9 {
   @Autowired
   MemoRepository memoRepository;


   @Test
   public void testsort(){
       //mon 내림차순으로 정렬
       Sort sort1 = Sort.by("mno").descending();


       //Pageable 인터페이스 생성
       Pageable pageable = PageRequest.of(0,10,sort1);


       //Page 인터페이스 생성
       Page<Memo> result = memoRepository.findAll(pageable);


       System.out.println("---1번째 방법------------");
       //Java Stream forEach
       result.get().forEach( i ->{
           System.out.println(i);
       });
       System.out.println("---2번째 방법------------");
       //이것도 가능
       for(Memo memo : result.getContent() ){
           System.out.println(memo);
       }//end for


   }//end void
}//end class



