package org.dao;

import java.util.List;

import org.model.SuperUser;
import org.model.User;

public interface UserDao {
	/**
	 *注册，手机号、名字、密码、时间 
	 */
	public boolean Register(User user);
	/**
	 * 验证密码是否正确
	 */
	public User validateUser(Long userid,String password);
	/**
	 * 登录
	 */
	public User Login(String phone,String password);
	/**
	 * 更新用户信息
	 */
	public boolean update(User user);
	/**
	 * 通过手机号找到用户
	 */
	public User findUserByPhone(String phone);
	/**
	 * 通过id找到用户
	 */
	public User findUserById(Long id);
	/**
	 * 获取用户总数
	 */
	public Long getCount();
	/**
	 * 获取用户列表
	 */
	public List<User> getUserList(Integer position);
	/**
	 * 添加新的超级用户
	 */
	public boolean AddSuper(SuperUser superUser);
	/**
	 * 获取超级用户列表
	 */
	public List<SuperUser> getSuperList();
	/**
	 * 删除超级用户
	 */
	public boolean deleteSuper(SuperUser superUser);
	/**
	 * 删除用户
	 */
	public boolean deleteUser(User user);
	/**
	 * 编辑超级用户
	 */
	public boolean editSuper(SuperUser superUser);
	/**
	 * 通过用户名找到超级用户
	 */
	public SuperUser findSuperByName(String username);
	/**
	 * 用户名密码确认
	 */
	public SuperUser validate(String username,String password);
}
