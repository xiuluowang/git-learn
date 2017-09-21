package com.ordinov.ersp.dictionary.service;

import java.util.List;

import com.ordinov.ersp.dictionary.entity.Dictionary;
import com.ordinov.ersp.dictionary.entity.DictionaryType;

/**
 * 字典管理Service接口
 * @author lilk
 *
 */
public interface IDictionaryMngService {

	/**
	 * 通过字典分类获取某分类下的所有字典数据
	 * @param type
	 * @return
	 */
	public List<Dictionary> getDictionnaryListByType(DictionaryType type);
}
