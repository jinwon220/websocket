package bit.or.kr.dao;

import java.sql.SQLException;

import bit.or.kr.dto.Member;

public interface MemberDao {
	
	//접속
	public int insert(Member member) throws ClassNotFoundException, SQLException;
	
	//검색
	public Member select(String userid) throws ClassNotFoundException, SQLException;
}
