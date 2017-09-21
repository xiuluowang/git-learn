package com.ordinov.ersp.user.dao;

import java.util.List;

import com.ordinov.ersp.user.entity.User;

/**
 * 用户管理DAO接口
 */
public interface IUserMngDAO {
	
	/**
	 * 增加用户
	 * @param user
	 */
	public String addUser(User user);
	
	/**
	 * 修改用户
	 * @param user
	 */
	public boolean modifyUser(User user);
	
	/**
	 * 根据用户编号查询用户
	 * @param usrid
	 */
	public User findUserByID(String usrid);
	
	/**
	 * 查询所有用户
	 */
	public List<User> findUsers();
	
	/**
	 * 删除用户
	 * @param user
	 */
	public boolean deleteUser(User user);
	
	/**
	 * 批量删除用户
	 * @param userList
	 */
	public boolean deleteUsers(List<User> userList);
	
	/**
	 * 根据条件查询用户
	 * @param user
	 */
	public List<User> findUsers(User user);
	
	/**
	 * 分页获取用户信息
	 * @param searchText 查询条件
	 * @param page 当前页数
	 * @param rows 显示的行数
	 * @param sidx 排序字段
	 * @param sord 排序方式
	 */
	public List<User> queryUsers(String searchText,int page,int rows,String sidx,String sord);
	
	/**
	 * 获取总记录数
	 * @param searchText 查询条件
	 */
	public long queryTotal(String searchText);
	
	/**
	 * 验证参数唯一性
	 */
	public boolean valUserByParam(User user);
	
	

}
