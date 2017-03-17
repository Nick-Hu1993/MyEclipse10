package org.dao;

import java.util.List;
import java.util.Map;

import org.model.Replys;

public interface ReplysDao {
	/**
	 * 网页后台管理接口-
	 * 获取评论对应的回复
	 */
	public List<Map<String, String>> getRepList1(String commentId);
	/**
	 * APP接口-
	 * 获取评论对应的回复
	 */
	public List<Map<String, String>> getRepList(String commentId);
	/**
	 * 获取评论对应的回复对象列表 APP接口
	 */
	public List<Replys> getReplys1(String commentId);
	/**
	 * 获取该id对应的reply对象
	 */
	public Replys getRep(String replyId);
	/**
	 * 删除该reply对象
	 */
	public boolean delReply(String replyId);
	/**
	 * 新增回复
	 */
	public boolean insert(Replys reply);
}
