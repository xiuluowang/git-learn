package com.ordinov.ersp.index.dao.impl;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import com.ordinov.ersp.index.dao.ITeachingClassMngDAO;
import com.ordinov.ersp.index.entity.TeachingClass;

public class TeachingClassMngDAOTest{

	@Autowired
	ITeachingClassMngDAO dao = null;
	
	//@Test
	public void testAddSpringTester() {
		TeachingClass tester = new TeachingClass();
		tester.setName("北京欢迎您-" + String.format("%08d", (int)(Math.random()*1000d)));
		
		Assert.assertEquals(36, this.dao.addTeachingClass(tester).length());
	}

}
