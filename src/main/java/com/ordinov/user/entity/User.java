package com.ordinov.user.entity;

import java.io.Serializable;

public class User implements Serializable {

	/**
	 * 序列化版本号
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 用户编号,采用UUID
	 */
	private String userid;
	
	/**
	 * 登录名称
	 */
	private String loginName;
	
	/**
	 * 姓名
	 */
	private String userName;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "User [userid=" + userid + ", loginName=" + loginName
				+ ", userName=" + userName + "]";
	}
}
