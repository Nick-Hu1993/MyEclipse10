package org.dao;

import java.util.List;

import org.model.Can;

public interface CanDao {
	/**
	 * 获取罐体的总数
	 */
	public Long getCount();
	/**
	 * 获取罐体信息列表
	 */
	public List<Can> getList(Integer position);
	/**
	 *根据序列号找到罐体 
	 */
	public Can getCanBySerial(String Serial);
	/**
	 * 新增罐体
	 */
	public  boolean insert(Can can);
	/**
	 * 更新罐体信息
	 */
	public boolean update(Can can);
	/**
	 * 根据ID找到罐体
	 */
	public Can findCanById(Long id);
	/**
	 * 删除罐体
	 */
	public boolean delCan(Long id);
	
}
