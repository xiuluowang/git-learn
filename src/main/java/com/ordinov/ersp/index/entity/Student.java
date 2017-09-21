package com.ordinov.ersp.index.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="STUDENT")
public class Student {

	@Id
	@Column(name="ID",length=36)
	private String id;
	
	@Column(name="NAME",length=200)
	private String name;
	
	@ManyToOne(fetch=FetchType.EAGER,targetEntity=TeachingClass.class)
	@JoinColumn(name="CLASS_ID")
	private TeachingClass tClass = null;

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

	public void settClass(TeachingClass tClass) {
		this.tClass = tClass;
	}

	public TeachingClass gettClass() {
		return tClass;
	}
}
