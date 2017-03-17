package org.tool;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ChangTime {
	/*
	 * 1.获取当前时间戳*/
	public static String timeStamp(){  
        long time = System.currentTimeMillis();  
        String t = String.valueOf(time/1000);  
        return t;  
    }
	
	/*
	 * 2.将时间戳转换为时间格式显示*/
	public static String stampToDate(String t){
        String res;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(t);
        Date date = new Date(lt);
        res = sdf.format(date);
        return res;
    }
}