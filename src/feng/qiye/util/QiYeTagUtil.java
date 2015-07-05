package feng.qiye.util;


import feng.qiye.apis.TagAPI;
import feng.qiye.pojo.Result;
import feng.qiye.pojo.corp.UserList;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class QiYeTagUtil {
	/**
	 * 创建标签
	 * @param token
	 * @param jsonStr
	 * @return
	 */
	public static Result<String> createTag(String token, String jsonStr) {
		Result<String> result =new Result<String>();
		JSONObject jo = TagAPI.createTag(token, jsonStr);
		if (jo != null) {
			result.setErrmsg(ErrorCodeText.errorMsg(jo.getString("errcode")));
			result.setErrcode(jo.getString("errcode"));
			result.setObject(jo.getString("tagid"));
		}
		return result;
	}

	/**
	 * 更新标签
	 * @param token
	 * @param jsonStr
	 * @return
	 */
	public static Result<String> updateTag(String token, String jsonStr) {
		Result<String> result =new Result<String>();
		JSONObject jo = TagAPI.updateTag(token, jsonStr);
		if (jo != null) {
			result.setErrmsg(ErrorCodeText.errorMsg(jo.getString("errcode")));
			result.setErrcode(jo.getString("errcode"));
			result.setObject("");
		}
		return result;
	}

	/**
	 * 删除标签
	 * @param token
	 * @param tagid
	 * @return
	 */
	public static Result<String> deleteTag(String token, String tagid) {
		Result<String> result =new Result<String>();
		JSONObject jo = TagAPI.deleteTag(token, tagid);
		if (jo != null) {
			result.setErrmsg(ErrorCodeText.errorMsg(jo.getString("errcode")));
			result.setErrcode(jo.getString("errcode"));
			result.setObject("");
		}
		return result;
	}
	
	/**
	 * 获取标签成员
	 * @param token
	 * @param tagid
	 * @return
	 */
	public static Result<UserList[]> getTag(String token, String tagid) {
		Result<UserList[]> result = new Result<UserList[]>();
		JSONObject jo = TagAPI.getTag(token,tagid);
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
	 * 增加标签成员
	 * @param token
	 * @param jsonStr
	 * @return
	 */
	public static Result<String> addUserToTag(String token, String jsonStr) {
		Result<String> result =new Result<String>();
		JSONObject jo = TagAPI.addUserToTag(token, jsonStr);
		if (jo != null) {
			result.setErrmsg(ErrorCodeText.errorMsg(jo.getString("errcode")));
			result.setErrcode(jo.getString("errcode"));
			result.setObject("");
		}
		return result;
	}

	/**
	 * 删除标签成员
	 * @param token
	 * @param jsonStr
	 * @return
	 */
	public static Result<String> deleteUserByTag(String token, String jsonStr) {
		Result<String> result =new Result<String>();
		JSONObject jo = TagAPI.deleteUserByTag(token, jsonStr);
		if (jo != null) {
			result.setErrmsg(ErrorCodeText.errorMsg(jo.getString("errcode")));
			result.setErrcode(jo.getString("errcode"));
			result.setObject("");
		}
		return result;
	}
}
