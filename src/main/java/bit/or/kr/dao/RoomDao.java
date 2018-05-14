package bit.or.kr.dao;

import java.sql.SQLException;
import java.util.List;

import bit.or.kr.dto.Room;


public interface RoomDao {
	
	//방s
	public List<Room> selects() throws ClassNotFoundException, SQLException;
	
	//방 입자
	public Room select(int roomnumber) throws ClassNotFoundException, SQLException;
	
	//생성
	public int insert(String roomname) throws ClassNotFoundException, SQLException;
}
