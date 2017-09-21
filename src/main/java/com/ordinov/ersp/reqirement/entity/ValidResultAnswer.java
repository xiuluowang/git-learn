package com.ordinov.ersp.reqirement.entity;

import java.io.Serializable;

/**
 * 验证结果应答实体
 * @author dengfx
 *
 */
public class ValidResultAnswer implements Serializable{

	/**
	 * 默认序列化版本
	 */
	private static final long serialVersionUID = 1L;

	private boolean valid = false;
	
	public ValidResultAnswer() {
	}
	
	public ValidResultAnswer(boolean valid) {
		super();
		this.valid = valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public boolean isValid() {
		return valid;
	}
}
