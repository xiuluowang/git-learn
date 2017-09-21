package com.ordinov.ersp.product.util;

/**
 * 服务器返回信息
 * @author lilk
 *
 */
public class ServerAnswer {

	/**
	 * 操作结果
	 */
	private boolean result;
	
	/**
	 * 返回代码
	 */
	private String code;
	
	/**
	 * 描述信息
	 */
	private String desc;
	
	/**
	 * 返回的提示信息
	 */
	private Object responseInfo;

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
	
	public Object getResponseInfo() {
		return responseInfo;
	}

	public void setResponseInfo(Object responseInfo) {
		this.responseInfo = responseInfo;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public ServerAnswer(boolean result,Responsecode response, Object responseInfo) {
		
		this.result = result;
		this.code = response.getCode();
		this.desc = response.getDesc();
		this.responseInfo = responseInfo;
	}

	public ServerAnswer() {

	}

	
}
