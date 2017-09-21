package com.ordinov.ersp.reqirement.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.ordinov.ersp.reqirement.dao.IRequirementMngDAO;
import com.ordinov.ersp.reqirement.dao.tools.PaginationQueryHibernateCallback;
import com.ordinov.ersp.reqirement.entity.Requirement;

@Repository
public class RequirementMngDAO implements IRequirementMngDAO {
	/**
	 * 日志记录器
	 */
	private static final Logger LOGGER = Logger.getLogger(RequirementMngDAO.class);
	
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public String publish(Requirement requirement) {
		LOGGER.debug("dao_发布需求_start");
		
		try {
			requirement.setReqId(UUID.randomUUID().toString());
			requirement.setPublishDate(new Date(System.currentTimeMillis()));
			this.hibernateTemplate.save(requirement);
		} catch (DataAccessException e) {
			LOGGER.error("发布需求失败", e);
			e.printStackTrace();
		}
		return requirement.getReqId();
	}

	@Override
	public Requirement getRequirementById(String reqId) {
		LOGGER.debug("dao_根据请求编号获取需求对象_start");
		return this.hibernateTemplate.get(Requirement.class, reqId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Requirement> list(Requirement req,String searchText, int page, int rows,
			String sidx, String sord) {
		List<Requirement> result = null;
		StringBuilder builder = sqlBuilder(req,searchText);
		
		/*添加排序HQL部分*/
		if(sidx != null && !"".equals(sidx)){
			builder.append(" order by " + sidx);
		} else {
			builder.append(" order by prjEstId");
		}
		
		if(sord != null && !"".equals(sord)){
			builder.append(" " + sord);
		} else {
			builder.append(" desc");
		}
		
		/*使用HibernateTemplate进行分页查询*/
		try {
			PaginationQueryHibernateCallback callBack 
				= new PaginationQueryHibernateCallback(builder.toString(),page,rows);
			result = (List<Requirement>)this.hibernateTemplate.execute(callBack);
		} catch (Exception e) {
			LOGGER.error("DAO_带分页的项目估算列表查询失败,失败原因:"+e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	public StringBuilder sqlBuilder(Requirement req,String searchText){
		StringBuilder builder = new StringBuilder();
		builder.append("from Requirement t where 1=1 ");
		
		String fSearchText;
		if(searchText != null){
			fSearchText = searchText.trim().toLowerCase();
		} else {
			fSearchText = null;
		}
		
		if(req != null){
			
		}
		
		if(fSearchText != null){
			builder.append("and (lower(t.reqName) like '%" + fSearchText + "%' or ");
			builder.append("lower(t.reqDesc) like '%" + fSearchText+ "%')");
		}
		return builder;
	}

	@Override
	public long total(Requirement req,String searchText) {
		StringBuilder builder = new StringBuilder("select count(*) ");
		builder.append(sqlBuilder(req,searchText));
		
		Session session = this.hibernateTemplate.getSessionFactory().openSession();
		long count = 0l;
		try {
			count = (Long) session.createQuery(builder.toString()).uniqueResult();
		} catch (Exception e) {
			LOGGER.error("获取数量失败", e);
			e.printStackTrace();
		}finally{
			if(session != null){session.close();}
		}
		return count;
	}

	@Override
	public boolean isExist(String reqId, String reqName) {
		LOGGER.debug("dao_测试需求名称是否已经存在_start");
		StringBuilder sqlBuilder = new StringBuilder("select count(*) from Requirement t where 1=1 and ");
		sqlBuilder.append("lower(t.reqName) like '" + reqName.trim().toLowerCase() +"' ");
		if(reqId == null || "".equals(reqId)){
		}else{
			sqlBuilder.append("and t.reqId != '" + reqId +"' ");
		}
		Session session = this.hibernateTemplate.getSessionFactory().openSession();
		
		boolean result = false;
		try {
			Long count = (Long) session.createQuery(sqlBuilder.toString()).uniqueResult();
			if(count != null){
				result = count.intValue()>0?true:false;
			}else{
				result = false;
			}
		} catch (Exception e) {
			LOGGER.error("检测需求名称是否存在失败", e);
			e.printStackTrace();
		}finally{
			if(session != null){session.close();}
		}
		return result;
	}

	@Override
	public boolean delete(String requirementId) {
		LOGGER.debug("dao_通过需求编号删除需求_start");
		Requirement dbObject = this.getRequirementById(requirementId);
		boolean result = false;
		try {
			if(dbObject != null){
				this.hibernateTemplate.delete(dbObject);
				result = true;
			}
		} catch (DataAccessException e) {
			LOGGER.error("通过需求编号删除需求失败", e);
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean modify(Requirement req) {
		LOGGER.debug("dao_修改需求_start");
		Requirement dbObject = this.getRequirementById(req.getReqId());
		boolean result = false;
		try {
			if(dbObject != null){
				dbObject.setReqName(req.getReqName());
				dbObject.setReqDesc(req.getReqDesc());
				this.hibernateTemplate.update(dbObject);
				result = true;
			}
		} catch (DataAccessException e) {
			LOGGER.error("修改需求失败", e);
			e.printStackTrace();
		}
		return result;
	}
	
	
}
