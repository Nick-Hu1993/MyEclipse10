package org.dao;

import java.util.List;
import java.util.Map;

import org.model.Comments;
import org.model.Topics;

public interface CommentsDao {
	/**
	 * 网页后台管理接口-
	 * 获取话题对应的评论
	 */
	public List<Map<String, String>> getComList1(String topicId);
	/**
	 * APP接口-
	 * 获取话题对应的评论
	 */
	public List<Map<String, String>> getComList(String topicId);
	/**
	 * 获取话题对应的评论对象列表
	 */
	public List<Comments> getComments(String topicId,Integer position);
	/**
	 * APP接口，获取话题对应的评论对象列表,包含topic对象
	 */
	public List<Comments> getComments1(String topicId,Integer position);
	/**
	 * 获取该id对应的comment对象
	 */
	public Comments getCom(String commentId);
	/**
	 * 获取一个comment对象
	 */
	public Comments getCom(Long userid,Integer clock);
	
	/**
	 * 删除该comment对象
	 */
	public boolean delComment(String commentId);
	/**
	 * 新增评论
	 */
	public boolean insert(Comments comment);
}
