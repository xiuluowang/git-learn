package com.ordinov.user.service.impl;

import java.util.UUID;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import redis.clients.jedis.Jedis;

import com.ordinov.user.entity.User;
import com.ordinov.user.service.IUserManagerService;

@Service
public class UserService implements IUserManagerService {

	private Jedis jedis = null;
	
	public UserService(){
		jedis = new Jedis("192.168.1.200", 6379);
	}
	
	private static String USER_KEY = "USER:RESTAPI";
	
	private static String USER_ADD_ORDER = "USER:ADD_ORDER";
	
	private static String USER_ETAG = "USER:ETAG";
	
	@Override
	public User queryUser(String uid) {
		String jsonUser = jedis.hget(USER_KEY, uid);
		User user = (User) JSONObject.toBean(JSONObject.fromObject(jsonUser), User.class);
		return user;
	}

	@Override
	public User addUser(User user) {
		user.setUserid(UUID.randomUUID().toString());
		
		jedis.hset(USER_KEY, user.getUserid(), JSONObject.fromObject(user).toString());
		updateETagValue(user);
		
		jedis.zadd(USER_ADD_ORDER, System.currentTimeMillis(), user.getUserid());
		
		
		return user;
	}

	@Override
	public boolean modifyUser(User user) {
		boolean result = false;
		User newUser = queryUser(user.getUserid());
		if(newUser != null){
			newUser.setLoginName(user.getLoginName());
			newUser.setUserName(user.getUserName());
			
			jedis.hset(USER_KEY, user.getUserid(), JSONObject.fromObject(newUser).toString());
			updateETagValue(newUser);
			
			result = true;
		}
		return result;
	}
	
	private void updateETagValue(User user){
		StringBuilder builder = new StringBuilder("0");
		DigestUtils.appendMd5DigestAsHex(JSONObject.fromObject(user).toString().getBytes(), builder);
		
		jedis.hset(USER_ETAG, user.getUserid(), builder.toString());
	}

	@Override
	public boolean isClean(String uid, String eTag) {
		System.out.println("is clean ?");
		String eTagInCache = jedis.hget(USER_ETAG, uid);
		
		System.out.println("ETag in Server : " + eTagInCache);
		System.out.println("ETag in Client : " + eTag);
		
		return eTag.equals(eTagInCache);
	}

}
