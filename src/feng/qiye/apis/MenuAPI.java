package feng.qiye.apis;


import feng.qiye.enums.EnumMethod;
import feng.qiye.util.HttpRequestUtil;

import net.sf.json.JSONObject;

public class MenuAPI {
	// 创建菜单,{"errcode":0,"errmsg":"ok"}
	private static final String company_createMenu_url = "https://qyapi.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN&agentid=AgentID";
	// 删除菜单{"errcode":0,"errmsg":"ok"}
	private static final String company_deleteMenu_url = "https://qyapi.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN&agentid=AgentID";
	//获取菜单列表,返回结果与菜单创建的参数一致。
	private static final String company_getMenu_url ="https://qyapi.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN&agentid=AgentID";
	
	public static JSONObject createMenu(String token, int AgentID, String jsonStr) {
		String menuUrl = company_createMenu_url.replace("ACCESS_TOKEN", token).replace("AgentID", AgentID+"");
		JSONObject jo = HttpRequestUtil.httpRequest(menuUrl, EnumMethod.POST.name(), jsonStr);
		System.out.println("jo=" + jo);
		return jo;
	}
	
	public static JSONObject deleteMenu(String token,int AgentID){
		String menuUrl = company_deleteMenu_url.replace("ACCESS_TOKEN", token).replace("AgentID", AgentID+"");
		JSONObject jo = HttpRequestUtil.httpRequest(menuUrl, EnumMethod.GET.name(), null);
		System.out.println("jo=" + jo);
		return jo;
	}
	
	public static JSONObject getMenu(String token,int AgentID){
		String menuUrl = company_getMenu_url.replace("ACCESS_TOKEN", token).replace("AgentID", AgentID+"");
		JSONObject jo = HttpRequestUtil.httpRequest(menuUrl, EnumMethod.GET.name(), null);
		System.out.println("jo=" + jo);
		return jo;
	}
	
}
