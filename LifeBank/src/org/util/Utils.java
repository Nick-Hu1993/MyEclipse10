package org.util;

import java.io.File;
import java.util.List;

public class Utils {
	/**
	 * 保存的文件路径 根目录
	 */
//	public final static String BASESRC = "d:\\Tomcat 7.0\\webapps\\CellBank\\upload\\";
//	public final static String BASEURL = "http://192.168.1.102:8080/CellBank/upload/";
	public final static String BASESRC = "/opt/apache-tomcat-7.0.70/webapps/CellBank/upload/";
	public final static String BASEURL = "http://120.76.22.150:8080/CellBank/upload/";
	/**
	 * 删除文件
	 */
	public static boolean delFile(String fileName){  
        File file=new File(fileName);  
        if(file.exists()){
            return file.delete();  
        }
        return false;  
    }
	/**
	 * 判空之后转成空串
	 */
	public static Object isNull(Object a){
		if(a==null){
			return "";
		}else {
			return a;
		}
	}
	/**
	 * 判断是否为Integer
	 */
	public static boolean isInteger(String a){
		try {
			Integer.parseInt((String) a);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 判断是否为Long
	 */
	public static boolean isLong(String a){
		try {
			Long.parseLong((String) a);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	/**
	 * 批量删除文件
	 */
	public static boolean delFile(List<String> urlList){
		boolean a  = true;
		if (urlList != null && urlList.size() > 0) {
			for (String url : urlList) {
				if(!Utils.delFile(url.replace(Utils.BASEURL, Utils.BASESRC))){
					System.out.println("删除图片失败了");
					a=false;
				}
			}
			return a;
		}else {
			System.out.println("删除的话题或评论中没有包含任何图片");
			return true;
		}
	}
}
