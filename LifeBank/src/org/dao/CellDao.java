package org.dao;

import java.util.List;

import org.model.Cell;
import org.model.UserCell;

public interface CellDao {
	/**
	 * 获取用户对应的细胞列表
	 * @param userid 用户Id
	 */
	public List<UserCell> getCellList(Long userid);
	/**
	 * 批量删除多条用户细胞关联记录
	 */
	public boolean deleteUserCell(Long userid);
	/**
	 * 找到对应干细胞
	 * @param cellid 干细胞id
	 */
	public Cell getCell(Long cellid);
	/**
	 * 找出唯一的干细胞
	 */
	public Cell findCell(String name,String clock,String serial);
	/**
	 *找到对应的用户
	 */
	public UserCell getUserCell(Long cellid);
	/**
	 * 添加新的关联关系,细胞与用户
	 */
	public boolean insert(UserCell userCell);
	/**
	 * 添加新的细胞信息
	 */
	public boolean insert(Cell cell);
	/**
	 * 列出所有干细胞信息及相关用户信息
	 */
	public List<Object[]> getCells(Integer position);
	/**
	 * 获取干细胞的总数
	 */
	public Long getCellCount();
	/**
	 * 删除干细胞信息
	 */
	public boolean delete(Cell cell);
	/**
	 * 找出最大的id
	 */
	public Long getMaxId();
	/**
	 * 删除多个干细胞
	 */
	public boolean deleteCells(String[] cellids);
}
