package feng.qiye.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;


import feng.qiye.util.HttpRequestUtil;
import feng.qiye.util.MessageUtil;
import feng.qiye.web.OAuth2Controller;

public class OAuth2Service {
	public static String processRequest(String msg) {
		try {
			// xml请求解析
			Map<String, String> requestMap;
			requestMap = MessageUtil.parseXml(msg);
			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");
			// 关键字
			String keyword = requestMap.get("Content");
			
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				// 自定义菜单点击事件
				if (eventType.equalsIgnoreCase(MessageUtil.EVENT_TYPE_CLICK)) {
					// 事件KEY值，与创建自定义菜单时指定的KEY值对应  
                    String eventKey = requestMap.get("EventKey");
                    if(eventKey.equals("OAuth2")){
                    	StringBuffer buffer = new StringBuffer();
                    	String requestUrl = "http://figolove.oicp.net:8080/QiyeProject/oauth2.do";
                    	URL url = new URL(requestUrl);
            			HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();

            			httpUrlConn.setDoOutput(true);
            			httpUrlConn.setDoInput(true);
            			httpUrlConn.setUseCaches(false);
            			// 设置请求方式（GET/POST）
            			httpUrlConn.setRequestMethod("POST");
            			InputStream inputStream = httpUrlConn.getInputStream();
            			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            			String str = null;
            			while ((str = bufferedReader.readLine()) != null) {
            				buffer.append(str);
            			}
            			bufferedReader.close();
            			inputStreamReader.close();
            			// 释放资源
            			inputStream.close();
            			inputStream = null;
            			httpUrlConn.disconnect();
                    	/*
                    	 * get 请求
                        URL getUrl = new URL(url);  
                        HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();  
                        connection.connect();  
                        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));//设置编码,否则中文乱码  
                        String lines;  
                        while ((lines = reader.readLine()) != null){  
                            //lines = new String(lines.getBytes(), "utf-8");  
                            System.out.println(lines);  
                        }  
                        reader.close();  
                        // 断开连接  
                        connection.disconnect();  
                        */
                    }
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
