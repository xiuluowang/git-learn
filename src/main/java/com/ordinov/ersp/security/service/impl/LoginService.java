package com.ordinov.ersp.security.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ordinov.ersp.security.service.ILoginService;
import com.ordinov.ersp.user.dao.IUserMngDAO;
import com.ordinov.ersp.user.dao.IUserSecurityMngDAO;
import com.ordinov.ersp.user.entity.User;
import com.ordinov.ersp.user.entity.UserSecurity;
import com.ordinov.ersp.user.util.MD5DataUtils;

/**
 * 登录service
 * @author zhaowy
 *
 */
@Service
public class LoginService implements ILoginService {
	
	@Autowired
	private IUserMngDAO userDao = null;
	@Autowired
	private IUserSecurityMngDAO uSecurityDao = null;
	@Autowired  
	private HttpSession session;  

	@Override
	public User login(String data, String password) {
		User loginUsr = null;
		String em = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
		String ph = "^[1][3578]\\d{9}$";
		User user = new User();
		if (data.matches(em)) {
			user.setEmail(data);
		} else if (data.matches(ph)) {
			user.setTelephone(data);
		} else {
			user.setUname(data);
		}
		List<User> dataList = this.userDao.findUsers(user);
		if (dataList != null && dataList.size() > 0) {
			String pmd5;
			try {
				pmd5 = MD5DataUtils.encoderByMd5(password);
				
				user = dataList.get(0);
				UserSecurity security = this.uSecurityDao.findUserSecurityByID(user.getId());
				String pw = security.getPassword();
				if (pmd5.equals(pw)) {
					loginUsr = user;
					this.session.setAttribute("user",user);
					this.session.setMaxInactiveInterval(30*60);
				} 
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		} 
		return loginUsr;
	}

	@Override
	public boolean logout() {
		
		this.session.invalidate();
		return true;
	}

	@Override
	public String queryLoginInfo() {
		String nameinfo = "";
		User user = (User)this.session.getAttribute("user");
		if (user != null) {
			nameinfo = user.getName();
		}
		return nameinfo;
	}
	
}