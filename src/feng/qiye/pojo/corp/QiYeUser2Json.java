package feng.qiye.pojo.corp;
/**
 * 企业号创建成员类
 * @author Sunlight
 *
 */
public class QiYeUser2Json {
	private String userid;
	private String name;
	private String[] department;
	private String position;
	private String mobile;
	private String gender;
	private String tel;
	private String email;
	private String weixinid;
	private QiYiExtattr extattr;
	
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
	public String[] getDepartment() {
		return department;
	}
	public void setDepartment(String[] department) {
		this.department = department;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
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
	public QiYiExtattr getExtattr() {
		return extattr;
	}
	public void setExtattr(QiYiExtattr extattr) {
		this.extattr = extattr;
	}
	
}
