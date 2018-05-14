package bit.or.kr.dto;

public class Member {
	private String userid;
	private String password;
	private String ROLE_NAME;
	public Member() {}
	
	public Member(String userid, String password, String ROLE_NAME) {
		this.userid = userid;
		this.password = password;
		this.ROLE_NAME = ROLE_NAME;
	}
	
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getROLE_NAME() {
		return ROLE_NAME;
	}

	public void setROLE_NAME(String rOLE_NAME) {
		ROLE_NAME = rOLE_NAME;
	}

}
