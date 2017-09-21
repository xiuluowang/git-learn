package com.ordinov.ersp.index.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import base.BaseTester;

import com.ordinov.ersp.index.entity.TeachingClass;
import com.ordinov.ersp.index.service.ITeachingClassMngService;

public class TeachingClassMngServiceTest extends BaseTester{

	@Autowired
	private ITeachingClassMngService service = null;
	
	@Test
	public void testAddSpringTester() {
		TeachingClass tester = new TeachingClass();
		tester.setName("荷塘月色-" + String.format("%08d", (int)(Math.random()*1000d)));
		Assert.assertEquals(36, this.service.addTeachingClass(tester).length());
	}
	
	@Test
	public void testAddSpringTesterNull() {
		try{
			this.service.addTeachingClass(null);
		}catch(Exception e){
			Assert.assertEquals(NullPointerException.class.getName(), e.getClass().getName());
		}
	}

}
