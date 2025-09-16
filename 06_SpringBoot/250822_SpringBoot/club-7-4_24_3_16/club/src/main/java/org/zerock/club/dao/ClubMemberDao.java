package org.zerock.club.dao;

import java.sql.SQLException;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.zerock.club.dto.ClubMember;
import org.zerock.club.dto.ClubMember2;
import org.zerock.club.dto.ClubMemberRoleSet;

@Mapper
public interface ClubMemberDao {
	public void insertClubMember(ClubMember clubMember) 
			throws SQLException;

	public void insertClubRoleSet(ClubMemberRoleSet clubMemberRoleSet) 
			throws SQLException;

	public ClubMember2 findByEmail(@Param("email") String email, @Param("social") int social) 
			throws SQLException;
}// end interface