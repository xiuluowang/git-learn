package com.ordinov.ersp.dictionary.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ordinov.ersp.dictionary.dao.IDictionaryMngDAO;
import com.ordinov.ersp.dictionary.entity.Dictionary;
import com.ordinov.ersp.dictionary.entity.DictionaryType;
import com.ordinov.ersp.dictionary.service.IDictionaryMngService;

@Service
public class DictionaryMngService implements IDictionaryMngService {

	@Autowired
	private IDictionaryMngDAO dictionaryMngDAO;
	
	@Override
	public List<Dictionary> getDictionnaryListByType(DictionaryType type) {

		return dictionaryMngDAO.getDictionnaryListByType(type);
	}

}
