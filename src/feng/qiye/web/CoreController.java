package feng.qiye.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.apache.commons.io.IOUtils;

import feng.qiye.aes.AesException;
import feng.qiye.aes.WXBizMsgCrypt;
import feng.qiye.pojo.resp.TextMessage;
import feng.qiye.service.FaceService;
import feng.qiye.service.OAuth2Service;
import feng.qiye.service.WeatherService;
import feng.qiye.util.Constants;
import feng.qiye.util.MessageUtil;
/**
 * 注解方式打开链接
 * 
 * @author Sunlight
 *
 */
@Controller
public class CoreController {
	private String token = Constants.TOKEN;
	private String encodingAESKey =Constants.encodingAESKey;
	private String corpId = Constants.CORPID;

	@RequestMapping(value = { "/coreJoin.do" }, method = RequestMethod.GET)
	public void coreJoinGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// 微信加密签名
		String msg_signature = request.getParameter("msg_signature");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		// 随机字符串
		String echostr = request.getParameter("echostr");

		System.out.println("request=" + request.getRequestURL());

		PrintWriter out = response.getWriter();
		// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
		String result = null;
		try {
			WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(token, encodingAESKey,corpId);
			result = wxcpt.VerifyURL(msg_signature, timestamp, nonce, echostr);
		} catch (AesException e) {
			e.printStackTrace();
		}
		if (result == null) {
			result = token;
		}
		out.print(result);
		out.close();
		out = null;
	}

	@RequestMapping(value = { "/coreJoin.do" }, method = RequestMethod.POST)
	public void coreJoinPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		// 微信加密签名
		String msg_signature = request.getParameter("msg_signature");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		
		//从请求中读取整个post数据
		InputStream inputStream = request.getInputStream();
		String postData = IOUtils.toString(inputStream, "UTF-8");
		System.out.println(postData);
		
		String msg = "";
		String id = "";
		WXBizMsgCrypt wxcpt = null;
		try {
			wxcpt = new WXBizMsgCrypt(token, encodingAESKey, corpId);
			//解密消息
			msg = wxcpt.DecryptMsg(msg_signature, timestamp, nonce, postData);
			
			Map<String, String> requestMap = MessageUtil.parseXml(msg);
			// 消息类型
			id = requestMap.get("AgentID");
		} catch (AesException e) {
			e.printStackTrace();
			System.out.println(e);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		
		//企业号应用的ID
		int agentID = Integer.parseInt(id);
		String encryptMsg = "";
		String respMessage = "";
		switch(agentID){
			case 13:
				// 调用天气预报service
				respMessage = WeatherService.processRequest(msg);
				break;
			case 14:
				OAuth2Service.processRequest(msg);
				break;
			case 15:
				respMessage = FaceService.processRequest(msg);
				break;
		}
		
		try {
			//加密回复消息
			encryptMsg = wxcpt.EncryptMsg(respMessage, timestamp, nonce);
		} catch (AesException e) {
			e.printStackTrace();
		}
		// 响应消息
		PrintWriter out = response.getWriter();
		out.print(encryptMsg);
		out.close();
		
	}

}
