package com.naran.foundation.entity;

import java.io.Serializable;

/**
 * 用于判断返回正确与否
 * @author QuCeng
 */
public class ReturnMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean checked; // 判断是否成功
	
	private String msg; // 返回结果
	
	private Object param; // 参数

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getParam() {
		return param;
	}

	public void setParam(Object param) {
		this.param = param;
	}
	
}
