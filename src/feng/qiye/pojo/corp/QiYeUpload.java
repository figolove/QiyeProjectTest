package feng.qiye.pojo.corp;

/**
 * 
 * @author sunlight
 *
 */
public class QiYeUpload {
	private String type;
	private String media_id;
	private String created_at;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMedia_id() {
		return media_id;
	}
	public void setMedia_id(String mediaId) {
		media_id = mediaId;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String createdAt) {
		created_at = createdAt;
	}
	public QiYeUpload() {
		super();
	}
	@Override
	public String toString() {
		return "QiYeUpload2Json [created_at=" + created_at + ", media_id=" + media_id + ", type=" + type + "]";
	}
	
	
}
