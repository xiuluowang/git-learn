package com.ordinov.ersp.index.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ordinov.ersp.index.dao.ITeachingClassMngDAO;
import com.ordinov.ersp.index.entity.TeachingClass;
import com.ordinov.ersp.index.service.ITeachingClassMngService;

@Service
public class TeachingClassMngService implements ITeachingClassMngService {
	
	@Autowired
	private ITeachingClassMngDAO springTeserMngDAO = null;

	
	@Transactional(rollbackOn={Exception.class})
	@Override
	public String addTeachingClass(TeachingClass tester) {
		return this.springTeserMngDAO.addTeachingClass(tester);
	}
	
	
}
