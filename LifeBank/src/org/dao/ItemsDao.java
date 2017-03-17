package org.dao;

import java.util.List;

import org.model.Items;

public interface ItemsDao {
	/**
	 * 插入罐体数据记录
	 */
	public boolean insert(List<String> resultList);

	/**
	 * 获取相应罐体的最新数据
	 */
	public Items getLastItemsBySerial(String Serial);

	/**
	 * 获取相应罐体的历史记录
	 */
	public List<Items> getItemListBySerial(String Serial);

	/**
	 * 获取所有罐体数据的统计数
	 */
	public Long getCount();
	/**
	 * 获取指定月份的罐体数据统计数
	 */
	public Long getCount(Integer start_clock,Integer end_clock);
	/**
	 * 获取所有罐体的历史记录，根据页数
	 */
	public List<Items> getItemList(Integer position);
	/**
	 * 获取所有罐体的历史记录，包括罐体存放地点
	 */
	public List getItemList();
	/**
	 * 获取指定的items对象
	 */
	public boolean deleteItem(String id);

	/**
	 * 获得指定时间段的记录，只显示15条
	 */
	public List<Items> getItemsByPeriod(Integer start, Integer end,
			Integer position);

	/**
	 * 删除多个记录
	 */
	public boolean delete(String itemids[]);

	/**
	 * 删除某一个月的记录
	 */
	public boolean delete(Integer start_clock, Integer end_clock);
}
