package com.ordinov.user.service;

import com.ordinov.user.entity.User;

/**
 * 用户管理service
 * @author dengfx
 */
public interface IUserManagerService {

	public User queryUser(String uid);
	
	public User addUser(User user);
	
	public boolean modifyUser(User user);
	
	public boolean isClean(String uid,String eTag);
}
