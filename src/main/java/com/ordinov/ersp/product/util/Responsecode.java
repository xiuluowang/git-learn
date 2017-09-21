package com.ordinov.ersp.product.util;

/**
 * 返回错误的代码
 * @author lilk
 *
 */
public enum Responsecode {

	OK("2000","操作成功"),
	FAIL("5000","操作失败或未知错误导致"),
	PARAM_EXCETION("5001","参数异常"),
	DB_DATA_EXCETION("5002","数据库数据异常"),
	NULL_PARAM("5003","参数为空");
	
	/**
	 * 响应码
	 */
	private String code;
	/**
	 * 描述
	 */
	private String desc;
	Responsecode(String code,String desc){
		
		this.code = code;
		this.desc = desc;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
}
