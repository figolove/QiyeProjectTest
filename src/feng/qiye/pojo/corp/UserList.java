package feng.qiye.pojo.corp;

public class UserList {
	private String userid;
	private String name;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public UserList(String userid, String name) {
		super();
		this.userid = userid;
		this.name = name;
	}
	public UserList() {
		super();
	}
	
	
	
}
