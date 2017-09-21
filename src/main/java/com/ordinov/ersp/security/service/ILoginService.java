package com.ordinov.ersp.security.service;

import com.ordinov.ersp.user.entity.User;

/**
 * 登录service接口
 * @author zhaowy
 *
 */
public interface ILoginService {
	
	/**
	 * 登录
	 * @param data 用户名/邮箱/联系电话
	 * @param password 密码
	 */
	public User login(String data,String password);
	
	/**
	 * 登出
	 */
	public boolean logout();
	
	/**
	 * 获取登录信息
	 */
	public String queryLoginInfo();

}
