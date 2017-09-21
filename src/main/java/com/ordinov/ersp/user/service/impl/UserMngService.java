package com.ordinov.ersp.user.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ordinov.ersp.user.dao.IUserMngDAO;
import com.ordinov.ersp.user.dao.IUserSecurityMngDAO;
import com.ordinov.ersp.user.entity.User;
import com.ordinov.ersp.user.entity.UserSecurity;
import com.ordinov.ersp.user.service.IUserMngService;
import com.ordinov.ersp.user.util.MD5DataUtils;

/**
 * 用户管理Service处理类
 * @author zhaowy
 *
 */
@Service
public class UserMngService implements IUserMngService {
	
	@Autowired
	private IUserMngDAO userDao = null;
	@Autowired
	private IUserSecurityMngDAO uSecurityDao = null;
	
	@Transactional(rollbackOn={Exception.class})
	@Override
	public String addUser(User user) {
		
		String userId = null;
		if (user != null) {
			userId = this.userDao.addUser(user);
			if (userId != null && !"".equals(userId)) {
				try {
					UserSecurity us = new UserSecurity();
					us.setUserId(userId);
					String password = user.getPassword();
					String newpw;
					newpw = MD5DataUtils.encoderByMd5(password);
					
					if (newpw != null && !"".equals(newpw)) {
						us.setPassword(newpw);
					}
					this.uSecurityDao.addUserSecurity(us);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		} else {
			throw new IllegalArgumentException("添加的用户实体对象为空");
		}
		return userId;
	}
	
	@Transactional(rollbackOn={Exception.class})
	@Override
	public boolean modifyUser(User user) {
		
		boolean result = false;
		if (user == null) {
			throw new IllegalArgumentException("修改的用户实体对象为空");
		} else {
			result = this.userDao.modifyUser(user);
		}
		return result;
	}

	@Override
	public User findUserByID(String usrid) {
		User user = null;
		if (usrid != null && !"".equals(usrid) && usrid.length() == 36) {
			user = this.userDao.findUserByID(usrid);
		}
		return user;
	}

	@Override
	public List<User> findUsers() {
		
		return this.userDao.findUsers();
	}
	
	@Transactional(rollbackOn={Exception.class})
	@Override
	public boolean deleteUser(User user) {
		
		boolean result = false;
		if (user != null) {
			result = this.userDao.deleteUser(user);
			if (result) {
				UserSecurity us = new UserSecurity();
				us.setUserId(user.getId());
				result = this.uSecurityDao.deleteUserSecurity(us);
			}
		} else {
			throw new IllegalArgumentException("删除的用户实体对象为空");
		}
		return result;
	}
	
	@Transactional(rollbackOn={Exception.class})
	@Override
	public boolean deleteUsers(String userIds) {
		
		boolean result = false;
		if (userIds != null && !"".equals(userIds)) {
			List<User> dataList = new ArrayList<User>();
			List<UserSecurity> usdataList = new ArrayList<UserSecurity>();
			if (userIds != null && !"".equals(userIds)) {
				User usr = null;
				UserSecurity us = null;
				String[] ids = userIds.split(",");
				if (ids != null) {
					int length = ids.length;
					for (int i=0; i<length; i++) {
						usr = new User();
						usr.setId(ids[i]);
						us = new UserSecurity();
						us.setUserId(ids[i]);
						dataList.add(usr);
						usdataList.add(us);
					}
				}
			}
			result = this.userDao.deleteUsers(dataList);
			if (result) {
				result = this.uSecurityDao.deleteUserSecuritys(usdataList);
			}
		} else {
			throw new IllegalArgumentException("批量删除的用户编号数据为空");
		}
		return result;
	}

	@Override
	public List<User> findUsers(User user) {
		
		return this.userDao.findUsers(user);
	}

	@Override
	public UserSecurity findUserSecurityById(String userId) {
		
		UserSecurity us = null;
		if (userId != null && !"".equals(userId) && userId.length() == 36) {
			us = this.uSecurityDao.findUserSecurityByID(userId);
		}
		return us;
	}

	@Override
	public List<User> queryUsers(String searchText, int page, int rows,
			String sidx, String sord) {
		
		return this.userDao.queryUsers(searchText, page, rows, sidx, sord);
	}

	@Override
	public long queryTotal(String searchText) {
	
		return this.userDao.queryTotal(searchText);
	}

	@Override
	public boolean valUserByParam(User user) {
		
		return this.userDao.valUserByParam(user);
	}

}
