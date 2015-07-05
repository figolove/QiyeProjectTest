package feng.qiye.apis;


import feng.qiye.enums.EnumMethod;
import feng.qiye.util.HttpRequestUtil;

import net.sf.json.JSONObject;

public class DepartmentAPI {
	//创建部门
	private static final String create_department_url="https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token=ACCESS_TOKEN";
	//更新部门
	private static final String update_department_url="https://qyapi.weixin.qq.com/cgi-bin/department/update?access_token=ACCESS_TOKEN";
	//删除部门
	private static final String delete_department_url="https://qyapi.weixin.qq.com/cgi-bin/department/delete?access_token=ACCESS_TOKEN&id=ID";
	//获取部门列表
	private static final String get_department_url="https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token=ACCESS_TOKEN";
	
	public static JSONObject createDepartment(String token,String jsonStr){
		String requestUrl=create_department_url.replace("ACCESS_TOKEN", token);
		JSONObject jo=HttpRequestUtil.httpRequest(requestUrl, EnumMethod.POST.name(), jsonStr);
		System.out.println(jo);
		return jo;
	}
	
	public static JSONObject updateDepartment(String token,String jsonStr){
		String requestUrl=update_department_url.replace("ACCESS_TOKEN", token);
		JSONObject jo=HttpRequestUtil.httpRequest(requestUrl, EnumMethod.POST.name(), jsonStr);
		System.out.println(jo);
		return jo;
	}
	
	public static JSONObject deleteDepartment(String token,String departmentId){
		String requestUrl=delete_department_url.replace("ACCESS_TOKEN", token).replace("ID", departmentId);
		JSONObject jo=HttpRequestUtil.httpRequest(requestUrl, EnumMethod.GET.name(),null);
		System.out.println(jo);
		return jo;
	}
	
	public static JSONObject getDepartment(String token){
		String requestUrl=get_department_url.replace("ACCESS_TOKEN", token);
		JSONObject jo=HttpRequestUtil.httpRequest(requestUrl, EnumMethod.GET.name(), null);
		System.out.println(jo);
		return jo;
	}
}
