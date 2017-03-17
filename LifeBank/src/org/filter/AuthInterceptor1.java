package org.filter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.model.SuperUser;
import org.model.User;
import org.util.Utils;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthInterceptor1 extends AbstractInterceptor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String actionName = invocation.getInvocationContext().getName();
		// Map<String, Object> session =
		// invocation.getInvocationContext().getSession();
		// Map<String, Object> session =
		// ServletActionContext.getContext().getSession();
		HttpSession session1 = ServletActionContext.getRequest().getSession();
		// System.out.println(((User) session.get("user")).getUsername());
		// System.out.println(((User)
		// session1.getAttribute("user")).getUsername());
		// session1.setMaxInactiveInterval(30*1000);
		// System.out.println(ServletActionContext.getRequest().getParameter(
		// "username"));

		System.out.println("action name :" + actionName);
		SuperUser su = (SuperUser) session1.getAttribute("sUser");
		if (su != null && su.getUsername().equals("admin")) {
			System.out.println(su.getUsername());
			if (validate()) {
				return invocation.invoke();
			} else {
				List<Map<String, String>> result = new ArrayList<>();
				Map<String, String> message = new HashMap<String, String>();
				message.put("description", "参数不合法，请检查您的输入数据!");
				message.put("message", "error");
				result.add(message);
				ActionContext.getContext().put("result", result);
				// session.put("result", result);
				System.out.println(JSONArray.fromObject(result).toString());
				return Action.ERROR;
			}
		} else {
			List<Map<String, String>> result = new ArrayList<>();
			Map<String, String> message = new HashMap<String, String>();
			message.put("description", "您的权限不足以进行该操作，请使用管理员身份登录!");
			message.put("message", "error");
			result.add(message);
			System.out.println(JSONArray.fromObject(result).toString());
			ActionContext.getContext().put("result", result);
			// session.put("result", result);
			return Action.ERROR;
		}
	}

	public boolean validate() {
		String serial = ServletActionContext.getRequest()
				.getParameter("serial");
		if (serial != null) {
			if (!Utils.isLong(serial)) {
				return false;
			}
		}
		String canid = ServletActionContext.getRequest().getParameter("canid");
		if (canid != null) {
			if (!Utils.isLong(canid)) {
				return false;
			}
		}
		String itemid = ServletActionContext.getRequest()
				.getParameter("itemid");
		if (itemid != null) {
			if (!Utils.isLong(itemid)) {
				return false;
			}
		}
		String cellid = ServletActionContext.getRequest()
				.getParameter("cellid");
		if (cellid != null) {
			if (!Utils.isLong(cellid)) {
				return false;
			}
		}
		String num = ServletActionContext.getRequest().getParameter("num");
		if (num != null) {
			if (!Utils.isLong(num)) {
				return false;
			}
		}
		String userid = ServletActionContext.getRequest()
				.getParameter("userid");
		if (userid != null) {
			if (!Utils.isLong(userid)) {
				return false;
			}
		}
		String phone = ServletActionContext.getRequest().getParameter("phone");
		if (phone != null) {
			if (!Utils.isLong(phone)) {
				return false;
			}
		}
		String topicId = ServletActionContext.getRequest().getParameter(
				"topicId");
		if (topicId != null) {
			if (!Utils.isLong(topicId)) {
				return false;
			}
		}
		String commentId = ServletActionContext.getRequest().getParameter(
				"commentId");
		if (commentId != null) {
			if (!Utils.isLong(commentId)) {
				return false;
			}
		}
		String replyId = ServletActionContext.getRequest().getParameter(
				"replyId");
		if (replyId != null) {
			if (!Utils.isLong(replyId)) {
				return false;
			}
		}
		String id = ServletActionContext.getRequest().getParameter("id");
		if (id != null) {
			if (!Utils.isLong(id)) {
				return false;
			}
		}
		return true;
	}
}
