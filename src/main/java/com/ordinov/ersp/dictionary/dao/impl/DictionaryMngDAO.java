package com.ordinov.ersp.dictionary.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.ordinov.ersp.dictionary.dao.IDictionaryMngDAO;
import com.ordinov.ersp.dictionary.entity.Dictionary;
import com.ordinov.ersp.dictionary.entity.DictionaryType;

@Repository
public class DictionaryMngDAO implements IDictionaryMngDAO {

	private static final Logger LOGGER = Logger.getLogger(DictionaryMngDAO.class);
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Dictionary> getDictionnaryListByType(DictionaryType type) {
		
		List<Dictionary> list = null;
		if(type != null && type.getTypeName() != null && !"".equals(type.getTypeName())){
			
			String hql="from Dictionary where type=?";
			try{
				
				list = (List<Dictionary>)this.hibernateTemplate.find(hql, type.getTypeName());
			}catch(Exception ex){
				
				ex.printStackTrace();
				LOGGER.error("获取字典类型数据失败："+ex.getMessage(),ex);
			}
		}else{
			
			LOGGER.error("字典类型参数丢失,查询字典数据失败!");
		}
		return list;
	}

}
