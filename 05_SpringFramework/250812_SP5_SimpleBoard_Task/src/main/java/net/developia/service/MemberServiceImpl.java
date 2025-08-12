// MemberServiceImpl.java

package net.developia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import net.developia.dao.MemberDAO;
import net.developia.dto.MemberDTO;

/**
 * MemberService 인터페이스의 구현체입니다.
 * 회원 관련 비즈니스 로직을 실제로 처리합니다.
 */
@Slf4j
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDAO memberDAO; // MemberDAO를 주입받아 데이터베이스 연동

    /**
     * 사용자 로그인을 처리하는 메서드 구현입니다.
     * MemberDAO를 통해 데이터베이스에서 회원 정보를 조회하고,
     * 아이디와 비밀번호 일치 여부를 확인하여 로그인 성공/실패를 판단합니다.
     *
     * @param memberDTO 로그인 정보를 담고 있는 MemberDTO 객체 (ID, password)
     * @return 로그인 성공 시 해당 MemberDTO 객체 반환
     * @throws Exception 아이디 또는 비밀번호 불일치 시, 혹은 데이터베이스 접근 중 오류 발생 시
     */
    @Override
    public MemberDTO login(MemberDTO memberDTO) throws Exception {
        try {
            // MemberDAO를 호출하여 데이터베이스에서 회원 정보 조회
            MemberDTO loginMember = memberDAO.login(memberDTO);
            // 조회된 회원 정보가 없으면 (아이디 또는 비밀번호 불일치) 예외 발생
            if (loginMember == null) {
                throw new RuntimeException("아이디 혹은 비밀번호가 일치하지 않습니다.");
            }
            // 로그인 성공 시 조회된 회원 정보 반환
            return loginMember;
        } catch (Exception e) {
            // 로그인 처리 중 발생한 예외 로깅
            log.info(e.getMessage());
            // 예외를 다시 던져 상위 계층에서 처리하도록 함
            throw e;
        }
    }
}