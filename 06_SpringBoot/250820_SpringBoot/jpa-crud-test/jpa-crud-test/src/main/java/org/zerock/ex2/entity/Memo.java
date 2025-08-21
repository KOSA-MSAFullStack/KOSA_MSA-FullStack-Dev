package org.zerock.ex2.entity;

import lombok.*;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tbl_memo")
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Memo {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long mno;

   @Column(length = 200, nullable = false)
   private String memoText;
}//end class



