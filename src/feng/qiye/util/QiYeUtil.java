package feng.qiye.util;


import feng.qiye.apis.DepartmentAPI;
import feng.qiye.apis.MenuAPI;
import feng.qiye.apis.MessageAPI;
import feng.qiye.apis.OAuth2API;
import feng.qiye.apis.UserAPI;
import feng.qiye.pojo.AccessToken;
import feng.qiye.pojo.Result;
import feng.qiye.pojo.corp.PartyContainer;
import feng.qiye.pojo.corp.QiYeAgent;
import feng.qiye.pojo.corp.QiYeDepartment;
import feng.qiye.pojo.corp.QiYeUser;
import feng.qiye.pojo.corp.UserContainer;
import feng.qiye.pojo.corp.UserList;
import feng.qiye.util.ErrorCodeText;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 微信企业号调用类 {"errcode":0,"errmsg":"ok"} 此结果表示调用方法成功返回
 * 
 * @author Sunlight
 * 
 */
public class QiYeUtil {
	/**
	 * 获取企业号AccessToken
	 * 
	 * @param CorpID
	 * @param CorpSecret
	 * @return
	 */
	public static AccessToken getAccessToken(String CorpID, String CorpSecret) {
		AccessToken accessToken = WechatAccessToken.getAccessToken(CorpID,
				CorpSecret, 1);
		return accessToken;
	}

	/**
	 * OAuth2验证接口根据code获取成员信息
	 * 
	 * @param token
	 * @param code
	 * @param AgentID
	 * @return
	 */
	public static Result<String> oAuth2GetUserByCode(String token, String code,
			int AgentID) {
		Result<String> result = new Result<String>();
		JSONObject jo = WechatOAuth2.getUserByCode(token, code, AgentID);
		if (jo != null) {
			try {
				String userId = jo.getString("UserId");
				if (userId != null && userId.length() > 0) {
					result.setErrmsg("");
					result.setErrcode("0");
					result.setObject(userId);
				} else {
					result.setErrmsg(jo.getString("errmsg"));
					result.setErrcode(jo.getString("errcode"));
				}

			} catch (Exception e) {
				result.setErrmsg("accessToken 超时......");
				result.setErrcode("42001");
			}
		}
		return result;
	}
	
	/**
	 * 创建部门
	 * 
	 * @param token
	 * @param jsonStr
	 * @return
	 */
	public static Result<String> createDepartment(String token, String jsonStr) {
		Result<String> result = new Result<String>();
		JSONObject jo = DepartmentAPI.createDepartment(token, jsonStr);
		if (jo != null) {
			result.setErrmsg(ErrorCodeText.errorMsg(jo.getString("errcode")));
			result.setErrcode(jo.getString("errcode"));
			result.setObject(jo.getString("id"));
		}
		return result;
	}

	/**
	 * 修改部门
	 * 
	 * @param token
	 * @param jsonStr
	 * @return
	 */
	public static Result<String> updateDepartment(String token, String jsonStr) {
		Result<String> result = new Result<String>();
		JSONObject jo = DepartmentAPI.updateDepartment(token, jsonStr);
		if (jo != null) {
			result.setErrmsg(ErrorCodeText.errorMsg(jo.getString("errcode")));
			result.setErrcode(jo.getString("errcode"));
			result.setObject("");
		}
		return result;
	}

	/**
	 * 删除部门
	 * 
	 * @param jsonStr
	 * @return
	 */
	public static Result<String> deleteDepartment(String token, String jsonStr) {
		Result<String> result = new Result<String>();
		JSONObject jo = DepartmentAPI.deleteDepartment(token, jsonStr);
		if (jo != null) {
			result.setErrmsg(ErrorCodeText.errorMsg(jo.getString("errcode")));
			result.setErrcode(jo.getString("errcode"));
			result.setObject("");
		}
		return result;
	}

	/**
	 * 获取部门列表
	 * 
	 * @return
	 */
	public static Result<QiYeDepartment[]> getDepartment(String token) {
		Result<QiYeDepartment[]> result = new Result<QiYeDepartment[]>();
		JSONObject jo = DepartmentAPI.getDepartment(token);
		if (jo != null) {
			result.setErrmsg(ErrorCodeText.errorMsg(jo.getString("errcode")));
			result.setErrcode(jo.getString("errcode"));
			JSONArray ja = jo.getJSONArray("department");
			if (ja != null && ja.size() > 0) {
				QiYeDepartment[] departments = new QiYeDepartment[ja.size()];
				for (int i = 0; i < ja.size(); i++) {
					JSONObject jsonObject = ja.getJSONObject(i);
					int id = jsonObject.getInt("id");
					int parentid = jsonObject.getInt("parentid");
					departments[i] = new QiYeDepartment(id, jsonObject.get("name") + "", parentid);
				}
				result.setObject(departments);
			}
		}
		return result;
	}

	/**
	 * 创建企业成员
	 * 
	 * @param jsonStr
	 * @return
	 */
	public static Result<String> createUser(String token, String jsonStr) {
		Result<String> result = new Result<String>();
		JSONObject jo = UserAPI.createUser(token, jsonStr);
		if (jo != null) {
			result.setErrmsg(ErrorCodeText.errorMsg(jo.getString("errcode")));
			result.setErrcode(jo.getString("errcode"));
			result.setObject("");
		}
		return result;
	}

	/**
	 * 更新企业成员
	 * 
	 * @param jsonStr
	 * @return
	 */
	public static Result<String> updateUser(String token, String jsonStr) {
		Result<String> result = new Result<String>();
		JSONObject jo = UserAPI.updateUser(token, jsonStr);
		if (jo != null) {
			result.setErrmsg(ErrorCodeText.errorMsg(jo.getString("errcode")));
			result.setErrcode(jo.getString("errcode"));
			result.setObject("");
		}
		return result;
	}

	/**
	 * 删除企业成员
	 * 
	 * @param userId
	 * @return
	 */
	public static Result<String> deleteUser(String token, String userId) {
		Result<String> result = new Result<String>();
		JSONObject jo = UserAPI.deleteUser(token, userId);
		if (jo != null) {
			result.setErrmsg(ErrorCodeText.errorMsg(jo.getString("errcode")));
			result.setErrcode(jo.getString("errcode"));
			result.setObject("");
		}
		return result;
	}

	/**
	 * 获取成员
	 * 
	 * @return
	 */
	public static Result<QiYeUser> getUser(String token, String userId) {
		Result<QiYeUser> result = new Result<QiYeUser>();
		JSONObject jo = UserAPI.getUser(token, userId);
		if (jo != null) {
			result.setErrmsg(ErrorCodeText.errorMsg(jo.getString("errcode")));
			result.setErrcode(jo.getString("errcode"));
			if (result.getErrcode().equals("0")) {
				QiYeUser user = new QiYeUser();
				user.setUserId(jo.getString("userid"));
				user.setName(jo.getString("name"));
				user.setDepartment(jo.getString("department"));
				if (isEmpty(jo.toString(), "position", jo.getString("position"))) {
					user.setPosition(jo.getString("position"));
				}
				if (isEmpty(jo.toString(), "mobile", jo.getString("mobile"))) {
					user.setMobile(jo.getString("mobile"));
				}
				user.setGender(jo.getInt("gender"));
				if (isEmpty(jo.toString(), "tel", jo.getString("tel"))) {
					user.setTel(jo.getString("tel"));
				}
				if (isEmpty(jo.toString(), "email", jo.getString("email"))) {
					user.setEmail(jo.getString("email"));
				}
				if (isEmpty(jo.toString(), "weixinid", jo.getString("weixinid"))) {
					user.setWeixinid(jo.getString("weixinid"));
				}
				if (isEmpty(jo.toString(), "avatar", jo.getString("avatar"))) {
					user.setAvatar(jo.getString("avatar"));
				}
				user.setStatus(jo.getInt("status"));
				result.setObject(user);
			}
		}
		return result;
	}

	/**
	 * 获取用户信息，必须必填列
	 * 
	 * @param token
	 * @param userId
	 * @return
	 */
	public static Result<QiYeUser> getUser4Column(String token, String userId) {
		Result<QiYeUser> result = new Result<QiYeUser>();
		JSONObject jo = UserAPI.getUser(token, userId);
		if (jo != null) {
			result.setErrmsg(ErrorCodeText.errorMsg(jo.getString("errcode")));
			result.setErrcode(jo.getString("errcode"));
			if (result.getErrcode().equals("0")) {
				QiYeUser user = new QiYeUser();
				user.setUserId(jo.getString("userid"));
				user.setName(jo.getString("name"));
				user.setDepartment(jo.getString("department"));
				user.setStatus(jo.getInt("status"));
				result.setObject(user);
			}
		}
		return result;
	}

	/**
	 * 获取未关注成员列表
	 * 
	 * @return
	 */
	public static Result<QiYeUser[]> getNotConcernUser(String token, int departmentId, int fetch_child) {
		Result<QiYeUser[]> result = new Result<QiYeUser[]>();
		JSONObject jo = UserAPI.getNotConcernUser(token, departmentId, fetch_child);
		if (jo != null) {
			result.setErrmsg(ErrorCodeText.errorMsg(jo.getString("errcode")));
			result.setErrcode(jo.getString("errcode"));
			JSONArray ja = jo.getJSONArray("userlist");
			if (ja != null && ja.size() > 0) {
				QiYeUser[] users = new QiYeUser[ja.size()];
				for (int i = 0; i < ja.size(); i++) {
					JSONObject jsonObject = ja.getJSONObject(i);
					QiYeUser user = new QiYeUser();
					user.setUserId(jsonObject.getString("userid"));
					user.setName(jsonObject.getString("name"));
					user.setDepartment(jsonObject.getString("department"));
					if (isEmpty(jo.toString(), "position", jo.getString("position"))) {
						user.setPosition(jo.getString("position"));
					}
					if (isEmpty(jo.toString(), "mobile", jo.getString("mobile"))) {
						user.setMobile(jo.getString("mobile"));
					}
					user.setGender(jo.getInt("gender"));
					if (isEmpty(jo.toString(), "tel", jo.getString("tel"))) {
						user.setTel(jo.getString("tel"));
					}
					if (isEmpty(jo.toString(), "email", jo.getString("email"))) {
						user.setEmail(jo.getString("email"));
					}
					if (isEmpty(jo.toString(), "weixinid", jo.getString("weixinid"))) {
						user.setWeixinid(jo.getString("weixinid"));
					}
					if (isEmpty(jo.toString(), "avatar", jo.getString("avatar"))) {
						user.setAvatar(jo.getString("avatar"));
					}
					user.setGender(jsonObject.getInt("gender"));
					user.setStatus(jsonObject.getInt("status"));
					users[i] = user;
				}
				result.setObject(users);
			}
		}
		return result;
	}

	/**
	 * 获取部门成员列表
	 * 
	 * @param token
	 * @param departmentId
	 * @param fetch_child
	 * @param status
	 * @return
	 */
	public static Result<UserList[]> getUserByDepartment(String token, int departmentId, int fetch_child, int status) {
		Result<UserList[]> result = new Result<UserList[]>();
		JSONObject jo = UserAPI.getUserByDepartment(token, departmentId, fetch_child, status);
		if (jo != null) {
			result.setErrcode(jo.getString("errcode"));
			result.setErrmsg(ErrorCodeText.errorMsg(jo.getString("errcode")));
			JSONArray ja = jo.getJSONArray("userlist");
			if (ja != null && ja.size() > 0) {
				UserList[] userLists = new UserList[ja.size()];
				for (int i = 0; i < ja.size(); i++) {
					JSONObject jsonObject = ja.getJSONObject(i);
					userLists[i] = new UserList(jsonObject.getString("userid"), jsonObject.getString("name"));
				}
				result.setObject(userLists);
			}
		}
		return result;
	}

	/**
	 * 获取应用代理
	 * 
	 * @return
	 */
	public static Result<QiYeAgent> getAgent(String token, String agentid) {
		Result<QiYeAgent> result = new Result<QiYeAgent>();
		JSONObject jo = UserAPI.getAgent(token, agentid);
		if (jo != null) {
			System.out.println(jo);
			result.setErrmsg(ErrorCodeText.errorMsg(jo.getString("errcode")));
			result.setErrcode(jo.getString("errcode"));
			if (result.getErrcode().equals("0")) {
				QiYeAgent agent = new QiYeAgent();
				agent.setName(jo.getString("name"));
				agent.setAgentid(jo.getString("agentid"));
				JSONObject jsonObject = jo.getJSONObject("allowpartys");
				agent.setAllowpartys(new PartyContainer(jsonObject.getString("partyid")));
				agent.setAllowusers(new UserContainer(jo.getString("allowusers")));
				agent.setMode(jo.getString("mode"));
				if (agent.getMode() == "1") {
					agent.setCallbackurl(jo.getString("callbackurl"));
					agent.setUrltoken(jo.getString("urltoken"));
					agent.setReport_location_flag(jo.getString("report_location_flag"));
				}
				agent.setDescription(jo.getString("description"));
				agent.setRedirectdomain(jo.getString("redirectdomain"));
				agent.setRoundUrl(jo.getString("RoundUrl"));
				agent.setSquareUrl(jo.getString("SquareUrl"));
				agent.setUseridlist(jo.getString("useridlist"));
				agent.setClose(jo.getInt("close"));
				result.setObject(agent);
			}
		}
		return result;
	}

	/**
	 * 创建菜单
	 * 
	 * @param AgentID
	 *            应用ID
	 * @param jsonStr
	 * @return
	 */
	public static Result<String> createMenu(String token, int AgentID, String jsonStr) {
		Result<String> result = new Result<String>();
		JSONObject jo = MenuAPI.createMenu(token, AgentID, jsonStr);
		if (jo != null) {
			result.setErrmsg(ErrorCodeText.errorMsg(jo.getString("errcode")));
			result.setErrcode(jo.getString("errcode"));
			result.setObject("");
		}
		return result;
	}

	/**
	 * 删除菜单
	 * 
	 * @param AgentID
	 * @return
	 */
	public static Result<String> deleteMenu(String token, int AgentID) {
		Result<String> result = new Result<String>();
		JSONObject jo = MenuAPI.deleteMenu(token, AgentID);
		if (jo != null) {
			result.setErrmsg(ErrorCodeText.errorMsg(jo.getString("errcode")));
			result.setErrcode(jo.getString("errcode"));
			result.setObject("");
		}
		return result;
	}

	/**
	 * 获取菜单列表
	 * 
	 * @param AgentID
	 * @return
	 */
	public static Result<JSONObject> getMenu(String token, int AgentID) {
		Result<JSONObject> result = new Result<JSONObject>();
		JSONObject jo = MenuAPI.getMenu(token, AgentID);
		if (jo != null) {

			String errmsg = null;
			String errcode = null;
			try {
				errmsg = ErrorCodeText.errorMsg(jo.getString("errcode"));
				errcode = jo.getString("errcode");
				System.out.println("errmsg=" + errmsg);
				System.out.println("errcode=" + errcode);
				result.setErrmsg(errmsg);
				result.setErrcode(errcode);
			} catch (Exception e) {
			}

			if (result.getErrcode() == null && result.getErrmsg() == null) {
				result.setObject(jo.getJSONObject("menu"));
			}
		}
		return result;
	}

	/**
	 * 发送消息
	 * 
	 * @param jsonStr
	 * @return
	 */
	public static Result<String> sendMessage(String token, String jsonStr) {
		Result<String> result = new Result<String>();
		JSONObject jo = MessageAPI.sendMessage(token, jsonStr);
		if (jo != null) {
			System.out.println(jo);
			result.setErrmsg(ErrorCodeText.errorMsg(jo.getString("errcode")));
			result.setErrcode(jo.getString("errcode"));
			result.setObject("");
		}
		return result;
	}
	
	private static boolean isEmpty(String jo, String field, String str) {
		if (!jo.contains(field)) {
			return false;
		}
		if (str != null && str.length() > 0) {
			return true;
		}
		return false;
	}
	
}
