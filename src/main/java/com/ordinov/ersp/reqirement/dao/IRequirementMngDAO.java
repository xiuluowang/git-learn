package com.ordinov.ersp.reqirement.dao;

import java.util.List;

import com.ordinov.ersp.reqirement.entity.Requirement;

public interface IRequirementMngDAO {

	/**
	 * 发布需求
	 * @param requirement
	 * @return 成功则返回ID
	 */
	public String publish(Requirement requirement);
	
	/**
	 * 根据请求编号获取需求对象
	 * @param reqId
	 * @return
	 */
	public Requirement getRequirementById(String reqId);
	
	public List<Requirement> list(Requirement req,String searchText,int page,int rows,String sidx,String sord);
	public long total(Requirement req,String searchText);
	
	/**
	 * 测试需求名称是否已经存在(只是用于验证，业务系统不应该具备此验证).
	 * @param reqId
	 * @param reqName
	 * @return
	 */
	public boolean isExist(String reqId,String reqName);
	
	/**
	 * 通过需求编号删除需求
	 * @param requirementId
	 * @return
	 */
	public boolean delete(String requirementId);
	
	/**
	 * 修改需求
	 * @param req
	 * @return
	 */
	public boolean modify(Requirement req);
}
