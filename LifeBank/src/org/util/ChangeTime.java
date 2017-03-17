package org.util;

import java.text.SimpleDateFormat;

public class ChangeTime {
//	public static String TimeStamp2Date(String timestampString, String formats) {
//		Long timestamp = Long.parseLong(timestampString) * 1000;
//		String date = new java.text.SimpleDateFormat(formats)
//				.format(new java.util.Date(timestamp));
//		return date;
//	}
	/** 
     * 日期格式字符串转换成时间戳 
     * @param date 字符串日期 
     * @param format 如：yyyy-MM-dd HH:mm:ss 
     * @return 
     */  
    public static String date2TimeStamp(String date_str,String format){  
        try {  
            SimpleDateFormat sdf = new SimpleDateFormat(format);  
            return String.valueOf(sdf.parse(date_str).getTime()/1000);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return "";  
    }  
      
    /** 
     * 取得当前时间戳（精确到秒） 
     * @return 
     */  
    public static String timeStamp(){
        long time = System.currentTimeMillis();  
        String t = String.valueOf(time/1000);  
        return t;  
    }
      
    //  输出结果：  
    //  timeStamp=1417792627  
    //  date=2014-12-05 23:17:07  
    //  1417792627   
	public static String TimeStamp2Date(String timestampString, String formats) {
		Long timestamp = Long.parseLong(timestampString) * 1000;
		String date = new java.text.SimpleDateFormat(formats)
				.format(new java.util.Date(timestamp));
		return date;
	}
}
