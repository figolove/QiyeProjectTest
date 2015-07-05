package feng.qiye.apis;


import feng.qiye.enums.EnumMethod;
import feng.qiye.util.HttpRequestUtil;
import net.sf.json.JSONObject;

public class UserAPI {
	// 创建成员
	private static final String create_user_url = "https://qyapi.weixin.qq.com/cgi-bin/user/create?access_token=ACCESS_TOKEN";
	// 更新成员
	private static final String update_user_url = "https://qyapi.weixin.qq.com/cgi-bin/user/update?access_token=ACCESS_TOKEN";
	// 成员删除
	private static final String delete_user_url = "https://qyapi.weixin.qq.com/cgi-bin/user/delete?access_token=ACCESS_TOKEN&userid=USERID";
	// 获取成员
	private static final String get_user_url = "https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&userid=USERID";
	// 获取未关注成员列表
	private static final String get_user_no_concern_url = "https://qyapi.weixin.qq.com/cgi-bin/user/list?access_token=ACCESS_TOKEN&department_id=DEPARTMENTID&fetch_child=FETCH";
	// 获取应用代理
	private static final String get_agent_url = "https://qyapi.weixin.qq.com/cgi-bin/agent/get?access_token=ACCESS_TOKEN&agentid=AGENTID";
	
	//获取部门成员列表
	private static final String get_user_todepartment_url ="https://qyapi.weixin.qq.com/cgi-bin/user/simplelist?access_token=ACCESS_TOKEN&department_id=DEPARTMENTID&fetch_child=FETCH&status=STATUS";
	
	public static JSONObject createUser(String token, String jsonStr) {
		String requestUrl = create_user_url.replace("ACCESS_TOKEN", token);
		JSONObject jo = HttpRequestUtil.httpRequest(requestUrl, EnumMethod.POST.name(), jsonStr);
		return jo;
	}

	public static JSONObject updateUser(String token, String jsonStr) {
		String requestUrl = update_user_url.replace("ACCESS_TOKEN", token);
		JSONObject jo = HttpRequestUtil.httpRequest(requestUrl, EnumMethod.POST.name(), jsonStr);
		return jo;
	}

	public static JSONObject deleteUser(String token, String userId) {
		String requestUrl = delete_user_url.replace("ACCESS_TOKEN", token).replace("USERID", userId);
		JSONObject jo = HttpRequestUtil.httpRequest(requestUrl, EnumMethod.GET.name(), null);
		return jo;
	}

	public static JSONObject getUser(String token, String userId) {
		String requestUrl = get_user_url.replace("ACCESS_TOKEN", token).replace("USERID", userId);
		JSONObject jo = HttpRequestUtil.httpRequest(requestUrl, EnumMethod.GET.name(), null);
		return jo;
	}

	public static JSONObject getNotConcernUser(String token, int departmentId,int fetch_child) {
		String requestUrl = get_user_no_concern_url.replace("ACCESS_TOKEN", token).replace("DEPARTMENTID", departmentId+"").replace("FETCH", fetch_child+"");
		JSONObject jo = HttpRequestUtil.httpRequest(requestUrl, EnumMethod.GET.name(), null);
		return jo;
	}

	public static JSONObject getAgent(String token, String agentId) {
		String requestUrl = get_agent_url.replace("ACCESS_TOKEN", token).replace("AGENTID", agentId);
		JSONObject jo = HttpRequestUtil.httpRequest(requestUrl, EnumMethod.GET.name(), null);
		return jo;
	}
	
	public static JSONObject getUserByDepartment(String token, int departmentId,int fetch_child,int status) {
		String requestUrl = get_user_todepartment_url.replace("ACCESS_TOKEN", token).replace("DEPARTMENTID", departmentId+"").replace("FETCH", fetch_child+"").replace("STATUS", status+"");
		JSONObject jo = HttpRequestUtil.httpRequest(requestUrl, EnumMethod.GET.name(), null);
		return jo;
	}

}
