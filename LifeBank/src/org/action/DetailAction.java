package org.action;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.dao.PhotoDao;
import org.dao.UserDao;
import org.dao.UserDetailDao;
import org.dao.imp.PhotoDaoImp;
import org.dao.imp.UserDaoImp;
import org.dao.imp.UserDetailDaoImp;
import org.model.Photo;
import org.model.User;
import org.model.UserDetail;
import org.util.ChangeTime;
import org.util.PhotoUtils;
import org.util.Utils;

import com.opensymphony.xwork2.ActionSupport;

public class DetailAction extends ActionSupport implements ServletRequestAware,
		ServletResponseAware {

	private static final long serialVersionUID = 1L;
	private Long userid;
	private String username="";
	private String password="";
	private String address="";
	private String email="";
	private File file1;
	private String filename1;
	HttpServletRequest request = null;
	HttpServletResponse response = null;

	/**
	 * 更新个人详细信息
	 */
	public void UpdateInfo() {
		Map<String, String> message = new HashMap<String, String>();
		if (userid != null) {
			UserDetailDao uDetailDao = new UserDetailDaoImp();
			UserDetail uDetail = uDetailDao.getDetail(userid);
			UserDao uDao = new UserDaoImp();
			User user = uDao.findUserById(userid);
			if (!address.equals("")) {
				uDetail.setAddress(address);
			}
			if (!username.equals("")) {
				uDetail.setUsername(username);
				user.setUsername(username);
			}
			if (!email.equals("")) {
				uDetail.setEmail(email);
			}
			if (uDetailDao.update(uDetail)) {
				if(uDao.update(user)){
					message.put("message", "success");
					message.put("description", "更换成功");
				}else {
					message.put("message", "error");
					message.put("description", "更新失败");
				}
			} else {
				message.put("message", "error");
				message.put("description", "更新失败");
			}
		}
		message.put("parms", "userid=" + Utils.isNull(userid) + "&address=" + Utils.isNull(address)
				+ "&username=" + Utils.isNull(username) + "&email=" + Utils.isNull(email));
		JSONArray jsonArray = JSONArray.fromObject(message);
		responseMS(jsonArray);
	}

	/**
	 * 头像上传
	 */
	public void photoUp() {
		Map<String, String> message = new HashMap<String, String>();
		if (file1 != null && filename1 != null && userid != null) {
			String[] s = filename1.split("\\.");
			String time = ChangeTime.timeStamp();
			String src = Utils.BASESRC + s[0] + "_" + time + "." + s[1];
			String url = Utils.BASEURL + s[0] + "_" + time + "." + s[1];
			System.out.println("src:" + src);
			System.out.println("url:" + url);
			System.out.println(file1);
			if (file1 != null) {
				System.out.println(file1.getName());
			}
			PhotoDao pDao = new PhotoDaoImp();
			System.out.println(PhotoUtils.photoUp(
					(File) request.getAttribute("file1"), src));
			UserDao uDao = new UserDaoImp();
			uDao.findUserById(userid);
			// System.out.println(PhotoUtils.photoUp(file1, src));
			if (userid != null) {
				for (Photo photo : pDao.getUsedHead(userid, 0)) {
					if(!Utils.delFile(photo.getUrl().replace(Utils.BASEURL, Utils.BASESRC))){
						System.out.println("删除旧头像失败");
					}
				}
				if (pDao.insert(userid, 0, url)) {
					message.put("message", "success");
					message.put("description", "更换头像成功");
					message.put("headUrl", url);
				} else {
					message.put("message", "error");
					message.put("description", "更换头像失败");
				}
			}
		}
		message.put("parms", "userid=" + Utils.isNull(userid) + "filename1=" + Utils.isNull(filename1));
		if (file1 != null) {
			message.put("file", "file1.name=" + file1.getName());
		} else {
			message.put("file", "没有接收到文件!!!");
		}
		JSONArray jsonArray = JSONArray.fromObject(message);
		responseMS(jsonArray);
	}

	/**
	 * 获取个人详细信息
	 */
	public void getDetailInfo() {
		PhotoDao pDao = new PhotoDaoImp();
		UserDetailDao detailDao = new UserDetailDaoImp();
		Map<String, String> message = new HashMap<String, String>();
		if (userid != null) {
			UserDetail userDetail = detailDao.getDetail(userid);
			if (userDetail != null) {
				if (userDetail.getAddress() == null) {
					message.put("address", "");
				} else {
					message.put("address", userDetail.getAddress());
				}
				if (userDetail.getEmail() == null) {
					message.put("email", "");
				} else {
					message.put("email", userDetail.getEmail());
				}
				if (userDetail.getUsername() == null) {
					message.put("username", "");
				} else {
					message.put("username", userDetail.getUsername());
				}
				message.put("id", "" + userDetail.getId());
				message.put("userid", "" + userDetail.getUserid());
				if (pDao.getPhoto(userid, 0) != null) {
					message.put("headUrl", pDao.getPhoto(userid, 0).getUrl());
				} else {
					message.put("headUrl", "");
				}
			}
		}
		message.put("parms", "userid=" + Utils.isNull(userid));
		JSONArray jsonArray = JSONArray.fromObject(message);
		responseMS(jsonArray);
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

	public File getFile1() {
		return file1;
	}

	public void setFile1(File file1) {
		this.file1 = file1;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFilename1() {
		return filename1;
	}

	public void setFilename1(String filename1) {
		this.filename1 = filename1;
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

}
