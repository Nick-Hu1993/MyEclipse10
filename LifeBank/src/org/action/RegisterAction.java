package org.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.dao.UserDao;
import org.dao.UserDetailDao;
import org.dao.imp.UserDaoImp;
import org.dao.imp.UserDetailDaoImp;
import org.model.User;
import org.util.ChangeTime;
import org.util.Utils;

import com.opensymphony.xwork2.ActionSupport;

public class RegisterAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {
	private static final long serialVersionUID = 1L;
	HttpServletRequest request;
	HttpServletResponse response;
	private String phone;
	private String username;
	private String password;

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void register() {
		Map<String, String> message = new HashMap<String, String>();
		try {
			if (username != null && phone != null && password != null) {
				request.setCharacterEncoding("utf-8");
				username = new String(username.getBytes("ISO-8859-1"), "utf-8");
				System.out.println(phone);
				System.out.println(username);
				System.out.println(password);

				this.response.setCharacterEncoding("UTF-8");
				UserDao userDao = new UserDaoImp();

				String current = ChangeTime.timeStamp();
				User user = new User(Long.parseLong(phone), username, password,
						Integer.parseInt(current));
				User u = userDao.findUserByPhone(phone);
				if (u != null) {
					message.put("message", "error");
					message.put("description", "该手机号已注册，请登录");
				} else if (userDao.Register(user)) {
					UserDetailDao uDetailDao = new UserDetailDaoImp();
					Long userid = userDao.findUserByPhone(phone).getId();
					if (uDetailDao.insert(username, userid)) {
					} else {
						message.put("extral", "注册成功但未保存详细信息");
					}
					message.put("message", "success");
					message.put("description", "注册成功");
				} else {
					message.put("message", "error");
					message.put("description", "注册失败");
				}
			}
			message.put("parms","username="+Utils.isNull(username)+"&phone="+Utils.isNull(phone)+"&password"+Utils.isNull(password));
			JSONArray JsonArry = JSONArray.fromObject(message);
			JSONObject json = new JSONObject();
			json.element("JsonArry", JsonArry);
			response.setContentType("text/html;charset=utf-8");
			byte[] jsonBytes = json.toString().getBytes("utf-8");
			response.setContentLength(jsonBytes.length);
			response.getOutputStream().write(jsonBytes);
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
