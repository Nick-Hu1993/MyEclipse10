package org.util;

import java.util.HashMap;
/**
 * 返回的消息数组
 */
public class MapMessage extends HashMap<String, String>{
	private static final long serialVersionUID = 1L;
	/**
	 * @param type 0代表成功,1代表参数非法,2代表缺少必要参数,3代表数据操作异常导致的失败
	 */
	public MapMessage(Integer type){
		switch (type) {
		case 0:
			put("message", "success");
			put("description", "成功!");
		case 1:
			put("message", "error");
			put("description", "参数不合法，请检查您的输入!");
			break;
		case 2:
			put("message", "error");
			put("description", "缺少必要参数，请检查您的输入!");
			break;
		case 3:
			put("message", "error");
			put("description", "失败!");
		default:
			break;
		}
	}
}
