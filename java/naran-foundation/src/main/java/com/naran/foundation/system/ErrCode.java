package com.naran.foundation.system;

/**
 * 错误代码
 * @author QuCeng
 */
public class ErrCode {

	private String code;
	
	private String msg;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public ErrCode() {}
	
	public ErrCode(String code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
	
}
