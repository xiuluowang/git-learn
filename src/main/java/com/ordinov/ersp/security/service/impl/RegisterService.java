package com.ordinov.ersp.security.service.impl;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ordinov.ersp.security.service.IRegisterService;
import com.ordinov.ersp.user.dao.IUserMngDAO;
import com.ordinov.ersp.user.dao.IUserSecurityMngDAO;
import com.ordinov.ersp.user.entity.User;
import com.ordinov.ersp.user.entity.UserSecurity;
import com.ordinov.ersp.user.util.MD5DataUtils;

/**
 * 用户注册Service
 * @author zhaowy
 *
 */
@Service
public class RegisterService implements IRegisterService {
	
	@Autowired
	private IUserMngDAO userDao = null;
	@Autowired
	private IUserSecurityMngDAO uSecurityDao = null;
	@Autowired  
	private HttpSession session;  

	@Override
	public boolean valUserByParam(User user) {
		return this.userDao.valUserByParam(user);
	}
	
	@Transactional(rollbackOn={Exception.class})
	@Override
	public String register(User user) {
		String userId = null;
		if (user != null) {
			userId = this.addUser(user);
			if (userId != null && !"".equals(userId)) {
				this.session.setAttribute("user",user);
				this.session.setMaxInactiveInterval(30*60);
			}
		} else {
			throw new IllegalArgumentException("注册的用户实体对象为空");
		}
		return userId;
	}
	
	private String addUser(User user) {
		
		String userId = this.userDao.addUser(user);
		if (userId != null && !"".equals(userId)) {
			UserSecurity us = new UserSecurity();
			us.setUserId(userId);
			String password = user.getPassword();
			String newpw;
			try {
				newpw = MD5DataUtils.encoderByMd5(password);
				if (newpw != null && !"".equals(newpw)) {
					us.setPassword(newpw);
				}
				
				this.uSecurityDao.addUserSecurity(us);
				
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return userId;
	}

}
