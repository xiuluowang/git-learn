package com.ordinov.ersp.dictionary.entity;

/**
 * 字典类型枚举类
 * @author lilk
 *
 */
public enum DictionaryType {

	PRODUCTTYPE(1,"PRODUCTTYPE","产品类型");
	
	/**字典表类中编号*/
	private int id;
	/**字典表类型名称*/
	private String typeName;
	/**字典表类型描述*/
	private String desc;
	
	DictionaryType(int id,String typeName,String desc){
		
		this.id = id;
		this.typeName = typeName;
		this.desc = desc;
	}
	
	public int getId() {
		return id;
	}

	public String getTypeName() {
		return typeName;
	}

	public String getDesc() {
		return desc;
	}
}
