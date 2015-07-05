package feng.qiye.util;

import net.sf.json.JSONObject;

public class JsonUtil {
	/**
	 * JSON字符串转对象
	 * 
	 * @param json
	 * @param cls
	 * @return
	 */
	public static Object evalJson(String json, Class<?> cls) {
		JSONObject obj = JSONObject.fromObject(json);
		return JSONObject.toBean(obj, cls);

	}

	/**
	 * 对象转JSON字符串
	 * 
	 * @param obj
	 * @return
	 */
	public static String toJson(Object obj) {
		JSONObject json = JSONObject.fromObject(obj);
		return json.toString();
	}
}
