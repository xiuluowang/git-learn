package com.ordinov.ersp.reqirement.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ordinov.ersp.reqirement.dao.IRequirementMngDAO;
import com.ordinov.ersp.reqirement.entity.Requirement;
import com.ordinov.ersp.reqirement.service.IRequirementMngService;

@Service
public class RequirementMngService implements IRequirementMngService {

	@Autowired
	private IRequirementMngDAO requirementMngDAO = null;
	
	/**
	 * 日志记录器
	 */
	private static final Logger LOGGER = Logger.getLogger(RequirementMngService.class);

	@Transactional
	@Override
	public String publish(Requirement requirement) {
		LOGGER.debug("service_发布需求_start");
		return this.requirementMngDAO.publish(requirement);
	}

	@Override
	public Requirement getRequirementById(String reqId) {
		return this.requirementMngDAO.getRequirementById(reqId);
	}

	@Override
	public List<Requirement> list(Requirement req,String searchText, int page, int rows,
			String sidx, String sord) {
		return this.requirementMngDAO.list(req,searchText, page, rows, sidx, sord);
	}

	@Override
	public long total(Requirement req,String searchText) {
		return this.requirementMngDAO.total(req,searchText);
	}

	@Override
	public boolean isExist(String reqId, String reqName) {
		return this.requirementMngDAO.isExist(reqId, reqName);
	}

	@Transactional(rollbackOn={Exception.class})
	@Override
	public boolean delete(String requirementId) {
		return this.requirementMngDAO.delete(requirementId);
	}

	@Transactional(rollbackOn={Exception.class})
	@Override
	public boolean modify(Requirement req) {
		return this.requirementMngDAO.modify(req);
	}
	
}
