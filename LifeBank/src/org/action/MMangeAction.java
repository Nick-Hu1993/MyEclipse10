package org.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.dao.CommentsDao;
import org.dao.ReplysDao;
import org.dao.TopicsDao;
import org.dao.UserDao;
import org.dao.imp.CommentsDaoImp;
import org.dao.imp.ReplysDaoImp;
import org.dao.imp.TopicsDaoImp;
import org.dao.imp.UserDaoImp;
import org.imodel.IComments;
import org.imodel.IReplys;
import org.model.Comments;
import org.model.Replys;
import org.model.Topics;

import com.opensymphony.xwork2.ActionSupport;

public class MMangeAction extends ActionSupport {
	private List result;
	private String topicId;
	private String commentId;
	private String replyId;
	private Integer position=0;
	/**
	 * 显示所有的话题
	 */
	public String execute() {
		TopicsDao tDao = new TopicsDaoImp();
		List<Map<String, String>> list = tDao.getTopList1();
		if (list.isEmpty() || list == null) {
			Map<String, String> message = new HashMap<>();
			message.put("message", "error");
			message.put("description", "无任何话题!");
			list.add(message);
			result = list;
			return ERROR;
		} else {
			result = list;
			String JsonArry = JSONArray.fromObject(result).toString();
			System.out.println(JsonArry);
			
			return SUCCESS;
		}
	}

	/**
	 * 显示级联json,分级列出所有留言信息
	 */
	public String getAllInfo() {
		TopicsDao tDao = new TopicsDaoImp();
		List<Topics> list = tDao.getTopics(position);
		if (list.isEmpty() || list == null) {
			return ERROR;
		} else {
			result = list;
			return SUCCESS;
		}
	}
	/**
	 * 查看话题下的评论及回复
	 */
	public String getComRep() {
		CommentsDao cDao = new CommentsDaoImp();
		ReplysDao rDao = new ReplysDaoImp();
		List<Comments> comments = null;
		UserDao uDao = new UserDaoImp();
		System.out.println(topicId);
		position = 0;
		System.out.println(position);
		comments = cDao.getComments(topicId, position);
		List<IComments> iComments = new ArrayList<>();
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
					String Rusername = uDao.findUserById(reply.getUserid())
							.getUsername();
					iReply.setUsername(Rusername);
					iReply.setContent(reply.getContent());
					iReplys.add(iReply);
				}
			}
			iComment.setReplys(iReplys);
			iComment.setTopicid(comment.getTopic().getId());
			iComment.setUserid(comment.getUserid());
			String Cusername = uDao.findUserById(comment.getUserid())
					.getUsername();
			iComment.setUsername(Cusername);
			iComments.add(iComment);
		}
		if (iComments == null || iComments.isEmpty()) {
//			Map<String, String> map = new HashMap<>();
//			map.put("message", "error");
//			map.put("description", "无任何数据!");
			List<IComments> list= new ArrayList<>();
			
//			list.add(map);
//			result = list;
			result = list;
			return ERROR;
		} else {
			result = iComments;
			return SUCCESS;
		}
	}
	
	
	/**
	 * 点击话题查看评论
	 */
	public String getCommentsByTopic() {
		CommentsDao cDao = new CommentsDaoImp();
		List<Map<String, String>> list = cDao.getComList1(topicId);
		if (list.isEmpty() || list == null) {
			Map<String, String> message = new HashMap<>();
			message.put("message", "error");
			message.put("description", "无任何评论 !");
			list.add(message);
			result = list;
			return ERROR;
		} else {
			result = list;
			return SUCCESS;
		}
	}

	/**
	 * 点击评论查看回复
	 */
	public String getReplysByComment() {
		ReplysDao replysDao = new ReplysDaoImp();
		List<Map<String, String>> list = replysDao.getRepList1(commentId);
		if (list.isEmpty() || list == null) {
			Map<String, String> message = new HashMap<>();
			message.put("message", "error");
			message.put("description", "无任何回复!");
			list.add(message);
			result = list;
			return ERROR;
		} else {
			result = list;
			return SUCCESS;
		}
	}

	/**
	 * 删除话题
	 */
	public String delTopic() {
		TopicsDao tDao = new TopicsDaoImp();
		Map<String, String> message = new HashMap<>();
		List<Map<String, String>> list = new ArrayList<>();
		System.out.println("topicId"+topicId);
		if (tDao.delTop(topicId)) {
			message.put("message", "success");
			message.put("description", "删除话题成功");
			list.add(message);
			result = list;
			return SUCCESS;
		} else {
			message.put("message", "error");
			message.put("description", "删除话题失败");
			list.add(message);
			result = list;
			return ERROR;
		}
	}

	/**
	 * 删除评论
	 */
	public String delComment() {
		CommentsDao cDao = new CommentsDaoImp();
		Map<String, String> message = new HashMap<>();
		List<Map<String, String>> list = new ArrayList<>();
		System.out.println("commentId:"+commentId);
		if (cDao.delComment(commentId)) {
			message.put("message", "success");
			message.put("description", "删除评论成功");
			list.add(message);
			result = list;
			return SUCCESS;
		} else {
			message.put("message", "error");
			message.put("description", "删除评论失败");
			list.add(message);
			result = list;
			return ERROR;
		}
	}

	/**
	 * 删除回复
	 */
	public String delReply() {
		ReplysDao rDao = new ReplysDaoImp();
		Map<String, String> message = new HashMap<>();
		List<Map<String, String>> list = new ArrayList<>();
		System.out.println("replyId:"+replyId);
		if (rDao.delReply(replyId)) {
			message.put("message", "success");
			message.put("description", "删除回复成功");
			list.add(message);
			result = list;
			return SUCCESS;
		} else {
			message.put("message", "error");
			message.put("description", "删除回复失败");
			list.add(message);
			result = list;
			return ERROR;
		}
	}
	
	/**
	 * 按关键词搜索所有包含关键词的信息,暂时不做
	 */
	public String getMessageByKey() {
		
		return SUCCESS;
	}
	public List getResult() {
		return result;
	}

	public String getReplyId() {
		return replyId;
	}

	public void setReplyId(String replyId) {
		this.replyId = replyId;
	}

	public void setResult(List result) {
		this.result = result;
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
}
