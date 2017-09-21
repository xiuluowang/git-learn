package com.ordinov.ersp.index.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TEACHING_CLASS")
public class TeachingClass {

	@Id
	@Column(name="ID",length=36)
	private String id;
	
	@Column(name="NAME",length=200)
	private String name;

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
}
