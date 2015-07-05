package feng.qiye.apis;


import feng.qiye.enums.EnumMethod;
import feng.qiye.util.HttpRequestUtil;

import net.sf.json.JSONObject;

public class TagAPI {
	//创建标签
	private static final String create_tag_url="https://qyapi.weixin.qq.com/cgi-bin/tag/create?access_token=ACCESS_TOKEN";
	//更新标签名字 
	private static final String update_tag_url="https://qyapi.weixin.qq.com/cgi-bin/tag/update?access_token=ACCESS_TOKEN";
	//删除标签
	private static final String delete_tag_url="https://qyapi.weixin.qq.com/cgi-bin/tag/delete?access_token=ACCESS_TOKEN&tagid=TAGID";
	//获取标签成员
	private static final String get_user_totag_url="https://qyapi.weixin.qq.com/cgi-bin/tag/get?access_token=ACCESS_TOKEN&tagid=TAGID";
	//增加标签成员
	private static final String add_user_totag_url="https://qyapi.weixin.qq.com/cgi-bin/tag/addtagusers?access_token=ACCESS_TOKEN";
	//删除标签成员
	private static final String delete_user_totag_url="https://qyapi.weixin.qq.com/cgi-bin/tag/deltagusers?access_token=ACCESS_TOKEN";
	
	public static JSONObject createTag(String token, String jsonStr) {
		String requestUrl = create_tag_url.replace("ACCESS_TOKEN", token);
		JSONObject jo = HttpRequestUtil.httpRequest(requestUrl, EnumMethod.POST.name(), jsonStr);
		return jo;
	}

	public static JSONObject updateTag(String token, String jsonStr) {
		String requestUrl = update_tag_url.replace("ACCESS_TOKEN", token);
		JSONObject jo = HttpRequestUtil.httpRequest(requestUrl, EnumMethod.POST.name(), jsonStr);
		return jo;
	}

	public static JSONObject deleteTag(String token, String tagid) {
		String requestUrl = delete_tag_url.replace("ACCESS_TOKEN", token).replace("TAGID", tagid);
		JSONObject jo = HttpRequestUtil.httpRequest(requestUrl, EnumMethod.GET.name(), null);
		return jo;
	}

	public static JSONObject getTag(String token, String tagid) {
		String requestUrl = get_user_totag_url.replace("ACCESS_TOKEN", token).replace("TAGID", tagid);
		JSONObject jo = HttpRequestUtil.httpRequest(requestUrl, EnumMethod.GET.name(), null);
		return jo;
	}
	
	public static JSONObject addUserToTag(String token, String jsonStr) {
		String requestUrl = add_user_totag_url.replace("ACCESS_TOKEN", token);
		JSONObject jo = HttpRequestUtil.httpRequest(requestUrl, EnumMethod.POST.name(), jsonStr);
		return jo;
	}

	public static JSONObject deleteUserByTag(String token, String jsonStr) {
		String requestUrl = delete_user_totag_url.replace("ACCESS_TOKEN", token);
		JSONObject jo = HttpRequestUtil.httpRequest(requestUrl, EnumMethod.POST.name(), jsonStr);
		return jo;
	}
	
}
