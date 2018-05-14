package bit.or.kr.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import bit.or.kr.dao.RoomDao;
import bit.or.kr.dto.Room;


@Service
public class ChatService {
	@Autowired
	private SqlSession sqlsession;
	
	public List<Room> selects(){
		RoomDao dao = sqlsession.getMapper(RoomDao.class);
		List<Room> rooms = null;
		try {
			rooms = (ArrayList<Room>)dao.selects();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return rooms;
	}
	
	public Room select(int roomnumber){
		RoomDao dao = sqlsession.getMapper(RoomDao.class);
		Room room = null;
		try {
			room = dao.select(roomnumber);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return room;
	}
	
	public int insert(String roomname){
		RoomDao dao = sqlsession.getMapper(RoomDao.class);
		int result = 0;
		try {
			result = dao.insert(roomname);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return result;
	}
	
}
