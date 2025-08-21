package org.zerock.ex2.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.ex2.entity.Memo;

import java.util.List;

public interface MemoRepository2  extends JpaRepository<Memo,Long> {
   List<Memo> findByMnoBetweenOrderByMnoDesc(Long from, Long to);
  
   //퀴리메서드에 Pageable 파라미터 사용
   Page<Memo> findByMnoBetween(Long from , Long to, Pageable pageable);

   //삭제 퀴리 메소드
   void deleteMemoByMnoLessThan(Long num);

   ////삭제 퀴리 메소드 Mno를 지정하여 해당 데이터 삭제
   void deleteMemoByMno(Long num);

   //대소문자 구분, 별칭 필수
   @Query("select m from Memo m order by m.mno desc")
   List<Memo> getListDesc();

   //오라클 문법 그대로
  @Query(value = "select * from tbl_memo  order by mno desc",nativeQuery = true)
  List<Memo> getListDesc2();

   @Transactional
   @Modifying   //:mno , :memoText가 파라미터 ,대소문자구분
   @Query("update Memo m set m.memoText = :memoText where m.mno = :mno ")
   int updateMemoText1(@Param("mno") Long mno, @Param("memoText") String memoText);

   @Transactional
   @Modifying //클라스를 파라미터로 :#{#param}    중요[#이 2개]
   @Query("update Memo m set m.memoText = :#{#param.memoText} where m.mno = :#{#param.mno} ")
   int updateMemoText2(@Param("param") Memo memo);

   //:mno 파라미터
   @Query(value = "select m from Memo m where m.mno > :mno",
   countQuery = "select count(m) from Memo m where m.mno > :mno")
   Page<Memo> getListWithQuery(@Param("mno") Long mno, Pageable pageable);

   //:mno 파라미터, CURRENT_DATE 는 DB 구문
   @Query(value = "select m.mno, m.memoText,CURRENT_DATE from Memo m where m.mno > :mno",
           countQuery = "select count(m) from Memo m where m.mno > :mno")
   Page<Object[]> getListWithObject(@Param("mno") Long mno, Pageable pageable);

   //모델에 정의 되지은 데이터나 가공된 데이터등을 받아서 올때는 Object[]
   @Query(value = "select * from tbl_memo where mno >90", nativeQuery = true)
   List<Object[]> getNativeResult();
}//end inter..



