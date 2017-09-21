package com.ordinov.ersp.reqirement.entity;

import java.io.Serializable;

public class ServerAnswer implements Serializable{

	/**
	 * 偶人序列化版本号
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 执行结果
	 */
	private boolean result;
	
	/**
	 * 消息
	 */
	private String msg;
	
	/**
	 * 代号
	 */
	private Integer code;

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
	
	
}
