package com.ordinov.ersp.dictionary.service.impl;

import org.apache.log4j.Logger;

import com.ordinov.ersp.dictionary.entity.DictionaryType;

/**
 * 字典类型逻辑处理Service
 * @author lilk
 *
 */
public class DictionaryTypeService {

	private static final Logger LOGGER = Logger.getLogger(DictionaryTypeService.class);
	/**
	 * 获取所有字典分类数组
	 * @return
	 */
	public static DictionaryType[] getDictionaryTypeAllList(){
		
		LOGGER.debug("获取所有字典分类信息............");
		return DictionaryType.values();
	}
	
	public static DictionaryType getDictionaryTypeById(int id){
		
		DictionaryType[] dicTypeArray = DictionaryType.values();
		if(dicTypeArray != null && dicTypeArray.length >0){
			
			for (DictionaryType type : dicTypeArray) {

				if(type.getId() == id){
					
					LOGGER.debug("获取类型编号为："+id+"的字典类型为："
					+type.getTypeName()+"["+type.getDesc()+"]");
					return type;
				}
			}
		}
		LOGGER.debug("获取类型编号为："+id+"的字典类型为：NULL");
		return null;
	}
}
