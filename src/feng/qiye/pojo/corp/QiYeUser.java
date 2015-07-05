package feng.qiye.pojo.corp;
/**
 * 企业通讯录获取信息类
 * @author sunlight
 *
 */
public class QiYeUser {
	private String userId;
	private String name;
	private String department;
	private String position;
	private String mobile;
	private int gender;
	private String tel;
	private String email;
	private String weixinid;
	private String avatar;
	private int status;

	public QiYeUser() {
		super();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWeixinid() {
		return weixinid;
	}

	public void setWeixinid(String weixinid) {
		this.weixinid = weixinid;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public QiYeUser(String userId, String name, String department, String position, String mobile, int gender, String tel, String email, String weixinid, String avatar, int status) {
		super();
		this.userId = userId;
		this.name = name;
		this.department = department;
		this.position = position;
		this.mobile = mobile;
		this.gender = gender;
		this.tel = tel;
		this.email = email;
		this.weixinid = weixinid;
		this.avatar = avatar;
		this.status = status;
	}

}
