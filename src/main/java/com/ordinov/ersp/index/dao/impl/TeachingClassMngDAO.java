package com.ordinov.ersp.index.dao.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.ordinov.ersp.index.dao.ITeachingClassMngDAO;
import com.ordinov.ersp.index.entity.TeachingClass;
@Repository
public class TeachingClassMngDAO implements ITeachingClassMngDAO {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public String addTeachingClass(TeachingClass tester) {
		String id = null;
		try {
			tester.setId(UUID.randomUUID().toString());
			id = (String) this.getHibernateTemplate().save(tester);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return id;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

}
