package org.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.dao.PhotoDao;
import org.dao.UserDao;
import org.dao.imp.PhotoDaoImp;
import org.dao.imp.UserDaoImp;
import org.model.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport implements ServletRequestAware,
		ServletResponseAware {
	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	HttpServletRequest request = null;
	HttpServletResponse response = null;
	private String phone;
	private String password;
	private String newPassword;
	private Long userid;

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public void login() {
		try {
			System.out.println("phone=" + phone);
			System.out.println("password=" + password);
			Map<String, String> message = new HashMap<String, String>();
			this.response.setContentType("text/html;charset=utf-8");
			this.response.setCharacterEncoding("UTF-8");
			UserDao userDao = new UserDaoImp();
			User u = userDao.Login(phone, password);
			if (u != null) {
				HttpSession session = ServletActionContext.getRequest().getSession();
				session.setAttribute("user", u);
				PhotoDao pDao = new PhotoDaoImp();
				String headUrl = "";
				if (pDao.getPhoto(u.getId(), 0) != null) {
					headUrl = pDao.getPhoto(u.getId(), 0).getUrl();
				}
				// System.out.println(phone);
				message.put("username", u.getUsername());
				message.put("userid", "" + u.getId());
				message.put("message", "success");
				message.put("description", "登录成功");
				message.put("headUrl", headUrl);
			} else {
				message.put("message", "error");
				message.put("description", "账号或密码错误，请找回密码或注册");
			}
			JSONObject json = new JSONObject();
			JSONArray JsonArry = JSONArray.fromObject(message);
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

	/**
	 * 检测是否有人登录
	 */
	public void CheckAlive() {
		Map<String, String> message = new HashMap<String, String>();
		Map<String, Object> session = ActionContext.getContext().getSession();
		if (session.get(phone) != null && session.get(phone).equals("isalive")) {
			message.put("message", "success");
			message.put("description", "登录中...");
		} else {
			message.put("message", "error");
			message.put("description", "强制下线!");
		}
		JSONArray JsonArry = JSONArray.fromObject(message);
		responseMS(JsonArry);

	}

	public void changePw() {
		try {
			System.out.println("changePW---phone:" + phone + "&password:"
					+ password);
			Map<String, String> message = new HashMap<String, String>();
			UserDao uDao = new UserDaoImp();
			User user = null;
			if (phone != null) {
				user = uDao.findUserByPhone(phone);
			}
			if (userid != null) {
				user = uDao.findUserById(userid);
			}
			if (user == null) {
				message.put("message", "error");
				message.put("description", "该用户不存在，请重新注册");
			} else {
				user.setPassword(password);
				if (uDao.update(user)) {
					message.put("username", user.getUsername());
					message.put("message", "success");
					message.put("description", "密码修改成功");
				} else {
					message.put("message", "error");
					message.put("description", "密码修改失败");
				}
			}

			JSONObject json = new JSONObject();
			JSONArray JsonArry = JSONArray.fromObject(message);
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

	public void changePw1() {
		try {
			System.out.println("changePW---phone:" + phone + "&password:"
					+ password);
			Map<String, String> message = new HashMap<String, String>();
			UserDao uDao = new UserDaoImp();
			User user = null;
			if (userid != null) {
				user = uDao.validateUser(userid, password);
			}
			if (user == null) {
				message.put("message", "error");
				message.put("description", "旧密码错误，请重试");
			} else {
				if (newPassword != null) {
					user.setPassword(newPassword);
					if (uDao.update(user)) {
						message.put("username", user.getUsername());
						message.put("message", "success");
						message.put("description", "密码修改成功");
					} else {
						message.put("message", "error");
						message.put("description", "密码修改失败");
					}
				}
			}
			JSONObject json = new JSONObject();
			JSONArray JsonArry = JSONArray.fromObject(message);
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

	/**
	 * responseMS 响应请求 返回JsonArray
	 */
	private void responseMS(JSONArray JsonArry) {
		try {
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