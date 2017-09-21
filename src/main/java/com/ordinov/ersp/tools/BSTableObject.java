package com.ordinov.ersp.tools;

import java.io.Serializable;

public class BSTableObject<T> implements Serializable {

	/**
	 * 序列化版本号
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 总记录数
	 */
	private long total;
	
	/**
	 * 数据
	 */
	private Object rows;

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public Object getRows() {
		return rows;
	}

	public void setRows(Object rows2) {
		this.rows = rows2;
	} 
	
}