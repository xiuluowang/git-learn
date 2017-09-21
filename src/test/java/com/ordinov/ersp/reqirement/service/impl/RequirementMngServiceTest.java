package com.ordinov.ersp.reqirement.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ordinov.ersp.reqirement.entity.Requirement;
import com.ordinov.ersp.reqirement.service.IRequirementMngService;

import base.BaseTester;

public class RequirementMngServiceTest extends BaseTester{

	@Autowired
	private IRequirementMngService service = null;
		
	@Test
	public void testPublish() {
		Requirement requirement = new Requirement();
		requirement.setReqName("需求概述");
		requirement.setReqDesc("描述信息");
		
		Assert.assertEquals(36, this.service.publish(requirement).length());;
	}

}
