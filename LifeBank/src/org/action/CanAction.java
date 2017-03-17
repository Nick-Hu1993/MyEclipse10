package org.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.dao.CanDao;
import org.dao.imp.CanDaoImp;
import org.model.Can;

import com.opensymphony.xwork2.ActionSupport;

public class CanAction extends ActionSupport {
	private List result;
	private String serial;
	private String address;
	private String canid;
	private Integer position=1;

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCanid() {
		return canid;
	}

	public void setCanid(String canid) {
		this.canid = canid;
	}

	public List getResult() {
		return result;
	}

	public void setResult(List result) {
		this.result = result;
	}

	/**
	 * 获取罐体的页数
	 */
	public String getCanPage() {
		CanDao canDao = new CanDaoImp();
		Long count = canDao.getCount();
		List<Long> list = new ArrayList<>();
		list.add(count);
		Long pageCount = (long) 0;
		if (count % 15 > 0) {
			pageCount = count / 15 + 1;
		} else {
			pageCount = count / 15;
		}
		if (pageCount == 0) {
			pageCount = (long) 1;
		}
		result = list;
		return SUCCESS;
	}
	
	public String getCanCount(){
		CanDao canDao = new CanDaoImp();
		Long count = canDao.getCount();
		List<Long> list = new ArrayList<>();
		list.add(count);
		result = list;
		return SUCCESS;
	}

	/**
	 * 查看存储的罐体信息
	 */
	public String execute() {
		CanDao canDao = new CanDaoImp();
		List<Can> list = canDao.getList(position - 1);
		JSONArray jsonArray = JSONArray.fromObject(list);
		System.out.println(jsonArray);
		result = list;
		return SUCCESS;
	}

	/**
	 * 添加罐体信息
	 * 
	 * @throws UnsupportedEncodingException
	 */
	public String addCan() throws UnsupportedEncodingException {
		CanDao canDao = new CanDaoImp();
		 address = new String(address.getBytes("ISO-8859-1"), "utf-8");
		Can can = new Can(serial, address);
		Can can1 = canDao.getCanBySerial(serial);
		Map<String, String> message = new HashMap<>();
		List<Map<String, String>> list = new ArrayList<>();
		if (can1 != null) {
			message.put("message", "error");
			message.put("description", "罐体序列号重复!");
			list.add(message);
			result = list;
			return ERROR;
		}
		if (canDao.insert(can)) {
			message.put("message", "success");
			message.put("description", "新增罐体成功!");
			list.add(message);
			result = list;
			return SUCCESS;
		} else {
			message.put("message", "error");
			message.put("description", "新增罐体失败!");
			list.add(message);
			result = list;
			return ERROR;
		}
	}

	/**
	 * 修改罐体信息
	 * 
	 * @throws UnsupportedEncodingException
	 */
	public String updateCan() throws UnsupportedEncodingException {
		// address = new String(address.getBytes("ISO-8859-1"), "utf-8");
		CanDao canDao = new CanDaoImp();
		Map<String, String> message = new HashMap<>();
		List<Map<String, String>> list = new ArrayList<>();
		Can can = canDao.findCanById(Long.parseLong(canid));
		can.setAddress(address);
		can.setSerial(serial);
		if (canDao.update(can)) {
			message.put("message", "success");
			message.put("description", "修改信息成功!");
			list.add(message);
			result = list;
			return SUCCESS;
		} else {
			message.put("message", "error");
			message.put("description", "修改信息失败!");
			list.add(message);
			result = list;
			return ERROR;
		}
	}

	/**
	 * 删除罐体
	 */
	public String delCan() {
		CanDao canDao = new CanDaoImp();
		Map<String, String> message = new HashMap<>();
		List<Map<String, String>> list = new ArrayList<>();
		if (canDao.delCan(Long.parseLong(canid))) {
			message.put("message", "success");
			message.put("description", "删除罐体成功!");
			list.add(message);
			result = list;
			return SUCCESS;
		} else {
			message.put("message", "error");
			message.put("description", "删除罐体失败!");
			list.add(message);
			result = list;
			return ERROR;
		}
	}
}
