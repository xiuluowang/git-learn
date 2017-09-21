package com.ordinov.ersp.security.service;

import com.ordinov.ersp.user.entity.User;

public interface IRegisterService {
	
	/**
	 * 验证参数唯一性
	 */
	public boolean valUserByParam(User user);
	
	/**
	 * 注册用户
	 */
	public String register(User user);

}
