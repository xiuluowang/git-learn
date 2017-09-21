package com.ordinov.ersp.user.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import base.BaseTester;

import com.ordinov.ersp.user.entity.User;
import com.ordinov.ersp.user.service.IUserMngService;

public class UserMngServiceTest extends BaseTester{
	
	@Autowired
	private IUserMngService userService = null;
	
	@Test
	public void add() {
		User user = new User();
		user.setUname("lilk");
		user.setName("李刘坤");
		user.setUtype("1");
		user.setGender("2");
		user.setEmail("lilk@ordinov.com");
		user.setPassword("123456");
		user.setCompany("北京源硅创新科技有限公司");
		Assert.assertEquals("201", this.userService.addUser(user));
	}

}
