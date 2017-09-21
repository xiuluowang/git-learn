package com.ordinov.ersp.dictionary.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="DICTIONARY")
public class Dictionary {
	
	/**
	 * 字典数据编号
	 */
	@Id
	@Column(name="ID",nullable=false,length=36)
	private String id;
	
	/**
	 * 字典数据名称(系统中用到的值)
	 */
	@Column(name="NAME",nullable=false,length=200)
	private String name;
	
	/**
	 * 字典数据类型
	 */
	@Column(name="TYPE",nullable=false)
	private String type;
	
	/**
	 * 字典数据描述
	 */
	@Column(name="DESCRIPTION",length=1500)
	private String description;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
