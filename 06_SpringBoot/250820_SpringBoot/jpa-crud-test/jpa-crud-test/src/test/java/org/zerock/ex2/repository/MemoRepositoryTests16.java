package org.zerock.ex2.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.ex2.entity.Memo;


@SpringBootTest
public class MemoRepositoryTests16 {
   @Autowired
   MemoRepository2 memoRepository2;

   @Test
   public void testUpdateQueryParameter3() {
       Pageable pageable = PageRequest.of(0,10, Sort.by("mno").ascending());
       Page<Memo> result = memoRepository2.getListWithQuery(30L,pageable);
       result.get().forEach( i -> {
           System.out.println(i);
       });//end foreach

   }//end void
}//end class



