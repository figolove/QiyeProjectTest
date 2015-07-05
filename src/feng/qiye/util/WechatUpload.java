package feng.qiye.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONObject;

import feng.qiye.enums.EnumMethod;

/**
 * 上传文件
 * 
 * @author Sunlight
 *
 */
public class WechatUpload {
	private static final String upload_wechat_url = "https://qyapi.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";

	public static JSONObject upload(String accessToken, String type, String fileUrl) {
		JSONObject jsonObject = null;
		String last_wechat_url = upload_wechat_url.replace("ACCESS_TOKEN", accessToken).replace("TYPE", type);
		// 定义数据分割符
		String boundary = "----------sunlight";
		try {
			URL uploadUrl = new URL(last_wechat_url);
			HttpURLConnection uploadConn = (HttpURLConnection) uploadUrl.openConnection();
			uploadConn.setDoOutput(true);
			uploadConn.setDoInput(true);
			uploadConn.setRequestMethod("POST");
			// 设置请求头Content-Type
			uploadConn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
			// 获取媒体文件上传的输出流（往微信服务器写数据）
			OutputStream outputStream = uploadConn.getOutputStream();

			URL mediaUrl = new URL(fileUrl);
			HttpURLConnection meidaConn = (HttpURLConnection) mediaUrl.openConnection();
			meidaConn.setDoOutput(true);
			meidaConn.setRequestMethod("GET");

			// 从请求头中获取内容类型
			String contentType = meidaConn.getHeaderField("Content-Type");
			String filename=getFileName(fileUrl,contentType);
			// 请求体开始
			outputStream.write(("--" + boundary + "\r\n").getBytes());
			outputStream.write(String.format("Content-Disposition: form-data; name=\"media\"; filename=\"%s\"\r\n", filename).getBytes());
			outputStream.write(String.format("Content-Type: %s\r\n\r\n", contentType).getBytes());

			// 获取媒体文件的输入流（读取文件）
			BufferedInputStream bis = new BufferedInputStream(meidaConn.getInputStream());
			byte[] buf = new byte[1024 * 8];
			int size = 0;
			while ((size = bis.read(buf)) != -1) {
				// 将媒体文件写到输出流（往微信服务器写数据）
				outputStream.write(buf, 0, size);
			}
			// 请求体结束
			outputStream.write(("\r\n--" + boundary + "--\r\n").getBytes());
			outputStream.close();
			bis.close();
			meidaConn.disconnect();

			// 获取媒体文件上传的输入流（从微信服务器读数据）
			InputStream inputStream = uploadConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			StringBuffer buffer = new StringBuffer();
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			uploadConn.disconnect();
			// 使用json解析
			jsonObject = JSONObject.fromObject(buffer.toString());
			System.out.println(jsonObject);
		} catch (Exception e) {
			System.out.println("上传文件失败！");
			e.printStackTrace();
		}
		return jsonObject;
	}

	public static String getFileName(String fileUrl,String contentType) {
		String filename="";
		if (fileUrl != null && !"".equals(fileUrl)) {
			if(fileUrl.contains(".")){
				filename = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
			}else{
				if(contentType==null || "".equals(contentType)){
					return "";
				}
				String fileExt="";
				if ("image/jpeg".equals(contentType)) {
					fileExt = ".jpg";
				} else if ("audio/mpeg".equals(contentType)) {
					fileExt = ".mp3";
				} else if ("audio/amr".equals(contentType)) {
					fileExt = ".amr";
				} else if ("video/mp4".equals(contentType)) {
					fileExt = ".mp4";
				} else if ("video/mpeg4".equals(contentType)) {
					fileExt = ".mp4";
				} else if ("text/plain".equals(contentType)) {
					fileExt = ".txt";
				} else if ("text/xml".equals(contentType)) {
					fileExt = ".xml";
				} else if ("application/pdf".equals(contentType)) {
					fileExt = ".pdf";
				} else if ("application/msword".equals(contentType)) {
					fileExt = ".doc";
				} else if ("application/vnd.ms-powerpoint".equals(contentType)) {
					fileExt = ".ppt";
				} else if ("application/vnd.ms-excel".equals(contentType)) {
					fileExt = ".xls";
				}
				filename="Media文件"+fileExt;
			}
		}
		return filename;
	}


	public static JSONObject uploadByFile(String accessToken, String type, File file) {
		JSONObject jsonObject = null;
		String last_wechat_url = upload_wechat_url.replace("ACCESS_TOKEN", accessToken).replace("TYPE", type);
		// 定义数据分割符
		String boundary = "----------sunlight";
		try {
			//构造文件表单数据
			HashMap<String, File> fileparams = new HashMap<String, File>();
			fileparams.put("media", file);
			URL uploadUrl = new URL(last_wechat_url);
			HttpURLConnection uploadConn = (HttpURLConnection) uploadUrl.openConnection();
			uploadConn.setDoOutput(true);
			uploadConn.setDoInput(true);
			uploadConn.setRequestMethod("POST");
			// 设置请求头Content-Type
			uploadConn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
			uploadConn.connect();
			// 获取媒体文件上传的输出流（往微信服务器写数据）
			DataOutputStream outputStream = new DataOutputStream(uploadConn.getOutputStream());
			
			// 从请求头中获取内容类型
			String contentType = "Content-Type: " + getContentType();
			// 根据内容类型判断文件扩展名
			String filename =getFileName(file);
			// 请求体开始
			outputStream.write(("--" + boundary + "\r\n").getBytes());
			outputStream.write(String.format("Content-Disposition: form-data; name=\"media\"; filename=\"%s\"\r\n", filename).getBytes());
			outputStream.write(String.format("Content-Type: %s\r\n\r\n", contentType).getBytes());

			// 获取媒体文件的输入流（读取文件）
			DataInputStream in = new DataInputStream(new FileInputStream(file));
			byte[] buf = new byte[1024 * 8];
			int size = 0;
			while ((size = in.read(buf)) != -1) {
				// 将媒体文件写到输出流（往微信服务器写数据）
				outputStream.write(buf, 0, size);
			}
			// 请求体结束
			outputStream.write(("\r\n--" + boundary + "--\r\n").getBytes());
			outputStream.close();
			in.close();

			// 获取媒体文件上传的输入流（从微信服务器读数据）
			InputStream inputStream = uploadConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			StringBuffer buffer = new StringBuffer();
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			uploadConn.disconnect();
			// 使用json解析
			jsonObject = JSONObject.fromObject(buffer.toString());
			System.out.println(jsonObject);
		} catch (Exception e) {
			System.out.println("上传文件失败！");
			e.printStackTrace();
		}
		return jsonObject;
	}
	
	public static String getFileName(File file){
		String filename=file.getName();
		return filename;
	}

	// 获取文件的上传类型，图片格式为image/png,image/jpeg等。非图片为application /octet-stream
	private static String getContentType() throws Exception {
		return "application/octet-stream";
	}
	
	
	public static JSONObject upload(String accessToken, String type, File file){
		JSONObject jsonObject = null;
		String last_wechat_url = upload_wechat_url.replace("ACCESS_TOKEN", accessToken).replace("TYPE", type);
		// 定义数据分割符
		String boundary = "----------sunlight";
		try {
			URL url = new URL(last_wechat_url);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			conn.setRequestProperty("Charsert", "UTF-8"); 
			conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
			
			OutputStream out = new DataOutputStream(conn.getOutputStream());
			byte[] end_data = ("\r\n--" + boundary + "--\r\n").getBytes();// 定义最后数据分隔线
			StringBuilder sb = new StringBuilder();  
			sb.append("--");  
			sb.append(boundary);  
			sb.append("\r\n");  
			sb.append("Content-Disposition: form-data;name=\"media\";filename=\""+ file.getName() + "\"\r\n");  
			sb.append("Content-Type:application/octet-stream\r\n\r\n");  
			
			byte[] data = sb.toString().getBytes();
			out.write(data);
			DataInputStream in = new DataInputStream(new FileInputStream(file));
			int bytes = 0;
			byte[] bufferOut = new byte[1024*8];
			while ((bytes = in.read(bufferOut)) != -1) {
				out.write(bufferOut, 0, bytes);
			}
			out.write("\r\n".getBytes()); //多个文件时，二个文件之间加入这个
			in.close();
			out.write(end_data);
			out.flush();  
			out.close(); 
			
			// 定义BufferedReader输入流来读取URL的响应
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = null;
			StringBuffer buffer = new StringBuffer();
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			// 使用json解析
			jsonObject = JSONObject.fromObject(buffer.toString());
			System.out.println(jsonObject);

		} catch (Exception e) {
			System.out.println("发送POST请求出现异常！" + e);
			e.printStackTrace();
		}
		return jsonObject;
	}

	
	public static String getFileExt(String contentType) {
		String fileExt = "";
		if ("image/jpeg".equals(contentType)) {
			fileExt = ".jpg";
		} else if ("audio/mpeg".equals(contentType)) {
			fileExt = ".mp3";
		} else if ("audio/amr".equals(contentType)) {
			fileExt = ".amr";
		} else if ("video/mp4".equals(contentType)) {
			fileExt = ".mp4";
		} else if ("video/mpeg4".equals(contentType)) {
			fileExt = ".mp4";
		} else if ("text/plain".equals(contentType)) {
			fileExt = ".txt";
		} else if ("text/xml".equals(contentType)) {
			fileExt = ".xml";
		} else if ("application/pdf".equals(contentType)) {
			fileExt = ".pdf";
		} else if ("application/msword".equals(contentType)) {
			fileExt = ".doc";
		} else if ("application/vnd.ms-powerpoint".equals(contentType)) {
			fileExt = ".ppt";
		} else if ("application/vnd.ms-excel".equals(contentType)) {
			fileExt = ".xls";
		}
		return fileExt;
	}
	
	public static JSONObject uploadxxx(String accessToken, String type, File file) {
		JSONObject jsonObject = null;
		String last_wechat_url = upload_wechat_url.replace("ACCESS_TOKEN", accessToken).replace("TYPE", type);
		// 定义数据分割符
		String boundary = "----------sunlight";
		try {
			URL uploadUrl = new URL(last_wechat_url);
			HttpURLConnection uploadConn = (HttpURLConnection) uploadUrl.openConnection();
			uploadConn.setDoOutput(true);
			uploadConn.setDoInput(true);
			uploadConn.setRequestMethod("POST");
			// 设置请求头Content-Type
			uploadConn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
			// 获取媒体文件上传的输出流（往微信服务器写数据）
			OutputStream outputStream = uploadConn.getOutputStream();

			// 从请求头中获取内容类型
			String contentType = "Content-Type: " + getContentType();
			// 请求体开始
			outputStream.write(("--" + boundary + "\r\n").getBytes());
			outputStream.write(String.format("Content-Disposition: form-data; name=\"media\"; filename=\"%s\"\r\n", file.getName()).getBytes());
			outputStream.write(String.format("Content-Type: %s\r\n\r\n", contentType).getBytes());

			// 获取媒体文件的输入流（读取文件）
			DataInputStream in = new DataInputStream(new FileInputStream(file));
			byte[] buf = new byte[1024 * 8];
			int size = 0;
			while ((size = in.read(buf)) != -1) {
				// 将媒体文件写到输出流（往微信服务器写数据）
				outputStream.write(buf, 0, size);
			}
			// 请求体结束
			outputStream.write(("\r\n--" + boundary + "--\r\n").getBytes());
			outputStream.close();
			in.close();

			// 获取媒体文件上传的输入流（从微信服务器读数据）
			InputStream inputStream = uploadConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			StringBuffer buffer = new StringBuffer();
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			uploadConn.disconnect();
			// 使用json解析
			jsonObject = JSONObject.fromObject(buffer.toString());
			System.out.println(jsonObject);
		} catch (Exception e) {
			System.out.println("上传文件失败！");
			e.printStackTrace();
		}
		return jsonObject;
	}
}
