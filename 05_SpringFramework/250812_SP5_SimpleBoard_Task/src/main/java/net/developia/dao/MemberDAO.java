// MemberDAO.java

package net.developia.dao;

import java.sql.SQLException;
import net.developia.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

// 회원 DB 접근을 위한 DAO(Data Access Object) 인터페이스
// MyBatis의 @Mapper 어노테이션을 통해 SQL 매퍼와 연결
@Mapper
public interface MemberDAO {
    // 사용자 로그인 처리
    // 주어진 MemberDTO를 사용하여 DB에서 일치하는 회원 정보 조회
    /**
     * @param memberDTO     로그인 정보를 담고 있는 MemberDTO 객체
     * @return              로그인 성공 시 해당 MemberDTO 객체 반환, 실패 시 null 반환
     * @throws SQLException DB 접근 중 오류 발생 시
    */
    MemberDTO login(MemberDTO memberDTO) throws SQLException;
}