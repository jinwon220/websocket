package bit.or.kr.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bit.or.kr.dao.MemberDao;
import bit.or.kr.dto.Member;

@Service
public class JoinService {
	
	@Autowired
	private SqlSession sqlsession;
	
	public int insert(Member member){
		int result = 0;
		MemberDao dao = sqlsession.getMapper(MemberDao.class);
		try {
			result = dao.insert(member);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return result;
	}
	
	public Member select(String userid){
		MemberDao dao = sqlsession.getMapper(MemberDao.class);
		Member member = null;
		try {
			member = dao.select(userid);
		} catch (Exception e) {
			//여기로 들어온다면 해당 아이디를 가지고 있는 유저가 없음
		} 
		
		return member;
	}
}
