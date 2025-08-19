package org.zerock.ex3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.ex3.dto.SampleDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootTest
public class SampleDTO_List_print {

   @DisplayName("SampleDTO 출력")
   @Test
   public void testlist(){
	   
	   System.out.println("1번째 방법");
       List<SampleDTO> list = new ArrayList<>();       
       for (int i = 1; i <= 20; i++){
           SampleDTO dto = SampleDTO.builder()
                   .sno(Long.valueOf(i))
                   .first("First.." + i)
                   .last("Last.." + i)
                   .regTime(LocalDateTime.now())
                   .build();
           System.out.println(dto);
           list.add(dto);
       }//end for
      
       System.out.println(list);
       
       System.out.println("2번째 방법");
       List<SampleDTO> list2 = IntStream.rangeClosed(1,20).asLongStream()
               .mapToObj( i -> {
                   SampleDTO dto = SampleDTO.builder()
                           .sno(i)
                           .first("First.." + i)
                           .last("Last.." + i)
                           .regTime(LocalDateTime.now())
                           .build();
                   return dto;
               }).collect(Collectors.toList());
       System.out.println(list2);

   }//end void
}//end class



