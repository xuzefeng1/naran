package com.naran.foundation.entity;

import java.io.Serializable;

/**
 * 
 * @author QuCeng
 * 上传图片返回值
 */
public class ImageReturnValue implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public boolean code; // 判断是否上传成功

	public String result; // 返回的结果 如果code 为true 就返回图片名字 否则返回图片上传不成功原因

	public boolean isCode() {
		return code;
	}

	public void setCode(boolean code) {
		this.code = code;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
}
