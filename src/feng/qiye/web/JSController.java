package feng.qiye.web;

import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import feng.qiye.aes.Sign;
import feng.qiye.pojo.AccessToken;
import feng.qiye.util.Constants;
import feng.qiye.util.QiYeUtil;
import feng.qiye.util.WechatTicket;

@Controller
public class JSController {
	private static AccessToken accessToken = null;
	static {
		accessToken = QiYeUtil.getAccessToken("wx98ecfe8190b9399e", "1nS3UjJm0BSQajXPqn1HwLrSrsjiRmfiyZL6rgS6FZNT8dJ8XZDrKQRR4LrObiRb");
	}
	
	@RequestMapping(value="/jsapi")
	public String helloJs(Model model){
		
		String url = "http://qiye.omsapp.cn/jsapi";
		JSONObject jo = WechatTicket.getTicket(accessToken.getToken());
		System.out.println(jo);
		Map<String, String> ret = Sign.sign(jo.getString("ticket"), url);
        for (Map.Entry entry : ret.entrySet()) {
            System.out.println(entry.getKey() + ", " + entry.getValue());
        }
        model.addAttribute("appid", Constants.CORPID);
        model.addAttribute("jsapi_ticket", ret.get("jsapi_ticket"));
        model.addAttribute("timestamp", ret.get("timestamp"));
        model.addAttribute("nonceStr", ret.get("nonceStr"));
        model.addAttribute("signature", ret.get("signature"));
		return "jsapi";
	}
}
