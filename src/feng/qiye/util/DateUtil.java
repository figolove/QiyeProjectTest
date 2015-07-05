package feng.qiye.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * 时间操作类
 * @author sunlight
 *
 */
public class DateUtil {
	/** 
	 * 将long类型的时间转换成标准格式（yyyy-MM-dd HH:mm:ss） 
	 *  
	 * @param longTime 
	 * @return 
	 */  
	public static String formatTime(long longTime) {  
	    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    return format.format(new Date(longTime));  
	}  
	
	/**
	 * 获取long类型时间1
	 * 获取到的结果表示当时时间距离1970年1月1日0时0分0秒0毫秒的毫秒数
	 * @return
	 */
	public static long getLongTime() {
		return System.currentTimeMillis();
	}
	
	/**
	 * 获取long类型时间2
	 * 获取到的结果表示当时时间距离1970年1月1日0时0分0秒0毫秒的毫秒数
	 * @return
	 */
	public static long getLongTimeEx() {
		return new Date().getTime();
	}
	
	/**
	 * 将微信消息中的CreateTime转换成标准格式的时间（yyyy-MM-dd HH:mm:ss）
	 * 
	 * @param createTime 消息创建时间
	 * @return
	 */
	public static String formatTime(String createTime) {
		// 将微信传入的CreateTime转换成long类型，再乘以1000
		long msgCreateTime = Long.parseLong(createTime) * 1000L;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(new Date(msgCreateTime));
	}
	
	/** 
     * 获取前/后n天日期(M月d日) 
     *  
     * @return 
     */  
	public static String getMonthDay(int diff) {  
        DateFormat df = new SimpleDateFormat("M月d日");  
        Calendar c = Calendar.getInstance();  
        c.add(Calendar.DAY_OF_YEAR, diff);  
        return df.format(c.getTime());  
    }  
}
