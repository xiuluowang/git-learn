package com.ordinov.ersp.product.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.ordinov.ersp.dictionary.entity.Dictionary;

@Entity
@Table(name="PRODUCT")
public class Product {

	/**
	 * 产品编号
	 */
	@Id
	@Column(name="ID",nullable=false,length=36)
	private String id;
	
	/**
	 * 产品名称
	 */
	@Column(name="NAME",nullable=false,length=200)
	private String name;
	
	/**
	 * 产品型号
	 */
	@Column(name="MODEL",length=200)
	private String model;
	
	/**
	 * 产品类型
	 */
	@ManyToOne(fetch=FetchType.EAGER,targetEntity=Dictionary.class)
	@JoinColumn(name="TYPE")
	private Dictionary type;
	
	/**
	 * 产品参考价格
	 */
	@Column(name="REFERENCEPRICE",columnDefinition="double")
	private double referenceprice;
	
	/**
	 * 产品描述
	 */
	@Column(name="DESCRIPTION",length=1500)
	private String description;
	
	/**
	 * 发布时间
	 */
	@Column(name="PUBLISHDATE",columnDefinition="DATE")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date publishDate;

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

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Dictionary getType() {
		return type;
	}

	public void setType(Dictionary type) {
		this.type = type;
	}

	public double getReferenceprice() {
		return referenceprice;
	}

	public void setReferenceprice(double referenceprice) {
		this.referenceprice = referenceprice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
}
