package org.zerock.ex2.repository;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class MemoRepositoryTests1 {
   @Autowired
   MemoRepository memoRepository;


   @Test
   public void testClass(){
       System.out.println(memoRepository.getClass().getName());
   }


}// class



