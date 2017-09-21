package com.ordinov.ersp.dictionary.service.impl;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ordinov.ersp.dictionary.entity.Dictionary;
import com.ordinov.ersp.dictionary.entity.DictionaryType;
import com.ordinov.ersp.dictionary.service.IDictionaryMngService;

import base.BaseTester;

public class DictionaryMngServiceTest extends BaseTester {


	@Autowired
	private IDictionaryMngService dictionaryMngService;
	
	@Test
	public void getDictionaryList() {
		
		List<Dictionary> list = dictionaryMngService.getDictionnaryListByType(DictionaryType.PRODUCTTYPE);
		Assert.assertEquals(1, list.size());
	}
}
