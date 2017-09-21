package com.ordinov.ersp.user.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.ordinov.ersp.reqirement.dao.tools.PaginationQueryHibernateCallback;
import com.ordinov.ersp.user.dao.IUserMngDAO;
import com.ordinov.ersp.user.entity.User;

/**
 * 用户管理DAO实现类
 * @author zhaowy
 *
 */
@Repository
public class UserMngDAO implements IUserMngDAO {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public String addUser(User user) {
		
		String info = null;
		if (user == null) {
			throw new IllegalArgumentException("添加的用户对象为空");
		}
		try {
			user.setId(UUID.randomUUID().toString());
			this.hibernateTemplate.save(user);
			info = user.getId();
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return info;
	}

	@Override
	public boolean modifyUser(User user) {
		
		boolean info = false;
		if (user == null) {
			throw new IllegalArgumentException("修改的用户对象为空");
		}
		try {
			this.hibernateTemplate.update(user);
			info = true;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return info;
	}

	@Override
	public User findUserByID(String usrid) {
		
		User user = null;
		try {
			user = this.hibernateTemplate.get(User.class, usrid);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findUsers() {
		
		List<User> dataList = null;
		Session session = null;
		try {
			session = this.hibernateTemplate.getSessionFactory().openSession();
			String hql = "from User  where 1=1";
			dataList = session.createQuery(hql).list(); 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
			   session.close();
			}
		}
		return dataList;
	}

	@Override
	public boolean deleteUser(User user) {
		
		boolean info = false;
		if (user == null) {
			throw new IllegalArgumentException("删除的用户对象为空");
		}
		try {
			this.hibernateTemplate.delete(user);
			info = true;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return info;
	}

	@Override
	public boolean deleteUsers(List<User> userList) {
		
		boolean info = false;
		if (userList == null || userList.size() <=0) {
			throw new IllegalArgumentException("批量删除的用户对象为空");
		}
		try {
			this.hibernateTemplate.deleteAll(userList);
			info = true;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return info;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findUsers(User user) {
		
		List<User> dataList = null;
		Session session = null;
		try {
			session = this.hibernateTemplate.getSessionFactory().openSession();
			StringBuffer hql = new StringBuffer("from User  where 1=1");
			StringBuilder condition = this.handleCondition(user);
			hql.append(condition);
			dataList = session.createQuery(hql.toString()).list(); 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
			   session.close();
			}
		}
		return dataList;
	}
	
	/*
	 * 处理查询条件
	 */
	private StringBuilder handleCondition(User user) {
		
		StringBuilder condition = new StringBuilder();
		String uname = user.getUname();
		if (uname != null && !"".equals(uname)) {
			condition.append(" and uname = '");
			condition.append(uname).append("'");
		}
		String email = user.getEmail();
		if (email != null && !"".equals(email)) {
			condition.append(" and email = '");
			condition.append(email).append("'");
		}
		String telephone = user.getTelephone();
		if (telephone != null && !"".equals(telephone)) {
			condition.append(" and telephone = '");
			condition.append(telephone).append("'");
		}
		return condition;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> queryUsers(String searchText, int page, int rows,
			String sidx, String sord) {
		
		List<User> userList = new ArrayList<User>();
		StringBuilder builder = sqlBuilder(searchText);
		if (sidx != null && !"".equals(sidx)) {
			builder.append(" order by " + sidx);
		} else {
			builder.append(" order by uname");
		}
		if (sord != null && !"".equals(sord)) {
			builder.append(" " + sord);
		} else {
			builder.append(" desc");
		}
		try {
			PaginationQueryHibernateCallback
			callBack = new PaginationQueryHibernateCallback(builder.toString(),page,rows);
			userList = (List<User> )this.hibernateTemplate.execute(callBack);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userList;
	}

	@Override
	public long queryTotal(String searchText) {
		
		StringBuilder builder = new StringBuilder("select count(*) ");
		builder.append(sqlBuilder(searchText));
		Session session = this.hibernateTemplate.getSessionFactory().openSession();
		long count = 0l;
		try {
			count = (Long) session.createQuery(builder.toString()).uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return count;
	}
	
	public StringBuilder sqlBuilder(final String searchText){
		StringBuilder builder = new StringBuilder();
		builder.append("from User u where 1=1 ");
		
		String fSearchText;
		if (searchText != null && !"".equals(searchText)) {
			fSearchText = searchText.trim().toLowerCase();
		} else {
			fSearchText = null;
		}
		
		if (fSearchText != null && fSearchText != "") {
			builder.append("and (lower(u.uname) like '%" + searchText + "%' or ");
			builder.append("lower(u.email) like '%" + searchText+ "%' or ");
			builder.append("lower(u.telephone) like '%" + searchText+ "%' or ");
			builder.append("lower(u.name) like '%" + searchText+ "%')");
		}
		return builder;
	}

	@Override
	public boolean valUserByParam(User user) {
		
		boolean result = true;
		if(user != null){
			StringBuffer hql = new StringBuffer("select count(*) from User where 1=1");
			String uname = user.getUname();
			String phone = user.getTelephone();
			String email = user.getEmail();
			String id = user.getId();
			if(uname != null && !uname.trim().isEmpty()){
				hql.append(" and uname ='").append(uname).append("'");
			}
			if(phone != null && !phone.isEmpty()){
				hql.append(" and telephone ='").append(phone).append("'");
			}
			if(email != null && !email.isEmpty()){
				hql.append(" and email ='").append(email).append("'");
			}
			if(id != null && !id.isEmpty()){
				hql.append(" and id !='").append(id).append("'");
			}
			Session session = null;
			try {
				session = this.hibernateTemplate.getSessionFactory().openSession();
				@SuppressWarnings("unchecked")
				List<Long> count = session.createQuery(hql.toString()).list();
				if (count != null && !count.isEmpty()) {
					int data = count.get(0).intValue();
					if(data > 0){
						result = false;
					}else{
						result = true;
					}
				}
			} catch (Exception ex) {
				result = false;
				ex.printStackTrace();
			} finally {
				if (session != null) {
					session.close();
				}
			}
		}
		return result;
	}

}
