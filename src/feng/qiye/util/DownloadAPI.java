package feng.qiye.util;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


import feng.qiye.enums.EnumMethod;
import feng.qiye.pojo.Result;
import feng.qiye.pojo.corp.QiYeUpload;

import net.sf.json.JSONObject;

public class DownloadAPI {
	private static final String download_url = "https://qyapi.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
	private static final String upload_url = "https://qyapi.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
	public static SimpleDateFormat Dateformat = new SimpleDateFormat("/yyyy/MM/dd/HH/mm/ss/");

	/**
	 * 下载文件
	 * 
	 * @param accessToken
	 * @param media_id
	 * @param path
	 * @return null为成功
	 */
	public static String Download(String accessToken, String media_id, String path) {
		String url = download_url.replace("ACCESS_TOKEN", accessToken).replace("MEDIA_ID", media_id);
		byte[] data = HttpRequestUtil.httpRequest_byte(url, EnumMethod.GET.name(), null);
		if (data.length < 100) {
			String s = new String(data);
			if (s.startsWith("{") && s.endsWith("}")) {
				return s;
			}
		}
		try {
			FileOutputStream os = new FileOutputStream(path);
			os.write(data);
			os.close();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("assn_err_msg", "Download Exception:" + e.toString());
			return jsonObject.toString();
		}
	}

	/**
	 * 上传文件
	 * 
	 * @param accessToken
	 * @param type
	 * @param file
	 * @return
	 */
	public static Result<QiYeUpload> Upload(String accessToken, String type, File file) {
		Result<QiYeUpload> result = new Result<QiYeUpload>();
		String url = upload_url.replace("ACCESS_TOKEN", accessToken).replace("TYPE", type);
		JSONObject jsonObject;
		try {
			HttpPostUtil post = new HttpPostUtil(url);
			post.addParameter("media", file);
			String s = post.send();
			jsonObject = JSONObject.fromObject(s);
			if (jsonObject.containsKey("media_id")) {
				QiYeUpload upload=new QiYeUpload();
				upload.setMedia_id(jsonObject.getString("media_id"));
				upload.setType(jsonObject.getString("type"));
				upload.setCreated_at(jsonObject.getString("created_at"));
				result.setObject(upload);
				result.setErrmsg("success");
				result.setErrcode("0");
			} else {
				result.setErrmsg(jsonObject.getString("errmsg"));
				result.setErrcode(jsonObject.getString("errcode"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrmsg("Upload Exception:"+e.toString());
		}
		return result;
	}

	/**
	 * 根据URL下载文件
	 * 
	 * @param basePath
	 *            获取配置文件中的地址ShareFolder_Path
	 * @param url
	 *            文件URL
	 * @param type
	 *            文件类型
	 * @param extensionName
	 *            扩展名
	 * @return
	 */
	public static Result<String> DownloadCommon(String basePath, String url, String type, String extensionName) {
		Result<String> result = new Result<String>();
		Date d = new Date();
		String vPath = "/wx/" + type + Dateformat.format(d);
		String fileName = UUID.randomUUID().toString().replace("-", "") + "." + extensionName;
		String pPath = basePath + vPath;
		pPath = pPath.replace('\\', File.separatorChar);
		pPath = pPath.replace('/', File.separatorChar);
		File dirFile = new File(pPath);
		dirFile.mkdirs();
		pPath += fileName;
		vPath += fileName;
		String s = null;
		byte[] data = HttpRequestUtil.httpRequest_byte(url, EnumMethod.GET.name(), null);
		if (data == null || data.length == 0) {
			s = "下载失败";
		} else {
			try {
				FileOutputStream os = new FileOutputStream(pPath);
				os.write(data);
				os.close();
			} catch (Exception e) {
				e.printStackTrace();
				s = e.toString();
			}
		}
		if (s == null) {
			System.out.println("pPath="+pPath);
			result.setObject(vPath);
			return result;
		}
		result.setErrmsg(s);
		return result;
	}
}
