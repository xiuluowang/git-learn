package com.ordinov.ersp.user.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="USER")
public class User implements Serializable{
	
	
	private static final long serialVersionUID = 3470470025844025089L;

	/** 用户编号(UUID)*/
	@Id
	@Column(name="ID",length=36)
	private String id;
	
	/** 用户名*/
	@Column(name="UNAME",length=20)
	private String uname;
	
	/** 姓名*/
	@Column(name="NAME",length=200)
	private String name;
	
	/** 性别*/
	@Column(name="GENDER",length=2)
	private String gender;
	
	/** 邮箱*/
	@Column(name="EMAIL",length=200)
	private String email;
	
	/** 用户类型*/
	@Column(name="UTYPE",length=2)
	private String utype;
	
	/** 企业名称*/
	@Column(name="COMPANY",length=500)
	private String company;
	
	/** 企业地址*/
	@Column(name="CADDRESS",length=500)
	private String caddress;
	
	/** 联系电话*/
	@Column(name="TELEPHONE",length=20)
	private String telephone;
	
	/** 备注*/
	@Column(name="NOTE",length=1500)
	private String note;
	
	/** 密码*/
	@Transient
	private String password;
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCaddress() {
		return caddress;
	}

	public void setCaddress(String caddress) {
		this.caddress = caddress;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getUtype() {
		return utype;
	}

	public void setUtype(String utype) {
		this.utype = utype;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	
	

}
