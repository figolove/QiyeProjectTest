package feng.qiye.pojo.req;
/**
 * 请求消息之图片消息
 * @author sunlight
 *
 */
public class ImageMessage extends BaseMessage{
	/**
	 *  图片链接  
	 */
    private String PicUrl;

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}  
    
    
}
