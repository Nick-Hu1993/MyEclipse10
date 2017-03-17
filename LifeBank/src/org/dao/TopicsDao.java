package org.dao;

import java.util.List;
import java.util.Map;

import org.model.Topics;

public interface TopicsDao {
	/**
	 * 网页后台管理接口-
	 * 获取话题列表
	 */
	public List<Map<String, String>> getTopList1();
	/**
	 * APP接口-
	 * 获取话题列表
	 */
	public List<Map<String, String>> getTopList(Integer position);
	/**
	 * 获取话题列表 获取所有话题对象
	 */
	public List<Topics> getTopics(Integer position);
	/**
	 * 获取该id对应的topic对象
	 */
	public Topics getTop(String topicId);
	/**
	 * 获取topic对象
	 */
	public Topics getTop(Long userid,Integer clock);
	/**
	 * 删除该topic对象
	 */
	public boolean delTop(String topicId);
	/**
	 * 新增话题
	 */
	public boolean insert(Topics topic);
	/**
	 * 搜索话题
	 */
	public List<Topics> searchKeyWord(String keyword);
}	
