// MemberService.java

package net.developia.service;

import net.developia.dto.MemberDTO;

// 회원 관련 비즈니스 로직을 정의하는 서비스 인터페이스
// 컨트롤러와 DAO 사이에서 데이터 처리 및 비즈니스 규칙 적용
public interface MemberService {
    
    // 사용자 로그인을 처리하는 메서드
    /**
     * @param memberDTO     로그인 정보를 담고 있는 MemberDTO 객체
     * @return              로그인 성공 시 해당 MemberDTO 객체 반환, 실패 시 null or 예외 발생
     * @throws Exception    로그인 처리 중 발생할 수 있는 예외
    */
    MemberDTO login(MemberDTO memberDTO) throws Exception;
}