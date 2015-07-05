package feng.qiye.util;

import java.io.File;


import feng.qiye.pojo.Result;
import feng.qiye.pojo.corp.QiYeUpload;

/**
 * 企业号文件操作类
 * 
 * @author Sunlight
 * 
 */
public class QiYeFileUtil {
	/**
	 * 下载文件
	 * @param media_id 媒体Id
	 * @param path 路径
	 * @return
	 */
	public static String Download(String token,String media_id, String path) {
		return null;
	}
	
	/**
	 * 上传文件
	 * @param type
	 * @param file
	 * @return
	 */
	public static Result<QiYeUpload> Upload(String accessToken,String type, File file) {
		return DownloadAPI.Upload(accessToken, type, file);
	}
	
	/**
	 * 下载文件
	 * @param basePath
	 * @param url
	 * @param type
	 * @param extensionName
	 * @return
	 */
	public static Result<String> DownloadCommon(String basePath, String url, String type, String extensionName){
		return DownloadAPI.DownloadCommon(basePath, url, type, extensionName);
	}
}
