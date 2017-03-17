package org.action;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.dao.CommentsDao;
import org.dao.PhotoDao;
import org.dao.ReplysDao;
import org.dao.TopicsDao;
import org.dao.UserDao;
import org.dao.imp.CommentsDaoImp;
import org.dao.imp.PhotoDaoImp;
import org.dao.imp.ReplysDaoImp;
import org.dao.imp.TopicsDaoImp;
import org.dao.imp.UserDaoImp;
import org.imodel.IComments;
import org.imodel.IReplys;
import org.imodel.ITopics;
import org.model.Comments;
import org.model.Photo;
import org.model.Replys;
import org.model.Topics;
import org.model.User;
import org.util.ChangeTime;
import org.util.PhotoUtils;
import org.util.Utils;

import com.opensymphony.xwork2.ActionSupport;
import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;

public class AppMMange extends ActionSupport implements ServletRequestAware,
		ServletResponseAware {
	HttpServletRequest request = null;
	HttpServletResponse response = null;
	private Integer position; // item下标
	private String operation = "down"; // 上拉下拉标识
	private String topicId; // 话题id
	private String commentId; // 评论id
	private String replyId; // 回复id
	private String content; // 内容
	private Long userid; // 用户id
	private String title; // 标题
	private String type = ""; // 话题类型
	private String keyword = "";// 关键词
	private String clock = "";// 时间
	private static final long serialVersionUID = 1L;
	private List<File> upload;
	private List<String> fileNames;
	private String fileNUM = "0";
	private File file1;
	private File file2;
	private File file3;
	private File file4;
	private File file5;
	private File file6;
	private File file7;
	private File file8;
	private File file9;
	private Integer isIOS = 1;

	/**
	 * 显示所有的话题,限制10~20个，区别于后台管理 APP端提供一个标志位，表明是上拉还是下拉，默认为下拉刷新
	 */
	public void getTopics1() {
		TopicsDao tDao = new TopicsDaoImp();
		UserDao uDao = new UserDaoImp();
		List<Topics> topics = new ArrayList<>();
		// System.out.println(position);
		if (operation.equals("down")) {
			position = 0;
		}
		System.out.println(position);
		topics = tDao.getTopics(position);
		List<ITopics> iTopics = new ArrayList<>();
		if (topics != null && !topics.isEmpty()) {
			for (Topics topic : topics) {
				ITopics iTopic = new ITopics();
				iTopic.setClock(topic.getClock());
				iTopic.setContent(topic.getContent());
				iTopic.setId(topic.getId());
				iTopic.setTitle(topic.getTitle());
				iTopic.setType(topic.getType());
				iTopic.setUserid(topic.getUserid());
				iTopic.setCommentNum(topic.getComments().size());
				String username = "";
				List<String> urlList = new ArrayList<>();
				String headUrl = "";
				PhotoDao pDao = new PhotoDaoImp();
				if (uDao.findUserById(topic.getUserid()) != null) {
					username = uDao.findUserById(topic.getUserid())
							.getUsername();
					urlList = pDao.getUrlList(topic.getUserid(), topic.getId(),
							2);
					if (pDao.getPhoto(topic.getUserid(), 0) != null) {
						headUrl = pDao.getPhoto(topic.getUserid(), 0).getUrl();
					}
				} else {
					username = "该用户已被删除";
				}
				iTopic.setHeadUrl(headUrl);
				iTopic.setUrlList(urlList);
				iTopic.setUsername(username);
				iTopics.add(iTopic);
			}
		}
		if (position > 0) {
			Map<String, String> map = new HashMap<>();
			map.put("message", "error");
			map.put("description", "没有更多话题了");
			JSONArray jsonArray = JSONArray.fromObject(map);
			responseMS(jsonArray);
		} else if (iTopics == null || iTopics.isEmpty()) {
			Map<String, String> map = new HashMap<>();
			map.put("message", "error");
			map.put("description", "暂时没有任何话题");
			JSONArray jsonArray = JSONArray.fromObject(map);
			responseMS(jsonArray);
		} else {
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig
					.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
			JSONArray JsonArry = JSONArray.fromObject(iTopics, jsonConfig);
			responseMS(JsonArry);
		}
	}

	/**
	 * 查看话题下的评论及回复
	 */
	public void getComRep() {
		CommentsDao cDao = new CommentsDaoImp();
		ReplysDao rDao = new ReplysDaoImp();
		List<Comments> comments = null;
		UserDao uDao = new UserDaoImp();
		System.out.println(topicId);
		System.out.println(operation);
		if (operation.equals("down")) {
			position = 0;
		}
		System.out.println(position);
		if (topicId != null && position != null) {
			comments = cDao.getComments1(topicId, position);
		}
		List<IComments> iComments = new ArrayList<>();
		if (comments != null) {
			for (Comments comment : comments) {
				IComments iComment = new IComments();
				iComment.setClock(comment.getClock());
				iComment.setContent(comment.getContent());
				iComment.setId(comment.getId());
				List<Replys> replys = rDao.getReplys1(comment.getId() + "");
				List<IReplys> iReplys = new ArrayList<IReplys>();
				if (replys != null) {
					for (Replys reply : replys) {
						IReplys iReply = new IReplys();
						iReply.setClock(reply.getClock());
						iReply.setCommentid(comment.getId());
						iReply.setId(reply.getId());
						iReply.setUserid(reply.getUserid());
						String Rusername = "";
						if (uDao.findUserById(reply.getUserid()) != null) {
							Rusername = uDao.findUserById(reply.getUserid())
									.getUsername();
						} else {
							Rusername = "该用户已被删除";
						}
						iReply.setUsername(Rusername);
						iReply.setContent(reply.getContent());
						iReplys.add(iReply);
					}
				}
				iComment.setReplys(iReplys);
				iComment.setTopicid(comment.getTopic().getId());
				iComment.setUserid(comment.getUserid());
				String Cusername = "";
				List<String> urlList = new ArrayList<>();
				PhotoDao pDao = new PhotoDaoImp();
				String headUrl = "";
				if (uDao.findUserById(comment.getUserid()) != null) {
					Cusername = uDao.findUserById(comment.getUserid())
							.getUsername();
					urlList = pDao.getUrlList(comment.getUserid(),
							comment.getId(), 3);
					if (pDao.getPhoto(comment.getUserid(), 0) != null) {
						headUrl = pDao.getPhoto(comment.getUserid(), 0)
								.getUrl();
					}
				} else {
					Cusername = "该用户已被删除";
				}
				iComment.setHeadUrl(headUrl);
				iComment.setUrlList(urlList);
				iComment.setUsername(Cusername);
				iComments.add(iComment);
			}
		}
		if (iComments == null || iComments.isEmpty()) {
			Map<String, String> map = new HashMap<>();
			if (position > 0) {
				map.put("message", "error");
				map.put("description", "没有更多评论了");
			} else {
				map.put("message", "error");
				map.put("description", "暂时没有任何评论");
			}
			JSONArray jsonArray = JSONArray.fromObject(map);
			responseMS(jsonArray);
		} else {
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig
					.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
			JSONArray JsonArry = JSONArray.fromObject(iComments, jsonConfig);

			responseMS(JsonArry);
		}

	}

	/**
	 * 创建新话题
	 */
	public void AddTopic() {
		if (clock.equals("")) {
			clock = ChangeTime.timeStamp();
		}
		try {
			System.out.println("1>>>>>>>>>" + content);
			request.setCharacterEncoding("utf-8");
			System.out.println("2>>>>>>>>>" + content);
			// content = new String(content.getBytes("ISO-8859-1"), "utf-8");
			// title = new String(title.getBytes("ISO-8859-1"), "utf-8");
			// type = new String(type.getBytes("ISO-8859-1"), "utf-8");
			System.out.println("content:" + content);
			System.out.println("title:" + title);
			System.out.println("type:" + type);
			Map<String, String> message = new HashMap<String, String>();
			if (content != null && title != null && type != null
					&& userid != null && clock != null) {
				Topics topic = new Topics(userid, title, content,
						Integer.parseInt(clock), type);
				TopicsDao tDao = new TopicsDaoImp();
				if (tDao.insert(topic)) {
					Long id = tDao.getTop(userid, Integer.parseInt(clock))
							.getId();
					if (photoHandle(2, id)) {
						message.put("message", "success");
						message.put("description", "发布成功");
					} else {
						message.put("message", "success");
						message.put("description", "内容发布成功，并无上传图片");
					}
				} else {
					message.put("message", "error");
					message.put("description", "发布失败");
				}
			} else {
				message.put("message", "error");
				message.put("description", "缺少必要参数");
			}
			message.put("parms", "content=" + Utils.isNull(content) + "&title="
					+ Utils.isNull(title) + "&type=" + Utils.isNull(type)
					+ "&userid=" + Utils.isNull(userid));
			// 返回相应参数
			JSONArray JsonArry = JSONArray.fromObject(message);
			responseMS(JsonArry);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 发表评论
	 */
	public void addComment() {
		if (clock.equals("")) {
			clock = ChangeTime.timeStamp();
		}
		try {
			System.out.println(content);
			System.out.println(userid);
			System.out.println(topicId);
			request.setCharacterEncoding("utf-8");
			Map<String, String> message = new HashMap<String, String>();
			if (content == null || userid == null || topicId == null) {
				message.put("message", "error");
				message.put("description", "缺少必要参数");
			} else {
				System.out.println("content:" + content);
				TopicsDao tDao = new TopicsDaoImp();
				Topics topic = tDao.getTop(topicId);
				Comments comment = new Comments(userid, content,
						Integer.parseInt(clock), topic);
				CommentsDao cDao = new CommentsDaoImp();
				if (cDao.insert(comment)) {
					Long id = cDao.getCom(userid, Integer.parseInt(clock))
							.getId();
					if (photoHandle(3, id)) {
						message.put("message", "success");
						message.put("description", "发布成功");
					} else {
						message.put("message", "success");
						message.put("description", "内容发布成功，并无上传图片");
					}
				} else {
					message.put("message", "error");
					message.put("description", "发表失败");
				}
			}
			message.put(
					"parms",
					"content:" + Utils.isNull(content) + "&userid:"
							+ Utils.isNull(userid) + "&topicId:"
							+ Utils.isNull(topicId));
			JSONArray JsonArry = JSONArray.fromObject(message);
			responseMS(JsonArry);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 回复评论
	 */
	public void AddReply() {
		if (clock.equals("")) {
			clock = ChangeTime.timeStamp();
		}
		System.out.println("content:" + content);
		System.out.println("userid:" + userid);
		System.out.println("clock:" + clock);
		try {
			request.setCharacterEncoding("utf-8");
			Map<String, String> message = new HashMap<String, String>();
			if (content == null || userid == null || commentId == null) {
				message.put("message", "error");
				message.put("description", "缺少必要参数");
			} else {
				if (isIOS == 1) {
					System.out.println(content);
					content = new String(content.getBytes("ISO-8859-1"),
							"utf-8");
					System.out.println(content);
				}
				CommentsDao cDao = new CommentsDaoImp();
				Comments comment = cDao.getCom(commentId);
				Replys reply = new Replys(userid, content,
						Integer.parseInt(clock), comment);
				ReplysDao rDao = new ReplysDaoImp();

				if (rDao.insert(reply)) {
					message.put("message", "success");
					message.put("description", "回复成功");
				} else {
					message.put("message", "error");
					message.put("description", "回复失败");
				}
			}
			message.put(
					"parms",
					"content:" + Utils.isNull(content) + "&userid:"
							+ Utils.isNull(userid) + "&commentId:"
							+ Utils.isNull(commentId));
			JSONArray JsonArry = JSONArray.fromObject(message);
			responseMS(JsonArry);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除话题
	 */
	public void delTopic() {
		Map<String, String> message = new HashMap<String, String>();
		if (topicId != null) {
			TopicsDao tDao = new TopicsDaoImp();
			PhotoDao pDao = new PhotoDaoImp();
			Topics topic = tDao.getTop(topicId);
			if (tDao.delTop(topicId)) {
				List<String> urlList = pDao.getTopAllUrl(Long
						.parseLong(topicId));
				if (topic.getComments() != null
						&& topic.getComments().size() > 0) {
					for (Comments comment : topic.getComments()) {
						if (!pDao.delByForeignId(comment.getId())) {
							System.out.println("话题删除成功，但删除评论图片记录失败了");
						}
					}
				}
				if (!pDao.delByForeignId(Long.parseLong(topicId))) {
					System.out.println("话题删除成功，但删除话题图片记录失败了");
				} else {
					System.out.println("话题删除成功且删除话题图片记录成功");
				}
				if (!Utils.delFile(urlList)) {
					System.out.println("话题删除成功,但删除话题及评论图片失败");
				} else {
					System.out.println("话题删除成功,但删除话题及评论图片成功");
				}
				message.put("message", "success");
				message.put("description", "删除话题成功");
			} else {
				message.put("message", "error");
				message.put("description", "删除话题失败");
			}
		}
		message.put("parms", "topicId=" + Utils.isNull(topicId));
		JSONArray JsonArry = JSONArray.fromObject(message);
		responseMS(JsonArry);
	}

	/**
	 * 删除评论
	 */
	public void delComment() {
		CommentsDao cDao = new CommentsDaoImp();
		Map<String, String> message = new HashMap<String, String>();
		if (commentId != null) {
			PhotoDao pDao = new PhotoDaoImp();
			if (cDao.delComment(commentId)) {
				List<String> urlList = pDao.getUrlByForeignId(Long
						.parseLong(commentId));
				if (!Utils.delFile(urlList)) {
					System.out.println("删除成功，但删除评论图片失败");
				} else {
					System.out.println("删除成功且删除评论图片成功");
				}
				if (!pDao.delByForeignId(Long.parseLong(commentId))) {
					System.out.println("评论删除成功，但删除评论图片记录失败了");
				} else {
					System.out.println("删除成功且删除评论图片记录成功");
				}
				message.put("message", "success");
				message.put("description", "删除评论成功");
			} else {
				message.put("message", "error");
				message.put("description", "删除评论失败");
			}
		}
		message.put("parms", "commentId=" + Utils.isNull(commentId));
		JSONArray JsonArry = JSONArray.fromObject(message);
		responseMS(JsonArry);
	}

	/**
	 * 删除回复
	 */
	public void delReply() {
		ReplysDao rDao = new ReplysDaoImp();
		Map<String, String> message = new HashMap<String, String>();
		if (replyId != null) {
			if (rDao.delReply(replyId)) {
				message.put("message", "success");
				message.put("description", "删除回复成功");
			} else {
				message.put("message", "error");
				message.put("description", "删除回复失败");
			}
		}
		message.put("parms", "replyId=" + Utils.isNull(replyId));
		JSONArray JsonArry = JSONArray.fromObject(message);
		responseMS(JsonArry);
	}

	/**
	 * 查看用户自己发布的信息,暂时不做
	 */
	public void getMsByUser() {

	}

	/**
	 * 测试方法
	 */
	public void test1() {
		if (clock.equals("")) {
			clock = ChangeTime.timeStamp();
		}
		Map<String, String> message = new HashMap<String, String>();
		System.out.println("1" + request.getParameter("content"));
		System.out.println("2" + request.getAttribute("content"));
		System.out.println("3" + request.getParameter("userid"));
		System.out.println("4" + request.getAttribute("userid"));
		System.out.println("5" + content);
		System.out.println("6" + userid);
		TopicsDao tDao = new TopicsDaoImp();
		Topics topic = new Topics(userid, "asdasd", content,
				Integer.parseInt(clock), "测试话题");
		if (content != null || userid != null) {
			if (tDao.insert(topic)) {
				message.put("message", content);
			} else {
				message.put("message", "error1");
			}
		} else {
			message.put("message", "error2");
		}
		JSONArray JsonArry = JSONArray.fromObject(message);
		responseMS(JsonArry);
	}

	/**
	 * 根据关键字查找话题
	 * 
	 * @throws UnsupportedEncodingException
	 */
	public void searchByKey() throws UnsupportedEncodingException {
		TopicsDao tDao = new TopicsDaoImp();
		keyword = new String(keyword.getBytes("ISO-8859-1"), "utf-8");
		List<Topics> topics = new ArrayList<Topics>();
		if (keyword.equals("")) {
			position = 0;
			topics = tDao.getTopics(position);
		} else {
			topics = tDao.searchKeyWord(keyword);
		}
		List<ITopics> iTopics = new ArrayList<>();
		UserDao uDao = new UserDaoImp();
		if (topics != null && !topics.isEmpty()) {
			for (Topics topic : topics) {
				ITopics iTopic = new ITopics();
				iTopic.setClock(topic.getClock());
				iTopic.setContent(topic.getContent());
				iTopic.setId(topic.getId());
				iTopic.setTitle(topic.getTitle());
				iTopic.setType(topic.getType());
				iTopic.setUserid(topic.getUserid());
				iTopic.setCommentNum(topic.getComments().size());
				String username = "";
				List<String> urlList = new ArrayList<>();
				String headUrl = "";
				PhotoDao pDao = new PhotoDaoImp();
				if (uDao.findUserById(topic.getUserid()) != null) {
					username = uDao.findUserById(topic.getUserid())
							.getUsername();
					urlList = pDao.getUrlList(topic.getUserid(), topic.getId(),
							2);
					if (pDao.getPhoto(topic.getUserid(), 0) != null) {
						headUrl = pDao.getPhoto(topic.getUserid(), 0).getUrl();
					}
				} else {
					username = "该用户已被删除";
				}
				iTopic.setHeadUrl(headUrl);
				iTopic.setUrlList(urlList);
				iTopic.setUsername(username);
				iTopics.add(iTopic);
			}
		}
		if (iTopics.size() > 0) {
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig
					.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
			JSONArray JsonArry = JSONArray.fromObject(iTopics, jsonConfig);
			responseMS(JsonArry);
		} else {
			Map<String, String> map = new HashMap<>();
			map.put("message", "error");
			map.put("description", "未找到相关话题！");
			JSONArray jsonArray = JSONArray.fromObject(map);
			responseMS(jsonArray);
		}

	}

	/**
	 * 处理图片上传保存以及数据库数据插入 漏洞：不能使用List传输，因为srcList无法被赋值
	 */
	private boolean photoHandle(Integer type, Long id) {
		fileNames = new ArrayList<>();
		upload = new ArrayList<>();
		List<String> srcList = new ArrayList<>();
		List<String> urlList = new ArrayList<>();
		if (Integer.parseInt(fileNUM) > 0) {
			for (int i = 1; i <= Integer.parseInt(fileNUM); i++) {
				System.out.println(request.getAttribute("file" + i));
				System.out.println(request.getParameter("filename" + i));
				upload.add((File) request.getAttribute("file" + i));
				String a = request.getParameter("filename" + i);
				fileNames.add(a);
				String[] s = a.split("\\.");
				String time = ChangeTime.timeStamp();
				srcList.add(Utils.BASESRC + s[0] + "_" + time + "." + s[1]);
				urlList.add(Utils.BASEURL + s[0] + "_" + time + "." + s[1]);
			}
			PhotoUtils.photoUp(upload, srcList, fileNUM);
			PhotoDao pDao = new PhotoDaoImp();
			switch (type) {
			case 2:
				pDao.insert(userid, type, id, urlList);
				break;
			case 3:
				pDao.insert(userid, type, id, urlList);
				break;
			default:
				break;
			}
			return true;
		} else {
			return false;
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

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getTopicId() {
		return topicId;
	}

	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public String getReplyId() {
		return replyId;
	}

	public void setReplyId(String replyId) {
		this.replyId = replyId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
	}

	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getClock() {
		return clock;
	}

	public void setClock(String clock) {
		this.clock = clock;
	}

	public List<File> getUpload() {
		return upload;
	}

	public void setUpload(List<File> upload) {
		this.upload = upload;
	}

	public List<String> getFileNames() {
		return fileNames;
	}

	public void setFileNames(List<String> fileNames) {
		this.fileNames = fileNames;
	}

	public String getFileNUM() {
		return fileNUM;
	}

	public void setFileNUM(String fileNUM) {
		this.fileNUM = fileNUM;
	}

	// public List<InputStream> getFileList() {
	// return fileList;
	// }
	//
	// public void setFileList(List<InputStream> fileList) {
	// this.fileList = fileList;
	// }

	public File getFile1() {
		return file1;
	}

	public void setFile1(File file1) {
		this.file1 = file1;
	}

	public File getFile2() {
		return file2;
	}

	public void setFile2(File file2) {
		this.file2 = file2;
	}

	public File getFile3() {
		return file3;
	}

	public void setFile3(File file3) {
		this.file3 = file3;
	}

	public File getFile4() {
		return file4;
	}

	public void setFile4(File file4) {
		this.file4 = file4;
	}

	public File getFile5() {
		return file5;
	}

	public void setFile5(File file5) {
		this.file5 = file5;
	}

	public File getFile6() {
		return file6;
	}

	public void setFile6(File file6) {
		this.file6 = file6;
	}

	public File getFile7() {
		return file7;
	}

	public void setFile7(File file7) {
		this.file7 = file7;
	}

	public File getFile8() {
		return file8;
	}

	public void setFile8(File file8) {
		this.file8 = file8;
	}

	public File getFile9() {
		return file9;
	}

	public void setFile9(File file9) {
		this.file9 = file9;
	}

	public Integer getIsIOS() {
		return isIOS;
	}

	public void setIsIOS(Integer isIOS) {
		this.isIOS = isIOS;
	}

}
