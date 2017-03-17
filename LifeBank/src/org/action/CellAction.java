package org.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.dao.CanDao;
import org.dao.CellDao;
import org.dao.ItemsDao;
import org.dao.UserDao;
import org.dao.imp.CanDaoImp;
import org.dao.imp.CellDaoImp;
import org.dao.imp.ItemsDaoImp;
import org.dao.imp.UserDaoImp;
import org.hibernate.Session;
import org.model.Can;
import org.model.Cell;
import org.model.Items;
import org.model.User;
import org.model.UserCell;
import org.util.ChangeTime;
import org.util.MapMessage;
import org.util.Utils;

import com.mysql.jdbc.Connection;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
import com.sun.xml.internal.bind.v2.model.core.ID;

public class CellAction extends ActionSupport implements ServletRequestAware,
		ServletResponseAware {

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		
	}
//	private String phone = "13590440185";
//	private static final long serialVersionUID = 1L;
//	private List result = new ArrayList<>();
//	private String start_time; // 鏄剧ず鍘嗗彶璁板綍鐨勮捣濮嬫椂闂�
//	private String end_time; // 鏄剧ず鍘嗗彶璁板綍鐨勭粨鏉熸椂闂�
//	private String timeString; // 鏄剧ず鎸囧畾鏈堜唤鐨勮褰�
//	private String[] itemids; // 澶氶�鏃朵紶閫掓暟缁処temids
//	private String itemid;
//	private String cellid;// 骞茬粏鑳瀒d 鐢ㄤ簬鍒犻櫎骞茬粏鑳炰俊鎭�
//	private String serial; // 搴忓垪鍙�
//	private String name;
//	private String num;
//	HttpServletRequest request = null;
//	HttpServletResponse response = null;
//	private Integer position = -1;
//
//	/**
//	 * 鑾峰彇鐢ㄦ埛鐨勫共缁嗚優鐨勬渶鏂版暟鎹�
//	 */
//	public void getCellInfo() {
//		this.response.setContentType("text/html;charset=utf-8");
//		this.response.setCharacterEncoding("UTF-8");
//		Map<String, String> message = new HashMap<String, String>();
//		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
//		if (phone != null) {
//			CellDao cellDao = new CellDaoImp();
//			UserDao udao = new UserDaoImp();
//			ItemsDao iDao = new ItemsDaoImp();
//			User user = udao.findUserByPhone(phone);
//			if (user != null) {
//				List<UserCell> userCells = cellDao.getCellList(user.getId());
//				if (userCells == null || userCells.isEmpty()) {
//					message.put("缁嗚優鍚嶇О", "鎮ㄦ病鏈変繚瀛樹换浣曞共缁嗚優鍝�");
//					message.put("瀛樺偍鏁伴噺", "");
//					message.put("瀛樺偍鏃堕棿", "");
//					message.put("璁板綍鏃堕棿", "");
//					message.put("娑蹭綅浣庢姤璀�, "");
//					message.put("娑蹭綅楂樻姤璀�, "");
//					message.put("缃愬唴娓╁害楂樻姤璀�, "");
//					message.put("缃愬唴娓╁害瓒呴珮鎶ヨ", "");
//					message.put("娑叉爱缃愭恫浣�, "");
//					message.put("搴曢儴娑叉爱T1", "");
//					message.put("搴曢儴娑叉爱T2", "");
//					message.put("搴曢儴姘旀爱T3", "");
//					message.put("搴曢儴姘旀爱T4", "");
//					message.put("缃愪綋椤堕儴娓╁害", "");
//					message.put("鏍峰搧娓╁害", "");
//					message.put("瀛樻斁鍦扮偣", "");
//				} else {
//					Long cellid = userCells.get(0).getCellid();
//					Cell cell = cellDao.getCell(cellid);
//					if (cell != null) {
//						message.put("缁嗚優鍚嶇О", cell.getName());
//						message.put("瀛樺偍鏁伴噺", "" + cell.getNum());
//						message.put("瀛樺偍鏃堕棿", ChangeTime.TimeStamp2Date(
//								"" + cell.getClock(), "yyyy-MM-dd HH:mm:ss"));
//						Items item = iDao
//								.getLastItemsBySerial(cell.getSerial());
//						if (item != null) {
//							message.put("璁板綍鏃堕棿", ChangeTime.TimeStamp2Date(
//									""+item.getClock(), "yyyy-MM-dd HH:mm:ss"));
//							message.put("娑蹭綅浣庢姤璀�, item.getLLowAlarm());
//							message.put("娑蹭綅楂樻姤璀�, item.getLHighAlarm());
//							message.put("缃愬唴娓╁害楂樻姤璀�, item.getTHAlarm());
//							message.put("缃愬唴娓╁害瓒呴珮鎶ヨ", item.getTHhAlarm());
//							message.put("娑叉爱缃愭恫浣�, item.getLevel());
//							message.put("搴曢儴娑叉爱T1", item.getTemperature1());
//							message.put("搴曢儴娑叉爱T2", item.getTemperature2());
//							message.put("搴曢儴姘旀爱T3", item.getTemperature3());
//							message.put("搴曢儴姘旀爱T4", item.getTemperature4());
//							message.put("缃愪綋椤堕儴娓╁害", item.getVesselTemp());
//							message.put("鏍峰搧娓╁害", item.getSampleTemp());
//
//							CanDao canDao = new CanDaoImp();
//							Can can = canDao.getCanBySerial(cell.getSerial());
//							if (can != null) {
//								message.put("瀛樻斁鍦扮偣", can.getAddress());
//							} else {
//								message.put("瀛樻斁鍦扮偣", "");
//							}
//						} else {
//							message.put("璁板綍鏃堕棿", "");
//							message.put("娑蹭綅浣庢姤璀�, "");
//							message.put("娑蹭綅楂樻姤璀�, "");
//							message.put("缃愬唴娓╁害楂樻姤璀�, "");
//							message.put("缃愬唴娓╁害瓒呴珮鎶ヨ", "");
//							message.put("娑叉爱缃愭恫浣�, "");
//							message.put("搴曢儴娑叉爱T1", "");
//							message.put("搴曢儴娑叉爱T2", "");
//							message.put("搴曢儴姘旀爱T3", "");
//							message.put("搴曢儴姘旀爱T4", "");
//							message.put("缃愪綋椤堕儴娓╁害", "");
//							message.put("鏍峰搧娓╁害", "");
//							message.put("瀛樻斁鍦扮偣", "");
//						}
//					} else {
//						message.put("缁嗚優鍚嶇О", "");
//						message.put("瀛樺偍鏁伴噺", "");
//						message.put("瀛樺偍鏃堕棿", "");
//						message.put("璁板綍鏃堕棿", "");
//						message.put("娑蹭綅浣庢姤璀�, "");
//						message.put("娑蹭綅楂樻姤璀�, "");
//						message.put("缃愬唴娓╁害楂樻姤璀�, "");
//						message.put("缃愬唴娓╁害瓒呴珮鎶ヨ", "");
//						message.put("娑叉爱缃愭恫浣�, "");
//						message.put("搴曢儴娑叉爱T1", "");
//						message.put("搴曢儴娑叉爱T2", "");
//						message.put("搴曢儴姘旀爱T3", "");
//						message.put("搴曢儴姘旀爱T4", "");
//						message.put("缃愪綋椤堕儴娓╁害", "");
//						message.put("鏍峰搧娓╁害", "");
//						message.put("瀛樻斁鍦扮偣", "");
//					}
//				}
//			}
//		}
//		message.put("parms", "phone=" + Utils.isNull(phone));
//		list.add(message);
//		JSONObject json = new JSONObject();
//		JSONArray JsonArry = JSONArray.fromObject(list);
//		// System.out.println(JsonArry.toString());
//		try {
//			json.element("JsonArry", JsonArry);
//			response.setContentType("text/html;charset=utf-8");
//			byte[] jsonBytes = json.toString().getBytes("utf-8");
//			response.setContentLength(jsonBytes.length);
//			response.getOutputStream().write(jsonBytes);
//			response.getOutputStream().flush();
//			response.getOutputStream().close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	// ///////////////////////////鎴戞槸鍒嗗壊绾裤�銆傘�涓婇潰鏄疉PP浜や簰鎺ュ彛///////////////////////////
//	/**
//	 * 鑾峰彇鎵�湁缃愪綋鏁版嵁鎬婚〉鏁�
//	 */
//	public String getPageCount() {
//		List<Long> list = new ArrayList<>();
//		ItemsDao iDao = new ItemsDaoImp();
//		Long count = (long) 0;
//		if (end_time == null || start_time == null || end_time.equals("")
//				|| start_time.equals("")) {
//			count = iDao.getCount();
//		} else {
//			Integer start = Integer.parseInt(ChangeTime.date2TimeStamp(
//					start_time, "yyyy-MM-dd"));
//			Integer end = Integer.parseInt(ChangeTime.date2TimeStamp(end_time,
//					"yyyy-MM-dd"));
//			if (start < end) {
//				count = iDao.getCount(start, end);
//			}
//		}
//		Long pageCount = (long) 0;
//		if (count % 15 > 0) {
//			pageCount = count / 15 + 1;
//		} else {
//			pageCount = count / 15;
//		}
//		if (pageCount == 0) {
//			pageCount = (long) 1;
//		}
//		list.add(pageCount);
//		result = list;
//		return SUCCESS;
//	}
//
//	/**
//	 * 鑾峰彇鎵�湁缃愪綋鏁版嵁鐨勬�鏁�
//	 */
//	public String getCount() {
//		List<Long> list = new ArrayList<>();
//		ItemsDao iDao = new ItemsDaoImp();
//		Long count = (long) 0;
//		if (end_time == null || start_time == null || end_time.equals("")
//				|| start_time.equals("")) {
//			count = iDao.getCount();
//		} else {
//			Integer start = Integer.parseInt(ChangeTime.date2TimeStamp(
//					start_time, "yyyy-MM-dd"));
//			Integer end = Integer.parseInt(ChangeTime.date2TimeStamp(end_time,
//					"yyyy-MM-dd"));
//			System.out.println(start);
//			System.out.println(end);
//			if (start < end) {
//				count = iDao.getCount(start, end);
//			}
//		}
//		list.add(count);
//		result = list;
//		return SUCCESS;
//	}
//	public String getCellHistory1(){
//		ItemsDao iDao = new ItemsDaoImp();
//		List list = iDao.getItemList();
//		result = list;
//		return SUCCESS;
//	}
//
//	/**
//	 * 鑾峰彇缃愪綋鏁版嵁鐨勫巻鍙茶褰�濡傛灉鎺ユ敹鍒板弬鏁皊tart_time鍜宔nd_time鍒欐煡璇㈡寚瀹氭椂闂存鐨勬暟鎹�
//	 */
//	public String getCellHistory() {
//		System.out.println(position);
//		ItemsDao iDao = new ItemsDaoImp();
//		List<Items> items = new ArrayList<>();
//		if (end_time == null || start_time == null || end_time.equals("")
//				|| start_time.equals("")) {
//			items = iDao.getItemList(position - 1);
//			System.out.println(JSONArray.fromObject(items).toString());
//		} else {
//			Integer start = Integer.parseInt(ChangeTime.date2TimeStamp(
//					start_time, "yyyy-MM-dd"));
//			Integer end = Integer.parseInt(ChangeTime.date2TimeStamp(end_time,
//					"yyyy-MM-dd"));
//			if (start < end) {
//				items = iDao.getItemsByPeriod(start, end, position - 1);
//			}
//		}
//		CanDao canDao = new CanDaoImp();
//		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
//		if (items == null || items.isEmpty()) {
//			Map<String, String> map = new HashMap<String, String>();
//			map.put("message", "error");
//			map.put("itemid", "鏃犳暟鎹�);
//			list.add(map);
//			result = list;
//			String JsonArry = JSONArray.fromObject(result).toString();
//			System.out.println(JsonArry);
//			return ERROR;
//		} else {
//			for (Items item : items) {
//				Map<String, String> map = new HashMap<String, String>();
//				map.put("itemid", "" + item.getId());
//				map.put("璁板綍鏃堕棿", ChangeTime.TimeStamp2Date(""+item.getClock(),
//						"yyyy-MM-dd HH:mm:ss"));
//				map.put("娑蹭綅浣庢姤璀�, item.getLLowAlarm());
//				map.put("娑蹭綅楂樻姤璀�, item.getLHighAlarm());
//				map.put("缃愬唴娓╁害楂樻姤璀�, item.getTHAlarm());
//				map.put("缃愬唴娓╁害瓒呴珮鎶ヨ", item.getTHhAlarm());
//				map.put("娑叉爱缃愭恫浣�, item.getLevel());
//				map.put("搴曢儴娑叉爱T1", item.getTemperature1());
//				map.put("搴曢儴娑叉爱T2", item.getTemperature2());
//				map.put("搴曢儴姘旀爱T3", item.getTemperature3());
//				map.put("搴曢儴姘旀爱T4", item.getTemperature4());
//				map.put("缃愪綋椤堕儴娓╁害", item.getVesselTemp());
//				map.put("鏍峰搧娓╁害", item.getSampleTemp());
//
//				Can can = canDao.getCanBySerial(item.getSerial());
//				map.put("瀛樻斁鍦扮偣", can.getAddress());
//				list.add(map);
//			}
//			result = list;
//			String JsonArry = JSONArray.fromObject(result).toString();
//			System.out.println(JsonArry);
//			return SUCCESS;
//		}
//	}
//
//	/**
//	 * 鑾峰彇鎸囧畾鏃堕棿鍐呯殑鍘嗗彶璁板綍,鏃堕棿闇�杞崲鎴怚nteger绫诲瀷鏂逛究鏁版嵁搴撹繘琛屾煡鎵�涓�〉鍙樉绀�5鏉�
//	 */
//	public String getHistoryByPeriod() {
//		Integer start = 0;
//		Integer end = 0;
//		ItemsDao iDao = new ItemsDaoImp();
//		List<Items> items = iDao.getItemsByPeriod(start, end, position - 1);
//		if (items.isEmpty() || items == null) {
//			Map<String, String> map = new HashMap<String, String>();
//			map.put("message", "error");
//			map.put("description", "鏃犲巻鍙叉暟鎹�);
//			List<Map<String, String>> list = new ArrayList<>();
//			list.add(map);
//			result = list;
//			String JsonArry = JSONArray.fromObject(result).toString();
//			System.out.println(JsonArry);
//			return ERROR;
//		} else {
//			result = items;
//			return SUCCESS;
//		}
//	}
//
//	/**
//	 * 鍒犻櫎鏌愭鏃堕棿鐨勫巻鍙茶褰�鏃堕棿闇�杞崲鎴怚nteger绫诲瀷鏂逛究鏁版嵁搴撹繘琛屾煡鎵�
//	 */
//	public String deleteHistoryByPeriod() {
//		System.out.println("enter");
//		ItemsDao iDao = new ItemsDaoImp();
//
//		List<Map<String, String>> list = new ArrayList<>();
//		if (end_time == null || start_time == null || end_time.equals("")
//				|| start_time.equals("")) {
//			MapMessage message = new MapMessage(2);
//			list.add(message);
//			result = list;
//			return ERROR;
//		} else {
//			Integer start = Integer.parseInt(ChangeTime.date2TimeStamp(
//					start_time, "yyyy-MM-dd"));
//			Integer end = Integer.parseInt(ChangeTime.date2TimeStamp(end_time,
//					"yyyy-MM-dd"));
//
//			if (start < end) {
//				Map<String, String> message = new HashMap<>();
//				if (iDao.delete(start, end)) {
//					message.put("message", "success");
//					message.put("description", "鍒犻櫎鎴愬姛!");
//					list.add(message);
//					result = list;
//					return SUCCESS;
//				} else {
//					message.put("message", "error");
//					message.put("description", "鍒犻櫎澶辫触!");
//					list.add(message);
//					result = list;
//					return ERROR;
//				}
//			} else {
//				MapMessage message = new MapMessage(1);
//				list.add(message);
//				result = list;
//				return ERROR;
//			}
//		}
//
//	}
//
//	/**
//	 * 鏍规嵁鎻愪緵鐨勫垪琛ㄩ」鐨刬d鍒犻櫎鎸囧畾鐨勭綈浣撴暟鎹�
//	 */
//	public String deleteItem() {
//		ItemsDao iDao = new ItemsDaoImp();
//		Map<String, String> message = new HashMap<>();
//		List<Map<String, String>> list = new ArrayList<>();
//		if (itemid != null) {
//			if (iDao.deleteItem("" + itemid)) {
//				message.put("message", "success");
//				message.put("description", "鍒犻櫎鎴愬姛!");
//				list.add(message);
//				result = list;
//				return SUCCESS;
//			} else {
//				message.put("message", "error");
//				message.put("description", "鍒犻櫎澶辫触!");
//				list.add(message);
//				result = list;
//				return ERROR;
//			}
//		}
//		message.put("message", "error");
//		message.put("description", "澶辫触,鍙兘缂哄皯蹇呰鍙傛暟!");
//		message.put(
//				"params",
//				"phone=" + Utils.isNull(phone) + "&name=" + Utils.isNull(name)
//						+ "&num=" + Utils.isNull(num) + "&serial="
//						+ Utils.isNull(serial));
//		list.add(message);
//		result = list;
//		return ERROR;
//	}
//
//	/**
//	 * 鎵归噺鍒犻櫎,澶嶉�妗�
//	 */
//	public String deleteItems() {
//		ItemsDao iDao = new ItemsDaoImp();
//		Map<String, String> message = new HashMap<>();
//		List<Map<String, String>> list = new ArrayList<>();
//		if (itemids != null) {
//			if (iDao.delete(itemids)) {
//				message.put("message", "error");
//				message.put("description", "鎮ㄥ苟鏈�鎷╅渶瑕佸垹闄ょ殑鏁版嵁!");
//				list.add(message);
//				result = list;
//				return ERROR;
//			} else if (iDao.deleteItem("" + itemid)) {
//				message.put("message", "success");
//				message.put("description", "鍒犻櫎鎴愬姛!");
//				list.add(message);
//				result = list;
//				return SUCCESS;
//			} else {
//				message.put("message", "error");
//				message.put("description", "鍒犻櫎澶辫触!");
//				list.add(message);
//				result = list;
//				return ERROR;
//			}
//		}
//		return ERROR;
//	}
//
//	/**
//	 * 澶囨敞:缁存姢缁嗚優涓庣敤鎴峰叧绯诲睘浜庡鍊兼湇鍔�鏌ョ湅鐢ㄦ埛涓庡叾瀵瑰簲鐨勭粏鑳炵殑瀛樺偍淇℃伅
//	 */
//	/**
//	 * 鏂板缁嗚優,璁捐鎴愮敤鎴峰悕锛屾墜鏈哄彿锛岀粏鑳炲悕锛屼互鍙婂搴旂殑缃愪綋鐨勫簭鍒楀彿 闇�鍏堝０鏄庝互涓婂叏灞�彉閲�
//	 */
//	public String AddCell() {
//		// 鍘熺悊锛氭牴鎹彁渚涚殑鎵嬫満鍙锋壘鍒癠serid锛屽厛鎻掑叆缁嗚優璁板綍锛屽啀浠庣粏鑳炶褰曢噷鎵惧埌cellid
//		// 鎶奵ellid涓巙serid鍦╱ser_cell琛ㄨ繘琛岀粦瀹�
//		String clock = ChangeTime.timeStamp();
//		UserDao uDao = new UserDaoImp();
//		Map<String, String> message = new HashMap<>();
//		List<Map<String, String>> list = new ArrayList<>();
//		
//		try {
//			request.setCharacterEncoding("UTF-8");
//			name = new String(name.getBytes("ISO-8859-1"), "utf-8");
//			System.out.println(name);
//			if (phone != null && name != null && num != null && serial != null) {
//				if (Utils.isInteger(num) && phone.length() == 11
//						&& Utils.isLong(phone) && serial.length() < 11
//						&& serial.length() > 0) {
//					User user = uDao.findUserByPhone(phone);
//					if (user != null) {
//						CellDao cellDao = new CellDaoImp();
//						Cell cell = new Cell(name, Integer.parseInt(num),
//								serial, Integer.parseInt(clock));
//						if (cellDao.insert(cell)) {
//							Cell cell2 = cellDao.findCell(name, clock, serial);
//							UserCell userCell = new UserCell(user.getId(),
//									cell2.getId());
//							if (cellDao.insert(userCell)) {
//								message.put("message", "success");
//								message.put("description", "鎴愬姛!");
//								list.add(message);
//								result = list;
//								return SUCCESS;
//							}
//						}
//					} else {
//						message.put("description", "鎵句笉鍒版墜鏈哄彿瀵瑰簲鐨勭敤鎴�");
//					}
//				} else {
//					message.put("description", "澶辫触,杈撳叆鐨勫弬鏁颁笉鍚堟硶锛岃閲嶆柊杈撳叆!");
//				}
//			}
//			message.put("message", "error");
//			if (message.get("description") == null) {
//				message.put("description", "澶辫触,鍙兘缂哄皯蹇呰鍙傛暟!");
//			}
//			message.put("params", "phone=" + Utils.isNull(phone) + "&name="
//					+ Utils.isNull(name) + "&num=" + Utils.isNull(num)
//					+ "&serial=" + Utils.isNull(serial));
//			list.add(message);
//			result = list;
//			return ERROR;
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			message.put("message", "error");
//			message.put("description", "澶辫触,鏈嶅姟鍣ㄥ紓甯歌绋嶅悗閲嶈瘯");
//			list.add(message);
//			result = list;
//			return ERROR;
//		}
//	}
//
//	/**
//	 * 鑾峰彇瀛樺偍骞茬粏鑳炵殑鎬绘暟
//	 */
//	public String getCellCount() {
//		CellDao cellDao = new CellDaoImp();
//		Long count = cellDao.getCellCount();
//		List<Long> list = new ArrayList<>();
//		list.add(count);
//		result = list;
//		return SUCCESS;
//	}
//
//	/**
//	 * 鑾峰彇瀛樺偍鐨勫共缁嗚優鍒楄〃
//	 */
//	public String getCellList() {
//		CellDao cellDao = new CellDaoImp();
//		if (position == null) {
//			return ERROR;
//		}
//		List<Object[]> list = cellDao.getCells(position - 1);
//		if (list == null) {
//			return ERROR;
//		}
//		List<Map<String, String>> list1 = new ArrayList<>();
//		for (Object[] a : list) {
//			Map<String, String> map = new HashMap<>();
//			map.put("cellid", "" + a[0]);
//			map.put("username", "" + a[1]);
//			map.put("phone", "" + a[2]);
//			map.put("name", "" + a[3]);
//			map.put("num", "" + a[4]);
//			map.put("serial", "" + a[5]);
//			map.put("time",
//					""
//							+ ChangeTime.TimeStamp2Date("" + a[6],
//									"yyyy-MM-dd HH:mm:ss "));
//			list1.add(map);
//		}
//		result = list1;
//		return SUCCESS;
//	}
//
//	/**
//	 * 鍒犻櫎骞茬粏鑳炰俊鎭�
//	 */
//	public String DelCell() {
//		CellDao cellDao = new CellDaoImp();
//		// Map<String, String> message = new HashMap<>();
//		List<Map<String, String>> list = new ArrayList<>();
//		if (cellid == null) {
//			MapMessage message = new MapMessage(2);
//			list.add(message);
//			result = list;
//			return ERROR;
//		}
//		Cell cell = null;
//		cell = cellDao.getCell(Long.parseLong(cellid));
//		if (cell != null) {
//			if (cellDao.delete(cell)) {
//				MapMessage message = new MapMessage(0);
//				list.add(message);
//				result = list;
//				return SUCCESS;
//			} else {
//				MapMessage message = new MapMessage(3);
//				list.add(message);
//				result = list;
//				return ERROR;
//			}
//		} else {
//			Map<String, String> message = new HashMap<>();
//			message.put("message", "error");
//			message.put("description", "璇ョ粏鑳炰笉瀛樺湪!");
//			list.add(message);
//			result = list;
//			return ERROR;
//		}
//	}
//
//	public String[] getItemids() {
//		return itemids;
//	}
//
//	public void setItemids(String[] itemids) {
//		this.itemids = itemids;
//	}
//
//	public List getResult() {
//		return result;
//	}
//
//	public void setResult(List result) {
//		this.result = result;
//	}
//
//	public String getPhone() {
//		return phone;
//	}
//
//	public void setPhone(String phone) {
//		this.phone = phone;
//	}
//
//	public void setServletResponse(HttpServletResponse response) {
//		// TODO Auto-generated method stub
//		this.response = response;
//	}
//
//	public void setServletRequest(HttpServletRequest request) {
//		// TODO Auto-generated method stub
//		this.request = request;
//	}
//
//	public String getStart_time() {
//		return start_time;
//	}
//
//	public void setStart_time(String start_time) {
//		this.start_time = start_time;
//	}
//
//	public String getEnd_time() {
//		return end_time;
//	}
//
//	public void setEnd_time(String end_time) {
//		this.end_time = end_time;
//	}
//
//	public String getTimeString() {
//		return timeString;
//	}
//
//	public void setTimeString(String timeString) {
//		this.timeString = timeString;
//	}
//
//	public String getSerial() {
//		return serial;
//	}
//
//	public void setSerial(String serial) {
//		this.serial = serial;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getItemid() {
//		return itemid;
//	}
//
//	public void setItemid(String itemid) {
//		this.itemid = itemid;
//	}
//
//	public String getCellid() {
//		return cellid;
//	}
//
//	public void setCellid(String cellid) {
//		this.cellid = cellid;
//	}
//
//	public String getNum() {
//		return num;
//	}
//
//	public void setNum(String num) {
//		this.num = num;
//	}
//
//	public Integer getPosition() {
//		return position;
//	}
//
//	public void setPosition(Integer position) {
//		this.position = position;
//	}

}
