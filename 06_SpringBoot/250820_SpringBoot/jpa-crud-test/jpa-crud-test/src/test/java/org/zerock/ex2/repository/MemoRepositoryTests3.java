package org.zerock.ex2.repository;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.ex2.entity.Memo;
import java.util.Optional;


@SpringBootTest
public class MemoRepositoryTests3 {
   @Autowired
   MemoRepository memoRepository;


   @Test
   public void testSelect(){
       Long mno = 100l; //데이터베이스에 존재하는  mno 열 값
       Optional<Memo> result = memoRepository.findById(mno);
       System.out.println("=========");
       if( result.isPresent() ){
           Memo memo = result.get();
           System.out.println(memo);
       }//end if
   }//end void
}//end class




