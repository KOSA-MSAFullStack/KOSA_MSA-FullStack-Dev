package org.zerock.ex2.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.zerock.ex2.entity.Memo;


@SpringBootTest
public class MemoRepositoryTests7 {
   @Autowired
   MemoRepository memoRepository;

   @Test
   public void testPageDefault(){
      Pageable pageable = PageRequest.of(0,10);
      Page<Memo> result = memoRepository.findAll(pageable);
       System.out.println(result);
       System.out.println("--------------------");
       System.out.println("전체 페이지 : " + result.getTotalPages());
       System.out.println("전체 갯수 : " +  result.getTotalElements());
       System.out.println("페이지 번호 : " +  result.getNumber());
       System.out.println("페이지 사이즈 : " +  result.getSize());
       System.out.println("다음 페이지 여부 : " +  result.hasNext());
       System.out.println("처음 페이지 여부 : " + result.isFirst());

   }//end void
}//end class



