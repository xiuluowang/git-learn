package com.ordinov.ersp.dictionary.dao.impl;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ordinov.ersp.dictionary.dao.IDictionaryMngDAO;
import com.ordinov.ersp.dictionary.entity.Dictionary;
import com.ordinov.ersp.dictionary.entity.DictionaryType;

import base.BaseTester;

public class DictionaryMngDAOTest extends BaseTester {

	@Autowired
	private IDictionaryMngDAO dictionaryDAO = null;
	
	@Test
	public void getDictionaryList() {
		
		List<Dictionary> list = dictionaryDAO.getDictionnaryListByType(DictionaryType.PRODUCTTYPE);
		Assert.assertEquals(1, list.size());
	}
}
