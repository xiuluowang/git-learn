package com.ordinov.ersp.user.dao;

import java.util.List;

import com.ordinov.ersp.user.entity.UserSecurity;

public interface IUserSecurityMngDAO {
	
	/**
	 * 增加
	 * @param us
	 */
	public String addUserSecurity(UserSecurity us);
	
	/**
	 * 根据用户编号查询
	 * @param userId
	 */
	public UserSecurity findUserSecurityByID(String userId);
	
	/**
	 * 删除
	 * @param us
	 */
	public boolean deleteUserSecurity(UserSecurity us);
	
	/**
	 * 批量删除
	 * @param usList
	 */
	public boolean deleteUserSecuritys(List<UserSecurity> usList);
	
	
}
