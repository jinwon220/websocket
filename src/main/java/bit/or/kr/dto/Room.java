package bit.or.kr.dto;

public class Room {
	int roomnumber;
	String roomname;
	
	public Room() {}
	
	public Room(int roomnumber, String roomname) {
		this.roomnumber = roomnumber;
		this.roomname = roomname;
	}

	public int getRoomnumber() {
		return roomnumber;
	}

	public void setRoomnumber(int roomnumber) {
		this.roomnumber = roomnumber;
	}

	public String getRoomname() {
		return roomname;
	}

	public void setRoomname(String roomname) {
		this.roomname = roomname;
	}

	@Override
	public String toString() {
		return "Room [roomnumber=" + roomnumber + ", roomname=" + roomname + "]";
	}
}
