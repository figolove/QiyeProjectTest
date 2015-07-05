package feng.qiye.apis;


import feng.qiye.enums.EnumMethod;
import feng.qiye.util.HttpRequestUtil;

import net.sf.json.JSONObject;

public class MessageAPI {
	//发送消息
	private static final String send_message_url="https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=ACCESS_TOKEN";
	
	public static JSONObject sendMessage(String token,String jsonStr){
		System.err.println("token="+token+"\njsonStr="+jsonStr);
		String requestUrl=send_message_url.replace("ACCESS_TOKEN", token);
		JSONObject jo=HttpRequestUtil.httpRequest(requestUrl, EnumMethod.POST.name(), jsonStr);
		return jo;
	}
}
