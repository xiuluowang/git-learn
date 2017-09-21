package com.ordinov.ersp.user.dao.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.ordinov.ersp.user.dao.IUserSecurityMngDAO;
import com.ordinov.ersp.user.entity.UserSecurity;

@Repository
public class UserSecurityMngDAO implements IUserSecurityMngDAO {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public String addUserSecurity(UserSecurity us) {
		
		String info = null;
		try {
			this.hibernateTemplate.save(us);
			info = us.getUserId();
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return info;
	}

	@Override
	public UserSecurity findUserSecurityByID(String userId) {
		
		UserSecurity us = null;
		try {
			us = this.hibernateTemplate.get(UserSecurity.class, userId);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return us;
	}

	@Override
	public boolean deleteUserSecurity(UserSecurity us) {
	
		boolean info = false;
		try {
			this.hibernateTemplate.delete(us);
			info = true;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return info;
	}

	@Override
	public boolean deleteUserSecuritys(List<UserSecurity> usList) {
		
		boolean info = false;
		try {
			this.hibernateTemplate.deleteAll(usList);
			info = true;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return info;
	}

}
