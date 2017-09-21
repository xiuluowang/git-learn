package com.ordinov.ersp.reqirement.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ordinov.ersp.user.entity.User;

@Entity
@Table(name = "T_REQUIREMENT")
public class Requirement implements Serializable {

	/**
	 * 默认序列化版本号
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 需求编号
	 */
	@Id
	@Column(name = "REQ_ID", length = 36)
	private String reqId;

	/**
	 * 需求简述
	 */
	@Column(name = "REQ_NAME", length = 200)
	private String reqName;

	/**
	 * 需求详述
	 */
	@Column(name = "REQ_DESC", length = 2000)
	private String reqDesc;

	/**
	 * 发布时间
	 */
	@Column(name = "PUBLISH_DATE")
	private Date publishDate;

	/**
	 * 操作用户编号
	 */
	@ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "OPERATOR_ID")
	private User operator;

	/**
	 * 企业用户
	 */
	@ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "ENTERPRISE_ID")
	private User enterprise;

	public String getReqId() {
		return reqId;
	}

	public void setReqId(String reqId) {
		this.reqId = reqId;
	}

	public String getReqName() {
		return reqName;
	}

	public void setReqName(String reqName) {
		this.reqName = reqName;
	}

	public String getReqDesc() {
		return reqDesc;
	}

	public void setReqDesc(String reqDesc) {
		this.reqDesc = reqDesc;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public User getOperator() {
		return operator;
	}

	public void setOperator(User operator) {
		this.operator = operator;
	}

	public User getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(User enterprise) {
		this.enterprise = enterprise;
	}
}
