package feng.qiye.util;

import net.sf.json.JSONObject;

import feng.qiye.enums.EnumMethod;

public class WechatTicket {
	private static final String get_jsapi_ticket_url = "https://qyapi.weixin.qq.com/cgi-bin/get_jsapi_ticket?access_token=ACCESS_TOKEN";

	/**
	 * 根据code获取成员信息
	 * 
	 * @param token
	 * @param code
	 * @param AgentID
	 * @return
	 */
	public static JSONObject getTicket(String token) {
		String ticketUrl = get_jsapi_ticket_url.replace("ACCESS_TOKEN", token);
		JSONObject jo = HttpRequestUtil.httpRequest(ticketUrl, EnumMethod.GET.name(), null);
		System.out.println("jo=" + jo);
		return jo;
	}

}
