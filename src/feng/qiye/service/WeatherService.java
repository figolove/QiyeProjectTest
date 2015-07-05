package feng.qiye.service;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


import feng.qiye.enums.EnumMethod;
import feng.qiye.pojo.resp.Article;
import feng.qiye.pojo.resp.NewsMessage;
import feng.qiye.pojo.resp.TextMessage;
import feng.qiye.util.HttpRequestUtil;
import feng.qiye.util.MessageUtil;

/**
 * 处理微信发来的信息
 * @author Sunlight
 *
 */
public class WeatherService {

	public static String processRequest(String msg) {
		String respMessage = null;
		try {
			// 默认返回的文本消息内容
			String respContent = "请求处理异常，请稍候尝试！";

			System.out.println("msg_XML=="+msg);
			
			// xml请求解析	
			Map<String, String> requestMap = MessageUtil.parseXml(msg);

			System.out.println("Event=="+requestMap.get("Event"));
			
			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");
			// 关键字
			String keyword = requestMap.get("Content");
			// 经纬度
			String j = requestMap.get("Location_X");
			String w = requestMap.get("Location_Y");
			
			String url = "";
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				url = "http://api.map.baidu.com/telematics/v2/weather?output=json&location="+URLEncoder.encode(keyword,"UTF-8")+"&ak=1a3cde429f38434f1811a75e1a90310c";
			}
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				url = "http://api.map.baidu.com/telematics/v2/weather?output=json&location="+w+","+j+"&ak=1a3cde429f38434f1811a75e1a90310c";
			}
			JSONObject json = HttpRequestUtil.httpRequest(url, EnumMethod.POST.name(), null);
			//System.out.println(json);
			
			String city = json.getString("currentCity");
			JSONArray data = json.getJSONArray("results");
			JSONObject info1=data.getJSONObject(0);
			String date1=info1.getString("date");
			String weather1=info1.getString("weather");
			String wind1=info1.getString("wind");
			String temperature1=info1.getString("temperature");
			String nightPictureUrl1=info1.getString("nightPictureUrl");
			date1 = city+date1+weather1+wind1+temperature1;
			
			JSONObject info2=data.getJSONObject(1);
			String date2=info2.getString("date");
			String weather2=info2.getString("weather");
			String wind2=info2.getString("wind");
			String temperature2=info2.getString("temperature");
			String nightPictureUrl2=info2.getString("nightPictureUrl");
			date2 = city+date2+weather2+wind2+temperature2;
			
			JSONObject info3=data.getJSONObject(2);
			String date3=info3.getString("date");
			String weather3=info3.getString("weather");
			String wind3=info3.getString("wind");
			String temperature3=info3.getString("temperature");
			String nightPictureUrl3=info3.getString("nightPictureUrl");
			date3 = city+date3+weather3+wind3+temperature3;
			
			// 回复news消息
			NewsMessage newsMessage = new NewsMessage();
			newsMessage.setToUserName(fromUserName);
			newsMessage.setFromUserName(toUserName);
			newsMessage.setCreateTime(new Date().getTime());
			newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
			newsMessage.setFuncFlag(0);
			newsMessage.setArticleCount(3);
			
			List<Article> list = new ArrayList<Article>();
			Article article1 = new Article();
			article1.setTitle(date1);
			article1.setPicUrl(nightPictureUrl1);
			list.add(article1);
			
			Article article2 = new Article();
			article2.setTitle(date2);
			article2.setPicUrl(nightPictureUrl2);
			list.add(article2);
			
			Article article3 = new Article();
			article3.setTitle(date3);
			article3.setPicUrl(nightPictureUrl3);
			list.add(article3);
			
			newsMessage.setArticles(list);

			
			respMessage = MessageUtil.newsMessageToXml(newsMessage);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		return respMessage;
	}

}
