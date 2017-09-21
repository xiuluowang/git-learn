package com.ordinov.ersp.dictionary.dao;

import java.util.List;

import com.ordinov.ersp.dictionary.entity.Dictionary;
import com.ordinov.ersp.dictionary.entity.DictionaryType;

public interface IDictionaryMngDAO {

	/**
	 * 通过字典分类获取某分类下的所有字典数据
	 * @param type
	 * @return
	 */
	public List<Dictionary> getDictionnaryListByType(DictionaryType type);
}
