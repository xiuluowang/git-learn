package com.ordinov.ersp.reqirement.dao.tools;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;

/**
 * HibernateTemplate分页查询回调实现类 
 * @author dengfx
 *
 */
@SuppressWarnings("rawtypes")
public class PaginationQueryHibernateCallback implements HibernateCallback{
		
	private String queryHqlStr = null;
	
	private int page = 0;
	
	private int rows = 0;
	
	/**
	 * <p>构造器</P>
	 * @param queryHqlStr 完整的HQL查询语句
	 * @param page 请求查询的页码.无验证逻辑
	 * @param rows 请求查询的每页记录数
	 * */
	public PaginationQueryHibernateCallback(String queryHqlStr,int page,int rows){
		
		this.queryHqlStr = queryHqlStr;
		this.page = page;
		this.rows = rows;
	}
	/**
	 * 私有化无参构造函数
	 */
	@SuppressWarnings("unused")
	private PaginationQueryHibernateCallback(){
	}
	
	@Override
	public Object doInHibernate(Session session) throws HibernateException {
		return session.createQuery(queryHqlStr).setFirstResult((page-1)*rows)
		.setMaxResults(rows).list();
	}

}
