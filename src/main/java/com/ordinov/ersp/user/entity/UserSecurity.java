package com.ordinov.ersp.user.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USER_SECURITY")
public class UserSecurity implements Serializable{
	
	private static final long serialVersionUID = -3186975964010671623L;
	
	/** 用户编号*/
	@Id
	@Column(name="USER_ID",length=36)
	private String userId;
	
	/** 密码*/
	@Column(name="PASSWORD",length=200)
	private String password;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
