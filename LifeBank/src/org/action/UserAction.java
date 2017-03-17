package org.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.dao.CellDao;
import org.dao.ItemsDao;
import org.dao.PhotoDao;
import org.dao.UserDao;
import org.dao.UserDetailDao;
import org.dao.imp.CellDaoImp;
import org.dao.imp.ItemsDaoImp;
import org.dao.imp.PhotoDaoImp;
import org.dao.imp.UserDaoImp;
import org.dao.imp.UserDetailDaoImp;
import org.model.SuperUser;
import org.model.User;
import org.model.UserCell;
import org.util.ChangeTime;
import org.util.MD5;
import org.util.Utils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport implements ServletRequestAware,
		ServletResponseAware {
	private String phone;
	private String username;
	private String password;
	private String id;
	private List result;
	private int available = 1;
	private int priority = 2;
	private int position;
	HttpServletRequest request = null;
	HttpServletResponse response = null;

	/**
	 * 登录,超级用户的登录
	 */
	public String login() {
		UserDao uDao = new UserDaoImp();
		Map<String, String> message = new HashMap<>();
		List<Map<String, String>> list = new ArrayList<>();
		SuperUser su = uDao.validate(username, password);
		System.out.println(username);
		System.out.println(password);
		if (su == null) {
			message.put("message", "error");
			message.put("description", "用户名或密码错误");
			list.add(message);
			result = list;
			return ERROR;
		} else {
			Map<String, Object> session = ActionContext.getContext()
					.getSession();
			session.put("sUser", su);
			message.put("message", "success");
			message.put("description", "登录成功");
			list.add(message);
			result = list;
			return SUCCESS;
		}

	}

	/**
	 * 获取用户总数目
	 */
	public String getUserCount() {
		List<Long> list = new ArrayList<>();
		UserDao uDao = new UserDaoImp();
		Long count = uDao.getCount();
		list.add(count);
		result = list;
		return SUCCESS;
	}

	/**
	 * 查看所有用户，显示用户列表
	 */
	public String execute() {
		UserDao uDao = new UserDaoImp();
		System.out.println(position);
		List<User> list = uDao.getUserList(position - 1);
		result = list;
		return SUCCESS;
	}

	/**
	 * 后台新加新用户
	 * 
	 * @throws UnsupportedEncodingException
	 */
	public String addUser() throws UnsupportedEncodingException {
		Map<String, String> message = new HashMap<String, String>();
		UserDao userDao = new UserDaoImp();
		System.out.println(username);
		String current = ChangeTime.timeStamp();
		User user = new User(Long.parseLong(phone), username,
				new MD5().string2MD5(new MD5().string2MD5("123456" + "agp")),
				Integer.parseInt(current));
		User u = userDao.findUserByPhone(phone);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		if (u != null) {
			message.put("message", "error");
			message.put("description", "该手机号已注册!");
			list.add(message);
			result = list;
			return ERROR;
		} else if (userDao.Register(user)) {
			UserDetailDao uDetailDao = new UserDetailDaoImp();
			Long userid = userDao.findUserByPhone(phone).getId();
			if (uDetailDao.insert(username, userid)) {
			} else {
				message.put("extral", "添加用户成功但未保存详细信息");
			}
			message.put("message", "success");
			message.put("description", "添加用户成功");
			list.add(message);
			result = list;
			return SUCCESS;
		} else {
			message.put("message", "error");
			message.put("description", "添加用户失败");
			list.add(message);
			result = list;
			return ERROR;
		}

	}

	/**
	 * 新增超级用户
	 * 
	 * @throws UnsupportedEncodingException
	 */
	public String addSuper() throws UnsupportedEncodingException {
		Map<String, String> message = new HashMap<String, String>();
		UserDao uDao = new UserDaoImp();
		//获取当前时间戳
		String current = ChangeTime.timeStamp();
		username = new String(username.getBytes("ISO-8859-1"), "utf-8");
		SuperUser superUser = new SuperUser(username, password, available,
				priority, Integer.parseInt(current));
		SuperUser su = uDao.findSuperByName(username);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		if (su != null) {
			message.put("message", "error");
			message.put("description", "用户名重复!");
			list.add(message);
			result = list;
			return ERROR;
		} else if (uDao.AddSuper(superUser)) {
			message.put("message", "success");
			message.put("description", "添加成功");
			list.add(message);
			result = list;
			String JsonArry = JSONArray.fromObject(result).toString();
			System.out.println(JsonArry);
			return SUCCESS;
		} else {
			message.put("message", "error");
			message.put("description", "添加失败");
			list.add(message);
			result = list;
			return ERROR;
		}
	}

	/**
	 * 查找用户
	 */
	public String searchUser() {
		UserDao uDao = new UserDaoImp();
		User u = uDao.findUserByPhone(phone);
		if (u != null) {
			List<User> list = new ArrayList<User>();
			list.add(u);
			result = list;

			String JsonArry = JSONArray.fromObject(result).toString();
			System.out.println(JsonArry);
			return SUCCESS;
		} else {
			Map<String, String> message = new HashMap<String, String>();
			message.put("message", "error");
			message.put("description", "无该手机用户");
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			list.add(message);
			result = list;
			return ERROR;
		}
	}

	/**
	 * 获取超级用户列表
	 */
	public String getSuperList() {
		UserDao uDao = new UserDaoImp();
		List<SuperUser> list = uDao.getSuperList();
		result = list;
		String JsonArry = JSONArray.fromObject(result).toString();
		System.out.println(JsonArry);
		return SUCCESS;
	}

	/**
	 * 删除用户
	 */
	public String delUser() {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> message = new HashMap<String, String>();
		UserDao uDao = new UserDaoImp();
		System.out.println();
		User user = null;
		if (phone != null) {
			user = uDao.findUserByPhone(phone);
		}
		if (id != null) {
			if (Utils.isLong(id)) {
				user = uDao.findUserById(Long.parseLong(id));
			} else {
				message.put("message", "error");
				message.put("description", "请输入合法的id");
			}
		}
		if (user == null) {
			message.put("message", "error");
			message.put("description", "该用户不存在");
			list.add(message);
			result = list;
			return ERROR;
		}
		if (uDao.deleteUser(user)) {
			PhotoDao pDao = new PhotoDaoImp();
			UserDetailDao uDetailDao = new UserDetailDaoImp();
			CellDao cDao = new CellDaoImp();
			List<String> urlList = pDao.getUrlList(user.getId());
			if (!Utils.delFile(urlList)) {
				System.out.println("删除图片失败");
			}
			if (pDao.delete(user.getId())) {
				message.put("extral", "删除图片表成功");
				System.out.println("删除图片表成功");
			} else {
				message.put("extral", "删除用户成功,但删除图片表时失败了");
				System.out.println("删除用户成功,但删除图片表时失败了");
			}
			if (uDetailDao.delete(Long.parseLong(id))) {
				message.put("extral1", "删除个人详细信息成功");
				System.out.println("删除个人详细信息成功");
			} else {
				message.put("extral1", "删除用户成功,但删除个人详细信息时失败了");
				System.out.println("删除用户成功,但删除个人详细信息时失败了");
			}
			List<UserCell> userCells = cDao.getCellList(user.getId());
			String[] cellids = new String[100];
			int i = 0;
			for (UserCell userCell : userCells) {
				cellids[i] = "" + userCell.getCellid();
				i++;
			}
			if (cellids != null && cellids.length > 0) {
				if (cDao.deleteCells(cellids)) {
					System.out.println("删除用户细胞关联成功");
				} else {
					System.out.println("删除用户细胞关联失败");
				}
			}
			if (cDao.deleteUserCell(user.getId())) {
				System.out.println("删除用户细胞信息成功");
			} else {
				System.out.println("删除用户细胞信息失败");
			}
			message.put("message", "success");
			message.put("description", "删除用户成功");
			list.add(message);
			result = list;
			return SUCCESS;
		} else {
			message.put("message", "error");
			message.put("description", "删除用户失败");
			list.add(message);
			result = list;
			return ERROR;
		}
	}

	/**
	 * 删除超级用户
	 * 
	 * @throws UnsupportedEncodingException
	 */
	public String delSuper() throws UnsupportedEncodingException {
		UserDao uDao = new UserDaoImp();
		username = new String(username.getBytes("ISO-8859-1"), "utf-8");
		SuperUser superUser = uDao.findSuperByName(username);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> message = new HashMap<String, String>();
		if (superUser == null) {
			message.put("message", "error");
			message.put("description", "该用户不存在");
			list.add(message);
			result = list;
			return ERROR;
		}
		if (uDao.deleteSuper(superUser)) {
			message.put("message", "success");
			message.put("description", "删除成功");
			list.add(message);
			result = list;
			return SUCCESS;
		} else {
			message.put("message", "error");
			message.put("description", "删除失败");
			list.add(message);
			result = list;
			return ERROR;
		}
	}

	/**
	 * 编辑超级用户
	 * 
	 * @throws UnsupportedEncodingException
	 */
	public String editSuper() throws UnsupportedEncodingException {
		UserDao uDao = new UserDaoImp();
		username = new String(username.getBytes("ISO-8859-1"), "utf-8");
		SuperUser superUser = uDao.findSuperByName(username);
		superUser.setAvailable(available);
		superUser.setPriority(priority);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> message = new HashMap<String, String>();
		if (uDao.editSuper(superUser)) {
			message.put("message", "success");
			message.put("description", "更新成功");
			list.add(message);
			result = list;
			return SUCCESS;
		} else {
			message.put("message", "error");
			message.put("description", "更新失败");
			list.add(message);
			result = list;
			return ERROR;
		}
	}

	/**
	 * 
	 */

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List getResult() {
		return result;
	}

	public void setResult(List result) {
		this.result = result;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;

	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
