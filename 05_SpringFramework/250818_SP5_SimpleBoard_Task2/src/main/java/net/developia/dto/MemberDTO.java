// MemberDTO.java

package net.developia.dto;

import lombok.Data;

// 회원 정보를 담는 DTO(Data Transfer Object) 클래스
// DB의 회원 테이블과 매핑되며, 계층 간 데이터 전송에 사용
@Data
public class MemberDTO {
    // 회원 고유 ID (로그인 아이디)
    private String id;
    // 회원 비밀번호
    private String password;
    // 회원 이름
    private String name;
}